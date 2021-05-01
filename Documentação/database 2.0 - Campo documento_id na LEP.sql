/* DATABASE VERS�O 2.0 bloco MODIFCADO EM 30/04/2021 POR MAXIMILES*/
/* MODIFICA��ES
- Cria��o da tabela LEP
- Adi��o do Campo documento_id na tabela LEP
*/

/* Executar comando abaixo somente uma vez */
/* ****DROP SCHEMA `trace_finder` ; /***** */
/* *************************************** */

CREATE SCHEMA `trace_finder` ;

/* Tabela documento = Manual*/
CREATE TABLE `trace_finder`.`documento` (
  `documento_id` INT NOT NULL AUTO_INCREMENT,
  `documento_nome` VARCHAR(45) NULL,
  `documento_pn` VARCHAR(45) NULL,
  `documento_caminho` VARCHAR(255) NULL,
  PRIMARY KEY (`documento_id`));

 /* Tabela tra�o pertencente ao documento*/
 CREATE TABLE `trace_finder`.`traco_doc` (
  `traco_doc_id` INT NOT NULL AUTO_INCREMENT,
  `traco_doc_nome` VARCHAR(45) NULL,
  `traco_doc_descricao` VARCHAR(45) NULL,
  `traco_doc_codigo` VARCHAR(45) NULL,
  PRIMARY KEY (`traco_doc_id`));

 /* Tabela rela��o documento com Tra�o */ 
 CREATE TABLE `trace_finder`.`relacao_documento_traco` (
  `relacao_documento_traco_id` INT NOT NULL AUTO_INCREMENT,
  `traco_id` INT NULL,
  `doc_id` INT NULL,
  PRIMARY KEY (`relacao_documento_traco_id`));
  
 /* Tabela Codelist */ 
 CREATE TABLE `trace_finder`.`codelist` (
  `codelist_id` INT NOT NULL AUTO_INCREMENT,
  `codelist_secao` VARCHAR(45) NULL,
  `codelist_subsecao` VARCHAR(45) NULL,
  `codelist_nomebloco` VARCHAR(45) NULL,
  `codelist_codebloco` VARCHAR(45) NULL,
  `codelist_caminho` VARCHAR(255) NULL,
  `documento_id` INT(11) NULL,
  PRIMARY KEY (`codelist_id`));

 /* Tabela arquivo = Bloco*/  
  CREATE TABLE `trace_finder`.`arquivo` (
  `arquivo_id` INT NOT NULL AUTO_INCREMENT,
  `arquivo_nome` VARCHAR(45) NULL,
  `bloco_id` INT(11) NULL,
  PRIMARY KEY (`arquivo_id`));
 
  /* Tabela tra�os dos blocos (linha do codelist) */ 
  CREATE TABLE `trace_finder`.`traco_bloco` (
  `traco_bloco_id` INT NOT NULL AUTO_INCREMENT,
  `traco_id` INT(11) NULL,
  `bloco_id` INT(11) NULL,
  PRIMARY KEY (`traco_bloco_id`));

  /* Tabela paginas dos arquivos LEP */
  CREATE TABLE `trace_finder`.`arquivo_pagina` (
  `arquivo_pagina_id` INT NOT NULL AUTO_INCREMENT,
  `arquivo_id` VARCHAR(45) NULL,
  `arquivo_pagina_pagina` VARCHAR(45) NULL,
  `arquivo_pagina_modificacao` VARCHAR(45) NULL,
  `arquivo_pagina_revisao` VARCHAR(45) NULL,
  `arquivo_pagina_data_modificacao` VARCHAR(45) NULL,
  PRIMARY KEY (`arquivo_pagina_id`));

  /* Tabela usuarios */
  CREATE TABLE `trace_finder`.`usuario` (
  `usuario_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_nome` VARCHAR(45) NULL,
  `usuario_email` VARCHAR(45) NULL,
  `usuario_senha` VARCHAR(255) NULL,
  `usuario_nivel` VARCHAR(45) NULL,
  `usuario_login` VARCHAR(45) NULL,
  PRIMARY KEY (`usuario_id`));
  
    /* Tabela LEP */
  CREATE TABLE `trace_finder`.`lep` (
  `lep_id` INT NOT NULL AUTO_INCREMENT,
  `lep_bloco` VARCHAR(50) NULL,
  `lep_code` VARCHAR(20) NULL,
  `lep_pagina` VARCHAR(10) NULL,
  `lep_modificacao` VARCHAR(20) NULL,
  `lep_revisao` VARCHAR(30) NULL,
  `arquivo_id` INT(11) NULL,
  `documento_id` INT(11) NULL,
  PRIMARY KEY (`lep_id`));

USE trace_finder;

/*INSERT'S*/

/* Inserindo novo manual*/
INSERT INTO documento (documento_nome, documento_pn, documento_caminho) VALUES ('ABC', '1234', 'C://caminho/docuemnto');
INSERT INTO documento (documento_nome, documento_pn, documento_caminho) VALUES ('DEF', '5678', 'C://caminho/docuemnto');

