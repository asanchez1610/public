package pe.gob.miraflores.mobile.controller;


import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.gob.miraflores.mobile.service.catastro.CatastroService;


@Controller
@RequestMapping(CatastroController.BASE_URL_MODULO_APE)
public class CatastroController {

	protected final Log log = LogFactory.getLog(getClass());
	protected static final String BASE_URL_MODULO_APE = "/catastro";

	@Autowired
	private CatastroService catastroService;

	@RequestMapping
	public ModelAndView startPage() {
		ModelAndView modelAndView = new ModelAndView("blank");
		return modelAndView;
	}
	
	@RequestMapping(value = "/obtener-georeferencia-via")
	public void getCatalogo(String query, HttpServletResponse response,String callback)
			throws Exception {
		try {
			
			boolean jsonP = false;
			String cb = callback;
			String jsonStr = catastroService.getGeoreferenciaXvia(query);
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
