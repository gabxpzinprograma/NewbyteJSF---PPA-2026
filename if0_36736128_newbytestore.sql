-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: sql206.infinityfree.com
-- Tempo de geração: 19/11/2025 às 04:37
-- Versão do servidor: 11.4.7-MariaDB
-- Versão do PHP: 7.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- CREATE DATABASE if0_36736128_newbytestore;
USE if0_36736128_newbytestore;
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `Aluno`
--

CREATE TABLE `Aluno` (
  `id_aluno` int(11) NOT NULL,
  `nome_aluno` varchar(100) DEFAULT NULL,
  `email_aluno` varchar(100) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `id_curso` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Aluno`
--

INSERT INTO `Aluno` (`id_aluno`, `nome_aluno`, `email_aluno`, `senha`, `id`, `id_curso`) VALUES
(25, 'Emanuel', '12345@12345', '$2y$10$KszVkJnHdQG8T2DYS9WbY.ALzr9F4eTMsEC8tPZIbe1WlJ/NUbmv2', NULL, NULL),
(24, 'Alan', 'alan@gmail.com', '$2y$10$HGmdy4/shiU.b7xa98OuUeOPNL7IVPz4uHygM2mf2G4ibSONhq6Bm', NULL, NULL),
(23, '12345', '12345@12345', '$2y$10$2hBW5xNMubIKFJ7qgEDEjeJPWLqvkHWuuhgBAgHFFNROF3oi5W7Au', NULL, NULL),
(22, 'Gabrieltop', 'gm351802@gmail.com', '$2y$10$xzNbuZqoHAjMudlQ14jMsOlA021WyX.9aS115gTPnT7D5h3IA35gO', NULL, NULL),
(21, '1234', '1234@1234', '$2y$10$z1hhBvBOR1lmgMiC/KoyweM5IkOZ9eXzv9cjiiRCgI4M6YKdsF9yC', NULL, NULL),
(20, 'gab', 'gm351802@gmail.com', '$2y$10$72xYeljLoQGt0FrakYIOoOCcKt72i70Rt1Op59K7yATEWcTgs22a6', NULL, NULL),
(19, '123', '123@123', '$2y$10$DOk9QnLrlgoNcCVUWszVjuT0ezne2rb5CSAZHbUnbpZ4pJqQqy4iq', NULL, NULL),
(26, 'user', 'gm@gm.com', '$2y$10$PyQZFCJuQqa1n1B3PiluzuiEWG9EqaL.grFfw4N0DrJD7nFdMzq5G', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Aluno_Aula`
--

CREATE TABLE `Aluno_Aula` (
  `aluno_id` int(11) NOT NULL,
  `aula_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Aluno_Aula`
--

INSERT INTO `Aluno_Aula` (`aluno_id`, `aula_id`) VALUES
(19, 0),
(19, 7);

-- --------------------------------------------------------

--
-- Estrutura para tabela `Aluno_Curso`
--

CREATE TABLE `Aluno_Curso` (
  `aluno_id` int(11) NOT NULL,
  `curso_id` int(11) NOT NULL,
  `progresso` int(11) DEFAULT 0,
  `matricula` datetime DEFAULT current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Aluno_Curso`
--

INSERT INTO `Aluno_Curso` (`aluno_id`, `curso_id`, `progresso`, `matricula`) VALUES
(26, 24, 50, '2025-11-11 07:00:41'),
(25, 31, 100, '2025-11-11 07:00:41'),
(24, 23, 50, '2025-11-11 07:00:41'),
(23, 26, 100, '2025-11-11 07:00:41'),
(23, 23, 100, '2025-11-11 07:00:41'),
(22, 23, 50, '2025-11-11 07:00:41'),
(21, 26, 66, '2025-11-11 07:00:41'),
(21, 23, 150, '2025-11-11 07:00:41'),
(21, 24, 50, '2025-11-11 07:00:41'),
(20, 26, 33, '2025-11-11 07:00:41'),
(20, 24, 0, '2025-11-11 07:00:41'),
(20, 23, 100, '2025-11-11 07:00:41'),
(19, 27, 0, '2025-11-11 12:21:12'),
(19, 26, 0, '2025-11-11 12:20:06'),
(19, 25, 100, '2025-11-11 12:18:04'),
(19, 23, 100, '2025-11-11 12:11:39'),
(19, 24, 50, '2025-11-11 12:09:10');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Aula`
--

CREATE TABLE `Aula` (
  `id_aluno` int(11) NOT NULL,
  `id_professor` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL,
  `nivel_aluno` varchar(50) DEFAULT NULL,
  `tempo_de_aula` int(11) DEFAULT NULL,
  `dias_por_semana` int(11) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `aulas_numero` int(11) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Aula`
--

INSERT INTO `Aula` (`id_aluno`, `id_professor`, `id_curso`, `nivel_aluno`, `tempo_de_aula`, `dias_por_semana`, `video`, `aulas_numero`, `titulo`) VALUES
(1, 1, 27, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1743443825/j554hjtcrae31onndj9r.webm', 1, 'AULA 01 - O BASICO'),
(1, 1, 25, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1743443781/thvsedkyc9mhcysh07yt.webm', 1, 'AULA 01 - O BASICO'),
(1, 1, 26, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1743443803/hz6qefiqd5moikq0moag.webm', 1, 'AULA 01 - O BASICO'),
(1, 1, 24, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1743443731/fknbk8fqemdhwoqcp22u.webm', 1, 'AULA 01 - O BASICO'),
(1, 1, 23, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1743443763/gs6sk3jmxadwkvqbpjfn.mp4', 1, 'AULA 01 - O BASICO'),
(2, 1, 26, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1745585287/oyrslgdodvktuijiqmpt.mp4', 2, 'AULA 02 - O SIMPLES'),
(3, 1, 26, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1745606582/kqj02i8a68y7jns4rbgb.mp4', 3, 'AULA 03 - O QUASE INTERMEDIARIO'),
(2, 1, 24, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1745849756/ifgchphqsejvx51tvdzd.mp4', 2, 'AULA 02 - O QUASE MEDIO'),
(2, 1, 23, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1746019029/guvuznrss5p8rru0v32j.mp4', 2, 'AULA 02 - O MEDIO'),
(1, 1, 31, NULL, NULL, NULL, 'https://res.cloudinary.com/djucslux1/video/upload/v1757889762/fe4kom4gpwswe1sr57vc.mp4', 1, 'AULA 01 - O BASICO');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Certificado`
--

CREATE TABLE `Certificado` (
  `id_certificado` int(11) NOT NULL,
  `id_aluno` int(11) DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `data_de_conclusao` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Certificado`
--

INSERT INTO `Certificado` (`id_certificado`, `id_aluno`, `id_curso`, `data_de_conclusao`) VALUES
(1, 1, 1, '2024-12-30');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Curso`
--

CREATE TABLE `Curso` (
  `id_curso` int(11) NOT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `conteudo` text DEFAULT NULL,
  `status` enum('ativo','excluído') DEFAULT 'ativo',
  `imagem` varchar(255) DEFAULT NULL,
  `video` varchar(500) DEFAULT NULL,
  `aulas_topicos` varchar(255) DEFAULT NULL,
  `data_criacao` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Curso`
--

INSERT INTO `Curso` (`id_curso`, `titulo`, `conteudo`, `status`, `imagem`, `video`, `aulas_topicos`, `data_criacao`) VALUES
(24, 'CURSO DE CSS', 'DÃª vida Ã s suas pÃ¡ginas da web com nosso Curso de CSS! Este curso Ã© ideal para quem jÃ¡ possui uma compreensÃ£o bÃ¡sica de HTML e deseja aprimorar suas habilidades de design, transformando pÃ¡ginas simples em experiÃªncias visuais impressionantes.', 'ativo', 'https://res.cloudinary.com/djucslux1/image/upload/v1739964088/mpi1izbsfbr8e2eqvr7c.jpg', NULL, NULL, '2025-02-21 02:27:18'),
(23, 'CURSO DE HTML', 'Descubra o fascinante mundo do desenvolvimento web com nosso Curso de HTML! Este curso foi projetado para iniciantes e entusiastas que desejam aprender a criar pÃ¡ginas da web atraentes e funcionais.', 'ativo', 'https://res.cloudinary.com/djucslux1/image/upload/v1739964033/cvad3yauvqmoavykiygq.jpg', NULL, NULL, '2025-02-21 02:27:18'),
(25, 'CURSO DE JAVASCRIPT', 'Mergulhe no mundo da programaÃ§Ã£o com nosso Curso de JavaScript! Este curso Ã© perfeito para iniciantes e desenvolvedores que desejam adicionar interatividade e dinamismo Ã s suas pÃ¡ginas da web. Aprenda a transformar sites estÃ¡ticos em experiÃªncias envolventes e interativas.', 'ativo', 'https://res.cloudinary.com/djucslux1/image/upload/v1739964133/mojy44opjw2tss2fxbtq.jpg', NULL, NULL, '2025-02-21 02:27:18'),
(26, 'CURSO DE BANCO DE DADOS', 'Descubra o poder dos dados com nosso Curso de Banco de Dados! Este curso Ã© projetado para iniciantes e profissionais que desejam entender como os dados sÃ£o armazenados, gerenciados e manipulados em sistemas de banco de dados. Aprenda a construir e gerenciar bancos de dados eficientes que suportam aplicaÃ§Ãµes modernas.', 'ativo', 'https://res.cloudinary.com/djucslux1/image/upload/v1739964185/bimkei0xagsilab9wav3.jpg', NULL, NULL, '2025-02-21 02:27:18'),
(27, 'CURSO DE MONTAGEM E MANUTENÃ‡ÃƒO', 'Curso onde vocÃª aprende a montar e desmontar computares e formatar e solucionar problemas', 'ativo', 'https://res.cloudinary.com/djucslux1/image/upload/v1739965232/he3f8i3m4rthwizpewxg.jpg', NULL, NULL, '2025-02-21 02:27:18');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Professor`
--

CREATE TABLE `Professor` (
  `id_professor` int(11) NOT NULL,
  `nome_professor` varchar(100) DEFAULT NULL,
  `especialidade_professor` varchar(100) DEFAULT NULL,
  `disponibilidade_professor` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Professor`
--

INSERT INTO `Professor` (`id_professor`, `nome_professor`, `especialidade_professor`, `disponibilidade_professor`) VALUES
(1, 'Felipe', 'Programação', 'Segunda a Sexta');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Venda`
--

CREATE TABLE `Venda` (
  `id_venda` int(11) NOT NULL,
  `id_aluno` int(11) DEFAULT NULL,
  `id_vendedor` int(11) DEFAULT NULL,
  `data_venda` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Venda`
--

INSERT INTO `Venda` (`id_venda`, `id_aluno`, `id_vendedor`, `data_venda`) VALUES
(1, 1, 1, '2024-12-20');

-- --------------------------------------------------------

--
-- Estrutura para tabela `Vendedor`
--

CREATE TABLE `Vendedor` (
  `id_vendedor` int(11) NOT NULL,
  `nome_vendedor` varchar(100) DEFAULT NULL,
  `email_vendedor` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `Vendedor`
--

INSERT INTO `Vendedor` (`id_vendedor`, `nome_vendedor`, `email_vendedor`) VALUES
(1, 'Claudio Kauê', 'ckaue@gmail.com');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `Aluno`
--
ALTER TABLE `Aluno`
  ADD PRIMARY KEY (`id_aluno`);

--
-- Índices de tabela `Aluno_Aula`
--
ALTER TABLE `Aluno_Aula`
  ADD PRIMARY KEY (`aluno_id`,`aula_id`);

--
-- Índices de tabela `Aluno_Curso`
--
ALTER TABLE `Aluno_Curso`
  ADD PRIMARY KEY (`aluno_id`,`curso_id`),
  ADD KEY `curso_id` (`curso_id`);

--
-- Índices de tabela `Aula`
--
ALTER TABLE `Aula`
  ADD PRIMARY KEY (`id_aluno`,`id_professor`,`id_curso`),
  ADD KEY `id_curso` (`id_curso`),
  ADD KEY `id_professor` (`id_professor`);

--
-- Índices de tabela `Certificado`
--
ALTER TABLE `Certificado`
  ADD PRIMARY KEY (`id_certificado`),
  ADD KEY `id_aluno` (`id_aluno`),
  ADD KEY `id_curso` (`id_curso`);

--
-- Índices de tabela `Curso`
--
ALTER TABLE `Curso`
  ADD PRIMARY KEY (`id_curso`);

--
-- Índices de tabela `Professor`
--
ALTER TABLE `Professor`
  ADD PRIMARY KEY (`id_professor`);

--
-- Índices de tabela `Venda`
--
ALTER TABLE `Venda`
  ADD PRIMARY KEY (`id_venda`),
  ADD KEY `id_aluno` (`id_aluno`),
  ADD KEY `id_vendedor` (`id_vendedor`);

--
-- Índices de tabela `Vendedor`
--
ALTER TABLE `Vendedor`
  ADD PRIMARY KEY (`id_vendedor`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `Aluno`
--
ALTER TABLE `Aluno`
  MODIFY `id_aluno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de tabela `Certificado`
--
ALTER TABLE `Certificado`
  MODIFY `id_certificado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `Curso`
--
ALTER TABLE `Curso`
  MODIFY `id_curso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de tabela `Professor`
--
ALTER TABLE `Professor`
  MODIFY `id_professor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `Venda`
--
ALTER TABLE `Venda`
  MODIFY `id_venda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `Vendedor`
--
ALTER TABLE `Vendedor`
  MODIFY `id_vendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
