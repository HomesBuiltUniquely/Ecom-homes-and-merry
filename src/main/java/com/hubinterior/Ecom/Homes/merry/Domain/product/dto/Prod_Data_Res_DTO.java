package com.hubinterior.Ecom.Homes.merry.Domain.product.dto;

import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Category;
import com.hubinterior.Ecom.Homes.merry.Domain.product.enums.GlobalEnums.Offering_Type;

import java.util.List;
import java.util.Map;

public record Prod_Data_Res_DTO(

                Long prod_id,

                String offering_name,

                Offering_Type offering_type,

                String sku_id,

                Offering_Category category,

                String brand,

                List<String> tags,

                String short_desc,

                boolean featured_offer) {

        /**
         * Response for a bulk-update operation.
         * updated – products that were successfully persisted.
         * failedIds – prod_id → error message for each item that failed.
         * Empty map = all succeeded (200 OK).
         * Non-empty = partial failure (207 Multi-Status).
         */
        public record BulkUpdateResult(
                        List<Prod_Data_Res_DTO> updated,
                        Map<Long, String> failedIds) {
        }
}
