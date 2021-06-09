 CREATE TABLE `trace_finder`.`arquivo` (
  `arquivo_id` INT NOT NULL AUTO_INCREMENT,
  `arquivo_nome` VARCHAR(45) NULL,
  `codelist_id` INT(11) NULL,
  `arquivo_revisao` VARCHAR(45) NULL,
  `arquivo_caminho` VARCHAR(255) NULL,
  PRIMARY KEY (`arquivo_id`));