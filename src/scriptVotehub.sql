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

insert into adm values(1,'adm01','MEOhagF2o+J/IOKCgePLCLJGa601Pe12qYpncc68OIdErQOn06cmAsvPNB4/TcLq');
-- a senha criptografada é '12345678adm'

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
  tipo_votacao varchar(12),
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
  img_candidato varchar(200) not null,
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
-- criando estrutura da tabela `proposta`
--

Create table proposta(
id_proposta int AUTO_INCREMENT not null,
titulo varchar(300) not null,
descricao longtext not null,
id_votacao int not null,
primary key(id_proposta),
foreign key (id_votacao) references votacao(id_votacao)
);

--
-- criando estrutura da tabela `respostaproposta`
--

Create table respostaproposta(
id_respostaproposta int auto_increment not null,
resposta varchar (200) not null,
id_proposta int not null,
primary key(id_respostaproposta),
foreign key (id_proposta) references proposta(id_proposta)
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
);

--
-- criando estrutura da tabela `propostaVotante`
--

create table propostaVotante(
id_propostaVotante int not null auto_increment,
id_proposta int NOT NULL,
id_votante int not null,
primary key (id_propostaVotante),
FOREIGN KEY (id_proposta) REFERENCES proposta(id_proposta),
FOREIGN KEY (id_votante) REFERENCES votante(id_votante)
);