package pe.gob.miraflores.mobile.service.seguridad;

import pe.gob.miraflores.mobile.domain.seguridad.UsuarioLogin;

public interface SeguridadService {

	public UsuarioLogin getUsuarioByLogin(String desLogin) throws Exception;
	
}
