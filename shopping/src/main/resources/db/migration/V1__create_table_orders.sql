CREATE TABLE orders
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     VARCHAR(255)   NOT NULL,
    total_price DECIMAL(19, 2) NOT NULL,
    created_at  TIMESTAMP      NOT NULL
);
