package com.hubinterior.Ecom.Homes.merry.Domain.lead.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lead_id", unique = true, nullable = false)
    private String leadId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "lead_type")
    private String leadType;

    @Column(name = "status")
    private String status;

    @Column(name = "assigned_designer_email")
    private String assignedDesignerEmail;

    @Column(name = "assigned_sales_email")
    private String assignedSalesEmail;

    @Column(name = "territory")
    private String territory;

    @Column(name = "queue_type")
    private String queueType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
