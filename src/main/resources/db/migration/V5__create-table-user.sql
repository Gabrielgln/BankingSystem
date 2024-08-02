CREATE TABLE user
(
     id BINARY(16) NOT NULL PRIMARY KEY,
     email VARCHAR(255) NOT NULL,
     password VARCHAR(255) NOT NULL,
     role VARCHAR(255) NOT NULL,
     client_id BINARY(16),
     CONSTRAINT user_client_id_fkey FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE RESTRICT ON UPDATE CASCADE
);