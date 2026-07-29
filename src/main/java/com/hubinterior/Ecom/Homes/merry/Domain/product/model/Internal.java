package com.hubinterior.Ecom.Homes.merry.Domain.product.model;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Publishing_Status;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Restricted_Region;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Internal {

    private Visibility_Status visibility_status;

    private Access_Permissions access_permissions;

    private System_Hooks_Integration system_hooks_integration;

    private Audit_Trail_Notes audit_trail_notes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Visibility_Status {

        private Publishing_Status publishing_status;

        private Boolean visibility;

        private LocalDateTime schedule_launch;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Access_Permissions {

        private List<String> allowed_users;

        private Restricted_Region restricted_region;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class System_Hooks_Integration {

        private Erp_Module_Integration erp_module_integration;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Erp_Module_Integration {

            private Boolean sales_module;

            private Boolean inventory_sync;

            private Boolean procurement_pipeline;

            private String accounting_code;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Audit_Trail_Notes {

        private String desc;
    }
}
