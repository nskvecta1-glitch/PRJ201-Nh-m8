-- ============================================================
--  DeliveryAutoAlert - Database Schema
--  Target  : SQL Server 2019 (SSMS 2019)
--  Server  : localhost:1433
--  Login   : sa / 12345
--  Encoding: UTF-8
-- ============================================================

USE master;
GO

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'DeliveryAutoAlert')
    CREATE DATABASE DeliveryAutoAlert;
GO

USE DeliveryAutoAlert;
GO

-- ============================================================
-- 1. ROLES
-- ============================================================

CREATE TABLE Roles (
    role_id     INT IDENTITY(1,1) PRIMARY KEY,
    role_name   VARCHAR(50)  NOT NULL UNIQUE,
    description VARCHAR(200)
);
GO

-- ============================================================
-- 2. USERS
-- Passwords stored as SHA-256 hex strings (64 chars)
-- ============================================================

CREATE TABLE Users (
    user_id       INT IDENTITY(1,1) PRIMARY KEY,
    username      VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,   -- SHA-256 hex
    full_name     NVARCHAR(150),
    email         VARCHAR(150),
    role_id       INT NOT NULL REFERENCES Roles(role_id),
    is_active     BIT NOT NULL DEFAULT 1,
    created_at    DATETIME2 DEFAULT GETDATE()
);
GO

-- ============================================================
-- 3. CUSTOMERS
-- ============================================================

CREATE TABLE Customers (
    customer_id   INT IDENTITY(1,1) PRIMARY KEY,
    customer_name NVARCHAR(200) NOT NULL,
    phone         VARCHAR(20),
    address       NVARCHAR(300),
    email         VARCHAR(150),
    created_at    DATETIME2 DEFAULT GETDATE()
);
GO

-- ============================================================
-- 4. PRODUCTS
-- ============================================================

CREATE TABLE Products (
    product_id   INT IDENTITY(1,1) PRIMARY KEY,
    sku          VARCHAR(100) NOT NULL UNIQUE,
    product_name NVARCHAR(200) NOT NULL,
    price        DECIMAL(18,2),
    created_at   DATETIME2 DEFAULT GETDATE()
);
GO

-- ============================================================
-- 5. WAREHOUSES
-- ============================================================

CREATE TABLE Warehouses (
    warehouse_id   INT IDENTITY(1,1) PRIMARY KEY,
    warehouse_name NVARCHAR(200) NOT NULL,
    location       NVARCHAR(300),
    created_at     DATETIME2 DEFAULT GETDATE()
);
GO

-- ============================================================
-- 6. DELIVERY ORDERS
-- ============================================================

CREATE TABLE DeliveryOrders (
    order_id     INT IDENTITY(1,1) PRIMARY KEY,
    order_code   VARCHAR(100) NOT NULL UNIQUE,
    customer_id  INT NOT NULL REFERENCES Customers(customer_id),
    warehouse_id INT NOT NULL REFERENCES Warehouses(warehouse_id),
    order_date   DATETIME2 DEFAULT GETDATE(),
    status       VARCHAR(50) NOT NULL DEFAULT 'PENDING',
                 -- PENDING | DISPATCHED | DELIVERED | RETURNED | CANCELLED
    has_cod      BIT NOT NULL DEFAULT 0,
    cod_amount   DECIMAL(18,2),
    total_amount DECIMAL(18,2),
    created_at   DATETIME2 DEFAULT GETDATE()
);
GO

CREATE INDEX IX_DeliveryOrders_Status   ON DeliveryOrders(status);
CREATE INDEX IX_DeliveryOrders_Date     ON DeliveryOrders(order_date);
GO

-- ============================================================
-- 7. ORDER ITEMS
-- ============================================================

CREATE TABLE OrderItems (
    order_item_id INT IDENTITY(1,1) PRIMARY KEY,
    order_id      INT NOT NULL REFERENCES DeliveryOrders(order_id),
    product_id    INT NOT NULL REFERENCES Products(product_id),
    quantity      INT NOT NULL,
    unit_price    DECIMAL(18,2)
);
GO

-- ============================================================
-- 8. SHIPMENTS
-- ============================================================

CREATE TABLE Shipments (
    shipment_id     INT IDENTITY(1,1) PRIMARY KEY,
    order_id        INT NOT NULL REFERENCES DeliveryOrders(order_id),
    ship_date       DATETIME2,
    delivery_status VARCHAR(50) NOT NULL DEFAULT 'IN_TRANSIT',
                    -- IN_TRANSIT | DELIVERED | FAILED | RETURNED
    route           VARCHAR(200)
);
GO

-- ============================================================
-- 9. PROOF OF DELIVERY
-- ============================================================

CREATE TABLE ProofOfDelivery (
    pod_id        INT IDENTITY(1,1) PRIMARY KEY,
    shipment_id   INT NOT NULL REFERENCES Shipments(shipment_id),
    delivered_at  DATETIME2,
    receiver_name NVARCHAR(150),
    pod_image_url VARCHAR(500)
);
GO

