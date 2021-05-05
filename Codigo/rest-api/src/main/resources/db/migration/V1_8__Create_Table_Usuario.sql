 CREATE TABLE `trace_finder`.`usuario` (
  `usuario_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_nome` VARCHAR(45) NULL,
  `usuario_email` VARCHAR(45) NULL,
  `usuario_senha` VARCHAR(255) NULL,
  `usuario_nivel` VARCHAR(45) NULL,
  `usuario_login` VARCHAR(45) NULL,
  PRIMARY KEY (`usuario_id`));