CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       DECIMAL(19, 2) NOT NULL,
    identifier  VARCHAR(255)   NOT NULL UNIQUE,
    category    VARCHAR(50)    NOT NULL
);