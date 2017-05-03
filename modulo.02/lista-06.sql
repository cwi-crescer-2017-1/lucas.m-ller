/*
   --- LISTA DE EXERC�CIOS 06 ---

       https://goo.gl/cZm1nF
*/

/* exerc�cio 01:
   Lista qual o primeiro nome mais popular entre os clientes, considere apenas o primeiro nome.
*/
select TOP 1 SUBSTRING(Nome, 1, CHARINDEX(' ', Nome) - 1) AS PrimeiroNome,
       COUNT(1) AS NumOcorrencias 
from Cliente
group by SUBSTRING(Nome, 1, CHARINDEX(' ', Nome) - 1)
order by NumOcorrencias desc, PrimeiroNome;

/* exerc�cio 02:
   Liste o total de pedidos (quantidade e valor) realizados no m�s de abril/2017.
*/
select COUNT(1) as QuantPedidos, 
       SUM(ped.ValorPedido) as ValorTotal
from Pedido ped
where DATEPART(year, ped.DataPedido) = 2017 and DATEPART(month, ped.DataPedido) = 4;

/* exerc�cio 03:
   Identifique qual o estado (coluna UF da tabela Cidade) possu� o maior n�mero de clientes (tabela Cliente), 
   liste tamb�m qual o Estado possu� o menor n�mero de clientes.
*/
(select * from 
	(select TOP 1 cid.UF, COUNT(1) as QuantClientes
	from Cidade cid
	join Cliente cli on cli.IDCidade = cid.IDCidade
	group by cid.UF
	order by QuantClientes desc) as MaisClientes
union
select * from 
	(select TOP 1 cid.UF, COUNT(1) as QuantClientes
	from Cidade cid
	join Cliente cli on cli.IDCidade = cid.IDCidade
	group by cid.UF
	order by QuantClientes asc) as MenosClientes);

/* exerc�cio 04:
   Crie (insira) um novo registro na tabela de Produto, com as seguintes informa��es:
		Nome: Galocha Maragato
		Pre�o de custo: 35.67
		Pre�o de venda: 77.95
		Situa��o: A
*/
insert into Produto (Nome, PrecoCusto, PrecoVenda, Situacao) values
	('Galocha Maragato', 35.67, 77.95, 'A');

/* exerc�cio 05:
   Identifique e liste os produtos que n�o tiveram nenhum pedido, considere os relacionamentos 
   no modelo de dados, pois n�o h� relacionamento direto 
   entre Produto e Pedido (ser� preciso relacionar PedidoItem).

   => Obs.: o produto criado anteriormente dever� ser listado (apenas este)
*/
(select * from Produto
where IDProduto NOT IN 
	(select prod.IDProduto from Produto prod
	join PedidoItem item on item.IDProduto = prod.IDProduto
	group by prod.IDProduto));

/* exerc�cio 06:
   Liste os 30 produtos que mais geraram lucro em 2016.
*/
(select * from Produto
where IDProduto IN 
	(select TOP 30 prod.IDProduto
	from PedidoItem item
	join Produto prod on item.IDProduto = prod.IDProduto
	join Pedido ped on ped.IDPedido = item.IDPedido
	where DATEPART(year, ped.DataPedido) = 2016
	group by prod.IDProduto
	order by SUM((prod.PrecoVenda - prod.PrecoCusto) * item.Quantidade) desc));