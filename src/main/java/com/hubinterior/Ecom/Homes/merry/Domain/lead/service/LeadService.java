package com.hubinterior.Ecom.Homes.merry.Domain.lead.service;

import com.hubinterior.Ecom.Homes.merry.Domain.lead.dto.LeadResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.lead.model.Lead;
import com.hubinterior.Ecom.Homes.merry.Domain.lead.repository.LeadRepository;
import com.hubinterior.Ecom.Homes.merry.Domain.user.enums.UserRole;
import com.hubinterior.Ecom.Homes.merry.Exception.ResourceNotFoundException;
import com.hubinterior.Ecom.Homes.merry.Exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadResponse getLeadByLeadId(String leadId) {
        Lead lead = leadRepository.findByLeadId(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with lead ID: " + leadId));
        return toResponse(lead);
    }

    public List<LeadResponse> getDesignerQueue(String queueType) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UnauthorizedException("Authentication token is required to access designer queue.");
        }

        String loggedInUserEmail = auth.getName();
        String effectiveQueueType = (queueType == null || queueType.isBlank()) ? "d1" : queueType.trim();

        boolean isDesigner = hasRole(auth, UserRole.DESIGNERS.name());
        boolean isDesignManager = hasRole(auth, UserRole.DESIGN_MANAGERS.name());
        boolean isTdm = hasRole(auth, UserRole.TDM.name());

        List<Lead> leads;

        if (isDesigner) {
            // Designer gets ONLY leads assigned to their logged-in email
            leads = leadRepository.findByAssignedDesignerEmailAndQueueType(loggedInUserEmail, effectiveQueueType);
        } else if (isDesignManager || isTdm) {
            // Managers / TDMs see all queue leads for their team
            leads = leadRepository.findByQueueType(effectiveQueueType);
        } else {
            // Fallback for Admin
            leads = leadRepository.findByQueueType(effectiveQueueType);
        }

        return leads.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Page<LeadResponse> filterCrmLeads(String leadType, Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UnauthorizedException("Authentication token is required to access CRM leads.");
        }

        String loggedInUserEmail = auth.getName();
        boolean isSalesExec = hasRole(auth, UserRole.SALES_EXECUTIVE.name());

        Page<Lead> leadPage;

        if (isSalesExec) {
            // Sales Executive gets ONLY leads allocated directly to them
            if (leadType != null && !leadType.isBlank()) {
                leadPage = leadRepository.findByAssignedSalesEmailAndLeadType(loggedInUserEmail, leadType.trim(), pageable);
            } else {
                leadPage = leadRepository.findByAssignedSalesEmail(loggedInUserEmail, pageable);
            }
        } else {
            // Sales Managers & Sales Admins see all leads matching leadType
            if (leadType != null && !leadType.isBlank()) {
                leadPage = leadRepository.findByLeadType(leadType.trim(), pageable);
            } else {
                leadPage = leadRepository.findAll(pageable);
            }
        }

        return leadPage.map(this::toResponse);
    }

    private boolean hasRole(Authentication auth, String roleName) {
        if (auth == null || auth.getAuthorities() == null) {
            return false;
        }
        String targetAuthority = "ROLE_" + roleName;
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equalsIgnoreCase(targetAuthority) || a.getAuthority().equalsIgnoreCase(roleName));
    }

    private LeadResponse toResponse(Lead entity) {
        return new LeadResponse(
                entity.getId(),
                entity.getLeadId(),
                entity.getCustomerName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getLeadType(),
                entity.getStatus(),
                entity.getAssignedDesignerEmail(),
                entity.getAssignedSalesEmail(),
                entity.getTerritory(),
                entity.getQueueType(),
                entity.getCreatedAt()
        );
    }
}
