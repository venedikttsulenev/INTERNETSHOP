-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE user (
  id BIGINT IDENTITY,
  login VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  isAdmin BOOLEAN NOT NULL,
  isBlackListed BOOLEAN NOT NULL
);

CREATE TABLE product (
  id BIGINT IDENTITY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(16000) NOT NULL,
  count BIGINT NOT NULL,
  price BIGINT NOT NULL
);

CREATE TABLE payment (
  id BIGINT IDENTITY,
  user_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES user,
  product_id BIGINT,
  FOREIGN KEY (product_id) REFERENCES product,
  price BIGINT NOT NULL,
  paydate DATE,
  isPayed BOOLEAN NOT NULL
);

INSERT INTO user(id, login, password, isAdmin, isBlackListed) VALUES (1, 'guest', '', FALSE, FALSE);
INSERT INTO user(id, login, password, isAdmin, isBlackListed) VALUES (2, 'admin', 'admin', TRUE, FALSE);

INSERT INTO product(id, name, description, count, price) VALUES (1, 'Газонокосилка садовая', 'Самая лучшая газонокосилка', 10, 20000);
INSERT INTO product(id, name, description, count, price) VALUES (2, 'Pupa', 'Lupa', 10, 9999);
INSERT INTO product(id, name, description, count, price) VALUES (3, 'Product 1', '~(•‿•)~', 10, 9999);
INSERT INTO product(id, name, description, count, price) VALUES (4, '111', '2222229856291865291865281956721980572198057129847210937', 10, 9999);