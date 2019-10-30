-- ELEMENTOS DE CREACION DE VALOR -------- OBJETIVOS AMBIENTALES
INSERT INTO psy_objetivo_ambiental (descripcion, estado_registro) VALUES ('Riesgos reducidos', 'A');--1
INSERT INTO psy_objetivo_ambiental (descripcion, estado_registro) VALUES ('Oportunidades de negocio mejoradas', 'A');--2
INSERT INTO psy_objetivo_ambiental (descripcion, estado_registro) VALUES ('Licencias para operar y crecer', 'A');--3
INSERT INTO psy_objetivo_ambiental (descripcion, estado_registro) VALUES ('Atraccion y retencion fuerza de trabajo', 'A');--4
INSERT INTO psy_objetivo_ambiental (descripcion, estado_registro) VALUES ('Reconocimiento de marca y reputacion mejorada', 'A');--5
INSERT INTO psy_objetivo_ambiental (descripcion, estado_registro) VALUES ('Desempeño y eficiencia operacional mejorados', 'A');--6
INSERT INTO psy_objetivo_ambiental (descripcion, estado_registro) VALUES ('Capacidad mejorada para planear estrategicamente a largo plazo', 'A');--7

-- IMPACTO-OBJETIVO

INSERT INTO psy_impacto_objetivo (imam_codigo, obam_codigo, estado_registro) VALUES (6,6,'A');
INSERT INTO psy_impacto_objetivo (imam_codigo, obam_codigo, estado_registro) VALUES (5,3,'A');
INSERT INTO psy_impacto_objetivo (imam_codigo, obam_codigo, estado_registro) VALUES (5,1,'A');
INSERT INTO psy_impacto_objetivo (imam_codigo, obam_codigo, estado_registro) VALUES (1,6,'A');
INSERT INTO psy_impacto_objetivo (imam_codigo, obam_codigo, estado_registro) VALUES (1,7,'A');
INSERT INTO psy_impacto_objetivo (imam_codigo, obam_codigo, estado_registro) VALUES (4,2,'A');
INSERT INTO psy_impacto_objetivo (imam_codigo, obam_codigo, estado_registro) VALUES (3,4,'A');
INSERT INTO psy_impacto_objetivo (imam_codigo, obam_codigo, estado_registro) VALUES (2,5,'A');

--TEMAS------------------------------------------
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (4, 'Participacion en el mercado', 'A');--1
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (4, 'Competitividad', 'A');--2

INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (2, 'Imagen corporativa', 'A');--3
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (2, 'Visibilizacion de la gestion', 'A');--4
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (2, 'Conflictos', 'A');--5
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (2, 'Innovacion', 'A');--6

INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (5, 'Operacion y crecimiento', 'A');--7
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (5, 'Aseguramiento cumplimiento legal', 'A');--8
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (5, 'Riesgos', 'A');--9

INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (1, 'Costos', 'A');--10
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (1, 'Ahorro', 'A');--11
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (1, 'Ingresos', 'A');--12
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (1, 'Gestion', 'A');--13

INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (3, 'Competencias', 'A');--14
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (3, 'Clima Laboral', 'A');--15
INSERT INTO psy_tema (imam_codigo, descripcion, estado_registro) VALUES (3, 'Salud ambiental', 'A');--16