-- ============================================================
-- 10. OUTBOUND DOCUMENTS
-- ============================================================

CREATE TABLE OutboundDocs (
    outbound_id   INT IDENTITY(1,1) PRIMARY KEY,
    ref_order_id  INT REFERENCES DeliveryOrders(order_id),
    warehouse_id  INT NOT NULL REFERENCES Warehouses(warehouse_id),
    outbound_date DATETIME2 DEFAULT GETDATE(),
    status        VARCHAR(50) NOT NULL DEFAULT 'DRAFT'
                  -- DRAFT | CONFIRMED | CANCELLED
);
GO

CREATE INDEX IX_OutboundDocs_Date      ON OutboundDocs(outbound_date);
CREATE INDEX IX_OutboundDocs_Status    ON OutboundDocs(status);
GO

-- ============================================================
-- 11. INBOUND DOCUMENTS
-- ============================================================

CREATE TABLE InboundDocs (
    inbound_id   INT IDENTITY(1,1) PRIMARY KEY,
    ref_order_id INT REFERENCES DeliveryOrders(order_id),
    warehouse_id INT NOT NULL REFERENCES Warehouses(warehouse_id),
    inbound_date DATETIME2 DEFAULT GETDATE(),
    reason       NVARCHAR(300)
);
GO

-- ============================================================
-- 12. STOCK LEDGER
-- ============================================================

CREATE TABLE StockLedger (
    ledger_id    INT IDENTITY(1,1) PRIMARY KEY,
    product_id   INT NOT NULL REFERENCES Products(product_id),
    warehouse_id INT NOT NULL REFERENCES Warehouses(warehouse_id),
    change_qty   INT NOT NULL,   -- positive = stock IN, negative = stock OUT
    ref_type     VARCHAR(50),    -- OUTBOUND | INBOUND
    ref_id       INT,
    created_at   DATETIME2 DEFAULT GETDATE()
);
GO

CREATE INDEX IX_StockLedger_Product   ON StockLedger(product_id, warehouse_id);
GO

-- ============================================================
-- 13. INVOICES
-- ============================================================

CREATE TABLE Invoices (
    invoice_id   INT IDENTITY(1,1) PRIMARY KEY,
    order_id     INT NOT NULL REFERENCES DeliveryOrders(order_id),
    invoice_date DATETIME2 DEFAULT GETDATE(),
    total_amount DECIMAL(18,2) NOT NULL,
    status       VARCHAR(50) NOT NULL DEFAULT 'UNPAID'
                 -- UNPAID | PARTIAL | PAID | CANCELLED
);
GO

CREATE INDEX IX_Invoices_Status ON Invoices(status);
CREATE INDEX IX_Invoices_Date   ON Invoices(invoice_date);
GO

-- ============================================================
-- 14. PAYMENTS
-- ============================================================

CREATE TABLE Payments (
    payment_id     INT IDENTITY(1,1) PRIMARY KEY,
    invoice_id     INT NOT NULL REFERENCES Invoices(invoice_id),
    payment_date   DATETIME2 DEFAULT GETDATE(),
    amount         DECIMAL(18,2) NOT NULL,
    payment_method VARCHAR(50),  -- CASH | COD | TRANSFER | CARD
    status         VARCHAR(50) NOT NULL DEFAULT 'PENDING'
                   -- PENDING | COMPLETED | FAILED
);
GO

-- ============================================================
-- 15. ALERT RULES
-- ============================================================

CREATE TABLE AlertRules (
    rule_id         INT IDENTITY(1,1) PRIMARY KEY,
    rule_name       NVARCHAR(200),
    rule_type       VARCHAR(100),
                    -- LATE_DELIVERY | QTY_MISMATCH | COD_MISMATCH | MISSING_POD | NEG_STOCK
    threshold_value DECIMAL(18,2),
    severity        VARCHAR(20) NOT NULL DEFAULT 'MEDIUM'
                    -- LOW | MEDIUM | HIGH | CRITICAL
);
GO

-- ============================================================
-- 16. ALERT EVENTS
-- ============================================================

CREATE TABLE AlertEvents (
    alert_id   INT IDENTITY(1,1) PRIMARY KEY,
    rule_id    INT NOT NULL REFERENCES AlertRules(rule_id),
    ref_type   VARCHAR(50),   -- ORDER | SHIPMENT | INVOICE | STOCK
    ref_id     INT,
    risk_score FLOAT NOT NULL DEFAULT 0,
    created_at DATETIME2 DEFAULT GETDATE(),
    status     VARCHAR(50) NOT NULL DEFAULT 'OPEN'
               -- OPEN | ACKNOWLEDGED | CLOSED
);
GO

CREATE INDEX IX_AlertEvents_Status ON AlertEvents(status);
GO

-- ============================================================
-- 17. RECONCILIATION CASES
-- ============================================================

