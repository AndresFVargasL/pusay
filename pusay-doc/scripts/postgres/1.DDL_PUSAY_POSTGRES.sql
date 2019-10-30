-- Generado por Oracle SQL Developer Data Modeler 4.1.1.887
--   en:        2015-08-05 09:42:25 COT
--   sitio:      Oracle Database 10g
--   tipo:      Oracle Database 10g

CREATE TABLE PSY_PARAMETRO
  (
    PARAM_CODIGO bigserial NOT NULL ,
    --  NOMBRE DEL ASUNTO AMBIENTAL
    LLAVE          character varying (2000) NOT NULL ,
    VALOR     character varying (2000) NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
  ALTER TABLE PSY_PARAMETRO ADD CONSTRAINT PSY_PARAMETRO_PK PRIMARY KEY ( PARAM_CODIGO ) ;


CREATE TABLE PSY_ASUNTO_AMBIENTAL
  (
    ASAM_CODIGO bigserial NOT NULL ,
    --  NOMBRE DEL ASUNTO AMBIENTAL
    NOMBRE          character varying (255) NOT NULL ,
    DESCRIPCION     character varying (2000) NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_ASUNTO_AMBIENTAL.NOMBRE
IS
  'NOMBRE DEL ASUNTO AMBIENTAL' ;
  COMMENT ON COLUMN PSY_ASUNTO_AMBIENTAL.DESCRIPCION
IS
  'DESCRIPCION DEL ASUNTO AMBIENTAL' ;
  COMMENT ON COLUMN PSY_ASUNTO_AMBIENTAL.ESTADO_REGISTRO
IS
  'A-ACTIVO, I- INACTIVO' ;
  ALTER TABLE PSY_ASUNTO_AMBIENTAL ADD CONSTRAINT PSY_ASUNTO_AMBIENTAL_PK PRIMARY KEY ( ASAM_CODIGO ) ;

CREATE TABLE PSY_CIUDAD
  (
    CIUD_CODIGO bigserial NOT NULL ,
    PROV_CODIGO bigint NOT NULL ,
    --  NOMBRE DE LA CIUDAD
    NOMBRE character varying (255) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_CIUDAD.NOMBRE
IS
  'NOMBRE DE LA CIUDAD' ;
  ALTER TABLE PSY_CIUDAD ADD CONSTRAINT PSY_CIUDAD_PK PRIMARY KEY ( CIUD_CODIGO ) ;

CREATE TABLE PSY_DETALLE_ERIDA
  (
    DEER_CODIGO bigserial NOT NULL ,
    ASAM_CODIGO bigint NOT NULL ,
    IMAM_CODIGO bigint NOT NULL ,
    MAER_CODIGO bigint NOT NULL ,
    --  PESO DADO EN PORCENTAJE POR EL RESPONSABLE AMBIENTAL
    PESO NUMERIC (5,2) NOT NULL ,
    --  CALIFICACION INGRESADA POR EL RESPONSABLE AMBIENTAL UTILIZADA PARA CALCULAR
    --  LA VALORACION
    CALIFICACION NUMERIC (2) NOT NULL ,
    --  VALORACION DADA POR LA MULTIPLIACACION ENTRE EL PESO Y LA CALIFICACION
    VALORACION NUMERIC (10,2) ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_DETALLE_ERIDA.PESO
IS
  'PESO DADO EN PORCENTAJE POR EL RESPONSABLE AMBIENTAL' ;
  COMMENT ON COLUMN PSY_DETALLE_ERIDA.CALIFICACION
IS
  'CALIFICACION INGRESADA POR EL RESPONSABLE AMBIENTAL UTILIZADA PARA CALCULAR LA VALORACION' ;
  COMMENT ON COLUMN PSY_DETALLE_ERIDA.VALORACION
IS
  'VALORACION DADA POR LA MULTIPLIACACION ENTRE EL PESO Y LA CALIFICACION' ;
  COMMENT ON COLUMN PSY_DETALLE_ERIDA.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_DETALLE_ERIDA ADD CONSTRAINT PSY_DETALLE_ERIDA_PK PRIMARY KEY ( DEER_CODIGO ) ;



CREATE TABLE PSY_DETALLE_MAPA_ESTRATEGICO
  (
    DMAE_CODIGO bigserial NOT NULL ,
    MACO_CODIGO bigint NOT NULL ,
    MAES_CODIGO bigint NOT NULL ,
    TIPO character varying (1) ,  
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_DETALLE_MAPA_ESTRATEGICO.TIPO
IS
  '-- S:SELECCIONADA - T:TRABAJAR' ;   
COMMENT ON COLUMN PSY_DETALLE_MAPA_ESTRATEGICO.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_DETALLE_MAPA_ESTRATEGICO ADD CONSTRAINT DETALLE_MAPA_ESTRATEGICO_PK PRIMARY KEY ( DMAE_CODIGO ) ;

CREATE TABLE PSY_DETALLE_OBJETIVO_PLAN
  (
    DOBP_CODIGO bigserial NOT NULL ,
    OBES_CODIGO bigint NOT NULL ,
    OBPL_CODIGO bigint NOT NULL ,
    --  NOMBRE DADO POR LA EMPRESA AL OBJETIVO ESTRATEGICO CORPORATIVO PARA
    --  ASOCIARLO AL CATALOGO DE OBJETIVOS ESTRATEGICOS
    NOMBRE character varying (255) NOT NULL ,
    --  DESCRIPCION DEL OBJETIVO CORPORATIVO
    DESCRIPCION character varying (2000) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_DETALLE_OBJETIVO_PLAN.NOMBRE
IS
  'NOMBRE DADO POR LA EMPRESA AL OBJETIVO ESTRATEGICO CORPORATIVO PARA ASOCIARLO AL CATALOGO DE OBJETIVOS ESTRATEGICOS' ;
  COMMENT ON COLUMN PSY_DETALLE_OBJETIVO_PLAN.DESCRIPCION
IS
  'DESCRIPCION DEL OBJETIVO CORPORATIVO' ;
  COMMENT ON COLUMN PSY_DETALLE_OBJETIVO_PLAN.ESTADO_REGISTRO
IS
  'A- ACTIVO, I- INACTIVO' ;
  ALTER TABLE PSY_DETALLE_OBJETIVO_PLAN ADD CONSTRAINT PSY_DETALLE_OBJETIVO_PLAN_PK PRIMARY KEY ( DOBP_CODIGO ) ;

CREATE TABLE PSY_EMPRESA
  (
    EMPR_CODIGO bigserial NOT NULL ,
    CIUD_CODIGO bigint NOT NULL ,
    --  PERSONA DE CONTACTO EN LA EMPRESA
    PERS_CODIGO bigint ,
    --  NIT DE LA EMPRESA REGISTRADA
    NIT character varying (20) NOT NULL ,
    --  NOMBRE DE LA EMPRESA REGISTRADA
    NOMBRE character varying (255) NOT NULL ,
    --  DIRECCION DE LA EMPRESA
    DIRECCION_PRINCIPAL character varying (100) ,
    --  TELEFONO DE LA EMPRESA
    TELEFONO_PRINCIPAL NUMERIC (15) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_EMPRESA.NIT
IS
  'NIT DE LA EMPRESA REGISTRADA' ;
  COMMENT ON COLUMN PSY_EMPRESA.NOMBRE
IS
  'NOMBRE DE LA EMPRESA REGISTRADA' ;
  COMMENT ON COLUMN PSY_EMPRESA.DIRECCION_PRINCIPAL
IS
  'DIRECCION DE LA EMPRESA' ;
  COMMENT ON COLUMN PSY_EMPRESA.TELEFONO_PRINCIPAL
IS
  'TELEFONO DE LA EMPRESA' ;
  ALTER TABLE PSY_EMPRESA ADD CONSTRAINT PSY_EMPRESA_PK PRIMARY KEY ( EMPR_CODIGO ) ;

CREATE TABLE PSY_ESTRATEGIA_AMBIENTAL
  (
    ESAM_CODIGO bigserial NOT NULL ,
    --  NOMBRE DE LA ESTRATEGIA AMBIENTAL
    NOMBRE character varying (255) NOT NULL ,
    --  DESCRIPCION DE LA ESTRATEGIA AMBIENTAL
    DESCRIPCION character varying (2000) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_ESTRATEGIA_AMBIENTAL.NOMBRE
IS
  'NOMBRE DE LA ESTRATEGIA AMBIENTAL' ;
  COMMENT ON COLUMN PSY_ESTRATEGIA_AMBIENTAL.DESCRIPCION
IS
  'DESCRIPCION DE LA ESTRATEGIA AMBIENTAL' ;
  COMMENT ON COLUMN PSY_ESTRATEGIA_AMBIENTAL.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_ESTRATEGIA_AMBIENTAL ADD CONSTRAINT PSY_ESTRATEGIA_AMBIENTAL_PK PRIMARY KEY ( ESAM_CODIGO ) ;

CREATE TABLE PSY_EVALUACION_PEA_INDICADOR
  (
    CODIGO          bigserial NOT NULL ,
    INDI_CODIGO     bigint NOT NULL ,
    PEA_CODIGO      bigint NOT NULL ,
    MULTIPLE        character varying (50) ,
    PERIODO         NUMERIC (2) NOT NULL ,
    RESULTADO       NUMERIC (12,2) NOT NULL ,
    META            NUMERIC (12,2) NOT NULL ,
    HISTORIAL       NUMERIC (12,2) ,
    NORMA           NUMERIC (12,2) ,
    SECTORIAL       NUMERIC (12,2) ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
ALTER TABLE PSY_EVALUACION_PEA_INDICADOR ADD CONSTRAINT PSY_EVALUACION_PEA_PK PRIMARY KEY ( CODIGO ) ;

CREATE TABLE PSY_EVALUACION_PEA_OBJETIVO
  (
    CODIGO          bigserial NOT NULL ,
    PEA_CODIGO      bigint NOT NULL ,
    OBAM_CODIGO     bigint NOT NULL ,
    PERIODO         NUMERIC (2) NOT NULL ,
    RESULTADO       NUMERIC (12,2) NOT NULL ,
    HISTORIAL       NUMERIC (12,2) ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
ALTER TABLE PSY_EVALUACION_PEA_OBJETIVO ADD CONSTRAINT PSY_EVALUACION_PEA_OBJETIVO_PK PRIMARY KEY ( CODIGO ) ;

CREATE TABLE PSY_IMPACTO_AMBIENTAL
  (
    IMAM_CODIGO bigserial NOT NULL ,
    --  NOMBRE DEL IMPACTO AMBIENTAL
    NOMBRE character varying (255) NOT NULL ,
    --  DESCRIPCION DEL IMPACTO AMBIENTAL
    DESCRIPCION character varying (2000) NOT NULL ,
    --  A-ACTIVO, I- INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_IMPACTO_AMBIENTAL.ESTADO_REGISTRO
IS
  'A-ACTIVO, I- INACTIVO' ;
  ALTER TABLE PSY_IMPACTO_AMBIENTAL ADD CONSTRAINT PSY_IMPACTO_AMBIENTAL_PK PRIMARY KEY ( IMAM_CODIGO ) ;


CREATE TABLE PSY_IMPACTO_OBJETIVO
  (
    IMOB_CODIGO     bigserial NOT NULL ,
    IMAM_CODIGO     bigint NOT NULL ,
    OBAM_CODIGO     bigint NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
ALTER TABLE PSY_IMPACTO_OBJETIVO ADD CONSTRAINT PSY_IMPACTO_OBJETIVO_PK PRIMARY KEY ( IMOB_CODIGO ) ;


CREATE TABLE PSY_INDICADOR
  (
    CODIGO          bigserial NOT NULL ,
    OBAM_CODIGO     bigint NOT NULL ,
    SUBTE_CODIGO    bigint NOT NULL ,
    DESCRIPCION     character varying (200) NOT NULL ,
    UNIDAD_MEDIDA   character varying (200) NOT NULL ,
    TIPO_INDICADOR  character varying (1) NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
ALTER TABLE PSY_INDICADOR ADD CONSTRAINT PSY_INDICADOR_PK PRIMARY KEY ( CODIGO ) ;

CREATE TABLE PSY_IPU (
    ipu_codigo bigserial NOT NULL,
    plac_codigo bigint NOT NULL,
    fecha_informe date NOT NULL,
    periodo character varying(20) NOT NULL,
    periodo_fecha_inicio date,
    periodo_fecha_fin date,
    estado_ipu character varying(1) NOT NULL,
    estado_registro character varying(1) NOT NULL,
    avance_presupuestado numeric(14,4),
    avance_real numeric(14,4),
    causas_desviacion character varying(2000),
    acciones_propuestas character varying(2000),
    logros_alcanzados character varying(2000),
    logros_no_alcanzados character varying(2000),
    tipo_ipu character varying(1)
);
COMMENT ON COLUMN PSY_IPU.FECHA_INFORME
IS
  'FECHA DEL IPU' ;
  COMMENT ON COLUMN PSY_IPU.PERIODO
IS
  'PERIODO DEL IPU' ;
  COMMENT ON COLUMN PSY_IPU.PERIODO_FECHA_INICIO
IS
  'FECHA EN LA QUE INICIA EL PERIODO QUE ESTOY EVALUANDO' ;
  COMMENT ON COLUMN PSY_IPU.PERIODO_FECHA_FIN
IS
  'FECHA EN LA QUE TERMINA EL PERIODO QUE ESTOY EVALUANDO' ;
  COMMENT ON COLUMN PSY_IPU.ESTADO_IPU
IS
  'ESTADOS POR LOS CUALES PUEDE PASAR EL IPU' ;
  COMMENT ON COLUMN PSY_IPU.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_IPU ADD CONSTRAINT PSY_IPU_PK PRIMARY KEY ( IPU_CODIGO ) ;

CREATE TABLE PSY_ITEM_PRESUPUESTO
  (
    IPRE_CODIGO bigserial NOT NULL ,
    PRES_CODIGO bigint NOT NULL ,
    TIIP_CODIGO bigint NOT NULL ,
    --  FECHA INCIAL DEL PERIODO PARA EL ITEM DEL PRESUPUESTO
    FECHA_INICIO DATE NOT NULL ,
    FECHA_FIN    DATE ,
    --  VALOR PRESUPUESTADO PARA EL ITEM EN EL PERIODO
    VALOR_PRESUPUESTADO NUMERIC (12,2) NOT NULL ,
    --  VALOR EJECUTADO PARA EL ITEM EN EL PERIODO
    VALOR_EJECUTADO NUMERIC (12,2) ,
    --  SEMANA DEL ITEM
    SEMANA NUMERIC(3,0),
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_ITEM_PRESUPUESTO.FECHA_INICIO
IS
  'FECHA INCIAL DEL PERIODO PARA EL ITEM DEL PRESUPUESTO' ;
  COMMENT ON COLUMN PSY_ITEM_PRESUPUESTO.FECHA_FIN
IS
  'FECHA FINAL DEL PERIODO PARA EL PRESUPUESTO' ;
  COMMENT ON COLUMN PSY_ITEM_PRESUPUESTO.VALOR_PRESUPUESTADO
IS
  'VALOR PRESUPUESTADO PARA EL ITEM EN EL PERIODO' ;
  COMMENT ON COLUMN PSY_ITEM_PRESUPUESTO.VALOR_EJECUTADO
IS
  'VALOR EJECUTADO PARA EL ITEM EN EL PERIODO' ;
  COMMENT ON COLUMN PSY_ITEM_PRESUPUESTO.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_ITEM_PRESUPUESTO ADD CONSTRAINT PSY_ITEM_PRESUPUESTO_PK PRIMARY KEY ( IPRE_CODIGO ) ;

CREATE TABLE PSY_MAPA_ESTRATEGICO
  (
    MAES_CODIGO bigserial NOT NULL ,
    PEST_CODIGO bigint NOT NULL ,
    --  FECHA DE INICIO DEL MAPA ESTRATEGICO
    FECHA_INICIO DATE NOT NULL ,
    --  FECHA EN QUE SE GENERA EL MAPA ESTRATEGICO DEFINITIVO
    FECHA_FIN DATE ,
    --  ESTADOS POR LOS CUALES PUEDE PASAR EL MAPA ESTRATEGICO
    ESTADO_MAPA_ESTRATEGICO character varying (1) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_MAPA_ESTRATEGICO.FECHA_INICIO
IS
  'FECHA DE INICIO DEL MAPA ESTRATEGICO' ;
  COMMENT ON COLUMN PSY_MAPA_ESTRATEGICO.FECHA_FIN
IS
  'FECHA EN QUE SE GENERA EL MAPA ESTRATEGICO DEFINITIVO' ;
  COMMENT ON COLUMN PSY_MAPA_ESTRATEGICO.ESTADO_MAPA_ESTRATEGICO
IS
  'ESTADOS POR LOS CUALES PUEDE PASAR EL MAPA ESTRATEGICO' ;
  ALTER TABLE PSY_MAPA_ESTRATEGICO ADD CONSTRAINT PSY_MAPA_ESTRATEGICO_PK PRIMARY KEY ( MAES_CODIGO ) ;

CREATE TABLE PSY_MATRIZ_CORRELACION
  (
    MACO_CODIGO bigserial NOT NULL ,
    OBES_CODIGO bigint NOT NULL ,
    IMAM_CODIGO bigint NOT NULL ,
    ESAM_CODIGO bigint NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_MATRIZ_CORRELACION.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_MATRIZ_CORRELACION ADD CONSTRAINT PSY_MATRIZ_CORRELACION_PK PRIMARY KEY ( MACO_CODIGO ) ;

CREATE TABLE PSY_MATRIZ_ENCUESTA
  (
    MAEN_CODIGO bigserial NOT NULL ,
    PEST_CODIGO bigint NOT NULL ,
    --  CODIGO DE LA ENCUESTA QUE RELACIONA LA MATRIZ CON EL SISTEMA DE ENCUESTA
    CODIGO_ENCUESTA character varying (50) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_MATRIZ_ENCUESTA.CODIGO_ENCUESTA
IS
  'CODIGO DE LA ENCUESTA QUE RELACIONA LA MATRIZ CON EL SISTEMA DE ENCUESTA' ;
  COMMENT ON COLUMN PSY_MATRIZ_ENCUESTA.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_MATRIZ_ENCUESTA ADD CONSTRAINT MATRIZ_ENCUESTA_PK PRIMARY KEY ( MAEN_CODIGO ) ;

CREATE TABLE PSY_MATRIZ_ERIDA
  (
    MAER_CODIGO bigserial NOT NULL ,
    PEST_CODIGO bigint NOT NULL ,
    --  FECHA DE INICIO DEL LA MATRIZ ERIDA
    FECHA_INICIO DATE ,
    --  FECHA EN QUE SE GENERA EL INFORME DE DIAGNOSTICO DEFINITIVO
    FECHA_FIN DATE NOT NULL ,
    --  ESTADO POR EL QUE PUEDE PASAR LA ERIDA
    ESTADO_ERIDA character varying (1) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_MATRIZ_ERIDA.FECHA_INICIO
IS
  'FECHA DE INICIO DEL LA MATRIZ ERIDA' ;
  COMMENT ON COLUMN PSY_MATRIZ_ERIDA.FECHA_FIN
IS
  'FECHA EN QUE SE GENERA EL INFORME DE DIAGNOSTICO DEFINITIVO' ;
  COMMENT ON COLUMN PSY_MATRIZ_ERIDA.ESTADO_ERIDA
IS
  'ESTADO POR EL QUE PUEDE PASAR LA ERIDA' ;
  COMMENT ON COLUMN PSY_MATRIZ_ERIDA.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_MATRIZ_ERIDA ADD CONSTRAINT MATRIZ_ERIDA_PK PRIMARY KEY ( MAER_CODIGO ) ;

CREATE TABLE PSY_MONEDA
  (
    MONE_CODIGO bigserial NOT NULL ,
    --  NOMBRE DE LA MONEDA
    NOMBRE character varying (100) NOT NULL ,
    --  ABREVIATURA DEL NOMBRE MONEDA
    ABREVIATURA character varying (10) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_MONEDA.NOMBRE
IS
  'NOMBRE DE LA MONEDA' ;
  COMMENT ON COLUMN PSY_MONEDA.ABREVIATURA
IS
  'ABREVIATURA DEL NOMBRE MONEDA' ;
  COMMENT ON COLUMN PSY_MONEDA.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_MONEDA ADD CONSTRAINT PSY_MONEDA_PK PRIMARY KEY ( MONE_CODIGO ) ;

CREATE TABLE PSY_OBJETIVO_AMBIENTAL
  (
    CODIGO          bigserial NOT NULL ,
    DESCRIPCION     character varying (100) NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
ALTER TABLE PSY_OBJETIVO_AMBIENTAL ADD CONSTRAINT PSY_OBJETIVO_AMBIENTAL_PK PRIMARY KEY ( CODIGO ) ;

CREATE TABLE PSY_OBJETIVO_ESTRATEGICO
  (
    OBES_CODIGO bigserial NOT NULL ,
    --  NOMBRE DEL OBJETIVO ESTRATEGICO
    NOMBRE character varying (255) NOT NULL ,
    --  DESCRIPCION DEL OBJETIVO ESTRATEGICO
    DESCRIPCION character varying (2000) NOT NULL ,
    --  A- ACTIVO, I- INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_OBJETIVO_ESTRATEGICO.NOMBRE
IS
  'NOMBRE DEL OBJETIVO ESTRATEGICO' ;
  COMMENT ON COLUMN PSY_OBJETIVO_ESTRATEGICO.DESCRIPCION
IS
  'DESCRIPCION DEL OBJETIVO ESTRATEGICO' ;
  COMMENT ON COLUMN PSY_OBJETIVO_ESTRATEGICO.ESTADO_REGISTRO
IS
  'A- ACTIVO, I- INACTIVO' ;
  ALTER TABLE PSY_OBJETIVO_ESTRATEGICO ADD CONSTRAINT PSY_OBJETIVO_ESTRATEGICO_PK PRIMARY KEY ( OBES_CODIGO ) ;


CREATE TABLE PSY_OBJETIVO_IMPACTO
  (
    OBIM_CODIGO bigserial NOT NULL ,
    OBES_CODIGO bigint NOT NULL ,
    IMAM_CODIGO bigint NOT NULL ,
    --  -- A : ACTIVO - I:INACTIVO
    ESTADO_REGISTRO character varying (1)
  ) ;
COMMENT ON COLUMN PSY_OBJETIVO_IMPACTO.ESTADO_REGISTRO
IS
  '-- A : ACTIVO - I:INACTIVO' ;
ALTER TABLE PSY_OBJETIVO_IMPACTO ADD CONSTRAINT PSY_OBJETIVO_IMPACTO_PK PRIMARY KEY ( OBIM_CODIGO ) ;

  

CREATE TABLE PSY_OBJETIVO_PLAN
  (
    OBPL_CODIGO bigserial NOT NULL ,
    PEST_CODIGO bigint NOT NULL ,
    --  FECHA DE INICIO DEL OBJETIVO PLAN
    FECHA_INICIO DATE NOT NULL ,
    --  FECHA EN QUE SE GENERA EL PLAN ESTRATEGICO DEFINITIVO
    FECHA_FIN DATE ,
    --  ESTADOS POR LO QUE PUEDE PASAR LA DEFINICION DE LOS OBJETIVOS CORPORATIVOS
    ESTADO_OBJETIVO_PLAN character varying (1) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_OBJETIVO_PLAN.FECHA_INICIO
IS
  'FECHA DE INICIO DEL OBJETIVO PLAN' ;
  COMMENT ON COLUMN PSY_OBJETIVO_PLAN.FECHA_FIN
IS
  'FECHA EN QUE SE GENERA EL PLAN ESTRATEGICO DEFINITIVO' ;
  COMMENT ON COLUMN PSY_OBJETIVO_PLAN.ESTADO_OBJETIVO_PLAN
IS
  'ESTADOS POR LO QUE PUEDE PASAR LA DEFINICION DE LOS OBJETIVOS CORPORATIVOS' ;
  COMMENT ON COLUMN PSY_OBJETIVO_PLAN.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_OBJETIVO_PLAN ADD CONSTRAINT PSY_OBJETIVO_PLAN_PK PRIMARY KEY ( OBPL_CODIGO ) ;

CREATE TABLE PSY_PAIS
  (
    PAIS_CODIGO bigserial NOT NULL ,
    --  NOMBRE DEL PAIS
    NOMBRE character varying (255) NOT NULL ,
    --  A.ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_PAIS.NOMBRE
IS
  'NOMBRE DEL PAIS' ;
  ALTER TABLE PSY_PAIS ADD CONSTRAINT PSY_PAIS_PK PRIMARY KEY ( PAIS_CODIGO ) ;

CREATE TABLE PSY_PERSONA
  (
    PERS_CODIGO bigserial NOT NULL ,
    EMPR_CODIGO bigint NOT NULL ,
    --  NOMBRE COMPLETO DE LA PERSONA
    NOMBRE character varying (255) NOT NULL ,
    --  EMAIL DE LA PERSONA
    EMAIL character varying (100) NOT NULL ,
    --  TELEFONO DE CONTACTO PERSONA
    TELEFONO NUMERIC (15) ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_PERSONA.NOMBRE
IS
  'NOMBRE COMPLETO DE LA PERSONA' ;
  COMMENT ON COLUMN PSY_PERSONA.EMAIL
IS
  'EMAIL DE LA PERSONA' ;
  COMMENT ON COLUMN PSY_PERSONA.TELEFONO
IS
  'TELEFONO DE CONTACTO PERSONA' ;
  ALTER TABLE PSY_PERSONA ADD CONSTRAINT PSY_PERSONA_PK PRIMARY KEY ( PERS_CODIGO ) ;

CREATE TABLE PSY_PLAN_ACCION
  (
    PLAC_CODIGO bigserial NOT NULL ,
    PROG_CODIGO bigint NOT NULL ,
    --  FECHA DE INICIO PARA EL PLAN DE ACCION
    FECHA_INICIO DATE NOT NULL ,
    --  FECHA EN LA QUE SE ESPERA FINALIZAR EL PLAN DE ACCION
    FECHA_FIN_PLANEADA DATE NOT NULL ,
    --  FECHA REAL EN QUE SE CIERRA EL PLAN DE ACCION
    FECHA_FIN_REAL DATE ,
    --  NOMBRE DEL PLAN DE ACCION
    NOMBRE character varying (255) NOT NULL ,
    --  DESCRIPCION DEL PLAN DE ACCION
    DESCRIPCION character varying (2000) ,
    --  ESTADOS POR LOS QUE PASA EL PLAN DE ACCION
    ESTADO_PLAN_ACCION character varying (1) NOT NULL ,
    ESTADO_REGISTRO    character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_PLAN_ACCION.FECHA_INICIO
IS
  'FECHA DE INICIO PARA EL PLAN DE ACCION' ;
  COMMENT ON COLUMN PSY_PLAN_ACCION.FECHA_FIN_PLANEADA
IS
  'FECHA EN LA QUE SE ESPERA FINALIZAR EL PLAN DE ACCION' ;
  COMMENT ON COLUMN PSY_PLAN_ACCION.FECHA_FIN_REAL
IS
  'FECHA REAL EN QUE SE CIERRA EL PLAN DE ACCION' ;
  COMMENT ON COLUMN PSY_PLAN_ACCION.NOMBRE
IS
  'NOMBRE DEL PLAN DE ACCION' ;
  COMMENT ON COLUMN PSY_PLAN_ACCION.DESCRIPCION
IS
  'DESCRIPCION DEL PLAN DE ACCION' ;
  COMMENT ON COLUMN PSY_PLAN_ACCION.ESTADO_PLAN_ACCION
IS
  'ESTADOS POR LOS QUE PASA EL PLAN DE ACCION' ;
  COMMENT ON COLUMN PSY_PLAN_ACCION.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_PLAN_ACCION ADD CONSTRAINT PSY_PLAN_ACCION_PK PRIMARY KEY ( PLAC_CODIGO ) ;

CREATE TABLE PSY_PLAN_ESTRATEGIA
  (
    PLES_CODIGO     bigserial NOT NULL ,
    DMAE_CODIGO     bigint NOT NULL ,
    PROG_CODIGO     bigint NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ; 
ALTER TABLE PSY_PLAN_ESTRATEGIA ADD CONSTRAINT PSY_PLAN_ESTRATEGIA_PK PRIMARY KEY ( PLES_CODIGO ) ;

CREATE TABLE PSY_PLAN_ESTRATEGICO
  (
    PEST_CODIGO bigserial NOT NULL ,
    EMPR_CODIGO bigint NOT NULL ,
    --  NOMBRE DEL PLAN ESTRATEGICO
    NOMBRE character varying (255) NOT NULL ,
    --  FECHA DE APERTURA DEL PLAN ESTRATEGICO
    FECHA_INICIO DATE NOT NULL ,
    --  FECHA DE CIERRE DEL PLAN
    FECHA_FIN DATE ,
    --  DESCRIPCION DEL PLAN ESTRATEGICO AMBIENTAL
    DESCRIPCION character varying (2000) ,
    --  ESTADO POR EL CUAL PUEDE PASAR EL PLAN
    ESTADO_PLAN character varying (1) NOT NULL ,
    --  A- ACTIVO, I- INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_PLAN_ESTRATEGICO.NOMBRE
IS
  'NOMBRE DEL PLAN ESTRATEGICO' ;
  COMMENT ON COLUMN PSY_PLAN_ESTRATEGICO.FECHA_INICIO
IS
  'FECHA DE APERTURA DEL PLAN ESTRATEGICO' ;
  COMMENT ON COLUMN PSY_PLAN_ESTRATEGICO.FECHA_FIN
IS
  'FECHA DE CIERRE DEL PLAN' ;
  COMMENT ON COLUMN PSY_PLAN_ESTRATEGICO.ESTADO_PLAN
IS
  'ESTADO POR EL CUAL PUEDE PASAR EL PLAN' ;
  COMMENT ON COLUMN PSY_PLAN_ESTRATEGICO.ESTADO_REGISTRO
IS
  'A- ACTIVO, I- INACTIVO' ;
  ALTER TABLE PSY_PLAN_ESTRATEGICO ADD CONSTRAINT PSY_PLAN_ESTRATEGICO_PK PRIMARY KEY ( PEST_CODIGO ) ;

CREATE TABLE PSY_PLAN_ESTRATEGICO_AMBIENTAL
  (
    CODIGO          bigserial NOT NULL ,
    PEST_CODIGO     bigint NOT NULL ,
    NOMBRE 			character varying (255) NOT NULL ,
    FECHA_INICO     DATE NOT NULL ,
    FECHA_FIN       DATE ,
    ESTADO_PLAN 	character varying (1) NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
ALTER TABLE PSY_PLAN_ESTRATEGICO_AMBIENTAL ADD CONSTRAINT PSY_PEA_PK PRIMARY KEY ( CODIGO ) ;

CREATE TABLE PSY_PRESUPUESTO
  (
    PRES_CODIGO bigserial NOT NULL ,
    PLAC_CODIGO bigint NOT NULL ,
    MONE_CODIGO bigint NOT NULL ,
    --  FECHA DE APERTURA DEL PRESUPUESTO
    FECHA_INICIO DATE NOT NULL ,
    --  FECHA EN LA QUE SE CIERRA EL PRESUPUESTO
    FECHA_FIN DATE ,
    --  LOS ESTADOS POR LOS CUALES DEBE PASAR EL PRESUPUESTO
    ESTADO_PRESUPUESTO character varying (1) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_PRESUPUESTO.FECHA_INICIO
IS
  'FECHA DE APERTURA DEL PRESUPUESTO' ;
  COMMENT ON COLUMN PSY_PRESUPUESTO.FECHA_FIN
IS
  'FECHA EN LA QUE SE CIERRA EL PRESUPUESTO' ;
  COMMENT ON COLUMN PSY_PRESUPUESTO.ESTADO_PRESUPUESTO
IS
  'LOS ESTADOS POR LOS CUALES DEBE PASAR EL PRESUPUESTO' ;
  COMMENT ON COLUMN PSY_PRESUPUESTO.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_PRESUPUESTO ADD CONSTRAINT PSY_PRESUPUESTO_PK PRIMARY KEY ( PRES_CODIGO ) ;


CREATE TABLE PSY_PROGRAMA
  (
    PROG_CODIGO     bigserial NOT NULL ,
    NOMBRE          character varying (250) NOT NULL ,
    ESTADO_REGISTRO character varying (1)
  ) ;
ALTER TABLE PSY_PROGRAMA ADD CONSTRAINT PSY_PROGRAMA_PK PRIMARY KEY ( PROG_CODIGO ) ;


CREATE TABLE PSY_PROVINCIA
  (
    PROV_CODIGO bigserial NOT NULL ,
    PAIS_CODIGO bigint NOT NULL ,
    --  NOMBRE DE LA PROVINCIA, DEPARTAMENTO O ESTADO
    NOMBRE          character varying (200) NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_PROVINCIA.NOMBRE
IS
  'NOMBRE DE LA PROVINCIA, DEPARTAMENTO O ESTADO' ;
  COMMENT ON COLUMN PSY_PROVINCIA.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_PROVINCIA ADD CONSTRAINT PSY_PROVINCIA_PK PRIMARY KEY ( PROV_CODIGO ) ;

CREATE TABLE PSY_RESPONSABLE
  (
    RESP_CODIGO bigserial NOT NULL ,
    EMPR_CODIGO bigint NOT NULL ,
    --  NOMBRE DEL RESPONSABLE AMBIENTAL
    NOMBRE character varying (255) NOT NULL ,
    --  EMAIL DEL RESPONSABLE
    EMAIL character varying (100) NOT NULL ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1)
  ) ;
COMMENT ON COLUMN PSY_RESPONSABLE.NOMBRE
IS
  'NOMBRE DEL RESPONSABLE AMBIENTAL' ;
  COMMENT ON COLUMN PSY_RESPONSABLE.EMAIL
IS
  'EMAIL DEL RESPONSABLE' ;
  COMMENT ON COLUMN PSY_RESPONSABLE.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_RESPONSABLE ADD CONSTRAINT PSY_RESPONSABLE_PK PRIMARY KEY ( RESP_CODIGO ) ;

CREATE TABLE PSY_SEGUIMIENTO_TAREA
  (
    SETA_CODIGO bigserial NOT NULL ,
    TARE_CODIGO bigint NOT NULL ,
    DESCRIPCION character varying (2000) NOT NULL ,
    --  FECHA EN LA QUE REGISTRO EL SEGUMIENTO
    FECHA DATE ,
    --  A-ACTIVO, I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_SEGUIMIENTO_TAREA.FECHA
IS
  'FECHA EN LA QUE REGISTRO EL SEGUMIENTO' ;
  COMMENT ON COLUMN PSY_SEGUIMIENTO_TAREA.ESTADO_REGISTRO
IS
  'A-ACTIVO, I-INACTIVO' ;
  ALTER TABLE PSY_SEGUIMIENTO_TAREA ADD CONSTRAINT PSY_SEGUIMIENTO_TAREA_PK PRIMARY KEY ( SETA_CODIGO ) ;

CREATE TABLE PSY_SUBTEMA
  (
    CODIGO          bigserial NOT NULL ,
    TEMA_CODIGO     bigint NOT NULL ,
    DESCRIPCION     character varying (100) NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
ALTER TABLE PSY_SUBTEMA ADD CONSTRAINT PSY_SUBTEMA_PK PRIMARY KEY ( CODIGO ) ;

CREATE TABLE PSY_TAREA
  (
    TARE_CODIGO bigserial NOT NULL ,
    PLAC_CODIGO bigint NOT NULL ,
    RESP_CODIGO bigint NOT NULL ,
    --  DESCRIPCION DE LA TAREA
    DESCRIPCION character varying (2000) ,
    --  FECHA INICIO DE LA TAREA
    FECHA_INICIO DATE NOT NULL ,
    --  FECHA EN QUE SE PLANEA TERMINAR LA TAREA
    FECHA_FIN_PLANEADA DATE NOT NULL ,
    --  FECHA REAL EN QUE SE TERMINA LA TAREA
    FECHA_FIN_REAL DATE ,
    --  NUMERO DE LA SEMANA EN QUE SE PLANEA TERMINAR LA TAREA
    SEMANA_FIN_PLANEADA NUMERIC (3) NOT NULL ,
    --  SEMANA REAL DE FINALIZACION DE LA TAREA
    SEMANA_FIN_REAL NUMERIC (3) ,
    --  ESTADOS POR LOS CUALES PUEDE PASAR LA TAREA
    ESTADO_TAREA character varying (1) NOT NULL ,
    --  A-ACTIVO,I-INACTIVO
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_TAREA.DESCRIPCION
IS
  'DESCRIPCION DE LA TAREA' ;
  COMMENT ON COLUMN PSY_TAREA.FECHA_INICIO
IS
  'FECHA INICIO DE LA TAREA' ;
  COMMENT ON COLUMN PSY_TAREA.FECHA_FIN_PLANEADA
IS
  'FECHA EN QUE SE PLANEA TERMINAR LA TAREA' ;
  COMMENT ON COLUMN PSY_TAREA.FECHA_FIN_REAL
IS
  'FECHA REAL EN QUE SE TERMINA LA TAREA' ;
  COMMENT ON COLUMN PSY_TAREA.SEMANA_FIN_PLANEADA
IS
  'NUMERO DE LA SEMANA EN QUE SE PLANEA TERMINAR LA TAREA' ;
  COMMENT ON COLUMN PSY_TAREA.SEMANA_FIN_REAL
IS
  'SEMANA REAL DE FINALIZACION DE LA TAREA' ;
  COMMENT ON COLUMN PSY_TAREA.ESTADO_TAREA
IS
  'ESTADOS POR LOS CUALES PUEDE PASAR LA TAREA' ;
  ALTER TABLE PSY_TAREA ADD CONSTRAINT PSY_TAREA_PK PRIMARY KEY ( TARE_CODIGO ) ;

CREATE TABLE PSY_TEMA
  (
    CODIGO          bigserial NOT NULL ,
    IMAM_CODIGO     bigint NOT NULL ,
    DESCRIPCION     character varying (50) NOT NULL ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
ALTER TABLE PSY_TEMA ADD CONSTRAINT PSY_TEMA_PK PRIMARY KEY ( CODIGO ) ;

CREATE TABLE PSY_TIPO_ITEM_PRESUPUESTO
  (
    TIIP_CODIGO bigserial NOT NULL ,
    --  NOMBRE DEL TIPO DE PRESUPUESTO
    NOMBRE          character varying (100) NOT NULL ,
    DESCRIPCION     character varying (2000) ,
    ESTADO_REGISTRO character varying (1) NOT NULL
  ) ;
COMMENT ON COLUMN PSY_TIPO_ITEM_PRESUPUESTO.NOMBRE
IS
  'NOMBRE DEL TIPO DE PRESUPUESTO' ;
  COMMENT ON COLUMN PSY_TIPO_ITEM_PRESUPUESTO.DESCRIPCION
IS
  'DESCRIPCION DEL TIPO DE PRESUPUESTO' ;
  COMMENT ON COLUMN PSY_TIPO_ITEM_PRESUPUESTO.ESTADO_REGISTRO
IS
  'A-ACTICO, I-INACTICO' ;
  ALTER TABLE PSY_TIPO_ITEM_PRESUPUESTO ADD CONSTRAINT PSY_TIPO_ITEM_PRESUPUESTO_PK PRIMARY KEY ( TIIP_CODIGO ) ;


-- FOREIGN KEYS
ALTER TABLE PSY_CIUDAD ADD CONSTRAINT CIUDAD_PROVINCIA_FK FOREIGN KEY ( PROV_CODIGO ) REFERENCES PSY_PROVINCIA ( PROV_CODIGO ) ;

ALTER TABLE PSY_DETALLE_ERIDA ADD CONSTRAINT DEER_ASAM_FK FOREIGN KEY ( ASAM_CODIGO ) REFERENCES PSY_ASUNTO_AMBIENTAL ( ASAM_CODIGO ) ;

ALTER TABLE PSY_DETALLE_ERIDA ADD CONSTRAINT DEER_IMAM_FK FOREIGN KEY ( IMAM_CODIGO ) REFERENCES PSY_IMPACTO_AMBIENTAL ( IMAM_CODIGO ) ;

ALTER TABLE PSY_DETALLE_ERIDA ADD CONSTRAINT DEER_MAER_FK FOREIGN KEY ( MAER_CODIGO ) REFERENCES PSY_MATRIZ_ERIDA ( MAER_CODIGO ) ;

ALTER TABLE PSY_PLAN_ESTRATEGIA ADD CONSTRAINT DMAE_PLES_FK FOREIGN KEY ( DMAE_CODIGO ) REFERENCES PSY_DETALLE_MAPA_ESTRATEGICO ( DMAE_CODIGO ) ;

ALTER TABLE PSY_EMPRESA ADD CONSTRAINT EMPRESA_CIUDAD_FK FOREIGN KEY ( CIUD_CODIGO ) REFERENCES PSY_CIUDAD ( CIUD_CODIGO ) ;

ALTER TABLE PSY_EMPRESA ADD CONSTRAINT EMPRESA_PERSONA_FK FOREIGN KEY ( PERS_CODIGO ) REFERENCES PSY_PERSONA ( PERS_CODIGO ) ;

ALTER TABLE PSY_EVALUACION_PEA_INDICADOR ADD CONSTRAINT EVAIND_PEA_FK FOREIGN KEY ( PEA_CODIGO ) REFERENCES PSY_PLAN_ESTRATEGICO_AMBIENTAL ( CODIGO ) ;

ALTER TABLE PSY_EVALUACION_PEA_OBJETIVO ADD CONSTRAINT EVAOB_OBAM_FK FOREIGN KEY ( OBAM_CODIGO ) REFERENCES PSY_OBJETIVO_AMBIENTAL ( CODIGO ) ;

ALTER TABLE PSY_EVALUACION_PEA_OBJETIVO ADD CONSTRAINT EVAOB_PEA_FK FOREIGN KEY ( PEA_CODIGO ) REFERENCES PSY_PLAN_ESTRATEGICO_AMBIENTAL ( CODIGO ) ;

ALTER TABLE PSY_EVALUACION_PEA_INDICADOR ADD CONSTRAINT EVAPEA_INDI_FK FOREIGN KEY ( INDI_CODIGO ) REFERENCES PSY_INDICADOR ( CODIGO ) ;

ALTER TABLE PSY_IMPACTO_OBJETIVO ADD CONSTRAINT IMOB_IMAM_FK FOREIGN KEY ( IMAM_CODIGO ) REFERENCES PSY_IMPACTO_AMBIENTAL ( IMAM_CODIGO ) ;

ALTER TABLE PSY_IMPACTO_OBJETIVO ADD CONSTRAINT IMOB_OBAM_FK FOREIGN KEY ( OBAM_CODIGO ) REFERENCES PSY_OBJETIVO_AMBIENTAL ( CODIGO ) ;

ALTER TABLE PSY_INDICADOR ADD CONSTRAINT INDI_OBAM_FK FOREIGN KEY ( OBAM_CODIGO ) REFERENCES PSY_OBJETIVO_AMBIENTAL ( CODIGO ) ;

ALTER TABLE PSY_INDICADOR ADD CONSTRAINT INDI_SUBTE_FK FOREIGN KEY ( SUBTE_CODIGO ) REFERENCES PSY_SUBTEMA ( CODIGO ) ;

ALTER TABLE PSY_ITEM_PRESUPUESTO ADD CONSTRAINT IPRE_PRES_FK FOREIGN KEY ( PRES_CODIGO ) REFERENCES PSY_PRESUPUESTO ( PRES_CODIGO ) ;

ALTER TABLE PSY_ITEM_PRESUPUESTO ADD CONSTRAINT IPRE_TIIP_FK FOREIGN KEY ( TIIP_CODIGO ) REFERENCES PSY_TIPO_ITEM_PRESUPUESTO ( TIIP_CODIGO ) ;

ALTER TABLE PSY_IPU ADD CONSTRAINT IPU_PLAC_FK FOREIGN KEY ( PLAC_CODIGO ) REFERENCES PSY_PLAN_ACCION ( PLAC_CODIGO ) ;

ALTER TABLE PSY_MATRIZ_CORRELACION ADD CONSTRAINT MACO_ESAM_FK FOREIGN KEY ( ESAM_CODIGO ) REFERENCES PSY_ESTRATEGIA_AMBIENTAL ( ESAM_CODIGO ) ;

ALTER TABLE PSY_MATRIZ_CORRELACION ADD CONSTRAINT MACO_IMAM_FK FOREIGN KEY ( IMAM_CODIGO ) REFERENCES PSY_IMPACTO_AMBIENTAL ( IMAM_CODIGO ) ;

ALTER TABLE PSY_MATRIZ_CORRELACION ADD CONSTRAINT MACO_OBES_FK FOREIGN KEY ( OBES_CODIGO ) REFERENCES PSY_OBJETIVO_ESTRATEGICO ( OBES_CODIGO ) ;

ALTER TABLE PSY_MATRIZ_ENCUESTA ADD CONSTRAINT MAEN_PEST_FK FOREIGN KEY ( PEST_CODIGO ) REFERENCES PSY_PLAN_ESTRATEGICO ( PEST_CODIGO ) ;

ALTER TABLE PSY_MATRIZ_ERIDA ADD CONSTRAINT MAER_PEST_FK FOREIGN KEY ( PEST_CODIGO ) REFERENCES PSY_PLAN_ESTRATEGICO ( PEST_CODIGO ) ;

ALTER TABLE PSY_DETALLE_MAPA_ESTRATEGICO ADD CONSTRAINT MAES_DMAE_FK FOREIGN KEY ( MAES_CODIGO ) REFERENCES PSY_MAPA_ESTRATEGICO ( MAES_CODIGO ) ;

ALTER TABLE PSY_DETALLE_MAPA_ESTRATEGICO ADD CONSTRAINT MAES_MACO_FK FOREIGN KEY ( MACO_CODIGO ) REFERENCES PSY_MATRIZ_CORRELACION ( MACO_CODIGO ) ;

ALTER TABLE PSY_PRESUPUESTO ADD CONSTRAINT MONE_PRES_FK FOREIGN KEY ( MONE_CODIGO ) REFERENCES PSY_MONEDA ( MONE_CODIGO ) ;

ALTER TABLE PSY_OBJETIVO_IMPACTO ADD CONSTRAINT OBIM_IMAM_FK FOREIGN KEY ( IMAM_CODIGO ) REFERENCES PSY_IMPACTO_AMBIENTAL ( IMAM_CODIGO ) ;

ALTER TABLE PSY_OBJETIVO_IMPACTO ADD CONSTRAINT OBIM_OBES_FK FOREIGN KEY ( OBES_CODIGO ) REFERENCES PSY_OBJETIVO_ESTRATEGICO ( OBES_CODIGO ) ;

ALTER TABLE PSY_DETALLE_OBJETIVO_PLAN ADD CONSTRAINT OBPL_DOBP_FK FOREIGN KEY ( OBPL_CODIGO ) REFERENCES PSY_OBJETIVO_PLAN ( OBPL_CODIGO ) ;

ALTER TABLE PSY_DETALLE_OBJETIVO_PLAN ADD CONSTRAINT OBPL_OBES_FK FOREIGN KEY ( OBES_CODIGO ) REFERENCES PSY_OBJETIVO_ESTRATEGICO ( OBES_CODIGO ) ;

ALTER TABLE PSY_PLAN_ESTRATEGICO_AMBIENTAL ADD CONSTRAINT PEA_PLES_FK FOREIGN KEY ( PEST_CODIGO ) REFERENCES PSY_PLAN_ESTRATEGICO ( PEST_CODIGO ) ;

ALTER TABLE PSY_PERSONA ADD CONSTRAINT PERSONA_EMPRESA_FK FOREIGN KEY ( EMPR_CODIGO ) REFERENCES PSY_EMPRESA ( EMPR_CODIGO ) ;

ALTER TABLE PSY_PLAN_ESTRATEGICO ADD CONSTRAINT PEST_EMPR_FK FOREIGN KEY ( EMPR_CODIGO ) REFERENCES PSY_EMPRESA ( EMPR_CODIGO ) ;

ALTER TABLE PSY_MAPA_ESTRATEGICO ADD CONSTRAINT PEST_MAES_FK FOREIGN KEY ( PEST_CODIGO ) REFERENCES PSY_PLAN_ESTRATEGICO ( PEST_CODIGO ) ;

ALTER TABLE PSY_OBJETIVO_PLAN ADD CONSTRAINT PEST_OBPL_FK FOREIGN KEY ( PEST_CODIGO ) REFERENCES PSY_PLAN_ESTRATEGICO ( PEST_CODIGO ) ;

ALTER TABLE PSY_PRESUPUESTO ADD CONSTRAINT PRES_PLAC_FK FOREIGN KEY ( PLAC_CODIGO ) REFERENCES PSY_PLAN_ACCION ( PLAC_CODIGO ) ;

ALTER TABLE PSY_PROVINCIA ADD CONSTRAINT PROVINCIA_PAIS_FK FOREIGN KEY ( PAIS_CODIGO ) REFERENCES PSY_PAIS ( PAIS_CODIGO ) ;

ALTER TABLE PSY_PLAN_ACCION ADD CONSTRAINT PSY_PLAC_PROG_FK FOREIGN KEY ( PROG_CODIGO ) REFERENCES PSY_PROGRAMA ( PROG_CODIGO ) ;

ALTER TABLE PSY_PLAN_ESTRATEGIA ADD CONSTRAINT PSY_PLES_PROG_FK FOREIGN KEY ( PROG_CODIGO ) REFERENCES PSY_PROGRAMA ( PROG_CODIGO ) ;

ALTER TABLE PSY_RESPONSABLE ADD CONSTRAINT RESP_EMPR_FK FOREIGN KEY ( EMPR_CODIGO ) REFERENCES PSY_EMPRESA ( EMPR_CODIGO ) ;

ALTER TABLE PSY_SEGUIMIENTO_TAREA ADD CONSTRAINT SETA_TARE_FK FOREIGN KEY ( TARE_CODIGO ) REFERENCES PSY_TAREA ( TARE_CODIGO ) ;

ALTER TABLE PSY_SUBTEMA ADD CONSTRAINT SUBTEMA_TEMA_FK FOREIGN KEY ( TEMA_CODIGO ) REFERENCES PSY_TEMA ( CODIGO ) ;

ALTER TABLE PSY_TAREA ADD CONSTRAINT TARE_PLAC_FK FOREIGN KEY ( PLAC_CODIGO ) REFERENCES PSY_PLAN_ACCION ( PLAC_CODIGO ) ;

ALTER TABLE PSY_TAREA ADD CONSTRAINT TARE_RESP_FK FOREIGN KEY ( RESP_CODIGO ) REFERENCES PSY_RESPONSABLE ( RESP_CODIGO ) ;

ALTER TABLE PSY_TEMA ADD CONSTRAINT TEMA_IMPACTO_AMBIENTAL_FK FOREIGN KEY ( IMAM_CODIGO ) REFERENCES PSY_IMPACTO_AMBIENTAL ( IMAM_CODIGO ) ;


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            38
-- CREATE INDEX                             0
-- ALTER TABLE                             83
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
