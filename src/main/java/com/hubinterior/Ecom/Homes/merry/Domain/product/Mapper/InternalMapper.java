package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Internal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InternalMapper {

    // ── Root mapping ──────────────────────────────────────────────────────────
    @Mapping(source = "visibility_status", target = "visibility_status")
    @Mapping(source = "access_permissions", target = "access_permissions")
    @Mapping(source = "system_hooks_integration", target = "system_hooks_integration")
    @Mapping(source = "audit_trail_notes", target = "audit_trail_notes")
    Internal toEntity(Internal_Req_DTO req);

    @Mapping(source = "visibility_status", target = "visibility_status")
    @Mapping(source = "access_permissions", target = "access_permissions")
    @Mapping(source = "system_hooks_integration", target = "system_hooks_integration")
    @Mapping(source = "audit_trail_notes", target = "audit_trail_notes")
    Internal_Res_DTO toResponseDto(Internal entity);

    // ── Visibility_Status ─────────────────────────────────────────────────────
    @Mapping(source = "publishing_status", target = "publishing_status")
    @Mapping(source = "visibility", target = "visibility")
    @Mapping(source = "schedule_launch", target = "schedule_launch")
    Internal.Visibility_Status toEntity(Internal_Req_DTO.Visibility_Status_DTO dto);

    @Mapping(source = "publishing_status", target = "publishing_status")
    @Mapping(source = "visibility", target = "visibility")
    @Mapping(source = "schedule_launch", target = "schedule_launch")
    Internal_Res_DTO.Visibility_Status_DTO toDto(Internal.Visibility_Status entity);

    // ── Access_Permissions ────────────────────────────────────────────────────
    @Mapping(source = "allowed_users", target = "allowed_users")
    @Mapping(source = "restricted_region", target = "restricted_region")
    Internal.Access_Permissions toEntity(Internal_Req_DTO.Access_Permissions_DTO dto);

    @Mapping(source = "allowed_users", target = "allowed_users")
    @Mapping(source = "restricted_region", target = "restricted_region")
    Internal_Res_DTO.Access_Permissions_DTO toDto(Internal.Access_Permissions entity);

    // ── System_Hooks_Integration ──────────────────────────────────────────────
    @Mapping(source = "erp_module_integration", target = "erp_module_integration")
    Internal.System_Hooks_Integration toEntity(Internal_Req_DTO.System_Hooks_Integration_DTO dto);

    @Mapping(source = "erp_module_integration", target = "erp_module_integration")
    Internal_Res_DTO.System_Hooks_Integration_DTO toDto(Internal.System_Hooks_Integration entity);

    // ── Erp_Module_Integration ────────────────────────────────────────────────
    @Mapping(source = "sales_module", target = "sales_module")
    @Mapping(source = "inventory_sync", target = "inventory_sync")
    @Mapping(source = "procurement_pipeline", target = "procurement_pipeline")
    @Mapping(source = "accounting_code", target = "accounting_code")
    Internal.System_Hooks_Integration.Erp_Module_Integration toEntity(Internal_Req_DTO.Erp_Module_Integration_DTO dto);

    @Mapping(source = "sales_module", target = "sales_module")
    @Mapping(source = "inventory_sync", target = "inventory_sync")
    @Mapping(source = "procurement_pipeline", target = "procurement_pipeline")
    @Mapping(source = "accounting_code", target = "accounting_code")
    Internal_Res_DTO.Erp_Module_Integration_DTO toDto(Internal.System_Hooks_Integration.Erp_Module_Integration entity);

    // ── Audit_Trail_Notes ─────────────────────────────────────────────────────
    @Mapping(source = "desc", target = "desc")
    Internal.Audit_Trail_Notes toEntity(Internal_Req_DTO.Audit_Trail_Notes_DTO dto);

    @Mapping(source = "desc", target = "desc")
    Internal_Res_DTO.Audit_Trail_Notes_DTO toDto(Internal.Audit_Trail_Notes entity);
}
