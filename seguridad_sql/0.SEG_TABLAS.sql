  CREATE TABLE SEG_AUDITORIA
   (  AUT_CODIGO bigserial, 
  USU_CODIGO bigint, 
  TABLA character varying (200), 
  COD_REGISTRO NUMERIC, 
  CAMPO character varying (200), 
  TIPO character varying (100), 
  FECHA DATE, 
  VALOR_ANTERIOR character varying (200), 
  VALOR_NUEVO character varying (200)
   );

  ALTER TABLE SEG_AUDITORIA ADD CONSTRAINT LOG_PK PRIMARY KEY (AUT_CODIGO);




  CREATE TABLE SEG_CAMBIO_PASS
   (  CAP_CODIGO bigserial NOT NULL , 
  SEG_USUARIO_USU_CODIGO bigint, 
  CAP_FECHA_FIN TIMESTAMP (6), 
  CAP_TOKEN character varying (200), 
  CAP_ESTADO character varying (50), 
  CAP_FECHA_INI TIMESTAMP (6)
   );

    ALTER TABLE SEG_CAMBIO_PASS ADD CONSTRAINT SEG_CAMBIO_PASS_PK PRIMARY KEY (CAP_CODIGO);

    


  CREATE TABLE SEG_COMPANIA
   (  CIA_CODIGO bigserial NOT NULL , 
  CIA_CODIGO_INTERNO character varying (15) NOT NULL , 
  CIA_NOMBRE character varying (50) NOT NULL , 
  CIA_ESTADO_REGISTRO character varying (1) NOT NULL , 
  MOD_USU_CODIGO bigint
   ) ;

    ALTER TABLE SEG_COMPANIA ADD CONSTRAINT CIA_PK PRIMARY KEY (CIA_CODIGO);
 

  CREATE TABLE SEG_GRUPO_OPCION 
   (  GRU_CODIGO bigserial NOT NULL , 
  GRU_NOMBRE character varying (50) NOT NULL , 
  GRU_DESCRIPCION character varying (200), 
  GRU_LLAVE_ACCESO character varying (200), 
  GRU_ESTADO_REGISTRO character varying (1) NOT NULL ,
  GRU_ICONO character varying (200), 
  GRU_CODIGO_PADRE bigint, 
  SIS_CODIGO bigint NOT NULL , 
  MOD_USU_CODIGO bigint
   )  ;

    ALTER TABLE SEG_GRUPO_OPCION ADD CONSTRAINT GRU_PK PRIMARY KEY (GRU_CODIGO);
 
  CREATE TABLE SEG_HISTORIAL_CONSTRASENA 
   (  HCO_CODIGO bigserial NOT NULL , 
  HCO_CONSTRASENA character varying (50) NOT NULL , 
  HCO_FECHA_CAMBIO DATE NOT NULL , 
  HCO_DETALLE_CAMBIO character varying (100), 
  USU_CODIGO bigint NOT NULL 
   )  ;

    ALTER TABLE SEG_HISTORIAL_CONSTRASENA ADD CONSTRAINT HCO_PK PRIMARY KEY (HCO_CODIGO);
 

  CREATE TABLE SEG_OPCION 
   (  OPC_CODIGO bigserial NOT NULL , 
  OPC_NOMBRE character varying (100) NOT NULL , 
  OPC_DESCRIPCION character varying (200), 
  OPC_LLAVE_ACCESO character varying (200), 
  SEG_GRUPO_OPCION_GRU_CODIGO bigint NOT NULL , 
  OPC_ESTADO_REGISTRO character varying (1) NOT NULL , 
  MOD_USU_CODIGO bigint
   );

  ALTER TABLE SEG_OPCION ADD CONSTRAINT OPC_PK PRIMARY KEY (OPC_CODIGO);


  CREATE TABLE SEG_PARAMETRO 
   (  PAR_CODIGO bigserial NOT NULL , 
  PAR_NOMBRE character varying (100) NOT NULL , 
  PAR_VALOR_NUMERICO NUMERIC (10,4), 
  PAR_VALOR_ALFANUMERICO character varying (100), 
  PAR_VALOR_FECHA DATE, 
  PAR_ESTADO_REGISTRO character varying (1) NOT NULL , 
  MOD_USU_CODIGO bigint
   ) ;




  ALTER TABLE SEG_PARAMETRO ADD CONSTRAINT PAR_PK PRIMARY KEY (PAR_CODIGO);

  CREATE TABLE SEG_PERMISO 
   (  PER_CODIGO bigserial NOT NULL , 
  PER_ESTADO_REGISTRO character varying (1) NOT NULL , 
  ROL_CODIGO bigint, 
  OPC_CODIGO bigint, 
  USU_CODIGO bigint, 
  GRU_CODIGO bigint, 
  SIC_CODIGO bigint, 
  SUC_CODIGO bigint, 
  MOD_USU_CODIGO bigint
   ) ;

  ALTER TABLE SEG_PERMISO ADD CONSTRAINT PER_PK PRIMARY KEY (PER_CODIGO);
   

  CREATE TABLE SEG_ROL 
   (  ROL_CODIGO bigserial NOT NULL , 
  ROL_NOMBRE character varying (50) NOT NULL , 
  ROL_DESCRIPCION character varying (200), 
  ROL_DIAS_CADUCIDAD_PWD NUMERIC (3,0), 
  SEG_SISTEMA_SIS_CODIGO bigint NOT NULL , 
  ROL_ESTADO_REGISTRO character varying (1) NOT NULL , 
  MOD_USU_CODIGO bigint
   ) ;

  ALTER TABLE SEG_ROL ADD CONSTRAINT ROL_PK PRIMARY KEY (ROL_CODIGO);

  CREATE TABLE SEG_ROL_USUARIO 
   (  RLU_CODIGO bigserial NOT NULL , 
  SEG_USUARIO_USU_CODIGO bigint NOT NULL , 
  SEG_ROL_ROL_CODIGO bigint NOT NULL , 
  RLU_ESTADO_REGISTRO character varying (1) NOT NULL , 
  MOD_USU_CODIGO bigint
   );

  ALTER TABLE SEG_ROL_USUARIO ADD CONSTRAINT RLU_PK PRIMARY KEY (RLU_CODIGO);

  CREATE TABLE SEG_SISTEMA_CIA 
   (  SIC_CODIGO bigserial NOT NULL , 
  SEG_SISTEMA_SIS_CODIGO bigint NOT NULL , 
  SEG_COMPANIA_CIA_CODIGO bigint NOT NULL , 
  SIC_ESTADO_REGISTRO character varying (1), 
  MOD_USU_CODIGO bigint
   );

  ALTER TABLE SEG_SISTEMA_CIA ADD CONSTRAINT SIC_PK PRIMARY KEY (SIC_CODIGO);
 

  CREATE TABLE SEG_SISTEMA 
   (  SIS_CODIGO bigserial NOT NULL , 
  SIS_NOMBRE character varying (50) NOT NULL , 
  SIS_DESCRIPCION character varying (200), 
  SIS_ESTADO_REGISTRO character varying (1) NOT NULL , 
  MOD_USU_CODIGO bigint
   );

  ALTER TABLE SEG_SISTEMA ADD CONSTRAINT SIS_PK PRIMARY KEY (SIS_CODIGO);

  CREATE TABLE SEG_SUCURSAL 
   (  SUC_CODIGO bigserial NOT NULL , 
  SUC_CODIGO_INTERNO character varying (15) NOT NULL , 
  CIA_CODIGO bigint NOT NULL , 
  SUC_NOMBRE character varying (100) NOT NULL , 
  SUC_ESTADO_REGISTRO character varying (1) NOT NULL , 
  MOD_USU_CODIGO bigint
   );

  ALTER TABLE SEG_SUCURSAL ADD CONSTRAINT SUC_PK PRIMARY KEY (SUC_CODIGO);
 
  CREATE TABLE SEG_USUARIO 
   (  USU_CODIGO bigserial NOT NULL , 
  USU_NOMBRES character varying (50) NOT NULL , 
  USU_APELLIDOS character varying (50) NOT NULL , 
  USU_LOGIN character varying (30) NOT NULL , 
  USU_CONSTRASENA character varying (50) NOT NULL , 
  USU_ESTADO_REGISTRO character varying (1) NOT NULL , 
  USU_CODIGO_INTERNO character varying (50) NOT NULL , 
  MOD_USU_CODIGO bigint, 
  USU_ULTMIMA_MODIFICACION_PASS DATE, 
  USU_CORREO character varying (200), 
  USU_INTENTOS_FALLIDOS NUMERIC (18,0), 
  USU_COMPANIA_CIA_CODIGO NUMERIC (12,0)
   );

