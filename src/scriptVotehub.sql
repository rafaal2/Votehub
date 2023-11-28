create database votehub;
use votehub;

--
-- criando estrutura da tabela `adm`
--

CREATE TABLE adm (
  id_adm int NOT NULL AUTO_INCREMENT,
  login varchar(200) DEFAULT NULL,
  senha varchar(100) DEFAULT NULL,
  PRIMARY KEY (id_adm)
) ;

--
-- inserindo dados na tabela adm
--

insert into adm values(1,'adm01','12345678adm');

--
-- criando estrutura da tabela `votante`
--

CREATE TABLE votante (
  id_votante int NOT NULL AUTO_INCREMENT,
  matricula char(200) DEFAULT NULL,
  nome varchar(200) DEFAULT NULL,
  senha varchar(100) DEFAULT NULL,
  PRIMARY KEY (id_votante)
);

--
-- criando estrutura da tabela `votação`
--

CREATE TABLE votacao (
  id_votacao int NOT NULL AUTO_INCREMENT,
  nome_votacao char(200) DEFAULT NULL,
  data_inicio datetime DEFAULT NULL,
  data_fim datetime DEFAULT NULL,
  PRIMARY KEY (id_votacao)
);

--
-- criando estrutura da tabela `candidato`
--

CREATE TABLE candidato (
  numero_candidato varchar(100) NOT NULL,
  nome char(200) DEFAULT NULL,
  cargo char(100) DEFAULT NULL,
  id_votacao int not null,
  PRIMARY KEY (numero_candidato),
  FOREIGN KEY (id_votacao) REFERENCES votacao(id_votacao)
) ;

--
-- criando estrutura da tabela `voto`
--

CREATE TABLE voto (
  id_voto int NOT NULL AUTO_INCREMENT,
  numero_candidato varchar(100) DEFAULT NULL,
  PRIMARY KEY (id_voto),
  KEY numero_candidato (numero_candidato),
  CONSTRAINT voto_ibfk_1 FOREIGN KEY (numero_candidato) REFERENCES candidato (numero_candidato)
);

--
-- criando estrutura da tabela `votacaoVotante`
--

create table votacaoVotante(
id_votacaoVotante int not null auto_increment,
id_votacao int NOT NULL,
id_votante int not null,
primary key (id_votacaoVotante),
FOREIGN KEY (id_votacao) REFERENCES votacao(id_votacao),
FOREIGN KEY (id_votante) REFERENCES votante(id_votante)
)
