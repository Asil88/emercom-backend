INSERT INTO users (login, password, email, name, surname, country)
VALUES ('login', '$2a$12$vmfMM.9v6ambUJ0uhQBQseADrONhE/yUcM2LJJPkCuJFC6v.6kbiC', 'log@example.com', 'Логин', 'Паролев', 'Беларусь');

INSERT INTO users (login, password, email, name, surname, country)
VALUES ('test', '$2a$12$pnMmU7PsNFPiCj9eLvTnaeX6AbEOFrVu2RX6pEgjBEHYTmQNckmmG', 'test@example.com', 'Баг', 'Троянов', 'Россия');

INSERT INTO users (login, password, email, name, surname, country)
VALUES ('boris2005', '$2a$12$ffYMS/IjUHTodV6/lZnhL.870DiMoH8G9IR9CCtx.AP5OKG4ORV8S', 'boris@example.com', 'Ахилес', 'Тестовский', 'Беларусь');

INSERT INTO users (login, password, email, name, surname, country)
VALUES ('ahiles123', '$2a$12$A7VvObzkV6TNRxNMPOB0Fe5pCht6WfMoniYJd0/E4rl74plCsDArm', 'ahil@example.com', 'Ахилес', 'Эакидов', 'Беларусь');

INSERT INTO users (login, password, email, name, surname, country)
VALUES ('hector123', '$2a$12$yRBQKv5kLiK0JEkxvNx4g.fLc5wYkRuUISbyJ7AskqPuyGekOCef.', 'gektor@example.com', 'Гектор', 'Троянов', 'Россия');

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