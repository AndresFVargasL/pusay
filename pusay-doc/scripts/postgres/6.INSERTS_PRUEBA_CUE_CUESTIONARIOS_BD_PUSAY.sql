﻿INSERT INTO cue_configuracion (consecutivo, multiple_respuesta, multiple_respuesta_msj, retomar_cuestionario, redirigir_url, redirigir_cerrar, redirigir_informe, clave_acceso, vigencia_inicio, vigencia_fin, mensaje_cierre, mensaje_fecha_limite, mensaje_redireccional, mensaje_maximo_respuestas, mensaje_cuestionario_finalizad, mensaje_clave_incorrecta, abierto, puntaje_max, header, color_tabla, enunciado) VALUES (1, 0, NULL, 1, NULL, 1, NULL, NULL, '2015-03-30 00:00:00', '2015-12-31 00:00:00', 'No Aplica', 'No Aplica', 'No Aplica', 'No Aplica', 'No Aplica', 'No Aplica', 0, 5, 'No Aplica', 'e62525', 'Configuracion Encuestas Pusay');
INSERT INTO cue_configuracion (consecutivo, multiple_respuesta, multiple_respuesta_msj, retomar_cuestionario, redirigir_url, redirigir_cerrar, redirigir_informe, clave_acceso, vigencia_inicio, vigencia_fin, mensaje_cierre, mensaje_fecha_limite, mensaje_redireccional, mensaje_maximo_respuestas, mensaje_cuestionario_finalizad, mensaje_clave_incorrecta, abierto, puntaje_max, header, color_tabla, enunciado) VALUES (2, 0, NULL, 1, NULL, 1, NULL, NULL, '2015-03-30 00:00:00', '2015-12-31 00:00:00', 'No Aplica', 'No Aplica', 'No Aplica', 'No Aplica', 'No Aplica', 'No Aplica', 0, 1, 'No Aplica', 'eb1515', 'Encuestas Pusay Desempeño Ambiental, Grado de afectacion y Nivel Tecnico');

INSERT INTO cue_cuestionario_tipo (consecutivo, nombre, descripcion, estado) VALUES (1, 'Encuestas Pusay Desempeño Ambiental, Grado de afectacion y Nivel Tecnico', 'Encuestas Pusay Desempeño Ambiental, Grado de afectacion y Nivel Tecnico', 1);

INSERT INTO cue_responsable (identificacion, razon_social, nombre, apellido, email, email_soporte, pagina_soporte, telefono_1, telefono_2) VALUES (000000, '', 'PUSAY', 'PUSAY', 'Responsable Ambiental', 'PUSAY', 'PUSAY', '0000000', '0000000');

INSERT INTO cue_cuestionario (consecutivo, identificacion, configuracion, tipo, titulo, descripcion, terminos, fecha_creacion, estado) VALUES (1, 000000, 2, 1, 'Desempeño ambiental', 'Desempeño ambiental', 'Respuesta : Si o No', '2015-03-30 15:10:38.336', 1);
INSERT INTO cue_cuestionario (consecutivo, identificacion, configuracion, tipo, titulo, descripcion, terminos, fecha_creacion, estado) VALUES (2, 000000, 1, 1, 'Grado de afectacion', 'Grado de afectacion', 'Respuesta de 1 a 5', '2015-03-30 15:13:00.94', 1);
INSERT INTO cue_cuestionario (consecutivo, identificacion, configuracion, tipo, titulo, descripcion, terminos, fecha_creacion, estado) VALUES (3, 000000, 2, 1, 'Nivel Tecnico', 'Nivel Tecnico', 'Respuesta Si o No', '2015-03-30 15:13:57.014', 1);

INSERT INTO cue_lista (consecutivo, nombre, descripcion, estado) VALUES (1, 'Encuestas Pusay Desempeño Ambiental', 'Encuestas Pusay Desempeño Ambiental', 1);
INSERT INTO cue_lista (consecutivo, nombre, descripcion, estado) VALUES (2, 'Encuestas Pusay Grado de afectacion', 'Encuestas Pusay Grado de afectaciono', 1);
INSERT INTO cue_lista (consecutivo, nombre, descripcion, estado) VALUES (3, 'Encuestas Pusay Nivel Tecnico', 'Encuestas Pusay Nivel Tecnico', 1);

