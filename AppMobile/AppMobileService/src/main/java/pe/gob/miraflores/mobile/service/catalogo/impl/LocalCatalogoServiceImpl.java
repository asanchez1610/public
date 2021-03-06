package pe.gob.miraflores.mobile.service.catalogo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.miraflores.mobile.constantes.MobileConstantes;
import pe.gob.miraflores.mobile.dao.catalogo.CatalogoMapper;
import pe.gob.miraflores.mobile.domain.catalogo.Catalogo;
import pe.gob.miraflores.mobile.domain.catalogo.CatalogoCriteria;
import pe.gob.miraflores.mobile.service.catalogo.LocalCatalogoService;
import pe.gob.miraflores.mobile.util.Util;

@Service
public class LocalCatalogoServiceImpl implements LocalCatalogoService {

	@Autowired
	private CatalogoMapper catalogoMapper;
	
	public String getJsonCatalogoByGrupo(Integer idGrupo) throws Exception {
		// TODO Auto-generated method stub
		String action = "obtenerCatalogo.muni?ide="+idGrupo;
		String strJson = Util.getUrlRemote(MobileConstantes.URL_MIRAFLORES+action);
		return strJson;
	}

	public Catalogo findCatalogoByCodigo(String codigo,Integer idGrupo) throws Exception {
		// TODO Auto-generated method stub
		CatalogoCriteria criteria = new CatalogoCriteria();
		criteria.createCriteria().andCodCodigoEqualTo(codigo).andIndEstadoEqualTo(MobileConstantes.ESTADO_ACTIVO_STRING).andIdeGrupoEqualTo(idGrupo);
		List<Catalogo> list = catalogoMapper.selectByExample(criteria);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public Catalogo findCatalogoById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return catalogoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Catalogo> findCatalogoByGrupo(Integer idGrupo) throws Exception {
		// TODO Auto-generated method stub
		
		if(idGrupo == null){
			return null;
		}
		
		CatalogoCriteria c = new CatalogoCriteria();
		c.createCriteria().andIndEstadoEqualTo(MobileConstantes.ESTADO_ACTIVO_STRING).andIdeGrupoEqualTo(idGrupo);
			
		return catalogoMapper.selectByExample(c);
	}

}
