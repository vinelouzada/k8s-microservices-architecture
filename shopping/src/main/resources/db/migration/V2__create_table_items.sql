CREATE TABLE items
(
    order_id   BIGINT         NOT NULL,
    product_id VARCHAR(255)   NOT NULL,
    price      DECIMAL(19, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);