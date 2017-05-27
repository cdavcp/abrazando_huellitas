package sigep.service.Ivent;


import sigep.beans.Ivent.NotificacionBean;
import sigep.data.dao.Ivent.NotificacionesDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.Notificacion;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class NotificacionService extends BaseService<Notificacion, NotificacionBean> {
    @Inject
    private NotificacionesDao dao;


    public static final String PREFIJO_ARCHIVO = "artista";


    @Override
    public NotificacionBean findById(Long idEntity) {
        return null;
    }

    @Override
    public NotificacionBean create(NotificacionBean bean) throws SIGEPException {
        return null;
    }

    @Override
    public void update(NotificacionBean bean) throws SIGEPException {
        dao.update(new Notificacion(bean));
    }

    @Override
    public void bussinessValidation(Notificacion entity) throws SIGEPValidationException {

    }
}
