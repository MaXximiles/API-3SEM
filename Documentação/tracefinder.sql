CREATE TABLE usuario (
    usr_id NUMBER(5),
    usr_nome VARCHAR(70),
    usr_login VARCHAR(30),
    usr_senha VARCHAR(30),
    usr_email VARCHAR(45),
    CONSTRAINT pk_usuario_id PRIMARY KEY(usr_id)
);

CREATE TABLE documento_info (
    doc_id NUMBER(5),
    doc_name VARCHAR(40) NOT NULL,
    doc_parent_id NUMBER(5),
    doc_lastmodified DATE NOT NULL,
    doc_type VARCHAR(20) NOT NULL,
    CONSTRAINT pk_documento_info_id PRIMARY KEY(doc_id),
    CONSTRAINT fk_documento_info_parent_id FOREIGN KEY(doc_parent_id) REFERENCES documento_info(doc_id)
);

CREATE SEQUENCE documento_info_seq START WITH 1;

CREATE OR REPLACE TRIGGER documento_info_id_seq 
BEFORE INSERT ON documento_info 
FOR EACH ROW

BEGIN
  SELECT documento_info_seq.NEXTVAL
  INTO   :new.doc_id
  FROM   dual;
END;