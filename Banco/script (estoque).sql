/**
* Projeto de um sistema para gestão de estoque
* @author Allan Gomes
* @version 1.3 
*/

show databases;
create database dbestoque;
use dbestoque;

/**
* Login
*/
-- unique não permite valores duplicados/igual
create table usuarios (
id int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null unique,
senha varchar(250) not null,
perfil varchar(50) not null
);

alter table usuarios modify column login varchar(100);
create table fornecedores (
idFor int primary key auto_increment,
razaoSocial varchar(50) not null,
fantasia varchar(50) not null,
cnpj varchar(20) unique,
ie varchar(20) unique,
cep varchar(10) not null,
endereco varchar(50) not null,
numero varchar(6) not null,
complemento varchar(20),
bairro varchar(50) not null,
cidade varchar(50) not null,
uf char(2) not null,
nomeContato varchar(30) not null,
fone1 varchar(15) not null,
fone2 varchar(15),
email varchar(50) not null,
site varchar(50),
obs varchar(250)
);

describe usuarios;
drop table usuarios;
drop table fornecedores;
drop table clientes;


/*********** CRUD usuarios ***********/

-- CREATE (inserir 5 usuarios)
insert into usuarios (usuario,login,senha,perfil)
values ('Allan Gomes','Aln',md5('12345'),'admin');
insert into usuarios (usuario,login,senha,perfil)
values ('Leonardo','Leo',md5('23456'),'user');
insert into usuarios (usuario,login,senha,perfil)
values ('daleste','daleste',md5('34567'),'user');
insert into usuarios (usuario,login,senha,perfil)
values ('Mosquito','Mosquito',md5('45678'),'user');
insert into usuarios (usuario,login,senha,perfil)
values ('Vitao','Vitor',md5('56789'),'user');

/*********** FIM CRUD usuarios ***********/

/*********** CRUD fornecedores ***********/

insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone1, fone2, email, site, obs) values ('Kalunga', 'Kalunga', '43.283.811/0059-76', '206292929110', '70711-000', 'Rua dos Kalungas', '1000', '', 'Distrito Asa Norte', 'Brasilia', 'DF', 'Luiz Carlos', '1234-5678', '1234-0000', 'luiz.carlos@kalunga.com.br', 'www.kalunga.com.br', 'Pode pá');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone1, fone2, email, site, obs) values ('Kalango', 'Kalango', '44.283.811/0059-76', '207292929110', '70712-000', 'Rua dos Calangos', '2000', '', 'Calango do Norte', 'Amapá', 'AP', 'João Pedro', '1111-0000', '2222-0000', 'joao.pedro@kalango.com.br', 'www.kalango.com.br', 'Calanguinho');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone1, fone2, email, site, obs) values ('Kabum', 'Kabum', '45.283.811/0059-76', '207292929111', '70713-000', 'Rua do Kabum', '3000', '', 'Cambuquira', 'Londrina', 'PR', 'Maria do Carmo', '3333-0000', '4444-0000', 'maria.carmo@kabum.com.br', 'www.kabum.com.br', 'Explosao');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone1, fone2, email, site, obs) values ('Furacão', 'Furacao', '46.283.811/0059-76', '206292929112', '80711-000', 'Rua das Explosões', '4000', '', 'Explosivo', 'Blumenau', 'SC', 'Carlos Magno', '5555-0000', '6666-0000', 'carlos.magno@furacao.com.br', 'www.furacao.com.br', 'Ventania');
insert into fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone1, fone2, email, site, obs) values ('Fusca', 'Fusca', '47.283.811/0059-76', '206292929113', '90711-000', 'Rua dos carros', '5000', '', 'Fuscão Preto', 'Rocinha', 'RJ', 'Zé Pequeno', '7777-0000', '8888-0000', 'ze.pequeno@fusca.com.br', 'www.fusca.com.br', 'Vrum');

-- Selecionar todos os Fornecedores
select * from fornecedores;

-- pesquisa avançada 
select idFor, fantasia, fone1, fone2, nomeContato from fornecedores where fantasia like ('k%');

