-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO user(id, login, password, isAdmin, isBlackListed, account) VALUES (1, 'guest', 'guestguest', FALSE, FALSE, 2000);
INSERT INTO user(id, login, password, isAdmin, isBlackListed, account) VALUES (2, 'vasily', 'vasvasvas', FALSE, TRUE, 931);
INSERT INTO user(id, login, password, isAdmin, isBlackListed, account) VALUES (3, 'admin', 'adminadmin', TRUE, FALSE, 200000);

INSERT INTO product(id, name, description, count, price) VALUES (1, 'Lawn mower', 'Worlds best lawn mower', 10, 20000);
INSERT INTO product(id, name, description, count, price) VALUES (2, 'Lightsaber', 'Real jedi''s lighsaber' , 1, 1000000);
INSERT INTO product(id, name, description, count, price) VALUES (3, 'Your custom product', 'You can place it right here!' , 0, 150);