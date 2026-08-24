create table category (
    pcat_id bigint not null auto_increment,
    pcat_desc varchar(255),
    pcat_name varchar(255),
    primary key (pcat_id)
) engine=InnoDB;

create table login (
    login_id bigint not null auto_increment,
    logintime datetime(6),
    password varchar(255),
    username varchar(255),
    role enum ('ADMIN','CUSTOMER','ENTERPRISE'),
    primary key (login_id)
) engine=InnoDB;

create table product (
    assembly_required bit,
    cost_price float(23),
    current_stock integer,
    discount integer,
    featured_offer bit,
    height_cm float(23),
    inventory_sync bit,
    lead_time integer,
    length_cm float(23),
    margin_percentage integer,
    minimum_stock_level integer,
    procurement_pipeline bit,
    reorder_quantity integer,
    sales_module bit,
    selling_price float(23),
    visibility bit,
    weight_kg float(23),
    width_cm float(23),
    primary_category_id bigint,
    prod_id bigint not null auto_increment,
    schedule_launch datetime(6),
    secondary_category_id bigint,
    meta_desc varchar(500),
    short_desc varchar(500),
    accounting_code varchar(255),
    audit_notes varchar(255),
    brand varchar(255),
    image_360 varchar(255),
    inventory_sku_id varchar(255),
    long_desc TEXT,
    offering_name varchar(255) not null,
    page_title varchar(255),
    pricing_desc varchar(255),
    primary_image varchar(255),
    product_brochure varchar(255),
    sku_id varchar(255) not null,
    tech_desc varchar(255),
    upload_draw varchar(255),
    url_slug varchar(255),
    video_link varchar(255),
    additional_attributes json,
    allowed_users json,
    category enum ('BATH','BEDDING','DECOR','FURNITURE','KITCHEN','LIGHTING','OTHER','OUTDOOR','RUGS','STORAGE','WALL_ART') not null,
    finish_type enum ('BRUSHED','DISTRESSED','GLOSS','LACQUERED','MATTE','NATURAL','NONE','POLISHED','POWDER_COATED','SATIN','SEMI_GLOSS'),
    gallery_images json,
    gst_rate enum ('GST_0','GST_12','GST_18','GST_28','GST_5'),
    load_capacity enum ('ABOVE_200_KG','NOT_APPLICABLE','UP_TO_100_KG','UP_TO_150_KG','UP_TO_200_KG','UP_TO_50_KG','UP_TO_80_KG'),
    offering_type enum ('BUNDLE','PRODUCT','SERVICE') not null,
    preferred_vendor enum ('IMPORTED','IN_HOUSE','THIRD_PARTY','VENDOR_A','VENDOR_B','VENDOR_C'),
    price_unit enum ('PER_BOX','PER_KG','PER_LITRE','PER_METER','PER_PAIR','PER_PIECE','PER_SET','PER_SQ_METER'),
    primary_material enum ('ALUMINIUM','BAMBOO','ENGINEERED_WOOD','FABRIC','GLASS','GRANITE','LEATHER','MARBLE','MDF','METAL','OTHER','PLASTIC','PLYWOOD','RATTAN','SOLID_WOOD','STAINLESS_STEEL'),
    publishing_status enum ('APPROVED','ARCHIVED','DISCONTINUED','DRAFT','IN_REVIEW','PUBLISHED','SCHEDULED'),
    restricted_region enum ('DOMESTIC_ONLY','EAST_INDIA','EXPORT_ONLY','METRO_ONLY','NONE','NORTH_INDIA','SOUTH_INDIA','TIER_1_ONLY','WEST_INDIA'),
    secondary_material enum ('ENGINEERED_WOOD','FABRIC','FOAM','GLASS','LEATHER','MDF','METAL','NONE','OTHER','PLASTIC','SOLID_WOOD'),
    seo_keywords json,
    primary key (prod_id)
) engine=InnoDB;

create table product_tags (
    prod_id bigint not null,
    tag varchar(255)
) engine=InnoDB;

create table secondary_category (
    parent_scat_id bigint,
    primary_category_id bigint,
    scat_id bigint not null auto_increment,
    scat_desc varchar(255),
    scat_name varchar(255),
    primary key (scat_id)
) engine=InnoDB;

create table user (
    created_at datetime(6),
    phone_number bigint,
    pincode bigint,
    uuid bigint not null auto_increment,
    brand_name varchar(255),
    city varchar(255),
    description varchar(255),
    email varchar(255),
    first_name varchar(255),
    gst_number varchar(255),
    last_name varchar(255),
    password varchar(255),
    state varchar(255),
    role enum ('ADMIN','CUSTOMER','ENTERPRISE'),
    primary key (uuid)
) engine=InnoDB;

alter table product
   add constraint UK33ly4am1qqsbj8j1j7cyw7y27 unique (sku_id);

alter table product
   add constraint FKh14uv8fk6cibavvovd9fk7xw9
   foreign key (primary_category_id)
   references category (pcat_id);

alter table product
   add constraint FK5ujypc793ic3gtmvk3ly4xm1r
   foreign key (secondary_category_id)
   references secondary_category (scat_id);

alter table product_tags
   add constraint FK9261nab572rcinhhp67wotaih
   foreign key (prod_id)
   references product (prod_id);

alter table secondary_category
   add constraint FKl1h51qlbnl67i5ik217brmemr
   foreign key (parent_scat_id)
   references secondary_category (scat_id);

alter table secondary_category
   add constraint FKnotgpa9fuf4q3dmk7fe9cpimu
   foreign key (primary_category_id)
   references category (pcat_id);
