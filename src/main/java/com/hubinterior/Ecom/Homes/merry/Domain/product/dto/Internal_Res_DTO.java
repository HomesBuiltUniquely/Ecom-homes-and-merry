package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Publishing_Status;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Restricted_Region;

import java.time.LocalDateTime;
import java.util.List;

public record Internal_Res_DTO(

        Visibility_Status_DTO visibility_status,

        Access_Permissions_DTO access_permissions,

        System_Hooks_Integration_DTO system_hooks_integration,

        Audit_Trail_Notes_DTO audit_trail_notes

) {
    public record Visibility_Status_DTO(
            Publishing_Status publishing_status,
            Boolean visibility,
            LocalDateTime schedule_launch
    ) {}

    public record Access_Permissions_DTO(
            List<String> allowed_users,
            Restricted_Region restricted_region
    ) {}

    public record System_Hooks_Integration_DTO(
            Erp_Module_Integration_DTO erp_module_integration
    ) {}

    public record Erp_Module_Integration_DTO(
            Boolean sales_module,
            Boolean inventory_sync,
            Boolean procurement_pipeline,
            String accounting_code
    ) {}

    public record Audit_Trail_Notes_DTO(
            String desc
    ) {}
}
