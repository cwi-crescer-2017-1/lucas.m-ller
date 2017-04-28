/*
    --- LISTA DE EXERC�CIOS 2 ---
	 
	    https://goo.gl/pzpfQd
*/

-- Transa��es:
-- begin transaction - inicia a transa��o
-- rollback          - cancela a transa��o
-- commit            - efetiva a transa��o

-- exerc�cio 1:
-- select IDEmpregado, NomeEmpregado from Empregado order by DataAdmissao;

-- exerc�cio 2:
-- select * from Empregado where Comissao is null order by Salario;

-- exerc�cio 3:
/*
select IDEmpregado,
       NomeEmpregado, 
       (Salario*13)  as SalarioAnual,
	   (ISNULL(Comissao,0)*12) as ComissaoAnual,
	   ((Salario*13) + (ISNULL(Comissao,0)*12)) as RendaAnual
	   from Empregado
*/

-- exerc�cio 4:
/*
select IDEmpregado, NomeEmpregado, Cargo, Salario 
	   from Empregado
	   where (Salario*13) < 18500 or Cargo = 'Atendente'
*/