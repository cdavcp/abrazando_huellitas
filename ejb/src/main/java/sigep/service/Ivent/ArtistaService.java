package sigep.service.Ivent;


import sigep.beans.Ivent.ArtistaBean;
import sigep.beans.Ivent.TagBean;
import sigep.beans.Ivent.TipoShowBean;
import sigep.data.dao.Ivent.ArtistaDao;
import sigep.data.dao.Ivent.TagDao;
import sigep.data.dao.Ivent.TipoShowDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.Artista;
import sigep.model.Ivent.Tag;
import sigep.model.Ivent.TipoShow;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ArtistaService extends BaseService<Artista, ArtistaBean> {

    @Inject
    private TagService tagService;

    @Inject
    private ArtistaDao dao;
    @Inject
    private TagDao daoTag;
    @Inject
    private TipoShowDao daoTipoShow;


    public static final String PREFIJO_ARCHIVO = "artista";


    @Override
    public ArtistaBean findById(Long idEntity) {
        return dao.findById(idEntity).getBeanCompleto();
    }



    @Override
    public ArtistaBean create(ArtistaBean bean) throws SIGEPException{

        bean.setTags(tagService.createByList(bean.getTags()));
        return dao.create(new Artista(bean)).getBeanCompleto();
    }


    public List<ArtistaBean> findArtistaMG() {
        List<ArtistaBean> beans = new ArrayList<ArtistaBean>();
        List<Artista> eventos;
        eventos = dao.artistaMg();
        if (eventos != null) {
            for (Artista evento : eventos) {
                beans.add(evento.getBeanCompleto());
            }
        }
        return beans;
    }


    public List<TipoShowBean> findTiposShow(){
        List<TipoShow> tipoShows = daoTipoShow.findAll();

        List<TipoShowBean> beanList = new ArrayList<TipoShowBean>();

        for(TipoShow t : tipoShows)
        {
            beanList.add(t.getBean());
        }
        return beanList;
    }

    public List<ArtistaBean> findByFilters(ArtistaBean artista){
        List<Artista> artistas = dao.artistaByFilters(artista);

        List<ArtistaBean> beanList = new ArrayList<ArtistaBean>();
        for(Artista l : artistas)
        {
            beanList.add(l.getBeanCompleto());
        }

        return beanList;

    }


    public List<ArtistaBean> findByUsuarioMenu(Long idUsuario){
        List<Artista> artistas = dao.findByUsuarioMenu(idUsuario);

        List<ArtistaBean> beanList = new ArrayList<ArtistaBean>();
        for(Artista l : artistas)
        {
            beanList.add(l.getBeanCompleto());
        }

        return beanList;

    }


    public List<ArtistaBean> findByUsuario(Long idUsuario){
        List<Artista> artistas = dao.findByUsuario(idUsuario);

        List<ArtistaBean> beanList = new ArrayList<ArtistaBean>();
        for(Artista a : artistas)
        {
            beanList.add(a.getBeanCompleto());
        }

        return beanList;

    }

    public List<TagBean> findTagByTipoShow(Long idTipoShow){
        List<Tag> tags = dao.findTagByTipoShow(idTipoShow);

        List<TagBean> beanList = new ArrayList<TagBean>();

        for(Tag t : tags)
        {
            beanList.add(t.getBean());
        }
        return beanList;
    }


    @Override
    public void update(ArtistaBean bean) throws SIGEPException {
        dao.update(new Artista(bean));
    }

    @Override
    public void bussinessValidation(Artista entity) throws SIGEPValidationException {

    }
}
