package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Category;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Type;
import com.hubinterior.Ecom.Homes.merry.Domain.product.model.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Prod_Data_Req_DTO(

                @NotBlank(message = "Offering name cannot be empty") String offering_name,

                @NotNull(message = "Offering type is required") Offering_Type offering_type,

                @NotBlank(message = "SKU ID cannot be empty") String sku_id,

                @NotNull(message = "Category is required") Offering_Category category,

                String brand,

                List<String> tags,

                String short_desc,

                String long_desc,

                boolean featured_offer,

                @Valid Pricing pricing,

                @Valid Inventory inventory,

                @Valid SourcingLogistics sourcingLogistics,

                @Valid Media media,

                @Valid Specifications specifications,

                @Valid SEO seo,

                @Valid Internal internal) {

        /**
         * One entry in a bulk-update request.
         * prod_id identifies the row to update; data carries the new values.
         * Reuses Prod_Data_Req_DTO — no duplicate fields needed.
         */
        public record BulkUpdateEntry(
                        @NotNull(message = "prod_id is required for bulk update") Long prod_id,

                        @Valid @NotNull(message = "product data must not be null") Prod_Data_Req_DTO data) {
        }
}
