/*
   --- LISTA DE EXERC�CIO 03 ---

       https://goo.gl/y3lpOB
*/

/* exerc�cio 01:
   Fa�a uma consulta que liste o total de empregados admitidos no ano de 1980. 
   Deve ser projetado nesta consulta: ID, Nome e Idade no momento da admiss�o.
*/
select IDEmpregado, NomeEmpregado, DATEDIFF(year, DataNascimento, DataAdmissao) as IdadeQuandoAdmitido
from Empregado
where datepart(year, DataAdmissao) = 1980;

/* exerc�cio 02:
   Qual o cargo (tabela empregado) possui mais empregados ? Deve ser projetado apenas um registro. 
   ** DICA: Pesquise recursos espec�ficos para esta funcionalidade.
*/
select top 1 Cargo, count(1) as Empregados
from Empregado
group by Cargo
order by Empregados desc;

/* exerc�cio 03:
   Liste os estados (atributo UF) e o total de cidades existente em cada estado na tabela cidade.
*/
select UF, count(1) as Cidades
from Cidade
group by UF;

/* exerc�cio 04:
   Crie um novo departamento, denominado 'Inova��o' com localiza��o em 'S�O LEOPOLDO'. 
   Todos os empregados que foram admitidos no m�s de dezembro (qualquer ano) 
   que n�o ocupam o cargo de 'Atendente' devem ser ter o departamento (IDDepartamento) atualizado 
   para este novo registro (inova��o).
*/
BEGIN TRANSACTION

insert into Departamento (IDDepartamento, NomeDepartamento, Localizacao) 
            values (70, 'Inova��o', 'SAO LEOPOLDO');

update Empregado set IDDepartamento = 70
       where Cargo != 'Atendente' and DATEPART(MONTH, DataAdmissao) = 12;

COMMIT
ROLLBACK

select * from Empregado where Cargo != 'Atendente' and DATEPART(MONTH, DataAdmissao) = 12;