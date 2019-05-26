CREATE DATABASE voce_aluga;
Use voce_aluga;

create table Cliente (
Nome varchar(50) not null,
Telefone numeric(11),
Endereco varchar(100),
CEP varchar(100),
Data_Nasc varchar(10),
Lista_Negra boolean,
CNH numeric(15), 
CPF numeric(11),
Email varchar(100),
primary key (CNH) );



create table Reserva (
Avarias varchar(300),
Data_Ent varchar(10) not null,
Data_Ret varchar(10) not null,
ID numeric(6), primary key (ID) );

create table Carro (
Cor varchar(50),
Marca varchar(50),
Ano numeric(4),
KM numeric(6),
Modelo varchar(50) not null,
Placa varchar(8), primary key (Placa) );
