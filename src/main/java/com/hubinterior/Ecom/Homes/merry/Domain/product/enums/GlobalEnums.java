package com.hubinterior.Ecom.Homes.merry.Domain.product.enums;

public class GlobalEnums {

    private GlobalEnums() {}   // utility class — no instantiation

    // ─────────────────────────────────────────────────────────────────────────
    // General
    // ─────────────────────────────────────────────────────────────────────────

    public enum Offering_Type {
        PRODUCT,
        SERVICE,
        BUNDLE
    }

    public enum Offering_Category {
        FURNITURE,
        DECOR,
        LIGHTING,
        BEDDING,
        BATH,
        KITCHEN,
        OUTDOOR,
        STORAGE,
        RUGS,
        WALL_ART,
        OTHER
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Pricing
    // ─────────────────────────────────────────────────────────────────────────

    public enum Gst_Rate {
        GST_0,      // 0%  – exempt
        GST_5,      // 5%
        GST_12,     // 12%
        GST_18,     // 18%
        GST_28      // 28%
    }

    public enum Price_Unit {
        PER_PIECE,
        PER_SET,
        PER_PAIR,
        PER_METER,
        PER_SQ_METER,
        PER_KG,
        PER_LITRE,
        PER_BOX
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Sourcing & Logistics
    // ─────────────────────────────────────────────────────────────────────────

    public enum Preferred_Vendor {
        VENDOR_A,
        VENDOR_B,
        VENDOR_C,
        IN_HOUSE,
        THIRD_PARTY,
        IMPORTED
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Specifications
    // ─────────────────────────────────────────────────────────────────────────

    public enum Primary_Material {
        SOLID_WOOD,
        ENGINEERED_WOOD,
        MDF,
        PLYWOOD,
        METAL,
        STAINLESS_STEEL,
        ALUMINIUM,
        GLASS,
        MARBLE,
        GRANITE,
        FABRIC,
        LEATHER,
        PLASTIC,
        RATTAN,
        BAMBOO,
        OTHER
    }

    public enum Secondary_Material {
        NONE,
        SOLID_WOOD,
        ENGINEERED_WOOD,
        MDF,
        METAL,
        GLASS,
        FABRIC,
        FOAM,
        LEATHER,
        PLASTIC,
        OTHER
    }

    public enum Finish_Type {
        MATTE,
        GLOSS,
        SEMI_GLOSS,
        SATIN,
        LACQUERED,
        POWDER_COATED,
        POLISHED,
        BRUSHED,
        NATURAL,
        DISTRESSED,
        NONE
    }

    public enum Load_Capacity {
        UP_TO_50_KG,
        UP_TO_80_KG,
        UP_TO_100_KG,
        UP_TO_150_KG,
        UP_TO_200_KG,
        ABOVE_200_KG,
        NOT_APPLICABLE
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Internal
    // ─────────────────────────────────────────────────────────────────────────

    public enum Publishing_Status {
        DRAFT,
        IN_REVIEW,
        APPROVED,
        PUBLISHED,
        SCHEDULED,
        ARCHIVED,
        DISCONTINUED
    }

    public enum Restricted_Region {
        NONE,
        NORTH_INDIA,
        SOUTH_INDIA,
        EAST_INDIA,
        WEST_INDIA,
        METRO_ONLY,
        TIER_1_ONLY,
        EXPORT_ONLY,
        DOMESTIC_ONLY
    }
}
