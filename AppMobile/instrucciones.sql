/*
-- ENGLISH:
	Application description:
	Our Road Closure Registration tool that automatically generates a Waze-consumable feed is a java-based application that has been developed using the following frameworks:
	Backend:
	-Spring 4
	-Myibatis
	Frontend
	-ExtJs
	-Jquery
	-Bootstrap 3
	The original DBMS for our actual implementation is DB2, but the script can be taylored to support other DBMSs.
	The application is deployed in a Tomcat 7 server with JDK 7.
	We used Eclipse as IDE for the development (version Mars). 
	However there is no problem with using other IDES like netbeans for instance, since this application has been developed conforming the MAVEN standards.


-- ESPAÑOL:
	Descripción de la Aplicacion:
	La herramienta del formulario de cierre de calles es una aplicacion desarrollada en java web con los siguientes frameworks de desarrollo:
	Backend:
	-Spring 4
	-Myibatis
	Frontend
	-ExtJs
	-Jquery
	-Bootstrap 3
	La base de datos en la cual se desarrollo la aplicación es DB2.
	La aplicacion esta montada en servidor tomcat 7, con JDK 7.
	El IDE de desarrollo en el cual se implemento la aplicación fue eclipse en la version Mars, 
	sin embargo no existe ningun inconveniente en ejecutarlo en otros IDES como netbeans por ejemplo, debido a que dicha aplicación
	esta soportada bajo el estandares de MAVEN.

*/
-----------------------------------------------------------------------
--SCRIPT DE TABLAS
-----------------------------------------------------------------------
CREATE TABLE CTLMELECGO
(
   IDE_ELEMENTO int PRIMARY KEY NOT NULL,
   IDE_GRUPO int,
   COD_CODIGO varchar(20),
   DES_NOMBRE varchar(300),
   CLV_ABREVIAR varchar(100),
   IDE_ELEMENTO1 int,
   IND_ESTADO char(1),
   FEC_FECHA_CREACION date,
   REFERENCIA varchar(50),
   REFERENCIA1 varchar(50) NOT NULL,
   REFERENCIA2 varchar(50) NOT NULL
)
;
CREATE TABLE GEO_INCIDENCIA_MAP
(
   ID_INCIDENCIA int PRIMARY KEY,
   ID_EVENTO int,
   TIPO_EVENTO int,
   SUB_TIPO int,
   FEC_HORA_INICIO timestamp,
   FEC_HORA_FIN timestamp,
   DESCRIPCION varchar(600),
   SEVERIDAD varchar(400),
   CALLE varchar(400),
   TIPO_DIRECCION int,
   DIRECCION_FINAL varchar(500),
   NOMBRE_CALLE_INICIO varchar(500),
   NOMBRE_CALLE_FIN varchar(500),
   FEC_REGISTRA timestamp,
   FEC_MODIFICA timestamp,
   DES_USU_REGISTRA varchar(250),
   DES_USU_MODIFICA varchar(250),
   DES_IP_REGISTRA varchar(250),
   DES_IP_MODIFICA varchar(250),
   ID_TIPO_VIA int,
   CUADRA_EVENTO int,
   ID_VIA int,
   ID_CASO_VOXIVA int,
   TIPO_CASO_VOXIVA int,
   SUB_TIPO_CASO_VOXIVA int,
   TIPO_INCIDENCIA int,
   ID_VIA_CATASTRO int,
   ID_TIPO_VIA_CATASTRO int,
   ESTADO varchar(1),
   TIPO_CIERRE varchar(1),
   DES_CALLLES varchar(700),
   USUARIO_REGISTRO int,
   USUARIO_MODIFICA int
)
;
CREATE TABLE GEO_MAP_INICIDENCIA_LINEA
(
   ID_LINEA int PRIMARY KEY NOT NULL,
   POINT_INI varchar(300),
   POINT_FIN varchar(300),
   DES_CALLE varchar(600),
   INTERCEPCION_1 varchar(500),
   INTERCEPCION_2 varchar(500),
   ID_INCIDENCIA int,
   DIR_CARDINAL varchar(400)
)
;
CREATE TABLE GEOMGEOMETRIA
(
   IDGEOREFERENCIA int PRIMARY KEY,
   IDETIPOGEOMETRIA int,
   GEOMETRIA varchar(1500),
   IDECODVALOR int,
   IDECODIDENT int,
   INDESTADO int,
   DESDIRECCION varchar(500),
   ORDEN int
)
;

ALTER TABLE CTLMELECGO
ADD CONSTRAINT FK_ELEMENTO
FOREIGN KEY (IDE_ELEMENTO1)
REFERENCES CTLMELECGO(IDE_ELEMENTO) ON DELETE CASCADE
;
ALTER TABLE CTLMELECGO
ADD CONSTRAINT FK_GRUPO_CAT
FOREIGN KEY (IDE_GRUPO)
REFERENCES CTLMGPOELE(IDE_GRUPO_ELEMENTO) ON DELETE CASCADE
;
CREATE UNIQUE INDEX CTLMELECGO ON CTLMELECGO(IDE_ELEMENTO)
;
CREATE UNIQUE INDEX GEO_INCIDENCIA_MAP ON GEO_INCIDENCIA_MAP(ID_INCIDENCIA)
;
CREATE UNIQUE INDEX GEO_MAP_INICIDENCIA_LINEA ON GEO_MAP_INICIDENCIA_LINEA(ID_LINEA)
;
CREATE UNIQUE INDEX GEOMGEOMETRIA ON GEOMGEOMETRIA
(
  IDGEOREFERENCIA,
  IDGEOREFERENCIA
)
;

-----------------------------------------------------------------------
--END SCRIPT DE TABLAS
-----------------------------------------------------------------------


-----------------------------------------------------------------------
--INSERTS TABLA CATALOGO
-----------------------------------------------------------------------
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (1441,152,'1','PUNTO','',1441,'1',{d '2012-10-04'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8748,323,'1','Construcción o Incidente','',null,'1',{d '2015-10-26'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8749,323,'2','Via Cerrada','',null,'1',{d '2015-10-26'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8750,42,'30','TABLA_MAPA_INCIDENCIAS','',null,'1',{d '2015-10-27'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8765,39,'25','VOXIVA_WS','',null,'1',{d '2015-11-27'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8766,39,'26','FORMULARIO_CIERRE_CALLE','',null,'1',{d '2015-11-27'},'','','');
-----------------------------------------------------------------------
--END INSERTS TABLA CATALOGO
-----------------------------------------------------------------------

