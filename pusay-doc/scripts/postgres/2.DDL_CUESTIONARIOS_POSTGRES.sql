---------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------- AQUI EMPIEZA LA PARTE DE CUESTIONARIOS DE PUSAY ---------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE CUE_CATEGORIA 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, CUESTIONARIO NUMERIC(10, 0) 
, NOMBRE VARCHAR(256 ) NOT NULL 
, DESCRIPCION VARCHAR(512 ) 
, ESTADO NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_CATEGORIA PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);

CREATE TABLE CUE_CONFIGURACION 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, MULTIPLE_RESPUESTA NUMERIC(2, 0) NOT NULL 
, MULTIPLE_RESPUESTA_MSJ VARCHAR(512 ) 
, RETOMAR_CUESTIONARIO NUMERIC(1, 0) NOT NULL 
, REDIRIGIR_URL VARCHAR(512 ) 
, REDIRIGIR_CERRAR NUMERIC(1, 0) 
, REDIRIGIR_INFORME NUMERIC(1, 0) 
, CLAVE_ACCESO VARCHAR(20 ) 
, VIGENCIA_INICIO TIMESTAMP(6) NOT NULL 
, VIGENCIA_FIN TIMESTAMP(6) NOT NULL 
, MENSAJE_CIERRE VARCHAR(512 ) NOT NULL 
, MENSAJE_FECHA_LIMITE VARCHAR(512 ) NOT NULL 
, MENSAJE_REDIRECCIONAL VARCHAR(512 ) NOT NULL 
, MENSAJE_MAXIMO_RESPUESTAS VARCHAR(512 ) NOT NULL 
, MENSAJE_CUESTIONARIO_FINALIZAD VARCHAR(512 ) NOT NULL 
, MENSAJE_CLAVE_INCORRECTA VARCHAR(512 ) NOT NULL 
, ABIERTO NUMERIC(2, 0) NOT NULL 
, PUNTAJE_MAX NUMERIC(3, 0) 
, HEADER VARCHAR(512 ) NOT NULL 
, COLOR_TABLA VARCHAR(512 ) NOT NULL 
, ENUNCIADO VARCHAR(512 ) 
, CONSTRAINT PK_CUE_CONFIGURACION PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);

CREATE TABLE CUE_CONTACTO 
(
  IDENTIFICACION NUMERIC(17, 0) NOT NULL 
, NOMBRE VARCHAR(256 ) NOT NULL 
, APELLIDO VARCHAR(256 ) NOT NULL 
, EMAIL VARCHAR(256 ) 
, CELULAR VARCHAR(256 ) 
, EMPRESA VARCHAR(256 ) 
, ESTADO NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_CONTACTO PRIMARY KEY 
  (
    IDENTIFICACION 
  )
 
);


CREATE TABLE CUE_CUESTIONARIO_TIPO 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, NOMBRE VARCHAR(256 ) NOT NULL 
, DESCRIPCION VARCHAR(512 ) 
, ESTADO NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_CUESTIONARIO_TIPO PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);

CREATE TABLE CUE_CUESTIONARIO 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, IDENTIFICACION NUMERIC(17, 0) NOT NULL 
, CONFIGURACION NUMERIC(10, 0) NOT NULL 
, TIPO NUMERIC(10, 0) 
, TITULO VARCHAR(128 ) NOT NULL 
, DESCRIPCION VARCHAR(256 ) NOT NULL 
, TERMINOS VARCHAR(1024 ) 
, FECHA_CREACION TIMESTAMP(6) NOT NULL 
, ESTADO NUMERIC(10, 0) 
, CONSTRAINT PK_CUE_CUESTIONARIO PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);

CREATE TABLE CUE_ESTADO 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, NOMBRE VARCHAR(256 ) NOT NULL 
, DESCRIPCION VARCHAR(512 ) 
, ESTADO NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_ESTADO PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);

CREATE TABLE CUE_LISTA_CONTACTO 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, LISTA NUMERIC(10, 0) NOT NULL 
, CONTACTO NUMERIC(17, 0) NOT NULL 
, FECHA_HORA_ASIGNACION TIMESTAMP(6) NOT NULL 
, FECHA_HORA_FINALIZACION TIMESTAMP(6) NOT NULL 
, PUNTAJE_TOTAL NUMERIC(10, 0) 
, DURACION NUMERIC(3, 0) NOT NULL 
, PEST_CODIGO bigint NULL
, ESTADO NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_LISTA_CONTACTO PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);

CREATE TABLE CUE_LISTA_CUESTIONARIO 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, LISTA NUMERIC(10, 0) NOT NULL 
, CUESTIONARIO NUMERIC(10, 0) NOT NULL 
, FECHA_HORA_ASIGNACION TIMESTAMP(6) NOT NULL 
, CONSTRAINT PK_CUE_LISTA_CUESTIONARIO PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);



