package pe.gob.miraflores.mobile.service.mapaincidencias;

import java.io.File;
import java.util.List;
import java.util.Map;

import pe.gob.miraflores.mobile.domain.detalleincidencia.GeoDetalleIncidencia;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistro;

public interface MapaIncidenciasService {

	public String buildJsonRestWaze(Map<String, Object> params) throws Exception;
	
	public MapaIncidenciasRegistro registrar(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public List<MapaIncidenciasRegistro> obtenerIncidenciasNoProcesadas() throws Exception;
	
	public List<MapaIncidenciasRegistro> listaInicdenciasWaze(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public String listaIncidenciasXmlFormatWaze(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public MapaIncidenciasRegistro registrarIncidenciaVoxiva(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public String buildJsonRestDolphin() throws Exception;

	public GeoDetalleIncidencia obtenerDetalleIncidencia(Integer issi) throws Exception;
	
	public List<MapaIncidenciasRegistro> listaInicdenciasVoxiva(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public MapaIncidenciasRegistro obtenerIncidenciaPorId(Integer id) throws Exception;
	
	public Map<String, Object> listaInicdenciasPortipo(MapaIncidenciasRegistro incidencia) throws Exception;
	
	public void anularIncidencia(Integer id) throws Exception;
	
	public void enviarAlertasCierreCalle() throws Exception;
	
	public File exportarExcelBandejaCierreCalles(MapaIncidenciasRegistro incidencia) throws Exception;
	
}
