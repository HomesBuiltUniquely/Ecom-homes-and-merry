package com.hubinterior.Ecom.Homes.merry.Domain.lead.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.lead.dto.LeadResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.lead.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/v1/leads", "/api/v1/leads"})
@RequiredArgsConstructor
public class CrmLeadController {

    private final LeadService leadService;

    @GetMapping("/filter")
    public ResponseEntity<Page<LeadResponse>> filterCrmLeads(
            @RequestParam(name = "leadType", required = false) String leadType,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LeadResponse> response = leadService.filterCrmLeads(leadType, pageable);
        return ResponseEntity.ok(response);
    }
}
