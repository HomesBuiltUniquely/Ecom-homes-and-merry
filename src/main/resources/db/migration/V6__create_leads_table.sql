CREATE TABLE IF NOT EXISTS leads (
    id BIGINT NOT NULL AUTO_INCREMENT,
    lead_id VARCHAR(255) NOT NULL,
    customer_name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    lead_type VARCHAR(255),
    status VARCHAR(255),
    assigned_designer_email VARCHAR(255),
    assigned_sales_email VARCHAR(255),
    territory VARCHAR(255),
    queue_type VARCHAR(255),
    created_at DATETIME(6),
    PRIMARY KEY (id),
    UNIQUE KEY uk_leads_lead_id (lead_id)
) ENGINE=InnoDB;

CREATE INDEX idx_leads_queue_type ON leads (queue_type);
CREATE INDEX idx_leads_assigned_designer_queue ON leads (assigned_designer_email, queue_type);
CREATE INDEX idx_leads_lead_type ON leads (lead_type);
CREATE INDEX idx_leads_assigned_sales ON leads (assigned_sales_email);
