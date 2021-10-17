INSERT INTO customers (username, name, cnp, phone, mail, address)
VALUES ('DA111', 'Dan Abraham1', '1111111111111', '0740 000 111', 'da1@yahoo.com', 'Cluj'),
       ('DA222', 'Dan Abraham2', '2222222222222', '0740 000 222', 'da2@yahoo.com', 'Cluj'),
       ('DA333', 'Dan Abraham3', '3333333333333', '0740 000 333', 'da3@yahoo.com', 'Cluj')
;

INSERT INTO accounts (customer_id, iban, currency, status)
VALUES (1, 'ROINGBEURxxxxxxxxxxxxxx1', 'EUR', 'ACTIVE'),
       (1, 'ROINGBRONxxxxxxxxxxxxxx1', 'RON', 'ACTIVE'),
       (2, 'ROINGBEURxxxxxxxxxxxxxx2', 'EUR', 'ACTIVE'),
       (1, 'ROINGBRONxxxxxxxxxxxxx11', 'RON', 'INACTIVE');

INSERT INTO transactions (account_id, date_time, transaction_details, debit, credit, balance)
VALUES
       (1, '2021-09-29 10:00', 'salary', 2000, 0, 2000),
       (1, '2021-09-30 18:00', 'Vivo Center - Nike', 0, -100, 1900),
       (1, '2021-09-30 19:00', 'Vivo Center - Adidas', 0, -500, 1400),
       (1, '2021-09-30 20:00', 'salary', 1000, 0, 2400),
       (2, '2021-09-29 10:00', 'salary', 2000, 0, 2000),
       (3, '2021-09-29 10:00', 'salary', 2000, 0, 2000)
       ;