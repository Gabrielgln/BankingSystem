CREATE TABLE client (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    address_id BINARY(16) NOT NULL,
    CONSTRAINT client_address_id_fkey FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE RESTRICT ON UPDATE CASCADE
);