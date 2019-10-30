--INSERTS DE PARAMETROS DE PUSAY
INSERT INTO psy_parametro (llave, valor, estado_registro) VALUES ('urlServiciosRest','http://127.0.0.1:8080/seguridad_system/controller/loginRestService/','A');
INSERT INTO psy_parametro (llave, valor, estado_registro) VALUES ('urlCorreoRecuperacion','http://localhost:8080/pusay-web/recuperarClave.xhtml','A');

	----------------------------------------------------------------------------------------
------Inserts para los psy_impactos_ambientales-----------------------------------------
--contiene los impactoa ambientales encontrados en los libros de ecoconsulta------------
----------------------------------------------------------------------------------------
INSERT INTO psy_impacto_ambiental (imam_codigo, nombre, descripcion, estado_registro) VALUES (1, 'Financiero', 'Financiero', 'A');
INSERT INTO psy_impacto_ambiental (imam_codigo, nombre, descripcion, estado_registro) VALUES (2, 'Reputacion corporativa', 'Reputacion corporativa', 'A');
INSERT INTO psy_impacto_ambiental (imam_codigo, nombre, descripcion, estado_registro) VALUES (3, 'Relaciones laborales', 'Relaciones laborales', 'A');
INSERT INTO psy_impacto_ambiental (imam_codigo, nombre, descripcion, estado_registro) VALUES (4, 'Objetivos de mercadeo', 'Objetivos de mercadeo', 'A');
INSERT INTO psy_impacto_ambiental (imam_codigo, nombre, descripcion, estado_registro) VALUES (5, 'Exposicion actual de regulaciones', 'Exposicion actual de regulaciones', 'A');
INSERT INTO psy_impacto_ambiental (imam_codigo, nombre, descripcion, estado_registro) VALUES (6, 'Dependencia recursos naturales', 'Dependencia recursos naturales', 'A');

--Modificar secuencia para la tabla psy_impacto_ambiental
ALTER SEQUENCE psy_impacto_ambiental_imam_codigo_seq RESTART WITH 7;


------------------------------------------------------------------------------------------
------------Inserts para la tabla psy_estrategia_ambiental--------------------------------
-------------Las estrategias estan basadas en el libro de edgar------------------------------------
------------------------------------------------------------------------------------------

INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (1, 'Diseño para la sostenibilidad', 'Diseño para la sostenibilidad', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (2, 'Eco-efectividad', 'Eco-efectividad', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (3, 'Eco-eficiencia', 'Eco-eficiencia', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (4, 'Contabilidad medioambiental integrada', 'Contabilidad medioambiental integrada', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (5, 'Responsabilidad empresarial extendida', 'Responsabilidad empresarial extendida', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (6, 'Eco-diseño', 'Eco-diseño', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (7, 'Evaluacion del ciclo de vida', 'Evaluacion del ciclo de vida', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (8, 'Sistemas de gestion ambiental', 'Sistemas de gestion ambiental', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (9, 'Produccion mas limpia', 'Produccion mas limpia', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (10, 'Reduccion de costos', 'Reduccion de costos', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (11, 'Control de contaminacion', 'Control de contaminacion', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (12, 'Cumplimiento', 'Cumplimiento', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (13, 'Incumplimiento', 'Incumplimiento', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (14, 'Residuos', 'Residuos', 'A');
INSERT INTO psy_estrategia_ambiental (esam_codigo, nombre, descripcion, estado_registro) VALUES (15, 'Contaminacion', 'Contaminacion', 'A');

--modificar secuencia para la tabla psy_estrategia_ambiental
ALTER SEQUENCE psy_estrategia_ambiental_esam_codigo_seq RESTART WITH 16;

------------------------------------------------------------------------------------------
------------Inserts para la tabla psy_objetivo_estrategico--------------------------------
--------------------------encontrados en los libros de ecoconsulta------------------------
------------------------------------------------------------------------------------------
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (1, 'PLAN DE MODERNIZACION', 'PLAN DE MODERNIZACION', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (2, 'VENTAS', 'VENTAS', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (3, 'RENTABILIDAD Y UTILIDADDES', 'RENTABILIDAD Y UTILIDADDES', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (4, 'RESULTADO PARA LOS ACCIONISTAS', 'RESULTADO PARA LOS ACCIONISTAS', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (5, 'FIDELIZAR AL CONSUMIDOR', 'FIDELIZAR AL CONSUMIDOR', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (6, 'EXPANSION TERRITORIAL', 'EXPANSION TERRITORIAL', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (7, 'IMAGEN CORPORATIVA', 'IMAGEN CORPORATIVA', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (8, 'RESPONSABILIDAD SOCIAL', 'RESPONSABILIDAD SOCIAL', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (9, 'CALIDAD DEL PRODUCTO', 'CALIDAD DEL PRODUCTO', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (10, 'DESARROLLO DEL TALENTO', 'DESARROLLO DEL TALENTO', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (11, 'EFICIENCIA EN LA ADMINISTRACION DE LOS RECURSOS', 'EFICIENCIA EN LA ADMINISTRACION DE LOS RECURSOS', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (12, 'INNOVACION', 'INNOVACION', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (13, 'PRODUCTIVIDAD', 'PRODUCTIVIDAD', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (14, 'PENETRAR NUEVOS MERCADOS', 'PENETRAR NUEVOS MERCADOS', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (15, 'SERVICIO AL CLIENTE', 'SERVICIO AL CLIENTE', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (16, 'PARTICIPACION EN EL MERCADO', 'PARTICIPACION EN EL MERCADO', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (17, 'TECNOLOGIA', 'TECNOLOGIA', 'A');
INSERT INTO psy_objetivo_estrategico (obes_codigo, nombre, descripcion, estado_registro) VALUES (18, 'CRECIMIENTO FINANCIERO', 'CRECIMIENTO FINANCIERO', 'A');



--modificar secuencia para la tabla psy_estrategia_ambiental
ALTER SEQUENCE psy_objetivo_estrategico_obes_codigo_seq RESTART WITH 19;

------------------------------------------------------------------------------------------
------------Inserts para la tabla psy_asunto_ambiental--------------------------------
--------------------------encontrados en los libros de ecoconsulta------------------------
------------------------------------------------------------------------------------------

INSERT INTO psy_asunto_ambiental (asam_codigo, nombre, descripcion, estado_registro) VALUES (1, 'EMISIONES', 'EMISIONES', 'A');
INSERT INTO psy_asunto_ambiental (asam_codigo, nombre, descripcion, estado_registro) VALUES (2, 'VERTIMIENTOS', 'VERTIMIENTOS', 'A');
INSERT INTO psy_asunto_ambiental (asam_codigo, nombre, descripcion, estado_registro) VALUES (3, 'RESIDUOS SOLIDOS', 'RESIDUOS SOLIDOS', 'A');
INSERT INTO psy_asunto_ambiental (asam_codigo, nombre, descripcion, estado_registro) VALUES (4, 'MATERIA PRIMA', 'MATERIA PRIMA', 'A');
INSERT INTO psy_asunto_ambiental (asam_codigo, nombre, descripcion, estado_registro) VALUES (5, 'RIESGOS TECNOLOGICOS', 'RIESGOS TTECNOLOGICOS', 'A');
INSERT INTO psy_asunto_ambiental (asam_codigo, nombre, descripcion, estado_registro) VALUES (6, 'EFICIENCIA ENERGETICA', 'EFICIENCIA ENERGETICA', 'A');

--modificar secuencia para la tabla psy_asunto_ambiental
ALTER SEQUENCE psy_asunto_ambiental_asam_codigo_seq RESTART WITH 7;

------------------------------------------------------------------------------------------
------------Inserts para la tabla psy_empresa---------------------------------------------
--------------------------Empresa para pruebas-----------------------------------------
------------------------------------------------------------------------------------------
INSERT INTO psy_empresa (empr_codigo, ciud_codigo, pers_codigo, nit, nombre, direccion_principal, telefono_principal, estado_registro) VALUES (1, 1071, NULL, '900239769', 'Pusay', 'Avenida 4N # 2-78', 5546632, 'A');

--modificar secuencia para la tabla psy_asunto_ambiental
ALTER SEQUENCE psy_empresa_empr_codigo_seq RESTART WITH 2;


------------------------------------------------------------------------------------------
------------Inserts para la tabla psy_persona---------------------------------------------
--------------------------Persona para pruebas-----------------------------------------
------------------------------------------------------------------------------------------

INSERT INTO psy_persona (pers_codigo, empr_codigo, nombre, email, telefono, estado_registro) VALUES (1, 1, 'Edgar Quiñonez', 'ecoconsulta@hotmail.com', 3117468135, 'A');

--modificar secuencia para la tabla psy_persona
ALTER SEQUENCE psy_persona_pers_codigo_seq RESTART WITH 2;

-----------------------------------------------------------------------------------------
---------------Se actualiza la tabla de empresa para asignarle la persona creada--------
---------------------------------------------------------------------------------------
UPDATE psy_empresa SET pers_codigo=1 WHERE empr_codigo=1;

------------------------------------------------------------------------------------------
-----------Inserts para la tabla psy_plan_estrategico ----------------------------------
----------- Es un plan de prueba-------------
--INSERT INTO psy_plan_estrategico (pest_codigo, empr_codigo, nombre, fecha_inicio, fecha_fin, descripcion, estado_plan, estado_registro) VALUES (1, 1, 'PLAN DE PRUEBA :)', '2015-04-08', '2015-04-14', 'PLAN PARA PRUEBA', 'A', 'A');
--ALTER SEQUENCE psy_plan_estrategico_pest_codigo_seq RESTART WITH 2;

------------------------------------------------------------------------------------------
-----------Inserts para la tabla psy_matriz_correlacion ----------------------------------
----------- Matriz correlacion los datos los proporciono el Ing. Edgar-------------
--	FINANCIERO																						o  i  e  
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 1, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 1, 2, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 1, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 1, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 1, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 1, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 1, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 1, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 1, 4, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 1, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 1, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 1, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 1, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 1, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 1, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 1, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 1, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 1, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 1, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 1, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 1, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 1, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 1, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 1, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 1, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 1, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 1, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 1, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 1, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 1, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 1, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 1, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 1, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 1, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 1, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 1, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 1, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 1, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 1, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 1, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 1, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 1, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 1, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 1, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 1, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 1, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 1, 2, 'A');

--	REPUTACION CORPORATIVA																			o  i  e  
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 2, 3, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 2, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 2, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 2, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 2, 4, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 2, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 2, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 2, 4, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 2, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 2, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 2, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 2, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 2, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 2, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 2, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 2, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 2, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 2, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 2, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 2, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 2, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 2, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 2, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 2, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 2, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 2, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 2, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 2, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 2, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 2, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 2, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 2, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 2, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 2, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 2, 2, 'A');

--	RELACIONES LABORALES																			o  i  e  
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 3, 9, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 3, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 3, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 3, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 3, 8, 'A');

--	OBJETIVOS DE MERCADEO																			o  i  e  
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 4, 8, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 4, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 4, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 4, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 4, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 4, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 4, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 4, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 4, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 4, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 4, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 4, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 4, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 4, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 4, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 4, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 4, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 4, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 4, 8, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 4, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 4, 1, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 4, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 4, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 4, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 4, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 4, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 4, 5, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 4, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 4, 7, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 4, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 4, 2, 'A');

--	EXPOSICION ACTUAL A REGULACIONES																o  i  e  
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 5, 9, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 5, 9, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 5, 11, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 5, 9, 'A');

--	EXPOSICION ACTUAL A REGULACIONES																o  i  e  
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (2, 6, 7, 'A');  
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (3, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (4, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (5, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (6, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (7, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (8, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (9, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (10, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (11, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (12, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (13, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (14, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (15, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (16, 6, 7, 'A'); 
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 6, 3, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 6, 2, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 6, 6, 'A');
INSERT INTO psy_matriz_correlacion (obes_codigo, imam_codigo, esam_codigo, estado_registro) VALUES (17, 6, 7, 'A'); 

--------------------------------------------------------------------------------------------
--------------------- Insert item_presupuesto---------------------------
----------------------------Items presupuesto tomados de vortexbird------------------------------

INSERT INTO psy_tipo_item_presupuesto (nombre, descripcion, estado_registro) VALUES ('PERSONAL', 'PERSONAL', 'A');
INSERT INTO psy_tipo_item_presupuesto (nombre, descripcion, estado_registro) VALUES ('VIATICOS', 'VIATICOS', 'A');
INSERT INTO psy_tipo_item_presupuesto (nombre, descripcion, estado_registro) VALUES ('TECNOLOGIA', 'TECNOLOGIA', 'A');
INSERT INTO psy_tipo_item_presupuesto (nombre, descripcion, estado_registro) VALUES ('INSUMOS', 'INSUMOS', 'A');
INSERT INTO psy_tipo_item_presupuesto (nombre, descripcion, estado_registro) VALUES ('CAPACITACION', 'CAPACITACION', 'A');
INSERT INTO psy_tipo_item_presupuesto (nombre, descripcion, estado_registro) VALUES ('ASESORIA Y FORMACION', 'ASESORIA Y FORMACION', 'A');


------------------------------------------------------------------------------------------
----------------------INSERTS PARA LA TABLA OBJETIVO IMPACTO------------------------------
------------------------------------------------------------------------------------------
--																					 o i						
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (2,1,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (3,1,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (4,1,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (5,2,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (6,2,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (7,2,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (8,2,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (8,3,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (9,3,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (10,3,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (11,3,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (12,3,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (13,3,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (16,4,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (2,4,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (15,4,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (5,4,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (14,4,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (17,5,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (12,5,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (13,5,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (7,5,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (8,5,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (17,6,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (12,6,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (13,6,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (9,6,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (11,6,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (7,6,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (2,6,'A');
INSERT INTO psy_objetivo_impacto (obes_codigo, imam_codigo, estado_registro) VALUES (6,6,'A');



------------------------------------------------------------------------------------------
-----------Inserts para la tabla psy_mapa_estrategico ----------------------------------
----------- Mapa estrategico para pruebas------------------------------------------------

--INSERT INTO psy_mapa_estrategico (maes_codigo, pest_codigo, fecha_inicio, fecha_fin, estado_mapa_estrategico, estado_registro) VALUES (1, 1, '2015-04-02', '2015-10-01', 'A', 'A');
--ALTER SEQUENCE psy_mapa_estrategico_maes_codigo_seq RESTART WITH 2;

------------------------------------------------------------------------------------------
-----------Inserts para la tabla psy_detalle_mapa_estrategico ----------------------------------
----------- Mapa estrategico para pruebas------------------------------------------------

--INSERT INTO psy_detalle_mapa_estrategico (dmae_codigo, maes_codigo, estado_registro) VALUES (1, 1, 1, 'A');
--INSERT INTO psy_detalle_mapa_estrategico (dmae_codigo, maes_codigo, estado_registro) VALUES (2, 3, 1, 'A');
--INSERT INTO psy_detalle_mapa_estrategico (dmae_codigo, maes_codigo, estado_registro) VALUES (3, 5, 1, 'A');
--ALTER SEQUENCE psy_detalle_mapa_estrategico_dmae_codigo_seq RESTART WITH 4;