ALTER TABLE SEG_USUARIO ADD CONSTRAINT USU_PK PRIMARY KEY (USU_CODIGO);

 

        
  -- CREATE SEQUENCE  SEQ_AUDITORIA  MINVALUE 1 MAXVALUE 9223372036854775807 
  -- INCREMENT BY 1 START WITH 1 CACHE 20;
  --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;

  -- CREATE SEQUENCE  SEQ_CAMBIO_PASS  MINVALUE 1 MAXVALUE 9223372036854775807 
  -- INCREMENT BY 1 START WITH 163 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_COMPANIA  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 5 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
  -- CREATE SEQUENCE  SEQ_GRUPO_OPCION  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 283 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_HISTORIAL_CONTRASEÑA  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 1 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_OPCION  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 351 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;

   --CREATE SEQUENCE  SEQ_PARAMETRO  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 3 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_PERMISO  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 1688 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_ROL  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 262 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_ROL_USUARIO  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 1417 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_SISTEMA  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 101 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_SISTEMA_CIA  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 141 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_SUCURSAL  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 4 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;
   
   --CREATE SEQUENCE  SEQ_USUARIO  MINVALUE 1 MAXVALUE 9223372036854775807 
   --INCREMENT BY 1 START WITH 1239 CACHE 20;
   --ALTER SEQUENCE psy_matriz_correlacion_maco_codigo_seq RESTART WITH 6;




