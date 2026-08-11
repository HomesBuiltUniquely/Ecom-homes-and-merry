package com.hubinterior.Ecom.Homes.merry.Domain.product.Mapper;

import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Req_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.dto.Internal_Res_DTO;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.Internal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternalMapper {

    // ── Root mapping ──────────────────────────────────────────────────────────
    Internal toEntity(Internal_Req_DTO req);

    Internal_Res_DTO toResponseDto(Internal entity);

    // ── Visibility_Status ─────────────────────────────────────────────────────
    Internal.Visibility_Status toEntity(Internal_Req_DTO.Visibility_Status_DTO dto);

    Internal_Res_DTO.Visibility_Status_DTO toDto(Internal.Visibility_Status entity);

    // ── Access_Permissions ────────────────────────────────────────────────────
    Internal.Access_Permissions toEntity(Internal_Req_DTO.Access_Permissions_DTO dto);

    Internal_Res_DTO.Access_Permissions_DTO toDto(Internal.Access_Permissions entity);

    // ── System_Hooks_Integration ──────────────────────────────────────────────
    Internal.System_Hooks_Integration toEntity(Internal_Req_DTO.System_Hooks_Integration_DTO dto);

    Internal_Res_DTO.System_Hooks_Integration_DTO toDto(Internal.System_Hooks_Integration entity);

    // ── Erp_Module_Integration ────────────────────────────────────────────────
    Internal.System_Hooks_Integration.Erp_Module_Integration toEntity(Internal_Req_DTO.Erp_Module_Integration_DTO dto);

    Internal_Res_DTO.Erp_Module_Integration_DTO toDto(Internal.System_Hooks_Integration.Erp_Module_Integration entity);

    // ── Audit_Trail_Notes ─────────────────────────────────────────────────────
    Internal.Audit_Trail_Notes toEntity(Internal_Req_DTO.Audit_Trail_Notes_DTO dto);

    Internal_Res_DTO.Audit_Trail_Notes_DTO toDto(Internal.Audit_Trail_Notes entity);
}
