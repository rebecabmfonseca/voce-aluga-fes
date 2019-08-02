-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql10.freemysqlhosting.net
-- Tempo de geração: 02/08/2019 às 11:37
-- Versão do servidor: 5.5.58-0ubuntu0.14.04.1
-- Versão do PHP: 7.0.33-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `sql10292127`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `Carro`
--

CREATE TABLE `Carro` (
  `Cor` varchar(50) DEFAULT NULL,
  `Marca` varchar(50) DEFAULT NULL,
  `Ano` decimal(4,0) DEFAULT NULL,
  `KM` decimal(6,0) DEFAULT NULL,
  `Modelo` varchar(50) NOT NULL,
  `Placa` varchar(8) NOT NULL DEFAULT '',
  `Grupo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Carro`
--

INSERT INTO `Carro` (`Cor`, `Marca`, `Ano`, `KM`, `Modelo`, `Placa`, `Grupo`) VALUES
('AZUL', 'FIAT', '2015', '50000', 'PALIO', 'ABC123', 'B'),
('VERDE', 'VW', '2012', '6000', 'CARRO NEM TAO NOVO', 'AME 9893', 'CLEAN'),
('AMARELO', 'PALIO', '2009', '12000', 'LEGAL', 'AWS5689', 'E'),
('BRANCO', 'MARCA', '2019', '1000', 'MODELO', 'BGV123', 'A'),
('BRANCO', 'BMW', '2015', '7001', 'CARRO LUXO', 'NNN 9893', 'A');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Cliente`
--

CREATE TABLE `Cliente` (
  `Nome` varchar(50) NOT NULL,
  `Telefone` decimal(11,0) DEFAULT NULL,
  `Endereco` varchar(100) DEFAULT NULL,
  `CEP` varchar(100) DEFAULT NULL,
  `Data_Nasc` varchar(10) DEFAULT NULL,
  `Lista_Negra` tinyint(1) DEFAULT NULL,
  `CNH` decimal(9,0) NOT NULL DEFAULT '0',
  `CPF` decimal(11,0) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Cliente`
--

INSERT INTO `Cliente` (`Nome`, `Telefone`, `Endereco`, `CEP`, `Data_Nasc`, `Lista_Negra`, `CNH`, `CPF`, `Email`) VALUES
('MARIA', '21212121212', 'RUA DAS PALMEIRAS, 21, RIO DE JANEIRO', '20010120', '9/7/2019', 0, '12121212', '12121212122', 'exemplo@teste.com'),
('REBECA FONSECA', '21212121211', 'RUA DAS DORES, 21,  RIO DE JANEIRO', '20751390', '9/7/1994', 0, '123333331', '13423456787', 'teste_email@teste.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `EstMan`
--

CREATE TABLE `EstMan` (
  `Hora_Func` varchar(5) DEFAULT NULL,
  `Endereco_1` varchar(50) NOT NULL,
  `Endereco_2` varchar(50) DEFAULT NULL,
  `ID` decimal(6,0) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Filial`
--

CREATE TABLE `Filial` (
  `Hora_Func` varchar(5) DEFAULT NULL,
  `Endereco_1` varchar(50) NOT NULL,
  `Endereco_2` varchar(50) DEFAULT NULL,
  `ID` decimal(6,0) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `Financeiro`
--

CREATE TABLE `Financeiro` (
  `ID_Financeiro` int(11) NOT NULL,
  `ID_Reserva` int(11) DEFAULT NULL,
  `Forma_Pagamento` varchar(50) DEFAULT NULL,
  `valor` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Financeiro`
--

INSERT INTO `Financeiro` (`ID_Financeiro`, `ID_Reserva`, `Forma_Pagamento`, `valor`) VALUES
(3, 1221, 'Dinheiro', '600.0'),
(5, 1221, 'Cartão de crédito', '890.0'),
(6, 1221, 'Cartão de crédito', '230.0');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Manutencao`
--

CREATE TABLE `Manutencao` (
  `ID_Manutencao` int(11) NOT NULL,
  `Data_Inicio` varchar(50) DEFAULT NULL,
  `Data_Previsao` varchar(50) DEFAULT NULL,
  `Placa` varchar(8) DEFAULT NULL,
  `Descricao` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Manutencao`
--

INSERT INTO `Manutencao` (`ID_Manutencao`, `Data_Inicio`, `Data_Previsao`, `Placa`, `Descricao`) VALUES
(1, '2019-07-01', '2019-07-09', 'AME 9893', 'troca de amortecedor'),
(2, '2019-07-09', '2019-07-10', 'NNN 9893', 'lavar carro'),
(5, '2019-07-01', '2019-07-03', 'NNN 9893', 'Troca de pneu');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Reserva`
--

CREATE TABLE `Reserva` (
  `Avarias` varchar(300) DEFAULT NULL,
  `Data_Ent` varchar(10) NOT NULL,
  `Data_Ret` varchar(10) NOT NULL,
  `ID` int(11) NOT NULL,
  `Placa` varchar(8) NOT NULL,
  `CNH` decimal(9,0) NOT NULL,
  `Apolice` decimal(6,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `Reserva`
--

INSERT INTO `Reserva` (`Avarias`, `Data_Ent`, `Data_Ret`, `ID`, `Placa`, `CNH`, `Apolice`) VALUES
('arranhão no parachoque', '02/05/2019', '02/06/2019', 1221, 'ABC123', '123333331', '21'),
(NULL, '02/02/2019', '02/03/2019', 1234, 'ABC123', '123333331', '123456'),
('farol quebrado', '02/02/2019', '02/03/2019', 1235, 'ABC123', '123333331', '1221'),
(NULL, '26/6/2019', '12/6/2019', 1236, 'ABC123', '123333331', '12345'),
(NULL, '2/8/2019', '8/7/2019', 1238, 'ABC123', '123333331', '123123'),
(NULL, '9/7/2019', '1/7/2019', 1239, 'ABC123', '123333331', '121212'),
('Pneu furado.', '26/7/2019', '30/6/2019', 1240, 'ABC123', '123333331', '902382'),
('Pneu furado.', '26/7/2019', '30/6/2019', 1241, 'ABC123', '123333331', '455567'),
('Pneu furado.', '1/8/2019', '8/7/2019', 1242, 'ABC123', '123333331', '245233'),
('Sem cinto de segurança.', '25/7/2019', '30/6/2019', 1243, 'ABC123', '12121212', '123131');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Serviços`
--

CREATE TABLE `Serviços` (
  `ID` decimal(6,0) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `Carro`
--
ALTER TABLE `Carro`
  ADD PRIMARY KEY (`Placa`);

--
-- Índices de tabela `Cliente`
--
ALTER TABLE `Cliente`
  ADD PRIMARY KEY (`CNH`);

--
-- Índices de tabela `EstMan`
--
ALTER TABLE `EstMan`
  ADD PRIMARY KEY (`ID`);

--
-- Índices de tabela `Filial`
--
ALTER TABLE `Filial`
  ADD PRIMARY KEY (`ID`);

--
-- Índices de tabela `Financeiro`
--
ALTER TABLE `Financeiro`
  ADD PRIMARY KEY (`ID_Financeiro`),
  ADD KEY `ID_Reserva` (`ID_Reserva`);

--
-- Índices de tabela `Manutencao`
--
ALTER TABLE `Manutencao`
  ADD PRIMARY KEY (`ID_Manutencao`),
  ADD KEY `Placa` (`Placa`);

--
-- Índices de tabela `Reserva`
--
ALTER TABLE `Reserva`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Placa` (`Placa`),
  ADD KEY `CNH` (`CNH`);

--
-- Índices de tabela `Serviços`
--
ALTER TABLE `Serviços`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `Financeiro`
--
ALTER TABLE `Financeiro`
  MODIFY `ID_Financeiro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de tabela `Manutencao`
--
ALTER TABLE `Manutencao`
  MODIFY `ID_Manutencao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de tabela `Reserva`
--
ALTER TABLE `Reserva`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1244;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `Financeiro`
--
ALTER TABLE `Financeiro`
  ADD CONSTRAINT `Financeiro_ibfk_1` FOREIGN KEY (`ID_Reserva`) REFERENCES `Reserva` (`ID`);

--
-- Restrições para tabelas `Manutencao`
--
ALTER TABLE `Manutencao`
  ADD CONSTRAINT `Manutencao_ibfk_1` FOREIGN KEY (`Placa`) REFERENCES `Carro` (`Placa`);

--
-- Restrições para tabelas `Reserva`
--
ALTER TABLE `Reserva`
  ADD CONSTRAINT `Reserva_ibfk_1` FOREIGN KEY (`Placa`) REFERENCES `Carro` (`Placa`),
  ADD CONSTRAINT `Reserva_ibfk_2` FOREIGN KEY (`CNH`) REFERENCES `Cliente` (`CNH`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
