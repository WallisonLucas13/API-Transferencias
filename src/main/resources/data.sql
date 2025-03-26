INSERT IGNORE INTO person (id, type, full_name, cpf, email, password) VALUES (1, 'USER', 'Usuário 1', '253.964.835-55', 'user1@gmail.com', '123');
INSERT IGNORE INTO person (id, type, full_name, cpf, email, password) VALUES (2, 'SHOPKEEPER', 'Lojista 1', '359.872.728-31', 'lojista1@gmail.com', '123');
INSERT IGNORE INTO person (id, type, full_name, cpf, email, password) VALUES (3, 'USER', 'Usuário 2', '794.139.302-60', 'user2@gmail.com', '123');
INSERT IGNORE INTO person (id, type, full_name, cpf, email, password) VALUES (4, 'SHOPKEEPER', 'Lojista 2', '990.186.111-07', 'lojista2@gmail.com', '123');

INSERT IGNORE INTO wallet (id, balance, owner_id) VALUES (1, 10000.0, 1);
INSERT IGNORE INTO wallet (id, balance, owner_id) VALUES (2, 0.0, 2);
INSERT IGNORE INTO wallet (id, balance, owner_id) VALUES (3, 5000.0, 3);
INSERT IGNORE INTO wallet (id, balance, owner_id) VALUES (4, 0.0, 4);

UPDATE person SET wallet_id = 1 WHERE person.id = 1;
UPDATE person SET wallet_id = 2 WHERE person.id = 2;
UPDATE person SET wallet_id = 3 WHERE person.id = 3;
UPDATE person SET wallet_id = 4 WHERE person.id = 4;