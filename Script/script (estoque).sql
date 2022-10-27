/**
* Projeto de um sistema para gestão de estoque
* @author Allan Gomes
* @version 1.0 
*/

show databases;
create database dbestoque;
use dbestoque;

/**
* Login
*/
create table usuarios (
id int primary key auto_increment,
usuario varchar(50) not null,
login varchar(20) not null,
senha varchar(250) not null
);

alter table usuarios modify column login varchar(100);

describe usuarios;


/*********** CRUD ***********/

-- CREATE (inserir 5 usuarios)
insert into usuarios (usuario,login,senha)
values ('Adm','Allan Gomes','12345');
insert into usuarios (usuario,login,senha)
values ('Galpao','Leonardo','23456');
insert into usuarios (usuario,login,senha)
values ('Galpao','Daleste','34567');
insert into usuarios (usuario,login,senha)
values ('Galpao','Mosquito','45678');
insert into usuarios (usuario,login,senha)
values ('Galpao','Vitor','56789');

-- READ1 (selecionar todos os usuarios)
select * from usuarios;

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

