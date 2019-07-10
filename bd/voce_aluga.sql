CREATE DATABASE voce_aluga;
Use voce_aluga;

create table Cliente (
Nome varchar(50) not null,
Telefone numeric(11,0),
Endereco varchar(100),
CEP varchar(100),
Data_Nasc varchar(10),
Lista_Negra boolean,
CNH numeric(9,0), 
CPF numeric(11),
Email varchar(100),
primary key (CNH) );

create table Carro (
Cor varchar(50),
Marca varchar(50),
Ano numeric(4),
KM numeric(6),
Modelo varchar(50) not null,
Placa varchar(8), primary key (Placa) );

create table Reserva (
Avarias varchar(300),
Data_Ent varchar(10) not null,
Data_Ret varchar(10) not null,
ID numeric(6,0),
Placa varchar(8) not null,
CNH numeric(9,0) not null,
Apólice numeric(6,0) not null,
primary key (ID), 
foreign key (Placa) references Carro(Placa), 
foreign key (CNH) references Cliente(CNH) );

create table Serviços (
ID numeric(6,0),
primary key (ID) );

create table EstMan (
Hora_Func varchar(5),
Endereco_1 varchar(50) not null,
Endereco_2 varchar(50),
ID numeric(6,0), primary key (ID) );

create table Filial (
Hora_Func varchar(5),
Endereco_1 varchar(50) not null,
Endereco_2 varchar(50),
ID numeric(6,0), primary key (ID) );

create table Financeiro(
    ID_Financeiro INT(11) AUTO_INCREMENT,
    ID_Reserva INT(11),
    Forma_Pagamento varchar(50),
    valor varchar(30),
    primary key(ID_Financeiro),
    foreign key (ID_Reserva) references Reserva(ID)
);

