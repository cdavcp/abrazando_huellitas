package sigep.service;

import sigep.beans.SeguidoresXArtistaBean;
import sigep.beans.UsuarioBean;
import sigep.data.dao.SeguidoresXArtistaDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.SeguidoresXArtista;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class SeguidoresXArtistaService extends BaseService<SeguidoresXArtista, SeguidoresXArtistaBean> {

    @Inject
    SeguidoresXArtistaDao dao;

    @Override
    public SeguidoresXArtistaBean findById(Long idEntity) {
        return null;
    }

    @Override
    public SeguidoresXArtistaBean create(SeguidoresXArtistaBean bean) throws SIGEPException {
        return dao.create(new SeguidoresXArtista(bean)).getBeanCompleto();
    }

    @Override
    public void update(SeguidoresXArtistaBean bean) throws SIGEPException {

    }

    @Override
    public void bussinessValidation(SeguidoresXArtista entity) throws SIGEPValidationException {

    }


    public UsuarioBean findBySeguidor(SeguidoresXArtistaBean seguidorArtista){
        return dao.finBySeguidor(seguidorArtista.getArtista().getId(), seguidorArtista.getUsuario().getId() ).getBean();
    }

    public void deleteBySeguidor(UsuarioBean seguidorArtista){
        dao.delete(seguidorArtista.getId());
    }
}
