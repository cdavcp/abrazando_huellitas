package sigep.service.Ivent;

import sigep.beans.Ivent.ComentarioXEventoBean;
import sigep.data.dao.Ivent.ComentarioXEventoDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.ComentarioXEvento;
import sigep.service.sigep.BaseService;
import sigep.util.DateUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabrizio on 31/10/2016.
 */
@Stateless
public class ComentarioXEventoService extends BaseService<ComentarioXEvento, ComentarioXEventoBean> {

    @Inject
    ComentarioXEventoDao dao;
    @Override
    public ComentarioXEventoBean findById(Long idEntity) {
        return null;
    }

    public List<ComentarioXEventoBean> findByIdEvento(Long idEntity) {
        List<ComentarioXEvento> comentarios = dao.findByEvento(idEntity);

        List<ComentarioXEventoBean> beanList = new ArrayList<ComentarioXEventoBean>();
        for(ComentarioXEvento l : comentarios)
        {
            beanList.add(l.getBeanCompleto());
        }

        return beanList;
    }

    @Override
    public ComentarioXEventoBean create(ComentarioXEventoBean bean) throws SIGEPException {
        bean.setFechaComentario(DateUtil.currentDate());
        return dao.create(new ComentarioXEvento(bean)).getBeanCompleto();
    }

    @Override
    public void update(ComentarioXEventoBean bean) throws SIGEPException {

    }

    @Override
    public void bussinessValidation(ComentarioXEvento entity) throws SIGEPValidationException {

    }
}
