INSERT INTO users (login, password, email, name, surname, country)
VALUES ('login', 'password', 'log@example.com', 'Логин', 'Паролев', 'Беларусь');

INSERT INTO users (login, password, email, name, surname, country)
VALUES ('test', 'testpassword', 'test@example.com', 'Баг', 'Троянов', 'Россия');

INSERT INTO users (login, password, email, name, surname, country)
VALUES ('boris2005', '1234', 'boris@example.com', 'Ахилес', 'Тестовский', 'Беларусь');

INSERT INTO users (login, password, email, name, surname, country)
VALUES ('ahiles123', 'password123', 'ahil@example.com', 'Ахилес', 'Эакидов', 'Беларусь');

INSERT INTO users (login, password, email, name, surname, country)
VALUES ('hector123', 'password456', 'gektor@example.com', 'Гектор', 'Троянов', 'Россия');

INSERT INTO roles (user_id, role)
VALUES ((SELECT id FROM users WHERE login = 'login'), 'USER');

INSERT INTO roles (user_id, role)
VALUES ((SELECT id FROM users WHERE login = 'test'), 'USER');

INSERT INTO roles (user_id, role)
VALUES ((SELECT id FROM users WHERE login = 'boris2005'), 'USER');

INSERT INTO roles (user_id, role)
VALUES ((SELECT id FROM users WHERE login = 'ahiles123'), 'USER');

INSERT INTO roles (user_id, role)
VALUES ((SELECT id FROM users WHERE login = 'hector123'), 'USER');