SET @col_exists = (
    SELECT COUNT(*)
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'product'
      AND COLUMN_NAME = 'is_published'
);

SET @sql = IF(@col_exists = 0,
    'ALTER TABLE product ADD COLUMN is_published BIT(1) NOT NULL DEFAULT 0',
    'SELECT 1'
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
