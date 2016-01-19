package pe.gob.miraflores.mobile.controller;


import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.gob.miraflores.mobile.service.catalogo.LocalCatalogoService;


@Controller
@RequestMapping(CatalogoController.BASE_URL_MODULO_CATALOGO)
public class CatalogoController {

	protected final Log log = LogFactory.getLog(getClass());
	protected static final String BASE_URL_MODULO_CATALOGO = "/catalogo";

	@Autowired
	private LocalCatalogoService localCatalogoService;

	@RequestMapping
	public void getCatalogo(Integer idGrupo, HttpServletResponse response)
			throws Exception {
		try {
			
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String jsonStr = localCatalogoService.getJsonCatalogoByGrupo(idGrupo);
			if (!StringUtils.isEmpty(jsonStr)) {
				writer.write(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
