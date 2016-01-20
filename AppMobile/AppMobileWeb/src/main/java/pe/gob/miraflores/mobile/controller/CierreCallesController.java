package pe.gob.miraflores.mobile.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistro;
import pe.gob.miraflores.mobile.exception.NegocioException;
import pe.gob.miraflores.mobile.service.mapaincidencias.MapaIncidenciasService;


@Controller
@RequestMapping(CierreCallesController.BASE_URL_MODULO_APE)
public class CierreCallesController {

	protected final Log log = LogFactory.getLog(getClass());
	protected static final String BASE_URL_MODULO_APE = "/cierre-calles";

	@Autowired
	private MapaIncidenciasService mapaIncidenciasService;

	@RequestMapping
	public ModelAndView startPage() {
		ModelAndView modelAndView = new ModelAndView("blank");
		return modelAndView;
	}

	@RequestMapping(value = "/registro")
	public ModelAndView formularioRegistro(Integer id) {
		ModelAndView modelAndView = new ModelAndView("private/formularioRegistroCierreCalles");
		modelAndView.addObject("idIncidencia", id);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/waze-service-xmlformat")
	public ModelAndView listaIncidenciasWazeXmlFormat(MapaIncidenciasRegistro incidencia)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView("private/xmlFormatWaze");
		
		try {
			String xmlString = mapaIncidenciasService.listaIncidenciasXmlFormatWaze(incidencia);
			modelAndView.addObject("xml", xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/registrar-incidencia" , method=RequestMethod.POST)
	public @ResponseBody
	Map<String, ? extends Object> registrarFormaulario(
			MapaIncidenciasRegistro incidencia) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {

			mapaIncidenciasService.registrar(incidencia);
			data.put("success", Boolean.TRUE);
		} catch (NegocioException e) {
			data.put("success", Boolean.FALSE);
			data.put("message", e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			data.put("success", Boolean.FALSE);
			data.put("message", "Error al intentar registrar los datos.");
			e.printStackTrace();
		}
		return data;
	}

	
	@RequestMapping(value = "/bandeja-cierre-calles")
	public ModelAndView bandejaCierreCalles() {
		ModelAndView modelAndView = new ModelAndView("private/bandejaCierreCalles");
		modelAndView.addObject("modoConsulta", "0");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/consulta-cierre-calles")
	public ModelAndView consultaCierreCalles() {
		ModelAndView modelAndView = new ModelAndView("private/bandejaCierreCalles");
		modelAndView.addObject("modoConsulta", "1");
		return modelAndView;
	}
	
	@RequestMapping(value = "/view-mapa-consulta")
	public ModelAndView viewMapaConsulta(String puntos,Integer idIncidencia) {
		ModelAndView modelAndView = new ModelAndView("private/view-mapa-consulta");
		modelAndView.addObject("puntos",puntos);
		modelAndView.addObject("idIncidencia",idIncidencia);
		return modelAndView;
	}
	
	@RequestMapping(value = "/exportar-bandeja")
	public void exportarBandeja(MapaIncidenciasRegistro incidencia,HttpServletResponse response) {
		System.out.println("exportar bandeja");
		System.out.println(incidencia.toString());
		
        try {
            File file = mapaIncidenciasService.exportarExcelBandejaCierreCalles(incidencia);
            response.setHeader("Content-Disposition", "; filename=\"CIERRE_CALLES.xls\";");
            response.setContentLength(Long.valueOf(file.length()).intValue());
            response.setContentType("application/octet-stream");
            FileInputStream fileIS = new FileInputStream(file);
            IOUtils.copy(fileIS, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }  
        
    
		
	}
	
	
}
