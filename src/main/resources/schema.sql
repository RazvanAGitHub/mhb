DROP TABLE IF EXISTS customers;

CREATE TABLE customers
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(64)  NOT NULL,
    name     VARCHAR(64)  NOT NULL,
    cnp      VARCHAR(13)  NOT NULL,
    phone    VARCHAR(32)  NOT NULL,
    mail     VARCHAR(64)  NOT NULL,
    address  VARCHAR(128) NOT NULL
);

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT         NOT NULL,
    iban        VARCHAR(24) NOT NULL,
    currency    VARCHAR(3)  NOT NULL
);

DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    account_id          INT          NOT NULL,
    date_time           datetime(0)  NOT NULL,
    transaction_details VARCHAR(255) NOT NULL,
    debit               bigint       NOT NULL,
    credit              bigint       NOT NULL,
    balance             bigint       NOT NULL
);