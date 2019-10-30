--INSERT INTO USUARIO
INSERT INTO seg_usuario VALUES (0, 'SA', 'SA', 'SA_SEGURIDAD', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', 0, '2015-06-17', 'avargas@vortexbird.com', 0, NULL);

-- INSERT INTO SEG_COMPANIA
INSERT INTO seg_compania VALUES (1, 'CO', 'ECOPUSAY.COM', '1', 0);

--INSERT INTO USUARIO
INSERT INTO seg_usuario VALUES (1, 'ADMIN PUSAY', 'ADMIN PUSAY', 'ADMIN_PUSAY', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', 0, '2015-06-17', 'avargas@vortexbird.com', 0, 1);

-- INSERT INTO SISTEMA
INSERT INTO seg_sistema VALUES (1, 'SISTEMA DE SEGURIDAD', 'SISTEMA DE SEGURIDAD', '1', 0);
INSERT INTO seg_sistema VALUES (2, 'PUSAY', 'PUSAY', '1', 0);

--INSERT INTO SISTEMA_COMPANIA
INSERT INTO seg_sistema_cia VALUES (1, 1, 1, '1', 0);
INSERT INTO seg_sistema_cia VALUES (2, 2, 1, '1', 0);

-- INSERT INTO ROL
INSERT INTO seg_rol VALUES (0, 'ADMIN SEGURIDAD', 'ADMINISTRADOR DEL SISTEMA DE SEGURIDAD', 999, 1, '1', 0);
INSERT INTO seg_rol VALUES (1, 'ADMIN PUSAY', 'ADMINISTRADOR DE PUSAY', 999, 2, '1', 0);
INSERT INTO seg_rol VALUES (2, 'RESPONSABLE AMBIENTAL', 'RESPONSABLE AMBIENTAL', 999, 2, '1', 0);
INSERT INTO seg_rol VALUES (3, 'USUARIO CONSULTA', 'USUARIO CONSULTA', 999, 2, '1', 0);

--INSERT INTO ROL_USUARIO
INSERT INTO seg_rol_usuario VALUES (0, 0, 0, '1', 0);
INSERT INTO seg_rol_usuario VALUES (1, 1, 1, '1', 1);

--INSERT INTO SUCURSAL
INSERT INTO seg_sucursal VALUES (1, '01', 1, 'Seccional Cali', '1', 0);

-- INSERT INTO PARAMETRO *
INSERT INTO seg_parametro VALUES (1, 'par 1', 1.0000, 'pr', '0001-04-16 BC', '1', 0);
INSERT INTO seg_parametro VALUES (2, 'dias_caducidad_pwd', 45.0000, NULL, NULL, '1', 0);

-- INSERT INTO GRUPO_OPCION
INSERT INTO seg_grupo_opcion VALUES (1, 'SEGURIDAD', 'SEGURIDAD', NULL, '1', NULL, NULL, 1, 0);
INSERT INTO seg_grupo_opcion VALUES (3, 'Plan estrategico', 'Plan estrategico', NULL, '1', 'icon-puzzle', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (4, 'Evaluacion Rapida del Desempeño Ambiental', 'Evaluacion Rapida del Desempeño Ambiental', NULL, '1', 'icon-doc-text-inv', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (5, 'Diagnostico Estrategico Ambiental', 'Diagnostico Estrategico Ambiental', NULL, '1', 'icon-check-1', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (6, 'Informe Diagnostico Estrategico Ambiental', 'Informe Diagnostico Estrategico Ambiental', NULL, '1', 'icon-doc-text', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (7, 'Direccionamiento Estrategico', 'Direccionamiento Estrategico', NULL, '1', 'icon-clipboard', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (8, 'Programas', 'Programas', NULL, '1', 'icon-tasks', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (9, 'Proyectos', 'Proyectos', NULL, '1', 'icon-paper-plane', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (10, 'Presupuestos', 'Presupuestos', NULL, '1', 'icon-dollar', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (11, 'IPU', 'IPU', NULL, '1', 'icon-chart', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (12, 'Plan Estrategico Ambiental', 'Plan Estrategico Ambiental', NULL, '1', 'icon-leaf', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (13, 'Sistema de indicadores estrategicos ambientales', 'Sistema de indicadores estrategicos ambientales', NULL, '1', 'icon-chart-bar', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (14, 'Planeacion Estrategica Ambiental', 'Planeacion Estrategica Ambiental', NULL, '1', 'icon-book', NULL, 2, 0);
INSERT INTO seg_grupo_opcion VALUES (15, 'Configuracion', 'Configuracion', NULL, '1', 'icon-wrench', NULL, 2, 0);






-- INSERT INTO SEG_OPCION *
INSERT INTO seg_opcion VALUES (1, 'USUARIO', 'USUARIO', '/XHTML/segUsuario.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (2, 'SUCURSAL', 'SUCURSAL', '/XHTML/segSucursal.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (3, 'SISTEMA', 'SISTEMA', '/XHTML/segSistema.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (4, 'ROL', 'ROL', '/XHTML/segRol.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (6, 'ROL X USUARIO', 'ROL X USUARIO', '/XHTML/segRolUsuario.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (7, 'GRUPO OPCION', 'GRUPO OPCION', '/XHTML/segGrupoOpcion.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (8, 'OPCION X ROL', 'OPCION X ROL', '/XHTML/tree.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (9, 'AUDITORIA', 'AUDITORIA', '/XHTML/segAuditoria.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (10, 'COMPANIA', 'COMPANIA', '/XHTML/segCompania.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (11, 'PARAMETROS', 'PARAMETROS', '/XHTML/segParametro.xhtml', 1, '0', 0);
INSERT INTO seg_opcion VALUES (12, 'SISTEMA X COMPANIA', 'SISTEMA X COMPANIA', '/XHTML/segSistemaCia.xhtml', 1, '1', 0);
INSERT INTO seg_opcion VALUES (13, 'OPCION', 'OPCION', '/XHTML/segOpcion.xhtml', 1, '1', 0);
	----PLAN ESTRATEGICO
	INSERT INTO seg_opcion VALUES (14, 'Gestionar Plan Estrategico', 'Gestionar Plan Estrategico', '/XHTML/GestionarPlanEstrategico.xhtml', 3, '1', 0);
	INSERT INTO seg_opcion VALUES (15, 'Gestionar Objetivos Corporativos', 'Gestionar Objetivos Corporativos', '/XHTML/GestionarObjetivoCorporativo.xhtml', 3, '1', 0);
	----EVALUACION RAPIDA DEL DESEMPEÑO AMBIENTAL
	INSERT INTO seg_opcion VALUES (16, 'Diligenciar Encuesta Desempeño Ambiental', 'Diligenciar Encuesta Desempeño Ambiental', '#{psyPlanEstrategicoView.redirectDesempenoAmbiental()}', 4, '1', 0);
	INSERT INTO seg_opcion VALUES (17, 'Diligenciar Encuesta Grado de Afectacion', 'Diligenciar Encuesta Grado de Afectacion', '#{psyPlanEstrategicoView.redirectGradoDeAfectacion()}', 4, '1', 0);
	INSERT INTO seg_opcion VALUES (18, 'Diligenciar Encuesta Desempeño Nivel Tecnico', 'Diligenciar Encuesta Desempeño Nivel Tecnico', '#{psyPlanEstrategicoView.redirectNivelTecnico()}', 4, '1', 0);
	INSERT INTO seg_opcion VALUES (19, 'Resultado Encuestas', 'Resultado Encuestas', '/XHTML/ResultadoEncuestas.xhtml', 4, '1', 0);
	----DIAGNOSTICO ESTRATEGICO AMBIENTAL
	INSERT INTO seg_opcion VALUES (20, 'Gestionar Diagnostico Estrategico Ambiental', 'Gestionar Diagnostico Estrategico Ambiental', '/XHTML/GestionarErida.xhtml', 5, '1', 0);
	----INFORME DIAGNOSTICO ESTRATEGICO AMBIENTAL
	INSERT INTO seg_opcion VALUES (21, 'Generar Informe Diagnostico Estrategico Ambiental', 'Generar Informe Diagnostico Estrategico Ambiental', '/XHTML/GenerarDiagnosticoDefinitivo.xhtml', 6, '1', 0);
	----DIRECCIONAMIENTO ESTRATEGICO
	INSERT INTO seg_opcion VALUES (22, 'Gestionar Direccionamiento Estrategico', 'Gestionar Direccionamiento Estrategico', '/XHTML/GestionarMapaEstrategico.xhtml', 7, '1', 0);
	----PROGRAMAS
	INSERT INTO seg_opcion VALUES (23, 'Gestionar Programas', 'Gestionar Programas', '/XHTML/GestionarPrograma.xhtml', 8, '1', 0);
	----PROYECTOS
	INSERT INTO seg_opcion VALUES (24, 'Gestionar Proyectos', 'Gestionar Proyectos', '/XHTML/GestionarPlanAccion.xhtml', 9, '1', 0);
	INSERT INTO seg_opcion VALUES (25, 'Gestion Seleccion de Tareas', 'Gestion Seleccion de Tareas', '/XHTML/GestionarTareas.xhtml', 9, '1', 0);
	INSERT INTO seg_opcion VALUES (26, 'Gestionar Responsables(Proyectos)', 'Gestionar Responsables(Proyectos)', '/XHTML/GestionarResponsables.xhtml', 9, '1', 0);
	----PRESUPUESTOS
	INSERT INTO seg_opcion VALUES (27, 'Gestionar Presupuestos', 'Gestionar Presupuestos', '/XHTML/GestionarPresupuesto.xhtml', 10, '1', 0);
	INSERT INTO seg_opcion VALUES (28, 'Items del Presupuesto', 'Items del Presupuesto', '/XHTML/GestionarItemsPresupuesto.xhtml', 10, '1', 0);
	----IPU
	INSERT INTO seg_opcion VALUES (29, 'Generar IPU tiempo', 'Generar IPU tiempo', '//XHTML/GestionarIpu.xhtml', 11, '1', 0);
	INSERT INTO seg_opcion VALUES (30, 'Generar IPU presupuesto', 'Generar IPU presupuesto', '/XHTML/GestionarIpuPresupuesto.xhtml', 11, '1', 0);
	INSERT INTO seg_opcion VALUES (31, 'Generar informe de proyecto', 'Generar informe de proyecto', '/XHTML/GenerarInformeProyecto.xhtml', 11, '1', 0);
	----PLAN ESTRATEGICO AMBIENTAL
	INSERT INTO seg_opcion VALUES (32, 'Gestionar Plan Estrategico Ambiental', 'Gestionar Plan Estrategico Ambiental', '/XHTML/GestionarPlanEstrategicoAmbiental.xhtml', 12, '1', 0);
	----SISTEMAS DE INDICADORES ESTRATEGICOS AMBIENTALES
	INSERT INTO seg_opcion VALUES (33, 'Tablero de Mando', 'Tablero de Mando', '/XHTML/VisualizarImpactosAmbientales.xhtml', 13, '1', 0);
	INSERT INTO seg_opcion VALUES (34, 'Analisis de Resultados', 'Tablero de Mando', '/XHTML/EvaluarIndicadores.xhtml', 13, '1', 0);
	INSERT INTO seg_opcion VALUES (35, 'Analisis de Creacion de Valor', 'Analisis de Creacion de Valor', '/XHTML/VisualizarObjetivosAmbientales.xhtml', 13, '1', 0);
	----CONFIGURACION
	INSERT INTO seg_opcion VALUES (36, 'Gestionar Objetivos Estrategicos', 'Gestionar Objetivos Estrategicos', '/XHTML/GestionarObjetivoEstrategico.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (37, 'Gestionar Impactos Ambientales', 'Gestionar Impactos Ambientales', '/XHTML/GestionarImpactoAmbiental.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (38, 'Gestionar Asuntos Ambientales', 'Gestionar Asuntos Ambientales', '/XHTML/GestionarAsuntoAmbiental.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (39, 'Gestionar Estrategias Ambientales', 'Gestionar Estrategias Ambientales', '/XHTML/GestionarEstrategiaAmbiental.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (40, 'Gestionar Objetivos Ambientales', 'Gestionar Objetivos Ambientales', '/XHTML/GestionarObjetivoAmbiental.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (41, 'Gestionar Tipos de Item de Presupuesto', 'Gestionar Tipos de Item de Presupuesto', '/XHTML/GestionarTipoItemPresupuesto.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (42, 'Gestionar Monedas', 'Gestionar Monedas', '/XHTML/GestionarMoneda.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (43, 'Gestionar Paises', 'Gestionar Paises', '/XHTML/GestionarPais.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (44, 'Gestionar Provincias', 'Gestionar Provincias', '/XHTML/GestionarProvincia.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (45, 'Gestionar Ciudades', 'Gestionar Ciudades', '/XHTML/GestionarCiudad.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (46, 'Gestionar Temas', 'Gestionar Temas', '/XHTML/GestionarTemas.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (47, 'Gestionar Subtemas', 'Gestionar Subtemas', '/XHTML/GestionarSubtema.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (48, 'Gestionar Indicadores', 'Gestionar Indicadores', '/XHTML/GestionarIndicador.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (49, 'Asociar Estrategias vs Objetivos vs Impactos', 'Asociar Estrategias vs Objetivos vs Impactos', '/XHTML/AsociarEstrategiasVsObjetivosVsImpactos.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (50, 'Asociar Objetivo Ambiental vs Impacto', 'Asociar Objetivo Ambiental vs Impacto', '/XHTML/AsociarObjetivoAmbientalvsImpacto.xhtml', 15, '1', 0);
	INSERT INTO seg_opcion VALUES (51, 'Asociar Objetivo Estrategico vs Impacto', 'Asociar Objetivo Estrategico vs Impacto', '/XHTML/AsociarObjetivoEstrategicoVsImpacto.xhtml', 15, '1', 0);
		----PLANEACION ESTRATEGICA AMBIENTAL
	INSERT INTO seg_opcion VALUES (52, 'Plan Estrategico Ambiental Corporativo', 'Plan Estrategico Ambiental Corporativo', '/XHTML/PEACorporativo.xhtml', 14, '1', 0);
	





-- INSERT INTO PERMISO
INSERT INTO seg_permiso VALUES (0, '1', 0, NULL, 0, 1, 1, NULL, 0);

INSERT INTO seg_permiso VALUES (2, '1', 1, NULL, NULL, 3, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (3, '1', 1, NULL, NULL, 4, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (4, '1', 1, NULL, NULL, 5, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (5, '1', 1, NULL, NULL, 6, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (6, '1', 1, NULL, NULL, 7, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (7, '1', 1, NULL, NULL, 8, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (8, '1', 1, NULL, NULL, 9, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (9, '1', 1, NULL, NULL, 10, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (10, '1', 1, NULL, NULL, 11, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (11, '1', 1, NULL, NULL, 12, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (12, '1', 1, NULL, NULL, 13, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (13, '1', 1, NULL, NULL, 14, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (14, '1', 1, NULL, NULL, 15, 2, NULL, 0);

INSERT INTO seg_permiso VALUES (21, '1', 2, NULL, NULL, 3, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (22, '1', 2, NULL, NULL, 4, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (23, '1', 2, NULL, NULL, 5, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (24, '1', 2, NULL, NULL, 6, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (25, '1', 2, NULL, NULL, 7, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (26, '1', 2, NULL, NULL, 8, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (27, '1', 2, NULL, NULL, 9, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (28, '1', 2, NULL, NULL, 10, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (29, '1', 2, NULL, NULL, 11, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (30, '1', 2, NULL, NULL, 12, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (31, '1', 2, NULL, NULL, 13, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (32, '1', 2, NULL, NULL, 14, 2, NULL, 0);

--INSERT INTO seg_permiso VALUES (34, '1', 3, NULL, NULL, 3, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (35, '1', 3, NULL, NULL, 4, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (36, '1', 3, NULL, NULL, 5, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (37, '1', 3, NULL, NULL, 6, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (38, '1', 3, NULL, NULL, 7, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (39, '1', 3, NULL, NULL, 8, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (40, '1', 3, NULL, NULL, 9, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (41, '1', 3, NULL, NULL, 10, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (42, '1', 3, NULL, NULL, 11, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (43, '1', 3, NULL, NULL, 12, 2, NULL, 0);
--INSERT INTO seg_permiso VALUES (44, '1', 3, NULL, NULL, 13, 2, NULL, 0);
INSERT INTO seg_permiso VALUES (45, '1', 3, NULL, NULL, 14, 2, NULL, 0);



-- ACTUALIZACION DE LAS SECUENCIAS CORRESPONDIENTES A CADA TABLA
ALTER SEQUENCE seg_usuario_usu_codigo_seq RESTART WITH 2;
ALTER SEQUENCE seg_compania_cia_codigo_seq RESTART WITH 2;
ALTER SEQUENCE seg_sistema_sis_codigo_seq RESTART WITH 3;
ALTER SEQUENCE seg_sistema_cia_sic_codigo_seq RESTART WITH 3;
ALTER SEQUENCE seg_rol_rol_codigo_seq RESTART WITH 4;
ALTER SEQUENCE seg_rol_usuario_rlu_codigo_seq RESTART WITH 2;
ALTER SEQUENCE seg_sucursal_suc_codigo_seq RESTART WITH 2;
ALTER SEQUENCE seg_parametro_par_codigo_seq RESTART WITH 3;
ALTER SEQUENCE seg_grupo_opcion_gru_codigo_seq RESTART WITH 21;
ALTER SEQUENCE seg_opcion_opc_codigo_seq RESTART WITH 53;
ALTER SEQUENCE seg_permiso_per_codigo_seq RESTART WITH 46;
