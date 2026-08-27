package com.hubinterior.Ecom.Homes.merry.Domain.lead.repository;

import com.hubinterior.Ecom.Homes.merry.Domain.lead.model.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    Optional<Lead> findByLeadId(String leadId);

    List<Lead> findByAssignedDesignerEmailAndQueueType(String assignedDesignerEmail, String queueType);

    List<Lead> findByQueueType(String queueType);

    Page<Lead> findByAssignedSalesEmailAndLeadType(String assignedSalesEmail, String leadType, Pageable pageable);

    Page<Lead> findByAssignedSalesEmail(String assignedSalesEmail, Pageable pageable);

    Page<Lead> findByLeadType(String leadType, Pageable pageable);
}