CREATE TABLE CUE_LISTA 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, NOMBRE VARCHAR(256 ) NOT NULL 
, DESCRIPCION VARCHAR(512 ) 
, ESTADO NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_LISTA PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);



CREATE TABLE CUE_NAVEGACION 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, CUESTIONARIO NUMERIC(10, 0) NOT NULL 
, PREGUNTA_ORIGEN NUMERIC(10, 0) NOT NULL 
, PREGUNTA_DESTINO NUMERIC(10, 0) NOT NULL 
, OPCION_RESPUESTA NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_NAVEGACION PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);

CREATE TABLE CUE_OPCION 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, PREGUNTA NUMERIC(10, 0) NOT NULL 
, ENUNCIADO VARCHAR(512 ) NOT NULL 
, CONDICION VARCHAR(512 ) 
, ORDEN NUMERIC(10, 0) NOT NULL 
, PUNTAJE NUMERIC(3, 0) 
, REQUIERE_AMPLIACION NUMERIC(2, 0) NOT NULL 
, LABEL_AMPLIACION VARCHAR(512 ) 
, INDICADOR_CORRECTA NUMERIC(2, 0) NOT NULL 
, ESTADO NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_OPCION PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);

CREATE TABLE CUE_PREGUNTA 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, CATEGORIA NUMERIC(10, 0) NOT NULL 
, ENUNCIADO VARCHAR(512 ) NOT NULL 
, CONDICION VARCHAR(512 ) NOT NULL 
, ORDEN NUMERIC(10, 0) NOT NULL 
, PUNTAJE NUMERIC(3, 0) 
, LABEL_AMPLIACION VARCHAR(512 ) 
, NRO_RESPUESTAS NUMERIC(3, 0) NOT NULL 
, ESTADO NUMERIC(10, 0) NOT NULL 
, CONSTRAINT PK_CUE_PREGUNTA PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);


CREATE TABLE CUE_RESPONSABLE 
(
  IDENTIFICACION NUMERIC(17, 0) NOT NULL 
, RAZON_SOCIAL VARCHAR(256 ) 
, NOMBRE VARCHAR(256 ) 
, APELLIDO VARCHAR(256 ) 
, EMAIL VARCHAR(256 ) NOT NULL 
, EMAIL_SOPORTE VARCHAR(256 ) 
, PAGINA_SOPORTE VARCHAR(256 ) 
, TELEFONO_1 VARCHAR(256 ) NOT NULL 
, TELEFONO_2 VARCHAR(256 ) 
, CONSTRAINT PK_CUE_RESPONSABLE PRIMARY KEY 
  (
    IDENTIFICACION 
  )
 
);


CREATE TABLE CUE_RESPUESTA 
(
  CONSECUTIVO NUMERIC(10, 0) NOT NULL 
, OPCION NUMERIC(10, 0) NOT NULL 
, LISTA_CONTACTO NUMERIC(10, 0) NOT NULL 
, FECHA_HORA_RESPUESTA TIMESTAMP(6) NOT NULL 
, IP VARCHAR(128 ) NOT NULL 
, RESPUESTA_AMPLIACION VARCHAR(200 ) 
, CONSTRAINT PK_CUE_RESPUESTA PRIMARY KEY 
  (
    CONSECUTIVO 
  )
 
);


----------------------------------------------- LLAVES FORANEAS PARA LAS TABLAS DE CUESTIONARIOS --------------------------------------------------

ALTER TABLE CUE_CATEGORIA
ADD CONSTRAINT FK_CUE_CATE_CATEGORIA_CUE_CUES FOREIGN KEY
(
  CUESTIONARIO 
)
REFERENCES CUE_CUESTIONARIO
(
  CONSECUTIVO 
);


ALTER TABLE CUE_CUESTIONARIO
ADD CONSTRAINT FK_CUE_CUES_CUESTIONA_CUE_CONF FOREIGN KEY
(
  CONFIGURACION 
)
REFERENCES CUE_CONFIGURACION
(
  CONSECUTIVO 
);

ALTER TABLE CUE_CUESTIONARIO
ADD CONSTRAINT FK_CUE_CUES_CUESTIONA_CUE_CUES FOREIGN KEY
(
  TIPO 
)
REFERENCES CUE_CUESTIONARIO_TIPO
(
  CONSECUTIVO 
);

ALTER TABLE CUE_CUESTIONARIO
ADD CONSTRAINT FK_CUE_CUES_CUESTIONA_CUE_ESTA FOREIGN KEY
(
  ESTADO 
)
REFERENCES CUE_ESTADO
(
  CONSECUTIVO 
);

ALTER TABLE CUE_CUESTIONARIO
ADD CONSTRAINT FK_CUE_CUES_CUESTIONA_CUE_RESP FOREIGN KEY
(
  IDENTIFICACION 
)
REFERENCES CUE_RESPONSABLE
(
  IDENTIFICACION 
);

