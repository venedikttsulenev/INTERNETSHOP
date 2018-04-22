-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO user(id, login, password, isAdmin, isBlackListed, account) VALUES (1, 'guest', 'guestguest', FALSE, FALSE, 2000);
INSERT INTO user(id, login, password, isAdmin, isBlackListed, account) VALUES (2, 'admin', 'adminadmin', TRUE, FALSE, 200000);

INSERT INTO product(id, name, description, count, price) VALUES (1, 'Lawn mower', 'Worlds best lawn mower', 10, 20000);
INSERT INTO product(id, name, description, count, price) VALUES (2, 'Pupa', 'Lupa', 10, 9999);
INSERT INTO product(id, name, description, count, price) VALUES (3, 'Product 1', 'Description 1', 0, 9999);
INSERT INTO product(id, name, description, count, price) VALUES (4, '111', '2222229856291865291865281956721980572198057129847210937', 10, 9999);