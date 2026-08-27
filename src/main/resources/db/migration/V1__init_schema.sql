create table if not exists category (
    pcat_id bigint not null auto_increment,
    pcat_desc varchar(255),
    pcat_name varchar(255),
    primary key (pcat_id)
) engine=InnoDB;

create table if not exists login (
    login_id bigint not null auto_increment,
    logintime datetime(6),
    password varchar(255),
    username varchar(255),
    role varchar(50),
    primary key (login_id)
) engine=InnoDB;

create table if not exists product (
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
    category varchar(50) not null,
    finish_type varchar(50),
    gallery_images json,
    gst_rate varchar(50),
    load_capacity varchar(50),
    offering_type varchar(50) not null,
    preferred_vendor varchar(50),
    price_unit varchar(50),
    primary_material varchar(50),
    publishing_status varchar(50),
    restricted_region varchar(50),
    secondary_material varchar(50),
    seo_keywords json,
    primary key (prod_id)
) engine=InnoDB;

create table if not exists product_tags (
    prod_id bigint not null,
    tag varchar(255)
) engine=InnoDB;

create table if not exists secondary_category (
    parent_scat_id bigint,
    primary_category_id bigint,
    scat_id bigint not null auto_increment,
    scat_desc varchar(255),
    scat_name varchar(255),
    primary key (scat_id)
) engine=InnoDB;

create table if not exists user (
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
    role varchar(50),
    primary key (uuid)
) engine=InnoDB;
