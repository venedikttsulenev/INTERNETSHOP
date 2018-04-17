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

INSERT INTO product(id, name, description, count, price) VALUES (1, 'Книга: Бертран Рассел: История западной философии', 'Бертран Рассел (1872 - 1970) - лауреат Нобелевской премии по литературе, математик, общественный деятель и один из величайших философов ХХ века, автор знаменитых работ "Проблемы философии", "Человеческое познание: его сфера и границы", "Азбука относительности", "Брак и мораль" и "История западной философии".
"История западной философии" - самый известный, фундаментальный труд Б. Рассела.', 25, 750);
INSERT INTO product(id, name, description, count, price) VALUES (2, 'Газонокосилка садовая', 'Самая лучшая газонокосилка', 10, 20000);