package sigep.service;

import sigep.beans.SeguidoresXLugarBean;
import sigep.beans.UsuarioBean;
import sigep.data.dao.SeguidoresXLugarDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.SeguidoresXLugar;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class SeguidoresXLugarService extends BaseService<SeguidoresXLugar, SeguidoresXLugarBean> {

    @Inject
    SeguidoresXLugarDao dao;

    @Override
    public SeguidoresXLugarBean findById(Long idEntity) {
        return null;
    }

    @Override
    public SeguidoresXLugarBean create(SeguidoresXLugarBean bean) throws SIGEPException {
        return dao.create(new SeguidoresXLugar(bean)).getBeanCompleto();
    }

    @Override
    public void update(SeguidoresXLugarBean bean) throws SIGEPException {

    }

    @Override
    public void bussinessValidation(SeguidoresXLugar entity) throws SIGEPValidationException {

    }


    public UsuarioBean findBySeguidor(SeguidoresXLugarBean seguidorLugar){
        return dao.finBySeguidor(seguidorLugar.getLugar().getId(), seguidorLugar.getUsuario().getId() ).getBean();
    }

    public void deleteBySeguidor(UsuarioBean seguidorLugar){
        dao.delete(seguidorLugar.getId());
    }
}
