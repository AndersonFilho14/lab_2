# Lembrete para os Desenvolvedores

Toda alteração deve ser feita na branch `develop`. Não se esqueça de **comitar** no final das suas alterações.

## Como Clonar o Repositório

```bash
git clone https://github.com/AndersonFilho14/lab_2.git

```
Mudar para a Branch `develop`

```bash
git checkout develop
```

Agora você pode começar a codar...

#Mercado 
Descrição: Um sistema de gerenciamento para um supermercado.

## Criação das Tabelas no Banco de Dados

### Tabela `clientes`

## Para criar o banco de dados 

TAB clientes:
```sql
CREATE TABLE clientes (
   id int(11) NOT NULL AUTO_INCREMENT,
   nome varchar(255) NOT NULL,
   email varchar(100) NOT NULL,
   senha varchar(255) NOT NULL,
   cpf char(11) NOT NULL,
   telefone varchar(15) DEFAULT NULL,
   pontos_fidelidade int(11) DEFAULT 0,
   PRIMARY KEY (id),
   UNIQUE KEY unique_email (email)
 ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
```

Tab funcionarios:
```sql
CREATE TABLE `funcionarios` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `nome` varchar(45) NOT NULL,
   `cargo` int(11) NOT NULL,
   `email` varchar(45) NOT NULL,
   `senha` varchar(45) NOT NULL,
   `cpf` varchar(11) NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `cpf_UNIQUE` (`cpf`),
   UNIQUE KEY `email_UNIQUE` (`email`)
 ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
```

TAB produtos:
```sql
CREATE TABLE `produtos` (
   `id_produtos` int(11) NOT NULL AUTO_INCREMENT,
   `nome_produto` varchar(45) NOT NULL,
   `categoria_produto` varchar(45) NOT NULL,
   `estoque_produto` int(11) NOT NULL,
   `preco_produto` decimal(10,2) NOT NULL,
   `validade_produto` date NOT NULL,
   `id_funcionario` int(11) NOT NULL,
   PRIMARY KEY (`id_produtos`)
 ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
```

## Mapa das Classes

Estrutura do projeto:

```plaintext
lab_2/
 ├── src/
 │   ├── main.java
 │   └── Controller/
 │   │    ├── ClienteController.java
 │   │    └── FuncionarioController.java
 │   ├── Model/
 │   │    ├── Dao/
 │   │    │   ├── ClienteDao.java
 │   │    │   └── FuncionarioDao.java
 │   │    ├── Database/
 │   │    │   └── ConnectionFactory
 │   │    ├── Cliente.java
 │   │    ├── Funcionario.java
 │   │    └── Pessoa.java
 │   └── View/
 │        ├── ClienteView.java
 │        └── FuncionarioView.java
 │        └── LoginView.java