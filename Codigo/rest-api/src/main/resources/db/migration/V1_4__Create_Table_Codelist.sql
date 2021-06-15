 CREATE TABLE `trace_finder`.`codelist` (
  `codelist_id` INT NOT NULL AUTO_INCREMENT,
  `codelist_secao` VARCHAR(45) NULL,
  `codelist_subsecao` VARCHAR(45) NULL,
  `codelist_numbloco` VARCHAR(45) NULL,
  `codelist_nomebloco` VARCHAR(45) NULL,
  `codelist_codebloco` VARCHAR(45) NULL,
  `codelist_caminho` VARCHAR(255) NULL,
  `documento_id` INT(11) NULL,
  PRIMARY KEY (`codelist_id`));