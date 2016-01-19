package pe.gob.miraflores.mobile.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pe.gob.miraflores.mobile.service.mapaincidencias.MapaIncidenciasService;

@Service("mobileJob")
public class MobileJob {

	@Autowired
	private MapaIncidenciasService mapaIncidenciasService;
	
	@Scheduled(cron="0 0 10 * * ?")
	public void test() throws Exception {
		//mapaIncidenciasService.enviarAlertasCierreCalle();
	}
	
}
