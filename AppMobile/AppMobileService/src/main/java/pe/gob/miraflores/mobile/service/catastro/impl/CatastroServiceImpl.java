package pe.gob.miraflores.mobile.service.catastro.impl;

import org.springframework.stereotype.Service;

import pe.gob.miraflores.mobile.constantes.MobileConstantes;
import pe.gob.miraflores.mobile.service.catastro.CatastroService;
import pe.gob.miraflores.mobile.util.Util;

@Service
public class CatastroServiceImpl implements CatastroService{
	
	public String getGeoreferenciaXvia(String query) throws Exception {
		// TODO Auto-generated method stub
		if(query == null){
			query = "";
		}
		query = query.replaceAll(" ", "%20");
		query = query.replaceAll("/i", "");
		query = query.replaceAll("/", "");
		String action = "obtenergeoreferenciaxvia.muni?query="+query+"&start=0&limit=100&page=1";
		String strJson = Util.getUrlRemote(MobileConstantes.URL_MIRAFLORES_2+action);
		return strJson;
	}


}
