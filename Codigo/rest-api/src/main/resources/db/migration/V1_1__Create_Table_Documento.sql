CREATE TABLE `trace_finder`.`documento` (
  `documento_id` INT NOT NULL AUTO_INCREMENT,
  `documento_nome` VARCHAR(45) NULL,
  `documento_pn` VARCHAR(45) NULL,
  `documento_caminho` VARCHAR(255) NULL,
  PRIMARY KEY (`documento_id`));