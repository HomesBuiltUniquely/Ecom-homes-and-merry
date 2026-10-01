-- Category (Primary Category) table columns
SET @col_exists = (
    SELECT COUNT(*) 
    FROM information_schema.COLUMNS 
    WHERE TABLE_SCHEMA = DATABASE() 
      AND TABLE_NAME = 'category' 
      AND COLUMN_NAME = 'image_url'
);

SET @sql = IF(@col_exists = 0, 
    'ALTER TABLE category ADD COLUMN image_url VARCHAR(500), ADD COLUMN page_title VARCHAR(255), ADD COLUMN meta_desc VARCHAR(500), ADD COLUMN url_slug VARCHAR(255), ADD COLUMN seo_keywords JSON, ADD COLUMN internal_tags JSON', 
    'SELECT 1'
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Secondary Category table columns
SET @col_exists = (
    SELECT COUNT(*) 
    FROM information_schema.COLUMNS 
    WHERE TABLE_SCHEMA = DATABASE() 
      AND TABLE_NAME = 'secondary_category' 
      AND COLUMN_NAME = 'image_url'
);

SET @sql = IF(@col_exists = 0, 
    'ALTER TABLE secondary_category ADD COLUMN image_url VARCHAR(500), ADD COLUMN page_title VARCHAR(255), ADD COLUMN meta_desc VARCHAR(500), ADD COLUMN url_slug VARCHAR(255), ADD COLUMN seo_keywords JSON, ADD COLUMN internal_tags JSON', 
    'SELECT 1'
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