ALTER TABLE CUE_LISTA_CONTACTO
ADD CONSTRAINT FK_CUE_LIST_LISTA_CON_CUE_CONT FOREIGN KEY
(
  CONTACTO 
)
REFERENCES CUE_CONTACTO
(
  IDENTIFICACION 
);

ALTER TABLE CUE_LISTA_CONTACTO
ADD CONSTRAINT FK_CUE_LIST_LISTA_CON_CUE_LIST FOREIGN KEY
(
  LISTA 
)
REFERENCES CUE_LISTA
(
  CONSECUTIVO 
);

ALTER TABLE CUE_LISTA_CUESTIONARIO
ADD CONSTRAINT FK_CUE_LIST_LISTA_CUE_CUE_CUES FOREIGN KEY
(
  CUESTIONARIO 
)
REFERENCES CUE_CUESTIONARIO
(
  CONSECUTIVO 
);

ALTER TABLE CUE_LISTA_CUESTIONARIO
ADD CONSTRAINT FK_CUE_LIST_LISTA_CUE_CUE_LIST FOREIGN KEY
(
  LISTA 
)
REFERENCES CUE_LISTA
(
  CONSECUTIVO 
);

ALTER TABLE CUE_NAVEGACION
ADD CONSTRAINT FK_CUE_NAVE_DESTINO FOREIGN KEY
(
  PREGUNTA_DESTINO 
)
REFERENCES CUE_PREGUNTA
(
  CONSECUTIVO 
);

ALTER TABLE CUE_NAVEGACION
ADD CONSTRAINT FK_CUE_NAVE_NAVEGACIO_CUE_CUES FOREIGN KEY
(
  CUESTIONARIO 
)
REFERENCES CUE_CUESTIONARIO
(
  CONSECUTIVO 
);

ALTER TABLE CUE_NAVEGACION
ADD CONSTRAINT FK_CUE_NAVE_NAVEGACIO_CUE_OPCI FOREIGN KEY
(
  OPCION_RESPUESTA 
)
REFERENCES CUE_OPCION
(
  CONSECUTIVO 
);

ALTER TABLE CUE_NAVEGACION
ADD CONSTRAINT FK_CUE_NAVE_ORIGEN FOREIGN KEY
(
  PREGUNTA_ORIGEN 
)
REFERENCES CUE_PREGUNTA
(
  CONSECUTIVO 
);

ALTER TABLE CUE_OPCION
ADD CONSTRAINT FK_CUE_OPCI_OPCION_PR_CUE_PREG FOREIGN KEY
(
  PREGUNTA 
)
REFERENCES CUE_PREGUNTA
(
  CONSECUTIVO 
);

ALTER TABLE CUE_PREGUNTA
ADD CONSTRAINT FK_CUE_PREG_PREGUNTA__CUE_CATE FOREIGN KEY
(
  CATEGORIA 
)
REFERENCES CUE_CATEGORIA
(
  CONSECUTIVO 
);

ALTER TABLE CUE_RESPUESTA
ADD CONSTRAINT FK_CUE_RESP_REFERENCE_CUE_LIST FOREIGN KEY
(
  LISTA_CONTACTO 
)
REFERENCES CUE_LISTA_CONTACTO
(
  CONSECUTIVO 
);

ALTER TABLE CUE_RESPUESTA
ADD CONSTRAINT FK_CUE_RESP_RESPUESTA_CUE_OPCI FOREIGN KEY
(
  OPCION 
)
REFERENCES CUE_OPCION
(
  CONSECUTIVO 
);


----------------------------------------------- SECUENCIAS EN LAS TABLAS DE CUESTIONARIO -----------------------------------------

CREATE SEQUENCE SEQ_CUE_CATEGORIA INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_CONFIGURACION INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_CONTACTO INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_CUESTIONARIO_TIPO INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_CUESTIONARIO INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_ESTADO INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 4 CACHE 20;

CREATE SEQUENCE SEQ_CUE_LISTA_CONTACTO INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_LISTA_CUESTIONARIO INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_LISTA INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_NAVEGACION INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_OPCION INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_PREGUNTA INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_RESPONSABLE INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

CREATE SEQUENCE SEQ_CUE_RESPUESTA INCREMENT BY 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 20;

----------------------------------------------- INSERTS NECESARIOS DE LOS ESTADOS -----------------------------------------

Insert into CUE_ESTADO (CONSECUTIVO,NOMBRE,DESCRIPCION,ESTADO) values (1,'Activo','Activo',1);
Insert into CUE_ESTADO (CONSECUTIVO,NOMBRE,DESCRIPCION,ESTADO) values (2,'Inactivo','Inactivo',1);
Insert into CUE_ESTADO (CONSECUTIVO,NOMBRE,DESCRIPCION,ESTADO) values (3,'Anulada','Anulada',1);

