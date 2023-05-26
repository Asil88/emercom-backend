
CREATE TABLE dbo.users (
                           id       BIGINT IDENTITY,
                           login    VARCHAR(50) NOT NULL UNIQUE,
                           password VARCHAR(100) NOT NULL,
                           email    VARCHAR(50) NOT NULL UNIQUE,
                           name     NVARCHAR(50) NOT NULL,
                           surname  NVARCHAR(50) NOT NULL,
                           country  NVARCHAR(50),
                           CONSTRAINT PK_users PRIMARY KEY (id)
);


CREATE TABLE dbo.roles (
                           id      BIGINT IDENTITY,
                           user_id BIGINT NOT NULL,
                           role    VARCHAR(50) NOT NULL,
                           CONSTRAINT PK_roles PRIMARY KEY (id),
                           CONSTRAINT roles_users_id_fk FOREIGN KEY (user_id) REFERENCES dbo.users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