ALTER TABLE SEG_AUDITORIA ADD CONSTRAINT LOG_USU_FK FOREIGN KEY (USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
    
ALTER TABLE SEG_CAMBIO_PASS ADD CONSTRAINT FK_USUARIO FOREIGN KEY (SEG_USUARIO_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
    
ALTER TABLE SEG_COMPANIA ADD CONSTRAINT MOD_USU_CIA_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
    
ALTER TABLE SEG_GRUPO_OPCION ADD CONSTRAINT GRU_GRU_FK FOREIGN KEY (GRU_CODIGO_PADRE)
    REFERENCES SEG_GRUPO_OPCION (GRU_CODIGO) ;
 
ALTER TABLE SEG_GRUPO_OPCION ADD CONSTRAINT MOD_USU_GRU_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
 
ALTER TABLE SEG_GRUPO_OPCION ADD CONSTRAINT SIS_GRU_FK FOREIGN KEY (SIS_CODIGO)
    REFERENCES SEG_SISTEMA (SIS_CODIGO) ;
    
ALTER TABLE SEG_HISTORIAL_CONSTRASENA ADD CONSTRAINT HCO_USU_FK FOREIGN KEY (USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;

ALTER TABLE SEG_OPCION ADD CONSTRAINT GRU_OPC_FK FOREIGN KEY (SEG_GRUPO_OPCION_GRU_CODIGO)
    REFERENCES SEG_GRUPO_OPCION (GRU_CODIGO) ;
 
ALTER TABLE SEG_OPCION ADD CONSTRAINT MOD_USU_OPC_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ; 

ALTER TABLE SEG_PARAMETRO ADD CONSTRAINT MOD_USU_PAR_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;

ALTER TABLE SEG_PERMISO ADD CONSTRAINT GRU_PER_FK FOREIGN KEY (GRU_CODIGO)
    REFERENCES SEG_GRUPO_OPCION (GRU_CODIGO) ;
 
ALTER TABLE SEG_PERMISO ADD CONSTRAINT MOD_USU_PER_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
 
ALTER TABLE SEG_PERMISO ADD CONSTRAINT OPC_PER_FK FOREIGN KEY (OPC_CODIGO)
    REFERENCES SEG_OPCION (OPC_CODIGO) ;
 
ALTER TABLE SEG_PERMISO ADD CONSTRAINT PER_SIC_FK FOREIGN KEY (SIC_CODIGO)
    REFERENCES SEG_SISTEMA_CIA (SIC_CODIGO) ;

ALTER TABLE SEG_PERMISO ADD CONSTRAINT PER_SUC_FK FOREIGN KEY (SUC_CODIGO)
    REFERENCES SEG_SUCURSAL (SUC_CODIGO) ;
 
ALTER TABLE SEG_PERMISO ADD CONSTRAINT ROL_PER_FK FOREIGN KEY (ROL_CODIGO)
    REFERENCES SEG_ROL (ROL_CODIGO) ;
 
ALTER TABLE SEG_PERMISO ADD CONSTRAINT USU_PER_FK FOREIGN KEY (USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;

    
ALTER TABLE SEG_SISTEMA ADD CONSTRAINT MOD_USU_SIS_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;

      
ALTER TABLE SEG_ROL ADD CONSTRAINT MOD_USU_ROL_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
 
ALTER TABLE SEG_ROL ADD CONSTRAINT SIS_ROL_FK FOREIGN KEY (SEG_SISTEMA_SIS_CODIGO)
    REFERENCES SEG_SISTEMA (SIS_CODIGO) ;

ALTER TABLE SEG_ROL_USUARIO ADD CONSTRAINT MOD_USU_RLU_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
 
ALTER TABLE SEG_ROL_USUARIO ADD CONSTRAINT ROL_RLU_FK FOREIGN KEY (SEG_ROL_ROL_CODIGO)
    REFERENCES SEG_ROL (ROL_CODIGO) ;
 
ALTER TABLE SEG_ROL_USUARIO ADD CONSTRAINT USU_RLU_FK FOREIGN KEY (SEG_USUARIO_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;

ALTER TABLE SEG_SISTEMA_CIA ADD CONSTRAINT MOD_USU_SIC_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
 
ALTER TABLE SEG_SISTEMA_CIA ADD CONSTRAINT SCI_SIS_FK FOREIGN KEY (SEG_SISTEMA_SIS_CODIGO)
    REFERENCES SEG_SISTEMA (SIS_CODIGO) ;
 
ALTER TABLE SEG_SISTEMA_CIA ADD CONSTRAINT SIC_CIA_FK FOREIGN KEY (SEG_COMPANIA_CIA_CODIGO)
    REFERENCES SEG_COMPANIA (CIA_CODIGO) ;
    

ALTER TABLE SEG_SUCURSAL ADD CONSTRAINT MOD_USU_SUC_FK FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
 
ALTER TABLE SEG_SUCURSAL ADD CONSTRAINT SUC_CIA_FK FOREIGN KEY (CIA_CODIGO)
    REFERENCES SEG_COMPANIA (CIA_CODIGO) ;

ALTER TABLE SEG_USUARIO ADD CONSTRAINT SEG_USUARIO_SEG_USUARIO_FK1 FOREIGN KEY (MOD_USU_CODIGO)
    REFERENCES SEG_USUARIO (USU_CODIGO) ;
    