/* Inserindo bloco */
INSERT INTO codelist (codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id)
VALUES ('Se��o 00','Subse��o 00','Nome Bloco0','Code Bloco 00','C://caminho/bloco/', '1');
INSERT INTO codelist (codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id)
VALUES ('Se��o 01','Subse��o 01','Nome Bloco1','Code Bloco 11','C://caminho/bloco/', '1');
INSERT INTO codelist (codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id)
VALUES ('Se��o 02','Subse��o 02','Nome Bloco2','Code Bloco 22','C://caminho/bloco/', '1');
INSERT INTO codelist (codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id)
VALUES ('Se��o 03','Subse��o 03','Nome Bloco3','Code Bloco 33','C://caminho/bloco/', '2');

/* Inserindo tra�os */
INSERT INTO traco_doc (traco_doc_nome, traco_doc_descricao, traco_doc_codigo)
VALUES ('Tra�o 1','Este � o tra�o do Documento','50');
INSERT INTO traco_doc (traco_doc_nome, traco_doc_descricao, traco_doc_codigo)
VALUES ('Tra�o 2','Este � o tra�o do Documento','25');
INSERT INTO traco_doc (traco_doc_nome, traco_doc_descricao, traco_doc_codigo)
VALUES ('Tra�o 3','Este � o tra�o do Documento','30');

/* Relacionando os tracos da tabela tracos de documento com os documentos(manuais)*/
INSERT INTO relacao_documento_traco (traco_id, doc_id) VALUES ('1','1');
INSERT INTO relacao_documento_traco (traco_id, doc_id) VALUES ('2','1');
INSERT INTO relacao_documento_traco (traco_id, doc_id) VALUES ('3','2');
INSERT INTO relacao_documento_traco (traco_id, doc_id) VALUES ('1','2');

/* Inserindo arquivos(blocos) no codelist */
INSERT INTO arquivo ( arquivo_nome, bloco_id ) VALUES ('arquivo1', '1');

/* Lendo as paginas do arquivo(LEP)*/
INSERT INTO arquivo_pagina (arquivo_id, arquivo_pagina_pagina, arquivo_pagina_modificacao, arquivo_pagina_revisao, arquivo_pagina_data_modificacao) 
VALUES ('1','1','','Original','01/04/2021' );
INSERT INTO arquivo_pagina (arquivo_id, arquivo_pagina_pagina, arquivo_pagina_modificacao, arquivo_pagina_revisao, arquivo_pagina_data_modificacao) 
VALUES ('1','2','new','Revis�o 1','07/04/2021' );
INSERT INTO arquivo_pagina (arquivo_id, arquivo_pagina_pagina, arquivo_pagina_modificacao, arquivo_pagina_revisao, arquivo_pagina_data_modificacao) 
VALUES ('1','3','new','Revis�o 1','07/04/2021' );
INSERT INTO arquivo_pagina (arquivo_id, arquivo_pagina_pagina, arquivo_pagina_modificacao, arquivo_pagina_revisao, arquivo_pagina_data_modificacao) 
VALUES ('1','4','del','Original','06/04/2021' );

/* Inserindo tracos dos arquivos(blocos)*/
/*INSERT INTO traco_arquivo (traco_arquivo_nome, traco_arquivo_descricao, traco_arquivo_codigo)
VALUES ('Tra�o 1','Este � o primeiro tra�o do arquivo(bloco)','50');

/*Inserindo tracos dos manuais*/
/*INSERT INTO traco_doc (traco_doc_nome, traco_doc_descricao, traco_doc_codigo)
VALUES ('Tra�o 1','Este � o primeiro tra�o do documento','74');*/

/* Relacionando os tracos da tabela tracos de arquivos com os arquivos*/
/*INSERT INTO relacao_arquivo_traco (traco_id, arquivo_id) VALUES ('1','1');*/

/* Inserindo usuarios */ 
INSERT INTO usuario (usuario_nome, usuario_email, usuario_senha, usuario_nivel, usuario_login) 
VALUES ('Teste','teste@gmail.com','$2a$10$lmr6SRlyL0dbggpRZV9E6OhJNQqO.wan.IIR0rqFOh4Si3aqH4gR2','1','teste');

INSERT INTO usuario (usuario_nome, usuario_email, usuario_senha, usuario_nivel, usuario_login) 
VALUES ('Teste123','teste@gmail.com','123','1','teste123');

use trace_finder;
select * from arquivo;
select * from codelist;
select * from documento;

SELECT lep_id, lep_bloco, lep_code, lep_pagina, lep_modificacao, lep_revisao, lep.arquivo_id  FROM lep  
INNER JOIN arquivo ON arquivo.arquivo_id = lep.arquivo_id
WHERE lep.arquivo_id = 40;