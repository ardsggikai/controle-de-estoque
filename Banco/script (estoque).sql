/**
* Projeto de um sistema para gestão de estoque
* @author Allan Gomes
* @version 1.1 
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

describe usuarios;
drop table usuarios;


/*********** CRUD ***********/

-- CREATE (inserir 5 usuarios)
insert into usuarios (usuario,login,senha,perfil)
values ('Aln','Allan Gomes',md5('12345'),'admin');
insert into usuarios (usuario,login,senha,perfil)
values ('Leo','Leonardo',md5('23456'),'user');
insert into usuarios (usuario,login,senha,perfil)
values ('daleste','daleste',md5('34567'),'user');
insert into usuarios (usuario,login,senha,perfil)
values ('Mosquito','Mosquito',md5('45678'),'user');
insert into usuarios (usuario,login,senha,perfil)
values ('Vitao','Vitor',md5('56789'),'user');

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

