package com.hubinterior.Ecom.Homes.merry.Domain.lead.controller;

import com.hubinterior.Ecom.Homes.merry.Domain.lead.dto.LeadResponse;
import com.hubinterior.Ecom.Homes.merry.Domain.lead.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/leads", "/api/v1/leads"})
@RequiredArgsConstructor
public class LeadQueueController {

    private final LeadService leadService;

    @GetMapping("/queue")
    public ResponseEntity<List<LeadResponse>> getDesignerQueue(
            @RequestParam(name = "type", required = false, defaultValue = "d1") String queueType) {
        List<LeadResponse> response = leadService.getDesignerQueue(queueType);
        return ResponseEntity.ok(response);
    }
}
