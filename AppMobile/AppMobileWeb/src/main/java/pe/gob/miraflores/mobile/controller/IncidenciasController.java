package pe.gob.miraflores.mobile.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pe.gob.miraflores.mobile.domain.mapaincidencias.MapaIncidenciasRegistro;
import pe.gob.miraflores.mobile.exception.NegocioException;
import pe.gob.miraflores.mobile.service.email.EmailService;
import pe.gob.miraflores.mobile.service.mapaincidencias.MapaIncidenciasService;


@Controller
@RequestMapping(IncidenciasController.BASE_URL_MODULO_INCIDENCIA)
public class IncidenciasController {

	protected final Log log = LogFactory.getLog(getClass());
	protected static final String BASE_URL_MODULO_INCIDENCIA = "/mapa-incidencia";

	@Autowired
	private MapaIncidenciasService mapaIncidenciasService;
	
	@Autowired
	private EmailService emailService;

	@RequestMapping
	public ModelAndView startPage() {
		ModelAndView modelAndView = new ModelAndView("blank");
		return modelAndView;
	}

	
	
	@RequestMapping(value = "/voxiva-list" , method=RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> voxivaList(
			MapaIncidenciasRegistro incidencia) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.put("data", mapaIncidenciasService.listaInicdenciasVoxiva(incidencia));
		} catch (Exception e) {
			// TODO: handle exception
			data.put("data", null);
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value = "/get-incidencia-by-id")
	public @ResponseBody
	Map<String, ? extends Object> getIncidenciaById(Integer id) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {

			data.put("incidencia", mapaIncidenciasService.obtenerIncidenciaPorId(id));
			data.put("success", Boolean.TRUE);
		} catch (NegocioException e) {
			data.put("success", Boolean.FALSE);
			data.put("message", e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			data.put("success", Boolean.FALSE);
			data.put("message", "Error al obtener los datos.");
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value = "/anular-incidencia")
	public @ResponseBody
	Map<String, ? extends Object> anularincidencia(Integer id) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			mapaIncidenciasService.anularIncidencia(id);
			data.put("success", Boolean.TRUE);
		}catch (Exception e) {
			// TODO: handle exception
			data.put("success", Boolean.FALSE);
			data.put("message", "Error al anular el registro.");
			e.printStackTrace();
		}
		return data;
	}
	
	
		
	@RequestMapping(value = "/incidencias-list" , method=RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> incidenciasList(
			MapaIncidenciasRegistro incidencia) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.putAll(mapaIncidenciasService.listaInicdenciasPortipo(incidencia));
		} catch (Exception e) {
			// TODO: handle exception
			data.put("data", null);
			data.put("count", 0);
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	@RequestMapping(value = "/test-email" , method=RequestMethod.GET)
	public @ResponseBody
	Map<String, ? extends Object> testEmail() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			
//			String[] arrMails = new String[1];
//			arrMails[0] = "FATIMARODRIGUEZSERRA@GMAIL.COM";
//			String mensaje ="<div style=\"width:650px;padding:15px;background:#f5f5f5;border:1px #ccc solid;font-family:Verdana;font-size:12px;\"><div style=\"background:#FFF;margin:4px 0px 4px 0px;border:solid 1px #CCC;\"><center><img height=\"65\" width=\"200\" src=\"http://digital.miraflores.gob.pe:8080/MirafloresV1/librerias/lib/imglibro/cabMirMail.jpg\"></center></div><h1 style=\"font-size:16px;text-align:center;\">Formulario de Libro de Reclamo N° 50-2015</h1><p><b>ESTIMADO(A):</b></p><p>UD. HA REALIZADO UNA SOLICITUD DE TIPO: <b>LIBRO DE RECLAMO,</b> ENVIANDO LA SIGUIENTE INFORMACIÓN:</p><table style=\"text-transform:uppercase;font-size:12px;\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td style=\"text-align:right;padding:5px;\"><b>SOLICITANTE:</b></td><td style=\"padding:5px;\" >CARMELA ANGELICA MARIA DE FATIMA</td></tr><tr><td style=\"text-align:right;padding:5px;\"><b>Local de Reclamo:</b></td><td style=\"padding:5px;\">PALACIO MUNICIPAL</td></tr><tr><td style=\"text-align:right;padding:5px;\"><b>fecha:</b></td><td style=\"padding:5px;\">12/12/2015</td></tr><tr><td style=\"text-align:right;padding:5px;\"><b>dni:</b></td><td style=\"padding:5px;\">07816120</td></tr><tr><td style=\"text-align:right;padding:5px;\"><b>TELÉFONO:</b></td><td style=\"padding:5px;\">980902244</td></tr><tr><td style=\"text-align:right;padding:5px;\"><b>DOMICILIO:</b></td><td style=\"padding:5px;\">CALLE BERLIN 1046 </td></tr><tr><td style=\"text-align:right;padding:5px;\"><b>correo electrónico:</b></td><td style=\"padding:5px;\">FATIMARODRIGUEZSERRA@GMAIL.COM </td></tr><tr><td style=\"text-align:right;padding:5px;\"><b>tipo servicio:</b></td><td style=\"padding:5px;\">ACCESO A LA INFORMACION SOLICITUD Nº 1269 EL 12 DE NOVIEMBRE DEL 2015 SOLICITE UNA COPIA DE LA LICENCIA DE EDIFICACIÓN CORRESPONDIENTE A LA INICIATIVA PRIVADA DE LOS ESTACIONAMIENTOS SUBTERRÁNEOS.EL 20 DE NOVIEMBRE SOLICITARON UNA PRORROGA DE CINCO DÍAS ÚTILES.¿QUE PASÓ? HA TRANSCURRIDO UN MES DESDE MI PEDIDO Y NADIE RESPONDE.</td></tr><tr><td style=\"text-align:right;padding:5px;\"><b>DESCRIPCIÓN DEL RECLAMO:</b></td><td style=\"padding:5px;\">REQUIERO LA INFORMACION</td></tr></tbody></table>";
//			emailService.enviarCorreo(arrMails, "Libro de Reclamo Nro. 50 - Municipalidad de Miraflores", mensaje);
			
			data.put("success", Boolean.TRUE);
		} catch (Exception e) {
			// TODO: handle exception
			data.put("success", Boolean.FALSE);
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value = "/sin-acceso")
	public ModelAndView demoMapaDistrito() {
		ModelAndView modelAndView = new ModelAndView("public/sinAcceso");
		return modelAndView;
	}
	
//	@RequestMapping(value = "/demo-distritos")
//	public ModelAndView demoMapaDistrito() {
//		ModelAndView modelAndView = new ModelAndView("public/mapaDistritosDemo");
//		return modelAndView;
//	}
	
	
	@RequestMapping(value = "/voxiva-register-incidencia")
	public void getCatalogo(Integer idIncidencia,String latitud,String longitud, HttpServletResponse response,String callback)
			throws Exception {
		try {
			
			boolean jsonP = false;
			String cb = callback;
			if (cb != null) {
			    jsonP = true;
			    response.setContentType("text/javascript");
			} else {
			    response.setContentType("application/json");
			}
			PrintWriter writer = response.getWriter();
			if (jsonP) {
				writer.write(cb + "(");
			}
			
			String jsonStr = "{success:true}";
			
			if (!StringUtils.isEmpty(jsonStr)) {
				writer.write(jsonStr);
			}
			if (jsonP) {
				writer.write(");");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