CREATE TABLE ReconciliationCases (
    case_id         INT IDENTITY(1,1) PRIMARY KEY,
    alert_id        INT NOT NULL REFERENCES AlertEvents(alert_id),
    case_status     VARCHAR(50) NOT NULL DEFAULT 'OPEN',
                    -- OPEN | IN_PROGRESS | RESOLVED | CLOSED
    assigned_to     INT REFERENCES Users(user_id),
    opened_at       DATETIME2 DEFAULT GETDATE(),
    closed_at       DATETIME2,
    resolution_note NVARCHAR(500)
);
GO

-- ============================================================
-- 18. ALERT ACTIONS  (action log per case)
-- ============================================================

CREATE TABLE AlertActions (
    action_id   INT IDENTITY(1,1) PRIMARY KEY,
    case_id     INT NOT NULL REFERENCES ReconciliationCases(case_id),
    action_by   INT REFERENCES Users(user_id),
    action_note NVARCHAR(500),
    action_time DATETIME2 DEFAULT GETDATE()
);
GO

-- ============================================================
-- 19. AI SCORING LOG
-- ============================================================

CREATE TABLE AI_Scoring_Log (
    log_id           INT IDENTITY(1,1) PRIMARY KEY,
    ref_type         VARCHAR(50),
    ref_id           INT,
    delay_risk_score FLOAT,
    anomaly_score    FLOAT,
    scored_at        DATETIME2 DEFAULT GETDATE()
);
GO

-- ============================================================
-- SEED DATA
-- ============================================================

INSERT INTO Roles (role_name, description) VALUES
    ('ADMIN',      'Full system access'),
    ('DELIVERY',   'Delivery operations staff'),
    ('ACCOUNTING', 'Accounting and finance staff'),
    ('WAREHOUSE',  'Warehouse operations staff');
GO

-- Admin user — password: admin123
-- SHA-256 of "admin123" = 240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9
INSERT INTO Users (username, password_hash, full_name, email, role_id, is_active) VALUES
    ('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',
     N'System Admin', 'admin@local.com', 1, 1);
GO

-- Sample customers
INSERT INTO Customers (customer_name, phone, address, email) VALUES
    (N'Nguyen Van A', '0901234567', N'Ha Noi', 'nguyenvana@email.com'),
    (N'Tran Thi B',   '0907654321', N'TP. Ho Chi Minh', 'tranthib@email.com');
GO

-- Sample products
INSERT INTO Products (sku, product_name, price) VALUES
    ('SKU001', N'Dien thoai ABC',  5000000),
    ('SKU002', N'Laptop XYZ',     20000000),
    ('SKU003', N'Tai nghe DEF',     800000);
GO

-- Sample warehouses
INSERT INTO Warehouses (warehouse_name, location) VALUES
    (N'Kho Ha Noi',    N'So 1 Duong A, Ha Noi'),
    (N'Kho TP. HCM',   N'So 10 Duong B, TP. HCM');
GO

-- Sample alert rules
INSERT INTO AlertRules (rule_name, rule_type, threshold_value, severity) VALUES
    (N'Late delivery > 2 days',       'LATE_DELIVERY', 2,     'HIGH'),
    (N'Quantity mismatch > 0',        'QTY_MISMATCH',  0,     'HIGH'),
    (N'COD mismatch > 50000',         'COD_MISMATCH',  50000, 'CRITICAL'),
    (N'Missing proof of delivery',    'MISSING_POD',   0,     'MEDIUM'),
    (N'Negative stock detected',      'NEG_STOCK',     0,     'CRITICAL');
GO

-- ============================================================
-- ALTER TABLE FOR LOGIN — run these if you need to reset or
-- add users after the database is already created
-- ============================================================

-- Add a new user (replace values as needed)
-- password_hash below = SHA-256 of "admin123"
/*
INSERT INTO Users (username, password_hash, full_name, email, role_id, is_active)
VALUES (
    'newuser',
    '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',
    N'New User Full Name',
    'newuser@local.com',
    1,   -- role_id: 1=ADMIN 2=DELIVERY 3=ACCOUNTING 4=WAREHOUSE
    1
);
*/

-- Reset a user's password (change 'admin' and the hash as needed)
-- SHA-256 of "admin123" = 240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9
-- SHA-256 of "password" = 5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8
-- SHA-256 of "123456"   = 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
/*
UPDATE Users
SET password_hash = '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9'
WHERE username = 'admin';
*/

-- Activate / deactivate a user account
/*
UPDATE Users SET is_active = 1 WHERE username = 'admin';  -- activate
UPDATE Users SET is_active = 0 WHERE username = 'admin';  -- deactivate
*/

-- Change a user's role
/*
UPDATE Users SET role_id = 1 WHERE username = 'admin';  -- promote to ADMIN
*/

-- View all users and their roles (useful for debugging login)
/*
SELECT u.user_id, u.username, u.full_name, u.email,
       r.role_name, u.is_active, u.created_at
FROM Users u
JOIN Roles r ON u.role_id = r.role_id
ORDER BY u.user_id;
*/
GO
