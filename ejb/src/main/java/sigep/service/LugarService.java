package sigep.service;


import sigep.beans.ArtistaBean;
import sigep.beans.LugarBean;
import sigep.beans.TagBean;
import sigep.beans.TipoShowBean;
import sigep.data.dao.ArtistaDao;
import sigep.data.dao.LugarDao;
import sigep.data.dao.TipoShowDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Artista;
import sigep.model.Lugar;
import sigep.model.Tag;
import sigep.model.TipoShow;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class LugarService extends BaseService<Lugar, LugarBean> {

    @Inject
    private LugarDao dao;
    @Inject
    private TipoShowDao daoTipoShow;

    public static final String PREFIJO_ARCHIVO = "lugar";


    @Override
    public LugarBean findById(Long idEntity) {
        return dao.findById(idEntity).getBeanCompleto();
    }

    @Override
    public LugarBean create(LugarBean bean) throws SIGEPException {
        /*bean.setTags(tagService.createByList(bean.getTags()));*/
        return dao.create(new Lugar(bean)).getBeanCompleto();
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

    public List<LugarBean> findByUsuario(Long idUsuario){
        List<Lugar> lugares = dao.findByUsuario(idUsuario);

        List<LugarBean> beanList = new ArrayList<LugarBean>();
        for(Lugar l : lugares)
        {
            beanList.add(l.getBeanCompleto());
        }

        return beanList;

    }

    public List<LugarBean> findByFilters(LugarBean lugar){
        List<Lugar> lugares = dao.lugarByFilters(lugar);

        List<LugarBean> beanList = new ArrayList<LugarBean>();
        for(Lugar l : lugares)
        {
            beanList.add(l.getBeanCompleto());
        }

        return beanList;

    }

    public List<LugarBean> findByUsuarioMenu(Long idUsuario){
        List<Lugar> lugares = dao.findByUsuarioMenu(idUsuario);

        List<LugarBean> beanList = new ArrayList<LugarBean>();
        for(Lugar l : lugares)
        {
            beanList.add(l.getBeanCompleto());
        }

        return beanList;

    }

//    public List<TagBean> findTagByTipoShow(Long idTipoShow){
//        List<Tag> tags = dao.findTagByTipoShow(idTipoShow);
//
//        List<TagBean> beanList = new ArrayList<TagBean>();
//
//        for(Tag t : tags)
//        {
//            beanList.add(t.getBean());
//        }
//        return beanList;
//    }


    @Override
    public void update(LugarBean bean) throws SIGEPException {
        dao.update(new Lugar(bean));
    }

    @Override
    public void bussinessValidation(Lugar entity) throws SIGEPValidationException {

    }
}
