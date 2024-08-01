CREATE TABLE client (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    address_id VARCHAR(255) NOT NULL,
    account_id VARCHAR(255) NOT NULL,
    CONSTRAINT client_address_id_fkey FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT client_account_id_fkey FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE RESTRICT ON UPDATE CASCADE
);