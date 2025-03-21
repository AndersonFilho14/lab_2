use lab_2;
select * from clientes;

INSERT INTO clientes ( nome, email, senha, cpf, telefone, pontos_fidelidade) VALUES
( 'fantasma', '@', 'senha123', '00000000000', NULL, 0),
('Maria Oliveira', 'maria.oliveira@example.com', 'senha123', '98765432100', '11987654321', 1),
( 'pitomba de tereza', 'larita@gmail.com', 'senha123', '12345678901', '11987654321', 4),
( 'ande', 'aso@fam.com', 'abcd', '12345678901', NULL, 0);

INSERT INTO funcionarios (id, nome, cargo, email, senha, cpf, empregado, telefone) VALUES
(1, 'gerente', 3, 'gerente@email.com', 'mercado123', '00000000000', 1, NULL),
(2, 'Marcio De Miro', 2, 'marcio@email.com', 'mercado123', '00000000001', 1, '81'),
(3, 'Seu Miro', 1, 'mironoteueatiro@gmail.com', 'mercado123', '00000000002', 1, '81981818181');

INSERT INTO produtos (id_produtos, nome_produto, categoria_produto, estoque_produto, preco_produto, validade_produto, id_funcionario, prateleira) VALUES
(1, 'paçoca', 'COMIDA', 75, 0.25, '2025-12-31', 1, 1),
(2, 'Sabão em Pó', 'LIMPEZA', 40, 5.00, '2025-06-30', 1, 1),
(3, 'Martelo', 'MATERIAL', 30, 15.00, '2026-03-15', 1, 0),
(4, 'Biscoito treloso de flocos, 120g', 'COMIDA', 40, 1.90, '2025-03-30', 2, 0),
(5, 'Gelatina sem açúcar', 'COMIDA', 30, 3.59, '2025-04-20', 1, 1),
(6, 'Carregador de celular', 'MATERIAL', 37, 38.00, '2050-10-10', 3, 1),
(8, 'Rapadura', 'COMIDA', 60, 15.90, '2025-12-30', 3, 1),
(9, 'Monitor', 'MATERIAL', 12, 500.00, '2035-07-23', 3, 1),
(10, 'Amaciante', 'LIMPEZA', 60, 35.80, '2028-04-17', 3, 1),
(11, 'Porta copo', 'MATERIAL', 2, 25.00, '2035-01-01', 2, 1),
(12, 'Ps4', 'MATERIAL', 55, 1000.00, '2025-04-01', 2, 0);
