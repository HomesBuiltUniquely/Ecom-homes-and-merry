CREATE TABLE IF NOT EXISTS brands (
    brand_id BIGINT NOT NULL AUTO_INCREMENT,
    brand_name VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255),
    code VARCHAR(100) NOT NULL UNIQUE,
    country VARCHAR(100),
    logo_url VARCHAR(500),
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at DATETIME(6),
    updated_at DATETIME(6),
    PRIMARY KEY (brand_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS brand_categories (
    brand_id BIGINT NOT NULL,
    category_name VARCHAR(100),
    CONSTRAINT fk_brand_categories_brand FOREIGN KEY (brand_id) REFERENCES brands (brand_id) ON DELETE CASCADE
) ENGINE=InnoDB;

ALTER TABLE product ADD COLUMN IF NOT EXISTS brand_id BIGINT;
