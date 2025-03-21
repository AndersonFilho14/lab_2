-- Criação do banco de dados (opcional)
CREATE DATABASE IF NOT EXISTS lab_2;
USE lab_2;

-- Remover tabelas caso já existam
DROP TABLE IF EXISTS financeiro;
DROP TABLE IF EXISTS produtos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS funcionarios;

-- Criar tabela de funcionários
CREATE TABLE `funcionarios` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `nome` varchar(45) NOT NULL,
   `cargo` int(11) NOT NULL,
   `email` varchar(45) NOT NULL,
   `senha` varchar(45) NOT NULL,
   `cpf` varchar(11) NOT NULL,
   `empregado` tinyint(1) NOT NULL DEFAULT 1,
   `telefone` varchar(15) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `cpf_UNIQUE` (`cpf`),
   UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Criar tabela de produtos
CREATE TABLE `produtos` (
   `id_produtos` INT(11) NOT NULL AUTO_INCREMENT,
   `nome_produto` VARCHAR(45) NOT NULL,
   `categoria_produto` VARCHAR(45) NOT NULL,
   `estoque_produto` INT(11) NOT NULL,
   `preco_produto` DECIMAL(10,2) NOT NULL,
   `validade_produto` DATE NOT NULL,
   `id_funcionario` INT(11) NOT NULL,
   `prateleira` TINYINT(1) DEFAULT 1,
   PRIMARY KEY (`id_produtos`),
   KEY `id_funcionario` (`id_funcionario`),
   CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Criar tabela de clientes
CREATE TABLE `clientes` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `nome` varchar(255) NOT NULL,
   `email` varchar(100) NOT NULL,
   `senha` varchar(255) NOT NULL,
   `cpf` char(11) NOT NULL,
   `telefone` varchar(15) DEFAULT NULL,
   `pontos_fidelidade` int(11) DEFAULT 0,
   PRIMARY KEY (`id`),
   UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- Criar tabela financeira
CREATE TABLE `financeiro` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `valor_compra` decimal(10,2) NOT NULL,
   `id_cliente` int(11) NOT NULL,
   `data_compra` timestamp NOT NULL DEFAULT current_timestamp(),
   PRIMARY KEY (`id`),
   KEY `id_cliente` (`id_cliente`),
   CONSTRAINT `financeiro_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
