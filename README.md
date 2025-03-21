# 🛒 Sistema de Gerenciamento de Supermercado

Este projeto é um **Sistema de Gerenciamento de Supermercado**, desenvolvido em **Java**, utilizando o modelo **MVC (Model-View-Controller)** e acesso a banco de dados com o padrão **DAO (Data Access Object)**. O objetivo é permitir a gestão eficiente de clientes, funcionários, produtos e transações financeiras.

## 🎥 Vídeo do Projeto

Assista ao vídeo do projeto no YouTube:

[Vídeo do Projeto](https://youtu.be/UKCrNs8v1kk)

## 🚀 Funcionalidades

### 📌 **Clientes**
✅ Cadastro e autenticação  
✅ Acúmulo e resgate de pontos de fidelidade  
✅ Consulta de histórico de compras  
✅ Atualização de informações pessoais  

### 📌 **Funcionários**
✅ Cadastro, atualização e remoção de funcionários  
✅ Controle de cargos e status de emprego  
✅ Gerenciamento de estoque e reposição de produtos  
✅ Autenticação e controle de permissões  

### 📌 **Produtos**
✅ Cadastro, atualização e remoção de produtos  
✅ Controle de validade e disponibilidade em prateleira  
✅ Consulta e listagem de produtos por nome e categoria  

### 📌 **Carrinho de Compras**
✅ Adição, remoção e atualização de produtos no carrinho  
✅ Cálculo do valor total da compra  
✅ Finalização e registro da compra no sistema financeiro  

### 📌 **Financeiro**
✅ Registro e listagem de compras realizadas  
✅ Relatórios financeiros diários e gerais  
✅ Controle do fluxo de caixa do supermercado  

---

## 🛠️ Tecnologias Utilizadas

- **Java** (Programação Orientada a Objetos)
- **MySQL** (Banco de Dados Relacional)
- **JDBC** (Conexão com Banco de Dados)
- **MVC** (Model-View-Controller)
- **DAO** (Data Access Object)

---

## 🏗️ Estrutura do Projeto

Estrutura do projeto:

```plaintext
lab_2/
 ├── src/
 │   ├── main.java
 │   ├── Controller/
 │   │    ├── CarrinhoController.java
 │   │    ├── ClienteController.java
 │   │    ├── FinanceiroController.java
 │   │    ├── FuncionarioController.java
 │   │    └── ProdutoController.java
 │   ├── Model/
 │   │    ├── Dao/
 │   │    │   ├── ClienteDao.java
 │   │    │   ├── DAO.java
 │   │    │   ├── FinanceiroDao.java
 │   │    │   ├── FuncionarioDao.java
 │   │    │   └── ProdutoDao.java
 │   │    ├── Database/
 │   │    │   └── ConnectionFactory
 │   │    ├── Carrinho.java
 │   │    ├── Categoria.java
 │   │    ├── Cliente.java
 │   │    ├── Financeiro.java
 │   │    ├── Funcionario.java
 │   │    ├── Pessoa.java
 │   │    └── Produto.java
 │   └── View/
 │        ├── ClienteView.java
 │        ├── CompraView.java
 │        ├── FuncionarioView.java
 │        ├── LoginView.java
 │        └── ProdutoView.java
```
---

## 🏦 Banco de Dados

Para criar as tabelas do banco de dados, utilize os scripts SQL abaixo:

### **📌 Tabela `clientes`**
```sql
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
```

### **📌 Tabela `funcionarios`**
```sql
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

### **📌 Tabela `produtos`**
```sql
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
```

### **📌 Tabela `financeiro`**
```sql
CREATE TABLE `financeiro` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `valor_compra` decimal(10,2) NOT NULL,
   `id_cliente` int(11) NOT NULL,
   `data_compra` timestamp NOT NULL DEFAULT current_timestamp(),
   PRIMARY KEY (`id`),
   KEY `id_cliente` (`id_cliente`),
   CONSTRAINT `financeiro_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

---
## ⚙️ Como Executar o Projeto

### 🔧 **Pré-requisitos**

* Java 8 ou superior
* MySQL instalado e configurado
* IDE (Eclipse, IntelliJ, VS Code, etc.)

### 🚀 **Passos para rodar**

1. Clone o repositório:
    ```sh
    git clone https://github.com/AndersonFilho14/lab_2
    ```

2. Configure o banco de dados MySQL utilizando os scripts acima.
3. Atualize as configurações de conexão no `ConnectionFactory.java`.
4. Compile e execute a aplicação.

---

## 📌 UML do Projeto

### **Diagrama de Classes**
Está no arquivo '.png'

---

## 📝 Autores

* **[ANDERSON FILHO]** - [GitHub](https://github.com/AndersonFilho14)