package pe.gob.miraflores.mobile.service.mapaincidencias.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.miraflores.mobile.constantes.MobileConstantes;
import pe.gob.miraflores.mobile.dao.detalleincidencia.GeoDetalleIncidenciaMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.GeometriaLineaMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.MapaIncidenciasGeometriaMapper;
import pe.gob.miraflores.mobile.dao.mapaincidencias.MapaIncidenciasRegistroMapper;
import pe.gob.miraflores.mobile.domain.catalogo.Catalogo;
import pe.gob.miraflores.mobile.domain.detalleincidencia.GeoDetalleIncidencia;
import pe.gob.miraflores.mobile.domain.detalleincidencia.GeoDetalleIncidenciaCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.GeometriaLinea;
import pe.gob.miraflores.mobile.domain.mapaincidencias.GeometriaLineaCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasGeometria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasGeometriaCriteria;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistro;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistroCriteria;
import pe.gob.miraflores.mobile.domain.parametrosistema.ParametroSistema;
import pe.gob.miraflores.mobile.service.catalogo.LocalCatalogoService;
import pe.gob.miraflores.mobile.service.email.EmailService;
import pe.gob.miraflores.mobile.service.mapaincidencias.MapaIncidenciasService;
import pe.gob.miraflores.mobile.service.parametrosistema.ParametroSistemaService;
import pe.gob.miraflores.mobile.util.Util;

@Service
public class MapaIncidenciasServiceImpl implements MapaIncidenciasService {

	@Autowired
	private MapaIncidenciasRegistroMapper mapaIncidenciasRegistroMapper;

	@Autowired
	private MapaIncidenciasGeometriaMapper mapaIncidenciasGeometriaMapper;
	
	@Autowired 
	private GeoDetalleIncidenciaMapper geoDetalleIncidenciaMapper;

	@Autowired
	private LocalCatalogoService catalogoService;
	
	@Autowired
	private ParametroSistemaService parametroSistemaService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private GeometriaLineaMapper geometriaLineaMapper;
	
	public String buildJsonRestWaze(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		String action = MobileConstantes.URL_JSON_WAZE;
		String strJson = Util.getUrlRemote(action);
		return strJson;
	}

	public MapaIncidenciasRegistro registrar(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		if(incidencia.getIdIncidencia() == null){
			Date now = new Date();
			incidencia.setFecRegistra(now);
			incidencia.setEstado(MobileConstantes.ESTADO_ACTIVO);
			incidencia.setUsuarioRegistro(incidencia.getIdUsuarioSesion());
			mapaIncidenciasRegistroMapper.insertSelective(incidencia);
		}else{
			incidencia.setUsuarioModifica(incidencia.getIdUsuarioSesion());
			incidencia.setFecModifica(new Date());
			mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidencia);
		}
		
		MapaIncidenciasGeometriaCriteria c = new MapaIncidenciasGeometriaCriteria();
		c.createCriteria().andIdecodvalorEqualTo(incidencia.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
		
		mapaIncidenciasGeometriaMapper.deleteByExample(c);
		
		GeometriaLineaCriteria gc = new GeometriaLineaCriteria();
		gc.createCriteria().andIdIncidenciaEqualTo(incidencia.getIdIncidencia());
		geometriaLineaMapper.deleteByExample(gc);
		
		List<String> calles = new ArrayList<String>();
		
		if(incidencia.getTipoEvento() == MobileConstantes.WAZE_CONSTRUCTION_TYPE){
			for (MapaIncidenciasGeometria geometria : incidencia.getDetalleGeometria()) {
				geometria.setIndestado(MobileConstantes.ESTADO_ACTIVO_NUMERO);
				geometria.setIdetipogeometria(MobileConstantes.TIPO_GEOMETRIA_PUNTO);
				geometria.setIdecodident(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				geometria.setIdecodvalor(incidencia.getIdIncidencia());
				mapaIncidenciasGeometriaMapper.insertSelective(geometria);
				calles.add(geometria.getDesdireccion());
			}
		}else{
			for (GeometriaLinea linea : incidencia.getLineas()) {
				linea.setIdIncidencia(incidencia.getIdIncidencia());
				geometriaLineaMapper.insertSelective(linea);
				calles.add(linea.getDesCalle());
			}
		}

		MapaIncidenciasRegistro incidenciaUpdate = new MapaIncidenciasRegistro();
		incidenciaUpdate.setIdIncidencia(incidencia.getIdIncidencia());
		incidenciaUpdate.setDesCallles(StringUtils.join(calles, ", "));
		
		mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidenciaUpdate);
		
		return incidencia;
	}

