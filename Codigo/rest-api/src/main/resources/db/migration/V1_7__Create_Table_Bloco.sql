 CREATE TABLE `trace_finder`.`arquivo_pagina` (
  `arquivo_pagina_id` INT NOT NULL AUTO_INCREMENT,
  `arquivo_id` VARCHAR(45) NULL,
  `arquivo_pagina_pagina` VARCHAR(45) NULL,
  `arquivo_pagina_modificacao` VARCHAR(45) NULL,
  `arquivo_pagina_revisao` VARCHAR(45) NULL,
  `arquivo_pagina_data_modificacao` VARCHAR(45) NULL,
  PRIMARY KEY (`arquivo_pagina_id`));