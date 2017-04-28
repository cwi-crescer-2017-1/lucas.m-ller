/*
    --- LISTA DE EXERC�CIOS 1 ---
	 
	    https://goo.gl/WWuymZ
*/

-- Transa��es:
-- begin transaction - inicia a transa��o
-- rollback          - cancela a transa��o
-- commit            - efetiva a transa��o

-- exerc�cio 1:
-- select * into CidadeAux from Cidade

-- exerc�cio 2:
-- truncate table CidadeAux   ou   delete from CidadeAux
-- insert into CidadeAux (IDCidade, Nome, UF) select IDCidade, Nome, UF from Cidade where UF='RS'

-- exerc�cio 3:
/*
create table Produtos (
	IDProduto       int identity not null,
	Nome            varchar(50)  not null,
	NomeDescritivo  varchar(250) not null,
	DataCriacao     date         not null,
	LocalEstoque    varchar(100) not null,
	Quantidade      decimal(7,3) not null,
	Preco           decimal(9,2) not null,
		constraint PK_IDProduto primary key(IDProduto)
)
*/

-- exerc�cio 4:
/*
insert into Produtos (Nome, NomeDescritivo, DataCriacao, LocalEstoque, Quantidade, Preco) values
	('TV Samsung 42"', 'SmartTV 42 polegadas da Samsung com conex�o a Internet', GETDATE(), 'Corredor 5B', 10, 1499.99),
	('Computador Gamer', 'Computador Gamer com Intel i7-7700k, 16GB RAM, HD de 1TB e GTX 1080TI', GETDATE(), 'Corredor 7C', 5, 7999.99)
*/

-- converter string para datetime/date: select convert(datetime, '28/04/2017', 103) as FormatoDateTime
-- formato 103 = dd/mm/aaaa             select convert(date, '28/04/2017', 103)     as FormatoDate