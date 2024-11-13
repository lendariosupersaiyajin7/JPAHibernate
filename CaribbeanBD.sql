CREATE DATABASE Design;

USE Design;

CREATE TABLE Pessoa (
    Id_Pessoa INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Pessoa VARCHAR(100) NOT NULL,
    Email_Pessoa VARCHAR(100) NOT NULL UNIQUE,
    Senha_Pessoa VARCHAR(255) NOT NULL,
    Cpf_Pessoa VARCHAR(14) NOT NULL UNIQUE
);

CREATE TABLE Cliente (
    Id_Cliente INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Cliente VARCHAR(100) NOT NULL,
    Email_Cliente VARCHAR(100) NOT NULL UNIQUE,
    Senha_Cliente VARCHAR(255) NOT NULL,
    Cpf_Cliente VARCHAR(14) NOT NULL UNIQUE
);

INSERT INTO Cliente ( Nome_Cliente, Email_Cliente, Senha_Cliente, Cpf_Cliente)
VALUES ( 'João Silva', 'joao.silva@example.com', 'senhaSegura123', '123.456.789-00');
		

CREATE TABLE Vendedor (
    Id_Vendedor INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Vendedor VARCHAR(100) NOT NULL,
    Email_Vendedor VARCHAR(100) NOT NULL UNIQUE,
    Senha_Vendedor VARCHAR(255) NOT NULL,
    Cpf_Vendedor VARCHAR(14) NOT NULL UNIQUE
);

CREATE TABLE Jogos (
    Id_Jogo INT PRIMARY KEY AUTO_INCREMENT,
    Nome_Jogo VARCHAR(100) NOT NULL,
    Descricao TEXT,
    Preco DECIMAL(10, 2) NOT NULL,
    Categoria VARCHAR(50),
    Id_Vendedor INT,
    FOREIGN KEY (Id_Vendedor) REFERENCES Vendedor(Id_Vendedor)
);

CREATE TABLE CarrinhoDeCompras (
    Id_Carrinho INT PRIMARY KEY AUTO_INCREMENT,
    Id_Cliente INT,
    Id_Jogo INT,
    Quantidade INT NOT NULL DEFAULT 1,
    FOREIGN KEY (Id_Cliente) REFERENCES Cliente(Id_Cliente),
    FOREIGN KEY (Id_Jogo) REFERENCES Jogos(Id_Jogo)
);


CREATE TABLE HistoricoDeCompras (
    Id_Historico INT PRIMARY KEY AUTO_INCREMENT,
    Id_Cliente INT,
    Id_Jogo INT,
    DataCompra DATETIME DEFAULT CURRENT_TIMESTAMP,
    PrecoCompra DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (Id_Cliente) REFERENCES Cliente(Id_Cliente),
    FOREIGN KEY (Id_Jogo) REFERENCES Jogos(Id_Jogo)
);

INSERT INTO Cliente ( Nome_Cliente, Email_Cliente, Senha_Cliente, Cpf_Cliente)
VALUES ( 'Marcos Silva', 'marcos.silva@example.com', 'senhaSegura999', '123.956.777-75'),
		('Silvio Luiz', 'silvio.silva@example.com', 'senhaSegura888', '784.659.444-84');
        
        
Insert into CarrinhoDeCompras(Id_Cliente, Id_Jogo, Quantidade)
values (1, 1, 1);

SELECT * FROM Cliente;

SELECT * FROM Vendedor;

SELECT * FROM Jogos;


SELECT * FROM HistoricoDeCompras;

SELECT * FROM CarrinhoDeCompras;


SELECT * FROM Cliente WHERE Id_Cliente = 1;


drop table CarrinhoDeCompras;

drop table Cliente;

Drop database design;

SHOW CREATE TABLE CarrinhoDeCompras;

SHOW CREATE TABLE HistoricoDeCompras;

ALTER TABLE carrinhodecompras DROP COLUMN id;

ALTER TABLE historicodecompras DROP COLUMN id;


