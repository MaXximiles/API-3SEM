CREATE SCHEMA `trace_finder` ;

/* Tabela documento = Manual*/
CREATE TABLE `trace_finder`.`documento` (
  `documento_id` INT NOT NULL AUTO_INCREMENT,
  `documento_nome` VARCHAR(45) NULL,
  `documento_pn` VARCHAR(45) NULL,
  `documento_caminho` VARCHAR(255) NULL,
  PRIMARY KEY (`documento_id`));

 /* Tabela traço pertencente ao documento*/
 CREATE TABLE `trace_finder`.`traco_doc` (
  `traco_doc_id` INT NOT NULL AUTO_INCREMENT,
  `traco_doc_nome` VARCHAR(45) NULL,
  `traco_doc_descricao` VARCHAR(45) NULL,
  `traco_doc_codigo` VARCHAR(45) NULL,
  PRIMARY KEY (`traco_doc_id`));

 /* Tabela relação documento com Traço */ 
 CREATE TABLE `trace_finder`.`relacao_documento_traco` (
  `relacao_documento_traco_id` INT NOT NULL AUTO_INCREMENT,
  `traco_id` INT NULL,
  `doc_id` INT NULL,
  PRIMARY KEY (`relacao_documento_traco_id`));

 /* Codelist */ 
 CREATE TABLE `trace_finder`.`codelist` (
  `codelist_id` INT NOT NULL AUTO_INCREMENT,
  `codelist_secao` VARCHAR(45) NULL,
  `codelist_subsecao` VARCHAR(45) NULL,
  `codelist_nbloco` VARCHAR(45) NULL,
  `codelist_codebloco` VARCHAR(45) NULL,
  `codelist_caminho` VARCHAR(255) NULL,
  PRIMARY KEY (`codelist_id`));

 /* Tabela arquivo = Bloco*/  
  CREATE TABLE `trace_finder`.`arquivo` (
  `arquivo_id` INT NOT NULL AUTO_INCREMENT,
  `arquivo_nome` VARCHAR(45) NULL,
  `codelist_id` VARCHAR(45) NULL,
  PRIMARY KEY (`arquivo_id`));
  
  /* Tabela traços dos arquivos */ 
  CREATE TABLE `trace_finder`.`traco_arquivo` (
  `traco_arquivo_id` INT NOT NULL AUTO_INCREMENT,
  `traco_arquivo_nome` VARCHAR(45) NULL,
  `traco_arquivo_descricao` VARCHAR(45) NULL,
  `traco_arquivo_codigo` VARCHAR(45) NULL,
  PRIMARY KEY (`traco_arquivo_id`));

  /* Tabela relação arquivo para traços */
 CREATE TABLE `trace_finder`.`relacao_arquivo_traco` (
  `relacao_arquivo_traco_id` INT NOT NULL AUTO_INCREMENT,
  `traco_id` INT NULL,
  `arquivo_id` INT NULL,
  PRIMARY KEY (`relacao_arquivo_traco_id`));

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
  `usuario_senha` VARCHAR(45) NULL,
  `usuario_nivel` VARCHAR(45) NULL,
  `usuario_login` VARCHAR(45) NULL,
  PRIMARY KEY (`usuario_id`));

 /* ALTER TABLE'S*/ 
  
  ALTER TABLE `trace_finder`.`codelist` 
ADD COLUMN `documento_id` VARCHAR(45) NULL AFTER `codelist_caminho`;

ALTER TABLE `trace_finder`.`relacao_documento_traco` 
DROP COLUMN `arquivo_id`;


/*INSERT'S*/


/* Inserindo novo manual*/
INSERT INTO documento (documento_nome, documento_pn, documento_caminho) VALUES ('ABC', '1234', 'C://caminho/docuemnto');

/* Inserindo linha do codelist do manual*/
INSERT INTO codelist (codelist_secao, codelist_subsecao, codelist_nbloco, codelist_codebloco, codelist_caminho, documento_id)
VALUES ('Seção 00','Subseção 01','Nome Bloco','Code Bloco 22','C://caminho/bloco/', '1');

/* Inserindo arquivos(blocos) no codelist */
INSERT INTO arquivo ( arquivo_nome, codelist_id ) VALUES ('arquivo1', '1');

/* Lendo as paginas do arquivo(LEP)*/
INSERT INTO arquivo_pagina (arquivo_id, arquivo_pagina_pagina, arquivo_pagina_modificacao, arquivo_pagina_revisao, arquivo_pagina_data_modificacao) 
VALUES ('1','1','','Original','01/04/2021' );
INSERT INTO arquivo_pagina (arquivo_id, arquivo_pagina_pagina, arquivo_pagina_modificacao, arquivo_pagina_revisao, arquivo_pagina_data_modificacao) 
VALUES ('1','2','new','Revisão 1','07/04/2021' );
INSERT INTO arquivo_pagina (arquivo_id, arquivo_pagina_pagina, arquivo_pagina_modificacao, arquivo_pagina_revisao, arquivo_pagina_data_modificacao) 
VALUES ('1','3','new','Revisão 1','07/04/2021' );
INSERT INTO arquivo_pagina (arquivo_id, arquivo_pagina_pagina, arquivo_pagina_modificacao, arquivo_pagina_revisao, arquivo_pagina_data_modificacao) 
VALUES ('1','4','del','Original','06/04/2021' );

/* Inserindo tracos dos arquivos(blocos)*/
INSERT INTO traco_arquivo (traco_arquivo_nome, traco_arquivo_descricao, traco_arquivo_codigo)
VALUES ('Traço 1','Este é o primeiro traço do arquivo(bloco)','50');

/* Inserindo tracos dos manuais*/
INSERT INTO traco_doc (traco_doc_nome, traco_doc_descricao, traco_doc_codigo)
VALUES ('Traço 1','Este é o primeiro traço do documento','74');

/* Relacionando os tracos da tabela tracos de arquivos com os arquivos*/
INSERT INTO relacao_arquivo_traco (traco_id, arquivo_id) VALUES ('1','1');

/* Relacionando os tracos da tabela tracos de documento com os documentos(manuais)*/
INSERT INTO relacao_documento_traco (traco_id, doc_id) VALUES ('1','1');

/* Inserindo usuarios */ 
INSERT INTO usuario (usuario_nome, usuario_email, usuario_senha, usuario_nivel, usuario_login) 
VALUES ('Horacio Jandelinho','horacio@gmail.com','horacio123','1','horacio');

/* Aumentando campo de senha para criptografia*/
ALTER TABLE `trace_finder`.`usuario` 
CHANGE COLUMN `usuario_senha` `usuario_senha` VARCHAR(255) NULL DEFAULT NULL ;

/* Arrumando campo documento_id para INT */
ALTER TABLE `trace_finder`.`codelist` DROP COLUMN `documento_id`;

ALTER TABLE `trace_finder`.`codelist` ADD COLUMN `documento_id` INT(11) NULL AFTER `codelist_caminho`;

/* Adicionando relacionamentos entre tabelas */
/* Codelist > Documento */
ALTER TABLE codelist ADD CONSTRAINT fk_documento_codelist FOREIGN KEY (documento_id) REFERENCES documento(documento_id);
