-- CREATE CLIENT
INSERT INTO client (id , birth_date , first_name, last_name, phone, email, client_type) VALUES (1, '1995-4-8', 'Wiem','Hadj mabrouk', '0612345678', 'wiem.hajmabrouk@margo-group.com', 'Personne physique');


-- CREATE BANK_ACCOUNT
INSERT INTO bank_account (id, balance, creation_date, currency, client_id) VALUES (1, 3000, NOW(), 'EUR', 1);

-- CREATE OPERATION
INSERT INTO operation (id, amount, balance, operation_Date , operation_type, bank_account_id ) VALUES(1,1500, 3500, NOW(),'DEPOSIT',  1);
INSERT INTO operation (id, amount, balance, operation_Date , operation_type, bank_account_id ) VALUES(2,-500,3000, NOW(), 'WITHDRAWAL',   1);

