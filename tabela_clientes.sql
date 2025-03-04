
CREATE TABLE lab_2.produtos (
  id_produtos INT NOT NULL AUTO_INCREMENT,
  nome_produto VARCHAR(45) NOT NULL,
  categoria_produto VARCHAR(45) NOT NULL,
  estoque_produto INT NOT NULL,
  preco_produto DECIMAL(10,2) NOT NULL,
  validade_produto DATE NOT NULL,
  PRIMARY KEY (id_produtos)
);

INSERT INTO lab_2.produtos (nome_produto, categoria_produto, estoque_produto, preco_produto, validade_produto)
VALUES
  ('Paçoca', 'COMIDA', 100, 1.50, '2025-12-31'),
  ('Sabão em Pó', 'LIMPEZA', 50, 5.00, '2025-06-30'),
  ('Martelo', 'MATERIAL', 30, 15.00, '2026-03-15');