	public List<MapaIncidenciasRegistro> obtenerIncidenciasNoProcesadas() throws Exception {
		// TODO Auto-generated method stub
		MapaIncidenciasRegistroCriteria c = new MapaIncidenciasRegistroCriteria();
		c.createCriteria();
		
		List<MapaIncidenciasRegistro> list = mapaIncidenciasRegistroMapper.selectByExample(c);
		
		MapaIncidenciasGeometriaCriteria cg = null;
		
		if(list!=null){
			cg = new MapaIncidenciasGeometriaCriteria();
			for (MapaIncidenciasRegistro m : list) {
				cg.clear();
				cg.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				m.setDetalleGeometria(mapaIncidenciasGeometriaMapper.selectByExample(cg));
			}
		}
		
		return list;
	}

	public List<MapaIncidenciasRegistro> listaInicdenciasWaze(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		Date now = new Date();
		MapaIncidenciasRegistroCriteria c = new MapaIncidenciasRegistroCriteria();
		c.createCriteria().andFecHoraFinGreaterThanOrEqualTo(now)
		.andTipoIncidenciaEqualTo(MobileConstantes.FORMULARIO_CIERRE_CALLE)
		.andEstadoEqualTo(MobileConstantes.ESTADO_ACTIVO);
		
		List<MapaIncidenciasRegistro> list = mapaIncidenciasRegistroMapper.selectByExample(c);
		
		MapaIncidenciasGeometriaCriteria cg = null;
		
		if(list!=null){
			cg = new MapaIncidenciasGeometriaCriteria();
			for (MapaIncidenciasRegistro m : list) {
				cg.clear();
				cg.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				m.setDetalleGeometria(mapaIncidenciasGeometriaMapper.selectByExample(cg));
			}
		}
		return list;
	}

