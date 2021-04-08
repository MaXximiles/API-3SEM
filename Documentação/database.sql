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

  