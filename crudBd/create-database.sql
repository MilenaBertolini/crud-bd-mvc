create database ContatosCRUD;
use ContatosCRUD;

create table Contatos (
id int primary key,
inputNome varchar(255),
inputEmail varchar(255) unique,
inputEndereco varchar(255),
inputTelefone varchar(50)
);