	public String listaIncidenciasXmlFormatWaze(MapaIncidenciasRegistro incidencia)
			throws Exception {
		// TODO Auto-generated method stub
		
		String xml ="";
		xml+="<events>";
		
		SimpleDateFormat sdfGMT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z");
		sdfGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		SimpleDateFormat sdfGMT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS z");
		sdfGMT2.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		List<MapaIncidenciasRegistro> list = this.listaInicdenciasWaze(null);
		List<MapaIncidenciasGeometria> puntos = null;
		String[] arrPuntos = null;
		String[] arrPuntosEnd = null;
		
		if(list!=null){
			for (MapaIncidenciasRegistro mapaIncidenciasRegistro : list) {
				xml+="<event id=\""+(mapaIncidenciasRegistro.getIdIncidencia() +"_"+mapaIncidenciasRegistro.getFecRegistra().getTime())+"\"> ";
					
				if(mapaIncidenciasRegistro.getTipoEvento() == MobileConstantes.WAZE_CONSTRUCTION_TYPE){
					xml+="<type>CONSTRUCTION</type> ";
				}else{
					if(mapaIncidenciasRegistro.getTipoEvento() == MobileConstantes.WAZE_ROAD_CLOSED_TYPE){
						xml+="<type>ROAD_CLOSED</type> ";
					}
				}
				
				xml+="<start_date>"+(sdfGMT.format(mapaIncidenciasRegistro.getFecHoraInicio()))+"</start_date>";
				if(mapaIncidenciasRegistro.getFecModifica()!=null){
					xml+="<update_date>"+(sdfGMT.format(mapaIncidenciasRegistro.getFecHoraInicio()))+"</update_date>";
				}else{
					xml+="<update_date></update_date>";
				}
				
				xml+="<end_date>"+(sdfGMT2.format(mapaIncidenciasRegistro.getFecHoraFin()))+"</end_date>";
				xml+="<subtype></subtype>";
				
				xml+="<description>"+mapaIncidenciasRegistro.getDescripcion()+"</description>";
				
				xml+="<severity>"+mapaIncidenciasRegistro.getSeveridad()+"</severity>";
				
				xml+="<location>";
				
				puntos = mapaIncidenciasRegistro.getDetalleGeometria();
				if(puntos!=null){
					
					xml+="<street>"+puntos.get(0).getDesdireccion()+"</street> ";
					xml+="<city>Miraflores</city> ";
					
					arrPuntos = puntos.get(0).getGeometria().split(" "); 
					
					xml+="<latitude>"+arrPuntos[1]+"</latitude> ";
					
					xml+="<longitude>"+arrPuntos[0]+"</longitude> ";
					
					if(mapaIncidenciasRegistro.getDireccionFinal() == null){
						xml+="<direction>BOTH_DIRECTIONS</direction>";
					}else{
						xml+="<direction>"+mapaIncidenciasRegistro.getDireccionFinal()+"</direction>";
					}
					
					xml+="<specify_end>";
					if(puntos.size()>1 && mapaIncidenciasRegistro.getTipoEvento() == MobileConstantes.WAZE_ROAD_CLOSED_TYPE){
						
						arrPuntosEnd = puntos.get(1).getGeometria().split(" "); 
						if(!StringUtils.isEmpty(mapaIncidenciasRegistro.getNombreCalleInicio())){
							xml+="<from_cross_street>"+mapaIncidenciasRegistro.getNombreCalleInicio()+"</from_cross_street>";
						}
						
						if(!StringUtils.isEmpty(mapaIncidenciasRegistro.getNombreCalleFin())){
							xml+="<end_cross_street>"+mapaIncidenciasRegistro.getNombreCalleFin()+"</end_cross_street>";
						}
						
						
						xml+="<end_latitude>"+arrPuntosEnd[1]+"</end_latitude>";
						xml+="<end_longitude>"+arrPuntosEnd[0]+"</end_longitude>";
						
					}
					
					xml+="</specify_end> ";
					
				}
				
				xml+="</location>";
				
				xml+="</event>";
			}
		}
		
		
		xml+="</events>";
		
		
		return xml;
	}