INSERT INTO cue_lista_cuestionario (consecutivo, lista, cuestionario, fecha_hora_asignacion) VALUES (1, 2, 2, '2015-03-30 15:19:17.45');
INSERT INTO cue_lista_cuestionario (consecutivo, lista, cuestionario, fecha_hora_asignacion) VALUES (2, 1, 1, '2015-03-30 15:19:33.569');
INSERT INTO cue_lista_cuestionario (consecutivo, lista, cuestionario, fecha_hora_asignacion) VALUES (3, 3, 3, '2015-03-30 15:19:44.931');

INSERT INTO cue_categoria (consecutivo, cuestionario, nombre, descripcion, estado) VALUES (1, 2, 'Categoria Cuestionario Grado de afectacion', 'Categoria Cuestionario Grado de afectacion', 1);
INSERT INTO cue_categoria (consecutivo, cuestionario, nombre, descripcion, estado) VALUES (2, 1, 'Categoria Cuestionario Desempeño ambiental', 'Categoria Cuestionario Desempeño ambiental', 1);
INSERT INTO cue_categoria (consecutivo, cuestionario, nombre, descripcion, estado) VALUES (3, 3, 'Categoria Cuestionario Nivel Tecnico', 'Categoria Cuestionario Nivel Tecnico', 1);

--INSERT INTO cue_contacto (identificacion, nombre, apellido, email, celular, empresa, estado) VALUES (12345, 'Andres', 'Vargas', 'avargas@vortexbird.com', NULL, NULL, 1);
--INSERT INTO cue_contacto (identificacion, nombre, apellido, email, celular, empresa, estado) VALUES (123456, 'Andres', 'Vargas', 'avargas@vortexbird.com', NULL, NULL, 1);
--INSERT INTO cue_contacto (identificacion, nombre, apellido, email, celular, empresa, estado) VALUES (12345666, 'Andres', 'Vargas', 'avargas@vortexbird.com', NULL, NULL, 1);
--INSERT INTO cue_contacto (identificacion, nombre, apellido, email, celular, empresa, estado) VALUES (12345678, 'Andres', 'Vargas', 'avargas@vortexbird.com', NULL, NULL, 1);

--INSERT INTO cue_lista_contacto (consecutivo, lista, contacto, fecha_hora_asignacion, fecha_hora_finalizacion, puntaje_total, duracion, plac_codigo, estado) VALUES (21, 1, 12345, '2015-03-31 20:37:55.059', '2015-03-31 20:38:38.727', 6, 0, NULL, 2);
--INSERT INTO cue_lista_contacto (consecutivo, lista, contacto, fecha_hora_asignacion, fecha_hora_finalizacion, puntaje_total, duracion, plac_codigo, estado) VALUES (1, 1, 123456, '2015-03-30 15:04:13.773', '2015-03-30 15:04:13.773', 12, 0, NULL, 1);
--INSERT INTO cue_lista_contacto (consecutivo, lista, contacto, fecha_hora_asignacion, fecha_hora_finalizacion, puntaje_total, duracion, plac_codigo, estado) VALUES (22, 1, 12345666, '2015-03-31 20:45:12.577', '2015-03-31 20:45:12.577', 0, 0, NULL, 1);
--INSERT INTO cue_lista_contacto (consecutivo, lista, contacto, fecha_hora_asignacion, fecha_hora_finalizacion, puntaje_total, duracion, plac_codigo, estado) VALUES (41, 1, 12345678, '2015-03-31 23:05:32.703', '2015-03-31 23:05:32.703', 0, 0, NULL, 1);


INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (1, 2, '¿Existen programas en implementacion?', 'No Aplica', 1, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (2, 2, '¿Estan definidas las funciones y responsabilidades del area y areas implicadas?', 'No Aplica', 2, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (3, 2, '¿Cual es el nivel de ejecucion del plan de accion frente a los cronogramas propuestos? •	Menor o igual al 50% - Bajo (No)  •	Mayor al 80% - Bueno (Sí)  •	100% - Excelente (Sí)"', 'No Aplica', 3, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (4, 2, '¿Hay cumplimiento en las metas de los programas, planes, proyectos?', 'No Aplica', 4, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (5, 2, '¿Son pertinentes los programas y planes con de los objetivos corporativos?', 'No Aplica', 5, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (6, 2, '¿Existe una estructura organizativa frente a los planes de acción propuestos?', 'No Aplica', 6, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (7, 2, '¿Existe un sistema de información financiera y técnica implementados (contabilidad ambiental)?', 'No Aplica', 7, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (8, 2, '¿Se desarrolla una cultura ambiental corporativa. (Prevención de contaminación, respuesta accidentes, aprovechamiento de residuos y desechos, investigación, eficiencia, ciudadanía corporativa, conciencia ambiental individual)?', 'No Aplica', 8, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (9, 2, '¿Existe integración de los programas y proyectos ambientales con otros departamentos de la empresa?', 'No Aplica', 9, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (10, 2, '¿Hay oportunidades externas aprovechadas?', 'No Aplica', 10, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (11, 2, '¿Se han realizado auditorías ambientales?', 'No Aplica', 11, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (12, 2, '¿Se han presentado reportes internos?', 'No Aplica', 12, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (13, 2, '¿Se han presentado reportes externos?', 'No Aplica', 13, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (14, 1, 'Financiera: Pago multas, gastos jurídicos, altos costos operativos, costos de mitigación, prevención, compensación.', 'No Aplica', 1, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (15, 1, 'Reputación Corporativa: Demandas, multas, sanciones, quejas ante instituciones presentadas por las partes interesadas, alta exposición negativa de la marca por medios de comunicación y percepción ciudadana.', 'No Aplica', 2, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (16, 1, 'Relaciones Laborales: Número de demandas laborales, accidentes, rotación capital humano y demás, relacionadas con la gestión ambiental.', 'No Aplica', 3, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (17, 1, 'Objetivos de Mercadeo: Pérdida de participación en el mercado, no aplicación de licencia de funcionamiento, rechazo o desventaja en licitaciones.', 'No Aplica', 4, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (21, 1, 'Exposición actual frente a las regulaciones: Materiales peligrosos, Respel, planes de manejo, etc.', 'No Aplica', 5, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (22, 1, 'Dependencia Recurso Naturales: Retraso en la cadena de abastecimiento, legalidad de operación, proveedores y afectación fenómenos naturales.', 'No Aplica', 6, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (23, 3, '¿Se cuenta con un buen nivel de conocimientos técnicos requeridos?', 'No Aplica', 1, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (24, 3, '¿Se sistematiza la informacion sobre la reglamentacion en el sector?', 'No Aplica', 2, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (25, 3, '¿Se tiene conocimiento sobre las implicaciones ambientales, juridicas, sociales y economicas del nivel de desempeño ambiental?', 'No Aplica', 3, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (26, 3, '¿Se actualizan y monitorean las normas y alternativas tecnologías ambientales, que involucran la actividad económica de la organización?', 'No Aplica', 4, 1, '', 1, 1);
INSERT INTO cue_pregunta (consecutivo, categoria, enunciado, condicion, orden, puntaje, label_ampliacion, nro_respuestas, estado) VALUES (27, 3, '¿Cuenta con un programa de fortalecimiento a las competencias ambientales?', 'No Aplica', 5, 1, '', 1, 1);

INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (1, 1, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (2, 1, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (3, 2, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (4, 2, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (5, 3, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (6, 3, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (7, 4, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (8, 4, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (9, 5, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (10, 5, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (11, 6, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (12, 7, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (13, 7, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (14, 6, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (15, 8, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (16, 8, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (17, 9, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (18, 9, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (19, 10, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (20, 10, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (21, 11, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (22, 11, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (23, 12, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (24, 12, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (25, 13, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (26, 13, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (27, 14, '1', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (28, 14, '2', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (29, 14, '3', 'No Aplica', 3, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (30, 14, '4', 'No Aplica', 4, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (31, 14, '5', 'No Aplica', 5, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (32, 15, '1', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (33, 15, '2', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (34, 15, '3', 'No Aplica', 3, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (35, 15, '4', 'No Aplica', 4, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (36, 15, '5', 'No Aplica', 5, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (37, 16, '1', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (38, 16, '2', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (39, 16, '3', 'No Aplica', 3, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (40, 16, '4', 'No Aplica', 4, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (41, 16, '5', 'No Aplica', 5, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (42, 17, '1', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (43, 17, '2', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (61, 17, '3', 'No Aplica', 3, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (62, 17, '4', 'No Aplica', 4, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (63, 17, '5', 'No Aplica', 5, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (64, 21, '1', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (65, 21, '2', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (66, 21, '3', 'No Aplica', 3, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (67, 21, '4', 'No Aplica', 4, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (68, 21, '5', 'No Aplica', 5, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (69, 22, '1', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (70, 22, '2', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (71, 22, '3', 'No Aplica', 3, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (72, 22, '4', 'No Aplica', 4, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (73, 22, '5', 'No Aplica', 5, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (74, 23, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (75, 23, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (76, 24, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (77, 24, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (78, 25, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (79, 25, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (80, 26, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (81, 26, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (82, 27, 'Si', 'No Aplica', 1, 1, 0, NULL, 0, 1);
INSERT INTO cue_opcion (consecutivo, pregunta, enunciado, condicion, orden, puntaje, requiere_ampliacion, label_ampliacion, indicador_correcta, estado) VALUES (83, 27, 'No', 'No Aplica', 2, 1, 0, NULL, 0, 1);

--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (4, 31, 21, '2015-03-31 20:38:38.078', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (5, 35, 21, '2015-03-31 20:38:38.318', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (6, 39, 21, '2015-03-31 20:38:38.429', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (7, 62, 21, '2015-03-31 20:38:38.518', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (8, 68, 21, '2015-03-31 20:38:38.596', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (9, 73, 21, '2015-03-31 20:38:38.674', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (10, 1, 1, '2015-03-31 20:42:49.02', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (11, 4, 1, '2015-03-31 20:42:49.292', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (12, 6, 1, '2015-03-31 20:42:49.492', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (13, 7, 1, '2015-03-31 20:42:49.725', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (14, 10, 1, '2015-03-31 20:42:49.958', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (15, 11, 1, '2015-03-31 20:42:50.025', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (16, 13, 1, '2015-03-31 20:42:50.37', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (17, 15, 1, '2015-03-31 20:42:50.537', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (18, 17, 1, '2015-03-31 20:42:50.759', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (19, 19, 1, '2015-03-31 20:42:50.914', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (20, 21, 1, '2015-03-31 20:42:51.08', '192.168.0.12', NULL);
--INSERT INTO cue_respuesta (consecutivo, opcion, lista_contacto, fecha_hora_respuesta, ip, respuesta_ampliacion) VALUES (21, 24, 1, '2015-03-31 20:42:51.347', '192.168.0.12', NULL);

ALTER SEQUENCE seq_cue_categoria RESTART WITH 4;
ALTER SEQUENCE seq_cue_configuracion RESTART WITH 3;
--ALTER SEQUENCE seq_cue_contacto RESTART WITH 1;
ALTER SEQUENCE seq_cue_cuestionario RESTART WITH 4;
ALTER SEQUENCE seq_cue_cuestionario_tipo RESTART WITH 2;
ALTER SEQUENCE seq_cue_estado RESTART WITH 4;
ALTER SEQUENCE seq_cue_lista RESTART WITH 4;
--ALTER SEQUENCE seq_cue_lista_contacto RESTART WITH 42;
ALTER SEQUENCE seq_cue_lista_cuestionario RESTART WITH 4;
ALTER SEQUENCE seq_cue_navegacion RESTART WITH 1;
ALTER SEQUENCE seq_cue_opcion RESTART WITH 84;
ALTER SEQUENCE seq_cue_pregunta RESTART WITH 28;
ALTER SEQUENCE seq_cue_responsable RESTART WITH 1;
--ALTER SEQUENCE seq_cue_respuesta RESTART WITH 22;

