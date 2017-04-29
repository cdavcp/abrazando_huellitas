package sigep.service;

import sigep.beans.ArtistaBean;
import sigep.beans.SeguidoresXArtistaBean;
import sigep.beans.UsuarioBean;
import sigep.beans.VotacionArtistaBean;
import sigep.data.dao.ArtistaDao;
import sigep.data.dao.SeguidoresXArtistaDao;
import sigep.data.dao.VotacionArtistaDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Artista;
import sigep.model.SeguidoresXArtista;
import sigep.model.VotacionArtista;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class VotacionArtistaService extends BaseService<VotacionArtista, VotacionArtistaBean> {

    @Inject
    VotacionArtistaDao dao;

    @Inject
    ArtistaDao daoArtista;


    @Override
    public VotacionArtistaBean findById(Long idEntity) {
        return dao.findById(idEntity).getBeanCompleto();
    }

    @Override
    public VotacionArtistaBean create(VotacionArtistaBean bean) throws SIGEPException {

        VotacionArtistaBean votacionArtista = dao.create(new VotacionArtista(bean)).getBeanCompleto();
        ArtistaBean artista = bean.getArtista();
        artista.setPuntuacion(dao.findPromedioArtista(bean.getArtista().getId()));
        daoArtista.update(new Artista(artista));

        return votacionArtista;
    }

    @Override
    public void update(VotacionArtistaBean bean) throws SIGEPException {
        dao.update(new VotacionArtista(bean)).getBeanCompleto();
        ArtistaBean artista = bean.getArtista();
        artista.setPuntuacion(dao.findPromedioArtista(bean.getArtista().getId()));
        daoArtista.update(new Artista(artista));
    }

    public VotacionArtistaBean findBySeguidor(VotacionArtistaBean votacion){
        return dao.finBySeguidor(votacion.getArtista().getId(), votacion.getUsuario().getId() ).getBeanCompleto();
    }

    @Override
    public void bussinessValidation(VotacionArtista entity) throws SIGEPValidationException {

    }
}
