package com.hubinterior.Ecom.Homes.merry.Domain.lead.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record LeadResponse(
        @JsonProperty("id")
        Long id,

        @JsonProperty("lead_id")
        @JsonAlias({"leadId"})
        String leadId,

        @JsonProperty("customer_name")
        @JsonAlias({"customerName"})
        String customerName,

        @JsonProperty("email")
        String email,

        @JsonProperty("phone_number")
        @JsonAlias({"phoneNumber"})
        String phoneNumber,

        @JsonProperty("lead_type")
        @JsonAlias({"leadType"})
        String leadType,

        @JsonProperty("status")
        String status,

        @JsonProperty("assigned_designer_email")
        @JsonAlias({"assignedDesignerEmail"})
        String assignedDesignerEmail,

        @JsonProperty("assigned_sales_email")
        @JsonAlias({"assignedSalesEmail"})
        String assignedSalesEmail,

        @JsonProperty("territory")
        String territory,

        @JsonProperty("queue_type")
        @JsonAlias({"queueType"})
        String queueType,

        @JsonProperty("created_at")
        @JsonAlias({"createdAt"})
        LocalDateTime createdAt
) {}