/* (Aula 18)(Anotação)
Relacionamento de tabelas 1 - N (um para muitos)
Chave estrangeira (FK) - (PK)
idFor (chave estrangeira) usar mesmo nome e tipo de dados da chave primaria (PK) da tabela pai
 */
 
 -- timestamp default current_timestamp (obtém automaticamente a data e hora)
 -- date (tipo de dados relacionado a data)
 -- decimal(10,2) (tipo de dados relacionados a números não inteiros)
 -- decimal(10,2) (10 digitos com 2 casas decimais)
 create table produtos ( 
 codigo int primary key auto_increment,
 barcode varchar(255) unique,
 produto varchar(50) not null,
 descricao varchar(255),
 fabricante varchar(50)not null,
 datacad timestamp default current_timestamp,
 dataval date,
 estoque int not null,
 estoquemin int not null,
 unidade char(2) not null,
 localizacao varchar(50) not null,
 custo decimal(10,2) not null,
 lucro decimal(10,2),
 idFor int not null,
 foreign key (idFor) references fornecedores(idFor) -- comando que linka uma na outra/Relacionamento (PRESERVAR INTEGRIDADE DOS DADOS/Segurança, Não deixar produto sem fornecedor vise versa)
 );
 
 -- Descrever produtos
describe produtos;
 
 /* Fim */

-- CRUD Update
update usuarios set usuario = 'teste1', login = 'teste1', senha = md5('teste1'), perfil = 'user' where id = 20;
update fornecedores set razaoSocial = 'teste1', fantasia = 'teste1', cnpj = 'teste1', ie = 'teste1', cep = 'teste1', endereco = 'teste1', numero = 'teste1', complemento = 'teste1', bairro = 'teste1', cidade = 'teste1', uf = 'teste1', nomeContato = 'teste1', fone1 = 'teste1', fone2 = 'teste1', email = 'teste1', site = 'teste1', obs = 'teste1' where id = 20;

/********** Fim fornecedores ***********/

-- Criando Tabela clientes --
create table clientes (
idFor int primary key auto_increment,
Nome varchar(50) not null,
CPF varchar(20) unique,
Cep varchar(10) unique,
Endereco varchar(50) not null,
Numero varchar(6) not null,
Complemento varchar(20) not null,
Bairro varchar(50) not null,
Cidade varchar(50),
Uf char(2) not null,
Telefone varchar(15) not null,
Email varchar(50) not null
);
/*********** CRUD fornecedores ***********/

insert into clientes (Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone,  Email) 
values ('Allan','111.111.111-11','11111-11','TESTE','111','Teste','TESTE','São Paulo','SP','11111-1111','Teste@teste.com');
insert into clientes (Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone,  Email) 
values ('Allan','','','','','','','','','','');
insert into clientes (Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone,  Email)  
values ('Allan','','','','','','','','','','');
insert into clientes (Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone,  Email)  
values ('Allan','','','','','','','','','','');
insert into clientes (Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone,  Email) 
values ('Allan','','','','','','','','','','');

-- Selecionar todos os clientes
select * from clientes;

-- pesquisa avançada 
select idFor, Nome, CPF, Cep, Endereco, Numero, Complemento, Bairro, Cidade, Uf, Telefone, Email from clientes where Nome like ('A%');

/********* Começo Clientes *********/



/********* Fim Clientes *********/

-- inserindo uma senha criptografada com md5
insert into usuarios (usuario,login,senha,perfil)
values ('Robson Vaamonde','vava', md5('123456'),'admin');

-- READ1 (selecionar todos os usuarios)
select * from usuarios;

-- login (usuário e senha correspondente)
select * from usuarios where perfil='user';
select * from usuarios where login='Allan Gomes' and senha = md5('12345');

-- READ2 (selecionar 1 usuário especifico por id)
select * from usuarios where id = '?';

-- UPDATE (alterar todos os dados de um usuário em específico)
update usuarios set login = 'Exemplo',senha = 'Exemplo' where id = ?;

-- DELETE (excluir um usuário em especifico)
delete from usuarios where id = 9;

-- Gerar a documentação - Modelo ER (Engenharia reversa)
/**
* Database -> Reverse Enginneer Crtl+R
*/