	public MapaIncidenciasRegistro registrarIncidenciaVoxiva(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		if(incidencia.getIdIncidencia() == null){
			mapaIncidenciasRegistroMapper.insertSelective(incidencia);
		}else{
			mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(incidencia);
		}
		
		MapaIncidenciasGeometriaCriteria c = new MapaIncidenciasGeometriaCriteria();
		c.createCriteria().andIdecodvalorEqualTo(incidencia.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
		
		mapaIncidenciasGeometriaMapper.deleteByExample(c);
		
		MapaIncidenciasGeometria geometria = new MapaIncidenciasGeometria();
		geometria.setIndestado(MobileConstantes.ESTADO_ACTIVO_NUMERO);
		geometria.setIdetipogeometria(MobileConstantes.TIPO_GEOMETRIA_PUNTO);
		geometria.setIdecodident(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
		geometria.setIdecodvalor(incidencia.getIdIncidencia());
		geometria.setGeometria(incidencia.getLatitud()+" "+incidencia.getLongitud());
		
		mapaIncidenciasGeometriaMapper.insertSelective(geometria);
		
		return incidencia;
	}

	
	/* *
	 * DOLPHIN
	 * */
	
	public String buildJsonRestDolphin() throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder query = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dActual = sdf.format(new Date());
		query.append(MobileConstantes.ID_USER_DOLPHIN).append(MobileConstantes.CLI_SALT).append(dActual);
		
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(query.toString().getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
		
		String action = MobileConstantes.URL_JSON_DOLPHIN+sb.toString()+MobileConstantes.URL_JSON_DOLPHIN_END;
		String strJson = Util.getUrlRemote(action);
		return strJson;
	}

	@Override
	public List<MapaIncidenciasRegistro> listaInicdenciasVoxiva(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		MapaIncidenciasRegistroCriteria c1 = new MapaIncidenciasRegistroCriteria();
		c1.createCriteria().andTipoIncidenciaEqualTo(MobileConstantes.VOXIVA_WS);
		
		List<MapaIncidenciasRegistro> incidencias = mapaIncidenciasRegistroMapper.selectByExample(c1);
		
		MapaIncidenciasGeometria geometria = null;
		MapaIncidenciasGeometriaCriteria c2 = null;
		
		for (MapaIncidenciasRegistro m : incidencias) {
			try{
			c2 = new MapaIncidenciasGeometriaCriteria();
			c2.createCriteria().andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS).andIdecodvalorEqualTo(m.getIdIncidencia());
			geometria = mapaIncidenciasGeometriaMapper.selectByExample(c2).get(0);
			m.setLatitud(geometria.getGeometria().split(" ")[1]);
			m.setLongitud(geometria.getGeometria().split(" ")[0]);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		return incidencias;
	}
	
	@Override
	public GeoDetalleIncidencia obtenerDetalleIncidencia(Integer issi) throws Exception {

		GeoDetalleIncidencia detalle = null;
		GeoDetalleIncidenciaCriteria c = new GeoDetalleIncidenciaCriteria();
		c.createCriteria().andIssiEqualTo(issi);
		
		List<GeoDetalleIncidencia> listGeoDetalle = geoDetalleIncidenciaMapper.selectByExample(c);
		
		if(listGeoDetalle!=null && listGeoDetalle.size()>0){
			detalle = listGeoDetalle.get(0);
		}
		
		return detalle;
	}

	@Override
	public MapaIncidenciasRegistro obtenerIncidenciaPorId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		MapaIncidenciasRegistro incidencia = mapaIncidenciasRegistroMapper.selectByPrimaryKey(id);
		
		if(incidencia !=null){
			
			if(incidencia.getTipoIncidencia() != null){
					incidencia.setTipo(catalogoService.findCatalogoById(incidencia.getTipoIncidencia()));
			}
			
				
				MapaIncidenciasGeometriaCriteria c = new MapaIncidenciasGeometriaCriteria();
				c.createCriteria().andIdecodvalorEqualTo(incidencia.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				List<MapaIncidenciasGeometria> geometrias = mapaIncidenciasGeometriaMapper.selectByExample(c);
				incidencia.setDetalleGeometria(geometrias);
				

				GeometriaLineaCriteria c2 = new GeometriaLineaCriteria();
				c2.createCriteria().andIdIncidenciaEqualTo(incidencia.getIdIncidencia());
				List<GeometriaLinea> lineas = geometriaLineaMapper.selectByExample(c2);
				incidencia.setLineas(lineas);
				
			
			
			return incidencia;
		}
		
		return null;
	}

	@Override
	public Map<String, Object> listaInicdenciasPortipo(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		int c = 0;
		
		if(incidencia.getStart() == null){
			incidencia.setStart(0);
		}
		
		if(incidencia.getLimit() == null){
			incidencia.setLimit(10);
		}
		
		
		if (incidencia.getStart() != 0) {
			c = c + 1;
		}
		
		if(incidencia.getTipoIncidencia() != null){
			params.put("TIPO_INCIDENCIA", incidencia.getTipoIncidencia());
		}
		
		if(incidencia.getDesTipoIncidencia() != null){
			params.put("NOMBRE_TIPO_EVENTO", incidencia.getDesTipoIncidencia());
		}
		
		if(incidencia.getDescripcion() != null){
			params.put("DESCRIPCION", incidencia.getDescripcion());
		}
		
		if(incidencia.getStrFecIni() != null){
			params.put("FECHA_INI", incidencia.getStrFecIni());
		}
		
		if(incidencia.getStrFecFin() != null){
			params.put("FECHA_FIN", incidencia.getStrFecFin());
		}
		
		if(incidencia.getDesCallles() != null){
			params.put("DES_CALLLES", incidencia.getDesCallles());
		}
		
		params.put("START", incidencia.getStart());
		params.put("LIMIT", incidencia.getLimit());
		
		List<MapaIncidenciasRegistro> list =  mapaIncidenciasRegistroMapper.listarIncidenciasConPaginacion(params);
		
		MapaIncidenciasGeometriaCriteria cg = null;
		
		if(list!=null){
			cg = new MapaIncidenciasGeometriaCriteria();
			for (MapaIncidenciasRegistro m : list) {
				cg.clear();
				cg.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				m.setDetalleGeometria(mapaIncidenciasGeometriaMapper.selectByExample(cg));
			}
		}		
				
		result.put("data",list);
		result.put("count", mapaIncidenciasRegistroMapper.countListarIncidenciasConPaginacion(params));
		
		
		return result;
	}

	@Override
	public void anularIncidencia(Integer id) throws Exception {
		// TODO Auto-generated method stub
		MapaIncidenciasRegistro e = new MapaIncidenciasRegistro();
		e.setIdIncidencia(id);
		e.setFecModifica(new Date());
		e.setEstado(MobileConstantes.ESTADO_INACTIVO);
		mapaIncidenciasRegistroMapper.updateByPrimaryKeySelective(e);
		
	}

	public void enviarAlertasCierreCalle() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("inicio");
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, Object> params = new HashMap<String, Object>();
		String strNow = sdf.format(now); 
		params.put("FECHA_FIN", strNow);
		List<MapaIncidenciasRegistro> list = mapaIncidenciasRegistroMapper.cierreCallesPorVencer(params);
		
		Map<String, Object> paramsPorIniciar = new HashMap<String, Object>();
		paramsPorIniciar.put("FECHA_INI", strNow);
		List<MapaIncidenciasRegistro> listPorIniciar = mapaIncidenciasRegistroMapper.cierreCallesPorIniciar(paramsPorIniciar);

		
		
	}
	
	private void enviarNotificacion(List<MapaIncidenciasRegistro> list,String tipo,Date now) throws Exception{
		
		List<String> calles = null;
		
		List<MapaIncidenciasGeometria> geometrias = null;
		
		MapaIncidenciasGeometriaCriteria criteriaGeo = null;
		
		Catalogo tipoEvento = null;
		
		String tipoCierre = "";
		
		Integer countRegistros = 0;
		
		List<Catalogo> emailsCatalogo = null;
		
		if(list != null){
			
			int diasFaltantes;
			ParametroSistema parametroVencimiento = parametroSistemaService.obtenerParametroSistemaById(MobileConstantes.PARAMETRO_NUM_DIAS_VENCIMIENTO_CIERRA_CALLE);
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
			String mensaje = "<table style=\"border-top:1px #ccc solid;border-left:1px #ccc solid;border-right:1px #ccc solid;width:100%;font-family:Verdana;text-align:left;font-size:12px;\" cellpadding=\"0\" cellspacing=\"0\">";
			mensaje+="<tr><th style=\"padding:5px;background:#f5f5f5;border-right:1px #ccc solid;border-bottom:1px #ccc solid;\">TIPO DE EVENTO</th><th style=\"padding:5px;background:#f5f5f5;border-right:1px #ccc solid;border-bottom:1px #ccc solid;\">DESCRIPCIÓN</th><th style=\"padding:5px;background:#f5f5f5;border-right:1px #ccc solid;border-bottom:1px #ccc solid;\" >INICIO</th><th style=\"padding:5px;background:#f5f5f5;border-right:1px #ccc solid;border-bottom:1px #ccc solid;\" >VENCIMIENTO</th><th style=\"padding:5px;background:#f5f5f5;border-bottom:1px #ccc solid;\" >CALLES</th></tr>";
			for (MapaIncidenciasRegistro m : list) {
				 
				diasFaltantes = Util.daysBetween(now, m.getFecHoraFin());
				
				if(diasFaltantes<=Integer.parseInt(parametroVencimiento.getValor().trim())){
					
					criteriaGeo = new MapaIncidenciasGeometriaCriteria();
					criteriaGeo.clear();
					criteriaGeo.createCriteria().andIdecodvalorEqualTo(m.getIdIncidencia()).andIdecodidentEqualTo(MobileConstantes.TABLA_MAPA_INCIDENCIAS);
				
					geometrias = mapaIncidenciasGeometriaMapper.selectByExample(criteriaGeo);
					calles = new ArrayList<String>();
					for (MapaIncidenciasGeometria g : geometrias) {
						calles.add(g.getDesdireccion());
					}
					
					tipoEvento = catalogoService.findCatalogoById(m.getTipoEvento());
					
					
					if(m.getTipoEvento() == 8749){
						if(m.getTipoCierre()!=null){
						tipoCierre = "("+(m.getTipoCierre().equalsIgnoreCase("T")?"<span style=\"color:red;\">Cierre Total</span>":"<span style=\"color:green;\">Cierre Parcial</span>")+")";
						}else{
							tipoCierre="<span style=\"color:green;\">Cierre Parcial</span>";
						}
					}else{
						tipoCierre ="";
					}
					
					mensaje+="<tr>"
								+ "<td style=\"padding:5px;border-right:1px #ccc solid;border-bottom:1px #ccc solid;font-size:13px;\">"+tipoEvento.getDesNombre()+" "+tipoCierre+"</td>"
								+ "<td style=\"padding:5px;border-right:1px #ccc solid;border-bottom:1px #ccc solid;font-size:13px;\">"+m.getDescripcion()+"</td>"
								+ "<td style=\"padding:5px;border-right:1px #ccc solid;border-bottom:1px #ccc solid;font-size:13px;\" >"+sdf2.format(m.getFecHoraInicio())+"</td>"
								+ "<td style=\"border-bottom:1px #ccc solid;padding:5px;border-right:1px #ccc solid;font-size:13px;\" >"+sdf2.format(m.getFecHoraFin())+"</td>"
								+ "<td style=\"padding:5px;border-bottom:1px #ccc solid;font-size:13px;\" >"+StringUtils.join(calles, ", ")+"</td>"
							+ "</tr>";
				
				countRegistros++;
					
				}
				
			}
			
			mensaje+="</table>";
			
			mensaje ="<p>Estimado Usuario los siguientes registros de cierre de calles estan por vencer en pocos dias:</p>"+mensaje+""
					+ "<p>Para acceder a la consulta de cierre de calles hacer click <a href=\"http://digital.miraflores.gob.pe:8080/mobileApps/cierre-calles/consulta-cierre-calles\">aqui</a></p>";
			
			if(countRegistros > 0){
				
				emailsCatalogo = catalogoService.findCatalogoByGrupo(MobileConstantes.GRUPO_EMAILS_NOTI_CIERRE_CALLES);
				
				if(emailsCatalogo != null){
					String[] arrMails = new String[emailsCatalogo.size()];
					Catalogo emailCatalogo = null;
					for(int i = 0 ; i < emailsCatalogo.size() ; i++){
						emailCatalogo = emailsCatalogo.get(i);
						arrMails[i] = emailCatalogo.getDesNombre();
					}
					emailService.enviarCorreo(arrMails, "Notificación de vencimiento de cierre de calles", mensaje);
				}
				
			}
			
			
		}
	}

	@Override
	public File exportarExcelBandejaCierreCalles(MapaIncidenciasRegistro incidencia) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		
		int c = 0;
		
		if(incidencia.getStart() == null){
			incidencia.setStart(0);
		}
		
		if(incidencia.getLimit() == null){
			incidencia.setLimit(100000);
		}
		
		
		if (incidencia.getStart() != 0) {
			c = c + 1;
		}
		
		if(incidencia.getTipoIncidencia() != null){
			params.put("TIPO_INCIDENCIA", incidencia.getTipoIncidencia());
		}
		
		if(!StringUtils.isEmpty(incidencia.getDesTipoIncidencia())){
			params.put("NOMBRE_TIPO_EVENTO", incidencia.getDesTipoIncidencia());
		}
		
		if(!StringUtils.isEmpty(incidencia.getDescripcion())){
			params.put("DESCRIPCION", incidencia.getDescripcion());
		}
		
		if(!StringUtils.isEmpty(incidencia.getStrFecIni())){
			params.put("FECHA_INI", incidencia.getStrFecIni());
		}
		
		if(!StringUtils.isEmpty(incidencia.getStrFecFin())){
			params.put("FECHA_FIN", incidencia.getStrFecFin());
		}
		
		if(!StringUtils.isEmpty(incidencia.getDesCallles())){
			params.put("DES_CALLLES", incidencia.getDesCallles());
		}
		
		params.put("START", incidencia.getStart());
		params.put("LIMIT", incidencia.getLimit());
		
		List<MapaIncidenciasRegistro> list =  mapaIncidenciasRegistroMapper.listarIncidenciasConPaginacion(params);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Cierre de Calles");
				 
		 Row rowInit = sheet.createRow(0);
		 
		 Font headerFont = workbook.createFont();
		 headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		 
		 CellStyle headerStyle = workbook.createCellStyle();
		 headerStyle.setFont(headerFont);
		    
		 
		 Cell cell1 = rowInit.createCell(0);
		 cell1.setCellValue("TIPO EVENTO");
		 cell1.setCellStyle(headerStyle);
		 
		 Cell cell2 = rowInit.createCell(1);
		 cell2.setCellValue("TIPO CIERRE");
		 cell2.setCellStyle(headerStyle);
		 
		 Cell cell3 = rowInit.createCell(2);
		 cell3.setCellValue("DESCRIPCIÓN");
		 cell3.setCellStyle(headerStyle);
		 
		 Cell cell4 = rowInit.createCell(3);
		 cell4.setCellValue("CALLES");
		 cell4.setCellStyle(headerStyle);
		 
//		 Cell cell5 = rowInit.createCell(4);
//		 cell5.setCellValue("DIRECCION CARDINAL");
//		 cell5.setCellStyle(headerStyle);
		 
		 Cell cell6 = rowInit.createCell(4);
		 cell6.setCellValue("FECHA INICIO");
		 cell6.setCellStyle(headerStyle);
		 
		 Cell cell7 = rowInit.createCell(5);
		 cell7.setCellValue("FECHA FINALIZAÓN");
		 cell7.setCellStyle(headerStyle);
		 
		 int index = 1;
		 
		 Row row = null;
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

   		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("NORTH","NORTE");
		 map.put("SOUTH","SUR");
		 map.put("EAST","ESTE");
		 map.put("WEST","OESTE");
		 map.put("NORTH_WEST","NOR-OESTE");
		 map.put("SOUTH_WEST","SUR-OESTE");
		 map.put("NORTH_EAST","NOR-ESTE");
		 map.put("SOUTH_EAST","SUR-ESTE");
		 map.put("BOTH_DIRECTIONS","AMBAS DIRECCIONES");
		 
		 for (MapaIncidenciasRegistro m : list) {
			 row = sheet.createRow(index);
			 row.createCell(0).setCellValue(m.getNombreTipoEvento());
			 row.createCell(1).setCellValue((m.getTipoCierre()!=null && m.getTipoCierre().equalsIgnoreCase("T"))?"TOTAL":"PARCIAL");
			 row.createCell(2).setCellValue(m.getDescripcion());
			 row.createCell(3).setCellValue(m.getDesCallles());
			 //row.createCell(4).setCellValue((map.get(m.getDireccionFinal())!=null)?map.get(m.getDireccionFinal()).toString():m.getDireccionFinal());
			 row.createCell(4).setCellValue(sdf.format(m.getFecHoraInicio()));
			 row.createCell(5).setCellValue(sdf.format(m.getFecHoraFin()));
			 index++;
		}
		
		 sheet.autoSizeColumn(0);
		 sheet.autoSizeColumn(1);
		 sheet.autoSizeColumn(2);
		 sheet.autoSizeColumn(3);
		 sheet.autoSizeColumn(4);
		 sheet.autoSizeColumn(5);
//		 sheet.autoSizeColumn(6);
		 
		File temp = null;
		try {
			
			temp = File.createTempFile("Export_Cierre_Calles", ".xls");
			FileOutputStream out =  new FileOutputStream(temp);
			workbook.write(out);
			return temp;
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		    return null;
		} catch (IOException e) {
		    e.printStackTrace();
		    return null;
		}
	
		
	
	}
	
	
	
}
