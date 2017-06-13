/* -- LISTA DE EXERC�CIO 01 -- */

/* 01 - Liste os produtos (id e nome) que n�o tiveram nenhuma compra nos �ltimos quatro meses. */
SELECT IDPRODUTO, NOME FROM PRODUTO
WHERE IDPRODUTO NOT IN (
  SELECT ITEM.IDPRODUTO
  FROM PEDIDOITEM ITEM
  JOIN PEDIDO PED ON PED.IDPEDIDO = ITEM.IDPEDIDO
  WHERE PED.DATAPEDIDO >= ADD_MONTHS(TRUNC(SYSDATE),-4)
  GROUP BY ITEM.IDPRODUTO
)
ORDER BY IDPRODUTO;

/* 02 - Altere o status dos produtos (campo situacao) que n�o tiveram nenhum pedido nos �ltimos quatro meses. */
UPDATE PRODUTO
SET SITUACAO = 'L'
WHERE IDPRODUTO NOT IN (
  SELECT ITEM.IDPRODUTO
  FROM PEDIDOITEM ITEM
  JOIN PEDIDO PED ON PED.IDPEDIDO = ITEM.IDPEDIDO
  WHERE PED.DATAPEDIDO >= ADD_MONTHS(TRUNC(SYSDATE),-4)
  GROUP BY ITEM.IDPRODUTO
);
COMMIT; ROLLBACK;

/* 03 - Fa�a uma consulta que receba um par�metro sendo o IDProduto e liste a quantidade de itens 
        na tabela PedidoItem com este IDProduto foram vendidos no �ltimo ano (desde janeiro/2017). */
SELECT ITEM.IDPRODUTO, COUNT(*) AS QUANTIDADE
FROM PEDIDOITEM ITEM
JOIN PEDIDO PED ON PED.IDPEDIDO = ITEM.IDPEDIDO
WHERE PED.DATAPEDIDO >= TO_DATE('2017-01-01', 'yyyy-mm-dd')
  AND ITEM.IDPRODUTO = :IDPRODUTO
GROUP BY ITEM.IDPRODUTO;