--SUBTEMAS--------------------------------------------
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (1, 'Mercados verdes', 'A');--1

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (2, 'Diferenciacion de productos', 'A');--2
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (2, 'Contribucion a las ventas', 'A');--3
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (2, 'Servicio al cliente', 'A');--4
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (2, 'Posicionamiento', 'A');--5

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (3, 'Productos retirados', 'A');--6
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (3, 'Calificacion crediticia', 'A');--7
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (3, 'Contribucion imagen corporativa mejorada', 'A');--8
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (3, 'Reportes de desempeño ambientales a stakeholders', 'A');--9
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (3, 'Publicidad incrementada', 'A');--10
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (3, 'Desarrollo de conciencia y cultura ambienal corporativa', 'A');--11

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (4, 'Auditorias', 'A');--12
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (4, 'Premios', 'A');--13
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (4, 'Sellos', 'A');--14
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (4, 'Reconocimiento', 'A');--15
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (4, 'Compras verdes', 'A');--16
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (4, 'Certificaciones', 'A');--17

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (5, 'Relaciones stakeholders', 'A');--18

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (6, 'Practicas ambientales', 'A');--19
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (6, 'Modernizacion tecnologica', 'A');--20
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (6, 'Optimizacion', 'A');--21
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (6, 'Sustitucion', 'A');--22

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (7, 'Permisos ambientales', 'A');--23
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (7, 'Planes de manejo', 'A');--24
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (7, 'Licencias ambientales', 'A');--25
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (7, 'Cumplimiento legal', 'A');--26
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (8, 'Actualizacion legal', 'A');--27
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (8, 'Prospectiva legal', 'A');--28
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (8, 'Auditorias de cumplimiento', 'A');--29
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (9, 'Probablidad e impacto', 'A');--30

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Disposicion de residuos', 'A');--31
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Legales', 'A');--32
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Inversion', 'A');--33
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Acciones ambientales', 'A');--34
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Remediaciones', 'A');--35
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Multas - Sanciones', 'A');--36
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Compensacion comunidad', 'A');--37
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Compensacion trabajadores', 'A');--38
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Financiacion de proyectos', 'A');--39
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Accidentes y siniestros', 'A');--40
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Pasivos ambientales', 'A');--41
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (10, 'Productos retirados', 'A');--42
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (11, 'Reduccion de costo consumo de energia', 'A');--43
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (11, 'Reduccion de costo consumo de agua', 'A');--44
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (11, 'Reduccion de costo disposicion final', 'A');--45
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (11, 'Reduccion uso de recursos naturales', 'A');--46
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (11, 'Reduccion rotacion de empleados', 'A');--47
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (11, 'Reduccion costo de la deuda', 'A');--48

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (12, 'Ventas en mercados verdes', 'A');--49
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (12, 'Productos reciclados', 'A');--50
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (12, 'Materiales de desecho reciclados', 'A');--51

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (13, 'Licitaciones', 'A');--52
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (13, 'Retorno inversiones ambientales', 'A');--53
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (13, 'Valor economico agregado', 'A');--54
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (13, 'Niveles de proactividad', 'A');--55
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (13, 'Niveles de reactividad', 'A');--56
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (13, 'Alineacion estrategica corporativa', 'A');--57

INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (14, 'Certificaciones laborales', 'A');--58
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (15, 'Ambiente de trabajo', 'A');--59
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (16, 'Vigilancia ocupacional', 'A');--60
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (16, 'Limites de exposicion', 'A');--61
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (16, 'Valoracion de los peligros para la salud', 'A');--62
INSERT INTO psy_subtema (tema_codigo, descripcion, estado_registro) VALUES (16, 'Incapacidades', 'A');--63




--INDICADORES---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 1, 'Participacion en mercados verdes', 'Numero de mercados verdes en los que participa', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 1, 'Incrusion en nuevos segmentos de mercado', 'Numero de nuevos segmentos de mercado en los que participa', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 1, 'Desarrollo de nuevos productos o lineas', 'Numero de nuevos productos o lineas desarrolladas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 1, 'Desarrollo de nuevos servicios', 'Numero de nuevos servicios creados', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 2, 'Atributos ambientales generados en productos o servicios', 'Numero de atributos ambientales generados en productos o servicios', 'M', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 2, 'Margen de contribucion generada', 'Porcentaje de margen de contribucion generada por producto', 'M', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 2, 'Competitividad por precio generado', 'Porcentaje de reduccion de precio por producto generado', 'M', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 3, 'Contribucion licitaciones ganadas', 'Puntuacion desempeo ambiental en licitaciones', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 3, 'Participacion en ventas de productos reciclados', 'Porcentaje de participacion en ventas de productos reciclados', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 4, 'Informacion del producto', 'Porcentaje de producto con informacion sobre el desempeño ambiental', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 4, 'Manejo post consumo', 'Numeros de programas o proyectos postconsumo en ejecucion', 'M', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 4, 'Estrategia de comunicacion', 'Estrategia de comunicacion implementadas', 'M', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 5, 'Recordacion de marca asociado a el desempeño ambiental', 'Porcentaje de recordacion de marca asociado a el desempeño ambiental', '-', 'A');

INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 6, 'Lineas de producto retiradas /año', 'Numero de lineas de producto retiradas /año', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 6, 'Cantidad de producto retirado /año', 'Cantidad de producto retirado /año', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 7, 'Calificacion crediticia', 'Calificacion riesgo de credito por desempeño ambiental', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 8, 'Calificacion encuesta de percepcion', 'Porcentaje de contribucion al mejoramiento de la imagen corporativa', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 9, 'Frecuencia de reportes ambientales a stakeholders', 'Periodicidad de reportes ambientales a stakeholders', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 9, 'Cantidad de reportes ambientales a stakeholders', 'Numero de reportes ambientales a stakeholders', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 10, 'Publicidad incrementada por perdida de imagen relacionada con el desempeño ambiental', 'Valor desembolsado en publicidad incrementada por perdida de imagen relacionada con el desempeño ambiental', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 11, 'Programas y/o proyectos de voluntariado en ejecucion', 'Numero de programas y/o proyectos de voluntariado en ejecucion', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 11, 'Adopcion causa ambiental', 'Numero de acciones ambientales adoptadas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 11, 'Alineacion objetivos ambientales corporativos con los compromisos ambientales a nivel global y nacional', 'Participacion en programas sectoriales o nacionales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 11, 'Participacion de empleados en programas ambientales', 'Porcentaje de participacion de empleados en programas o proyectos ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 11, 'Desarrollo de actividades de educacion ambiental', 'Numero de actividades de educacion ambiental realizadas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 11, 'Compromiso explicito alta gerencia', 'Evidencias de compromiso con el mejoramiento ambiental de la organizacion', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 12, 'Auditorias desarrolladas', 'Numero de auditorias desarrolladas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 12, 'Tipo de auditorias reaizadas', 'Numero de auditorias desarrolladas por tipo', 'M', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 12, 'Participacion en convocatorias de premios relacionados con la excelencia ambiental', 'Numero de participantes en convocatorias de premios relacionados con excelencia ambiental', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 13, 'Premios recibidos', 'Numero de premios recibidos', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 14, 'Sellos ambientales otorgados', 'Numero de sellos ambientales otorgados', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 15, 'Reconocimientos desempeño ambiental', 'Numero de reconocimientos desempeño ambiental obtenidos', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 16, 'Compras proveedores verdes', 'Porcentaje de proveedores con certificaciones, premios o sellos ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 17, 'Certificaciones recibidas', 'Numero de certificaciones ambientales recibidas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 18, 'Protestas', 'Numero de protestas realizadas por stakeholder en el año', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 18, 'Quejas', 'Numero de quejas recibidas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 18, 'Quejas', 'Numero de quejas recibidas resueltas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 18, 'Inspecciones sorpresa', 'Numero de inspecciones sorpresa realizadas por autoridad ambiental o cliente', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 18, 'Requerimientos autoridad ambiental', 'Numero de requerimientos autoridad ambiental /año', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 19, 'Implementacion de nuevas practicas', 'Numero de nuevas practicas implementadas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 20, 'Reconversion tecnologica realizadas', 'Numero de reconversiones tecnologicas realizadas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 20, 'Iniciativas de reconversion tecnologica en desarrollo', 'Numero de iniciativas de reconversion tecnologica en desarrollo', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 21, 'Optimizacion de procesos', 'Numero de procesos optimizados', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 22, 'Sustitucion insumos toxicos', 'Numero de insumos toxicos sustituidos', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (5, 22, 'Sustitucion insumos toxicos', 'Cantidad de insumos toxicos sustituidos', '-', 'A');

INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 23, 'Permisos ambientales obtenidos', 'Permisos ambientales obtenidos/ permisos ambientales requeridos', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 24, 'Implementacion de planes de manejo', 'Numero de planes de manejo en ejecucion', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 24, 'Actualizacion de planes de manejo', 'Ajustes de planes de manejo aprobados y en ejecucion', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 25, 'Licencias ambientales vigentes', 'Numero de licencias ambientales vigentes', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 25, 'Licencias ambientales por vencerse', 'Numero de licencias ambientales por vencerse en 3 años', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 26, 'Cumplimiento legal', 'Porcentaje de cumplimiento legal', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 27, 'Ajustes de normatividad', 'Porcentaje de conocimiento de versiones actualizadas de normas legales interesadas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 28, 'Analisis de prospectiva legal', 'Talleres de prospectiva legal realizados', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (3, 29, 'Auditorias de cumplimiento', 'Numero de auditorias de cumplimiento realizadas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (1, 30, 'Reduccion de la probabilidad', 'Porcentaje de reduccion de las probabilidades por riesgo identificado', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (1, 30, 'Reduccion del impacto', 'Porcentaje de reduccion del impacto por riesgo identificado', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (1, 30, 'Mejoramiento de la capacidad de respuesta', 'Porcentaje de mejoramiento de la capacidad de respuesta por riesgo identificado', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (1, 30, 'Niveles de seguridad en las operaciones', 'Calificacion tabla de ponderacion: muy bajo, bajo, moderado, alto, muy alto', '-', 'A');

INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 31, 'Costo /año disposicion final de residuos', 'Valor gastado anualmente por la recoleccion y la disposicion segura', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 32, 'Costo /año por gastos legales relacionados con el desempeño ambiental', 'Valor gastado anualmente por servicios legales relacionados con el desempeño ambiental', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 33, 'Costo /año inversiones ambientales', 'Valor gastado anualmente en inversiones ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 34, 'Costo /año acciones ambientales', 'Valor gastado anualmente en acciones ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 35, 'Costo /año remediaciones ambientales', 'Valor gastado anualmente en remediaciones ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 36, 'Costo /año multas-sanciones ambientales', 'Valor gastado anualmente en pago de multas-sanciones ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 37, 'Costo /año compensaciones a la comunidad', 'Valor desembolsado en compensaciones a la comunidad', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 38, 'Costo /año compensaciones a los trabajadores', 'Valor desembolsado en compensaciones a los trabajadores', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 39, 'Costo capital financiacion de proyectos', 'Tasa de interes de prestamos para financiar inversion ambiental', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 40, 'Costo /año accidentes y siniestros', 'Valor desembolsado por accidentes y siniestros', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 41, 'Pasivos ambientales', 'Valor pasivos ambientales por periodo fiscal', 'M', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 42, 'Costo /año retiro de productos por asuntos ambientales', 'Valor gastado anualmente en retiro de productos por asuntos ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 43, 'Consumo anterior - consumo actual / consumo anterior', 'Porcentaje de reduccion de costo consumo de energia', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 44, 'Consumo anterior - consumo actual / consumo anterior', 'Porcentaje de reduccion de costo consumo de agua', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 45, 'Costo anterior - consumo actual / costo anterior', 'Porcentaje de reduccion de costo disposicion final', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 46, 'Costo uso anterior - costo uso actual / costo uso anterior', 'Porcentaje de reduccion de costo en uso de recursos naturales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 47, 'Costo rotacion empleados anterior - costo rotacion empleados actual / costo rotacion empleados', 'Porcentaje de reduccion de costo rotacion de empleados', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (6, 48, 'Tasa de interes deuda - Tasa de interes refinanciacion I / Tasa de interes deuda', 'Puntos porcentuales reduccion tasa de interes', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 49, 'Ingresos por venta en mercados verdes', '-', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 50, 'Ingresos por venta de productos reciclados', '-', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (2, 51, 'Ingresos por venta de materiales de desecho reciclados', '-', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (7, 52, 'Calificacion licitaciones presentadas', 'Puntuacion desempeño ambiental en licitaciones', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (7, 53, 'Tasa interna de retorno inversiones ambientales', 'TIR', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (7, 54, 'Valor agregado', 'Atributos ambientales percibidos generados', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (7, 55, 'Desembolso acciones preventivas / acciones ambientales', 'Porcentaje de acciones proactivas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (7, 56, 'Desembolso acciones reactivas / acciones ambientales', 'Porcentaje de acciones reactivas', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (7, 57, 'Nivel de articulacion objetivos estrategicos corporativos', 'Numero de objetivos estrategicos corporativos con los que se alinea el plan estrategico ambiental', '-', 'A');

INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 58, 'Certificaciones de empleados logradas', 'Porcentaje de empleados con certificaciones en competencias para la gestion amiental', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 59, 'Rotacion de empleados', 'Porcentaje rotacion de personal responsable de la gestion ambiental', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 59, 'Persepciones sobre el desempeño ambiental corporativo', 'Encuesta de satisfaccion', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 59, 'Empleados que participan en programas o proyectos ambientales', 'Numero de empleados que participan en programas o proyectos ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 59, 'Dias de paro', 'Numero de dias de paro de trabajo', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 59, 'Horas extras', 'Numero de horas extras trabajadas y relacionadas con asuntos ambientales', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 60, 'Estrategias de salud ocupacional en ejecucion', 'Numero de estrategias de salud ocupacional en ejecucion', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 61, 'Cumplimiento limites de exposicion', 'Porcentaje de cumplimiento de limites de exposicion', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 62, 'Estudios de valoracion de peligros para la salud', 'Numero de estudios realizados', '-', 'A');
INSERT INTO psy_indicador (obam_codigo, subte_codigo, descripcion, unidad_medida, tipo_indicador, estado_registro) VALUES (4, 63, 'Horas de incapacidad', 'Numero de horas de incapacidad por asuntos ambientales', '-', 'A');