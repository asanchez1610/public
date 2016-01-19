package pe.gob.miraflores.mobile.service.seguridad.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.miraflores.mobile.constantes.MobileConstantes;
import pe.gob.miraflores.mobile.dao.seguridad.UsuarioLoginMapper;
import pe.gob.miraflores.mobile.domain.seguridad.UsuarioLogin;
import pe.gob.miraflores.mobile.domain.seguridad.UsuarioLoginCriteria;
import pe.gob.miraflores.mobile.service.seguridad.SeguridadService;

@Service
public class SeguridadServiceImpl implements SeguridadService {

	@Autowired
	private UsuarioLoginMapper usuarioLoginMapper;
	
	@Override
	public UsuarioLogin getUsuarioByLogin(String desLogin) throws Exception {
		// TODO Auto-generated method stub
		UsuarioLoginCriteria c = new UsuarioLoginCriteria();
		c.createCriteria().andDesLoginUserLikeInsensitive("%"+desLogin+"%").andIdeEstadoNotEqualTo(MobileConstantes.ESTADO_INACTIVO_NUMERO).andInHabilitadoEqualTo("1");
		
		List<UsuarioLogin> result = usuarioLoginMapper.selectByExample(c);
		
		if(result != null && result.size()>0){
			return result.get(0);
		}
		
		return null;
	}

}
