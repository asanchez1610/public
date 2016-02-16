/*
-- ENGLISH:
	Application description:
	Our Road Closure Registration tool that automatically generates a Waze-consumable feed is a java-based application that has been developed using the following frameworks:
	Backend:
	-Spring 4
	-Mybatis
	Frontend
	-ExtJs
	-Jquery
	-Bootstrap 3
	The original DBMS for our actual implementation is DB2, but the script can be taylored to support other DBMSs.
	The application is deployed in a Tomcat 7 server with JDK 7.
	We used Eclipse as IDE for the development (version Mars). 
	However there is no problem with using other IDES like netbeans for instance, since this application has been developed conforming the MAVEN standards.

	The tool allows to zoom the map in to specific locations according to our cadastral Database. It does this by querying an external service that provides the spatial coordinates and a predefined polygon. The service is not included in the code we are sharing, so it is the duty of the development team to produce such a service. In order to avoid having to change the existing code, the service to be developed must produce a response with the following JSON structure: 
	{
		total: 0,
		data: [
			{
			tipo: "AVENIDAS",
			toponimia: "AV. JOSE A. LARCO",
			cdra: "4",
			gid: 478,
			punto: "-77.0290672344261 -12.121864951297",
			poligono: "-77.0290399232 -12.1214598600923,-77.029094545835 -12.1222700424757"
			}
		]
	}

	We provide an example link here:
	http://digital.miraflores.gob.pe:8080/MirafloresV1/obtenergeoreferenciaxvia.muni?query=larco%204&start=0&limit=100
	The result provided in this example zooms in to the polygon in the map that corresponds to the 4th block of Larco Avenue in Miraflores. 

-- ESPA�OL:
	Descripci�n de la Aplicacion:
	La herramienta del formulario de cierre de calles es una aplicacion desarrollada en java web con los siguientes frameworks de desarrollo:
	Backend:
	-Spring 4
	-Mybatis
	Frontend
	-ExtJs
	-Jquery
	-Bootstrap 3
	La base de datos en la cual se desarrollo la aplicaci�n es DB2.
	La aplicacion esta montada en servidor tomcat 7, con JDK 7.
	El IDE de desarrollo en el cual se implemento la aplicaci�n fue eclipse en la version Mars, 
	sin embargo no existe ningun inconveniente en ejecutarlo en otros IDES como netbeans por ejemplo, debido a que dicha aplicaci�n
	esta soportada bajo el estandares de MAVEN.
	NOTAS: 
	
	1. El servicio que proporciona informaci�n para la aproximaci�n de calles, se encuentra ubicado en el siguiente enlace como ejemplo:
	http://digital.miraflores.gob.pe:8080/MirafloresV1/obtenergeoreferenciaxvia.muni?query=larco 4&start=0&limit=100
	
	el cual traera la siguiente respuesta en formato json:
	{
		total: 0,
		data: [
			{
			tipo: "AVENIDAS",
			toponimia: "AV. JOSE A. LARCO",
			cdra: "4",
			gid: 478,
			punto: "-77.0290672344261 -12.121864951297",
			poligono: "-77.0290399232 -12.1214598600923,-77.029094545835 -12.1222700424757"
			}
		]
	}
	
	El ejemplo nos aproxima a la cuadra 4 de la av. larco en miraflores, con la geolocalizacion correspondiente a esta direcci�n
	
	2. En el formulario de registro existe una casilla de verificacion llamada visible, esta opci�n nos permite ocultar aquellos 
	registros que no deseemos que se muestren en el fit de waze.
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
   USUARIO_MODIFICA int,
   VISIBLE varchar(1)
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
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8748,323,'1','Construcci�n o Incidente','',null,'1',{d '2015-10-26'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8749,323,'2','Via Cerrada','',null,'1',{d '2015-10-26'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8750,42,'30','TABLA_MAPA_INCIDENCIAS','',null,'1',{d '2015-10-27'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8765,39,'25','VOXIVA_WS','',null,'1',{d '2015-11-27'},'','','');
INSERT INTO CTLMELECGO (IDE_ELEMENTO,IDE_GRUPO,COD_CODIGO,DES_NOMBRE,CLV_ABREVIAR,IDE_ELEMENTO1,IND_ESTADO,FEC_FECHA_CREACION,REFERENCIA,REFERENCIA1,REFERENCIA2) VALUES (8766,39,'26','FORMULARIO_CIERRE_CALLE','',null,'1',{d '2015-11-27'},'','','');
-----------------------------------------------------------------------
--END INSERTS TABLA CATALOGO
-----------------------------------------------------------------------
