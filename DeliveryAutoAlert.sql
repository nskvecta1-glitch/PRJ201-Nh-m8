CREATE DATABASE DeliveryAutoAlert;
GO

USE DeliveryAutoAlert;
GO
CREATE TABLE Roles (
    role_id INT PRIMARY KEY IDENTITY,
    role_name NVARCHAR(50) NOT NULL,
    description NVARCHAR(255)
);

CREATE TABLE Users (
    user_id INT PRIMARY KEY IDENTITY,
    username NVARCHAR(50) UNIQUE NOT NULL,
    password_hash NVARCHAR(255) NOT NULL,
    full_name NVARCHAR(100),
    email NVARCHAR(100),
    role_id INT,
    is_active BIT DEFAULT 1,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

CREATE TABLE Customers (
    customer_id INT PRIMARY KEY IDENTITY,
    customer_name NVARCHAR(150) NOT NULL,
    phone NVARCHAR(20),
    address NVARCHAR(255),
    email NVARCHAR(100),
    created_at DATETIME DEFAULT GETDATE()
);

CREATE TABLE Warehouses (
    warehouse_id INT PRIMARY KEY IDENTITY,
    warehouse_name NVARCHAR(150),
    location NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE()
);

CREATE TABLE Products (
    product_id INT PRIMARY KEY IDENTITY,
    sku NVARCHAR(50) UNIQUE NOT NULL,
    product_name NVARCHAR(150),
    price DECIMAL(18,2),
    created_at DATETIME DEFAULT GETDATE()
);
CREATE TABLE DeliveryOrders (
    order_id INT PRIMARY KEY IDENTITY,
    order_code NVARCHAR(50) UNIQUE,
    customer_id INT,
    warehouse_id INT,
    order_date DATETIME DEFAULT GETDATE(),
    status NVARCHAR(50),
    has_cod BIT DEFAULT 0,
    cod_amount DECIMAL(18,2),
    total_amount DECIMAL(18,2),
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id)
);

CREATE TABLE OrderItems (
    order_item_id INT PRIMARY KEY IDENTITY,
    order_id INT,
    product_id INT,
    quantity INT,
    unit_price DECIMAL(18,2),
    FOREIGN KEY (order_id) REFERENCES DeliveryOrders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);

CREATE TABLE Shipments (
    shipment_id INT PRIMARY KEY IDENTITY,
    order_id INT,
    ship_date DATETIME,
    delivery_status NVARCHAR(50),
    route NVARCHAR(100),
    FOREIGN KEY (order_id) REFERENCES DeliveryOrders(order_id)
);

CREATE TABLE ProofOfDelivery (
    pod_id INT PRIMARY KEY IDENTITY,
    shipment_id INT,
    delivered_at DATETIME,
    receiver_name NVARCHAR(100),
    pod_image_url NVARCHAR(255),
    FOREIGN KEY (shipment_id) REFERENCES Shipments(shipment_id)
);
CREATE TABLE OutboundDocs (
    outbound_id INT PRIMARY KEY IDENTITY,
    ref_order_id INT,
    warehouse_id INT,
    outbound_date DATETIME DEFAULT GETDATE(),
    status NVARCHAR(50),
    FOREIGN KEY (ref_order_id) REFERENCES DeliveryOrders(order_id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id)
);

CREATE TABLE InboundDocs (
    inbound_id INT PRIMARY KEY IDENTITY,
    ref_order_id INT,
    warehouse_id INT,
    inbound_date DATETIME DEFAULT GETDATE(),
    reason NVARCHAR(255),
    FOREIGN KEY (ref_order_id) REFERENCES DeliveryOrders(order_id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id)
);

CREATE TABLE StockLedger (
    ledger_id INT PRIMARY KEY IDENTITY,
    product_id INT,
    warehouse_id INT,
    change_qty INT,
    ref_type NVARCHAR(50),
    ref_id INT,
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id)
);
CREATE TABLE Invoices (
    invoice_id INT PRIMARY KEY IDENTITY,
    order_id INT,
    invoice_date DATETIME DEFAULT GETDATE(),
    total_amount DECIMAL(18,2),
    status NVARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES DeliveryOrders(order_id)
);

CREATE TABLE Payments (
    payment_id INT PRIMARY KEY IDENTITY,
    invoice_id INT,
    payment_date DATETIME DEFAULT GETDATE(),
    amount DECIMAL(18,2),
    payment_method NVARCHAR(50),
    status NVARCHAR(50),
    FOREIGN KEY (invoice_id) REFERENCES Invoices(invoice_id)
);
CREATE TABLE CODReconciliation (
    cod_id INT PRIMARY KEY IDENTITY,
    order_id INT,
    amount DECIMAL(12,2),
    collected_date DATETIME DEFAULT GETDATE(),
    status NVARCHAR(50),
    note NVARCHAR(255),

    FOREIGN KEY (order_id) REFERENCES DeliveryOrders(order_id)
);
CREATE TABLE AlertRules (
    rule_id INT PRIMARY KEY IDENTITY,
    rule_name NVARCHAR(150),
    rule_type NVARCHAR(100),
    threshold_value DECIMAL(18,2),
    severity NVARCHAR(50)
);

CREATE TABLE AlertEvents (
    alert_id INT PRIMARY KEY IDENTITY,
    rule_id INT,
    ref_type NVARCHAR(50),
    ref_id INT,
    risk_score FLOAT,
    created_at DATETIME DEFAULT GETDATE(),
    status NVARCHAR(50),
    FOREIGN KEY (rule_id) REFERENCES AlertRules(rule_id)
);

CREATE TABLE ReconciliationCases (
    case_id INT PRIMARY KEY IDENTITY,
    alert_id INT,
    case_status NVARCHAR(50),
    assigned_to INT,
    opened_at DATETIME DEFAULT GETDATE(),
    closed_at DATETIME,
    resolution_note NVARCHAR(500),
    FOREIGN KEY (alert_id) REFERENCES AlertEvents(alert_id),
    FOREIGN KEY (assigned_to) REFERENCES Users(user_id)
);

CREATE TABLE AlertActions (
    action_id INT PRIMARY KEY IDENTITY,
    case_id INT,
    action_by INT,
    action_note NVARCHAR(500),
    action_time DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (case_id) REFERENCES ReconciliationCases(case_id),
    FOREIGN KEY (action_by) REFERENCES Users(user_id)
);
CREATE TABLE AI_Scoring_Log (
    score_id INT PRIMARY KEY IDENTITY,
    ref_type NVARCHAR(50),
    ref_id INT,
    delay_risk_score FLOAT,
    anomaly_score FLOAT,
    created_at DATETIME DEFAULT GETDATE()
);
CREATE INDEX idx_order_date ON DeliveryOrders(order_date);
CREATE INDEX idx_order_status ON DeliveryOrders(status);
CREATE INDEX idx_outbound_date ON OutboundDocs(outbound_date);
CREATE INDEX idx_invoice_date ON Invoices(invoice_date);
CREATE INDEX idx_alert_status ON AlertEvents(status);
