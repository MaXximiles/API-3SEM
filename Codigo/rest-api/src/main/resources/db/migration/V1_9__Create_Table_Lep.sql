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