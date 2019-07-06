create table Cliente (
Nome varchar(50) not null,
Telefone numeric(11,0),
Endereco_1 varchar(100),
Endereco_2 varchar(100),
Data_Nasc date,
Lista_Negra boolean,
CNH numeric(9,0), primary key (CNH) );

create table Funcionario (
Nome varchar(50) not null,
Telefone numeric(11,0),
Endereco_1 varchar(100),
Endereco_2 varchar(100),
Cargo varchar(50),
Salario numeric(6,0),
ID_Func varchar(6), primary key (ID_Func),
ID_Fil varchar(6), 
foreign key (ID_Fil) references to Filial(ID_Fil) )


create table Carro (
Grupo varchar(5),
Cor varchar(50),
Marca varchar(50),
Ano numeric(4),
KM numeric(6),
Modelo varchar(50) not null,
Placa varchar(8), primary key (Placa) );

create table Reserva (
Avarias varchar(300),
Data_Ent date not null,
Data_Ret date not null,
ID_Res varchar(6),
Pagamento varchar(300),
Placa varchar(8) not null,
CNH numeric(9,0) not null,
Apolice varchar(9) not null,
primary key (ID_Res), 
foreign key (Placa) references Carro(Placa), 
foreign key (CNH) references Cliente(CNH) );

create table Conserto (
ID_servico varchar(6),
ID_Est varchar(6),
Placa varchar(8), 
Data_Ent date, 
Data_Ret date, 
foreign key (ID_Est) references EstMan(ID_Est),
foreign key (Placa) references Carro(Placa) );

create table EstMan (
Hora_Func varchar(5),
Endereco_1 varchar(50) not null,
Endereco_2 varchar(50),
ID_Est varchar(6), primary key (ID_Est) );

create table Filial (
Hora_Func varchar(5),
Endereco_1 varchar(50) not null,
Endereco_2 varchar(50),
ID_Fil varchar(6), primary key (ID_Fil) );

 

insert into Cliente (Nome, Telefone, Endereco_1, Endereco_2, Data_Nasc, Lista_Negra, CNH) 
values (‘Nome’, ‘123456789’, ‘Endereco_1’, ‘Endereco_2’, ‘Data_Nasc’, ‘0’, ‘123456789’)

insert into Funcionario (Nome, Telefone, Endereco_1, Endereco_2, Cargo, Salario, ID_Func, ID, Fil) 
values (‘Nome’, ‘123456789’, ‘Endereco_1’, ‘Endereco_2’, ‘Cargo’, ‘999999,00’, ‘123456’, ‘123456’


insert into Carro (Grupo, Cor, Marca, Ano, KM, Modelo, Placa) 
values (‘Grupo’, ‘Cor’, ‘Marca’, ‘2019’, ‘000000’, ‘Modelo’, ‘Placa’)
 
insert into Reserva (Avarias, Data_Ent, Data_Ret, ID_Res, Pagamento, Placa, CNH, Apolice) 
values (‘Avarias’, ‘Data_Ent’, ‘Data_Ret’, ‘123456’, ‘Pagamento’, ‘Placa’, ‘123456789’, ‘123456789’)
 
insert into Conserto (ID_servico, ID_Est, Placa, Data_Ent, Data_Ret) 
values (‘123456’, ‘123456’, ‘Placa’, ‘Data_Ent’, ‘Data_Ret’)

insert into EstMan (Hora_Func, Endereco_1, Endereco_2, ID_Est) 
values (‘Hora’, ‘Endereco’, ‘Endereco’, ‘123456’)

insert into Filial (Hora_Func, Endereco_1, Endereco_2, ID_Fil) 
values (‘Hora’, ‘Endereco’, ‘Endereco’, ‘123456’)
 

retorna vazio se não há carro disponivel do grupo selecionado. caso haja, retorna tabela com modelo e placa dos carros disponiveis do grupo:
select Modelo, Placa from Carro 
where (Grupo = Grupo2) AND Placa NOT IN 
(select Placa
from Reserva
where (Grupo = Grupo2)
AND
 (Data_Ret2 BETWEEN Data_Ret AND Data_Ent)
AND 
(Data_Ent2 BETWEEN Data_Ret AND Data_Ent))


retorna vazio se não há carro disponivel do modelo especificado. caso haja, retorna tabela com modelo e placa dos carros disponiveis:
select Modelo, Placa from Carro 
where (Modelo = Modelo2) AND Placa NOT IN 
(select Placa
from Reserva
where (Grupo = Grupo2)
AND
 (Data_Ret2 BETWEEN Data_Ret AND Data_Ent)
AND 
(Data_Ent2 BETWEEN Data_Ret AND Data_Ent)
