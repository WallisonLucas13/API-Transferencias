INSERT IGNORE INTO user (id, type, full_name, cpf, email, password) VALUES (1, 'COMMON', 'Usuário 1', '253.964.835-55', 'user1@gmail.com', '123');
INSERT IGNORE INTO user (id, type, full_name, cpf, email, password) VALUES (2, 'SHOPKEEPER', 'Lojista 1', '359.872.728-31', 'lojista1@gmail.com', '123');
INSERT IGNORE INTO user (id, type, full_name, cpf, email, password) VALUES (3, 'COMMON', 'Usuário 2', '794.139.302-60', 'user2@gmail.com', '123');
INSERT IGNORE INTO user (id, type, full_name, cpf, email, password) VALUES (4, 'SHOPKEEPER', 'Lojista 2', '990.186.111-07', 'lojista2@gmail.com', '123');

INSERT IGNORE INTO wallet (id, balance, owner_id) VALUES (1, 10000.0, 1);
INSERT IGNORE INTO wallet (id, balance, owner_id) VALUES (2, 0.0, 2);
INSERT IGNORE INTO wallet (id, balance, owner_id) VALUES (3, 5000.0, 3);
INSERT IGNORE INTO wallet (id, balance, owner_id) VALUES (4, 0.0, 4);

UPDATE user SET wallet_id = 1 WHERE user.id = 1;
UPDATE user SET wallet_id = 2 WHERE user.id = 2;
UPDATE user SET wallet_id = 3 WHERE user.id = 3;
UPDATE user SET wallet_id = 4 WHERE user.id = 4;