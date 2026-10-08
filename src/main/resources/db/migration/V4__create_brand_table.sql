CREATE TABLE IF NOT EXISTS brands (
    id BIGINT NOT NULL AUTO_INCREMENT,
    brand_name VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    country VARCHAR(100) NOT NULL,
    country_code VARCHAR(10) NOT NULL,
    offerings_count INT NOT NULL DEFAULT 0,
    categories JSON,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    logo_url VARCHAR(500),
    updated_date VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

SET @col_exists = (
    SELECT COUNT(*) 
    FROM information_schema.COLUMNS 
    WHERE TABLE_SCHEMA = DATABASE() 
      AND TABLE_NAME = 'product' 
      AND COLUMN_NAME = 'brand_id'
);

SET @sql_col = IF(@col_exists = 0, 
    'ALTER TABLE product ADD COLUMN brand_id BIGINT', 
    'SELECT 1'
);

PREPARE stmt_col FROM @sql_col;
EXECUTE stmt_col;
DEALLOCATE PREPARE stmt_col;

SET @fk_exists = (
    SELECT COUNT(*) 
    FROM information_schema.TABLE_CONSTRAINTS 
    WHERE CONSTRAINT_SCHEMA = DATABASE() 
      AND TABLE_NAME = 'product' 
      AND CONSTRAINT_NAME = 'fk_product_brand'
);

SET @sql_fk = IF(@fk_exists = 0, 
    'ALTER TABLE product ADD CONSTRAINT fk_product_brand FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE SET NULL', 
    'SELECT 1'
);

PREPARE stmt_fk FROM @sql_fk;
EXECUTE stmt_fk;
DEALLOCATE PREPARE stmt_fk;
