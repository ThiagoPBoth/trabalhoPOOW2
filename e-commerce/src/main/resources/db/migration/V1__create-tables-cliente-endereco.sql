CREATE TABLE endereco (
                          id BIGSERIAL PRIMARY KEY,
                            uuid UUID not null,
                          cep VARCHAR(10) NOT NULL,
                          rua VARCHAR(100) NOT NULL,
                          numero INT NOT NULL,
                          bairro VARCHAR(50) NOT NULL,
                          cidade VARCHAR(50) NOT NULL,
                          estado VARCHAR(2) NOT NULL
);


CREATE TABLE cliente (
                         id BIGSERIAL PRIMARY KEY,
                         uuid UUID not null,
                         fk_id_endereco BIGINT,
                         nome VARCHAR(100) NOT NULL,
                         cpf VARCHAR(14) NOT NULL UNIQUE,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         FOREIGN KEY (fk_id_endereco) REFERENCES endereco(id)
);

CREATE TABLE produto (
                         id BIGSERIAL PRIMARY KEY,
                         uuid UUID not null,
                         nome VARCHAR(100) NOT NULL,
                         quantidade INT NOT NULL,
                         valor FLOAT NOT NULL
);

CREATE TABLE compra (
                         id BIGSERIAL PRIMARY KEY,
                         uuid UUID not null,
                         fk_id_cliente BIGINT,
                         valor FLOAT NOT NULL,
                         FOREIGN KEY (fk_id_cliente) REFERENCES cliente(id)
);

CREATE TABLE item_compra (
                             id BIGSERIAL PRIMARY KEY,
                             uuid UUID not null,
                             fk_id_produto BIGINT NOT NULL,
                             fk_id_compra BIGINT NOT NULL,
                             quantidade INT NOT NULL,
                             valor_unitario FLOAT NOT NULL,
                             CONSTRAINT fk_produto FOREIGN KEY (fk_id_produto) REFERENCES produto(id) ON DELETE RESTRICT,
                             CONSTRAINT fk_compra FOREIGN KEY (fk_id_compra) REFERENCES compra(id) ON DELETE CASCADE
);