package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Publishing_Status;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Restricted_Region;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Internal {

    @Embedded
    private Visibility_Status visibility_status;

    @Embedded
    private Access_Permissions access_permissions;

    @Embedded
    private System_Hooks_Integration system_hooks_integration;

    @Embedded
    private Audit_Trail_Notes audit_trail_notes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Visibility_Status {

        @Enumerated(EnumType.STRING)
        @Column(name = "publishing_status")
        private Publishing_Status publishing_status;

        @Column(name = "visibility")
        private Boolean visibility;

        @Column(name = "schedule_launch")
        private LocalDateTime schedule_launch;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Access_Permissions {

        @JdbcTypeCode(SqlTypes.JSON)
        @Column(name = "allowed_users", columnDefinition = "json")
        @Builder.Default
        private List<String> allowed_users = new ArrayList<>();

        @Enumerated(EnumType.STRING)
        @Column(name = "restricted_region")
        private Restricted_Region restricted_region;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class System_Hooks_Integration {

        @Embedded
        private Erp_Module_Integration erp_module_integration;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @Embeddable
        public static class Erp_Module_Integration {

            @Column(name = "sales_module")
            private Boolean sales_module;

            @Column(name = "inventory_sync")
            private Boolean inventory_sync;

            @Column(name = "procurement_pipeline")
            private Boolean procurement_pipeline;

            @Column(name = "accounting_code")
            private String accounting_code;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Audit_Trail_Notes {

        @Column(name = "audit_notes")
        private String desc;
    }
}
