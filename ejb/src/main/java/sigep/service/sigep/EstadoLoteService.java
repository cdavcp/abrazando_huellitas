package sigep.service.sigep;

import sigep.beans.sigep.EstadoLoteBean;
import sigep.data.dao.sigep.EstadoLoteDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.EstadoLote;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EstadoLoteService extends BaseService<EstadoLote, EstadoLoteBean>{
    @Inject
    private EstadoLoteDao dao;

    @Override
    public EstadoLoteBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public EstadoLoteBean create(EstadoLoteBean bean) throws SIGEPException {
        return dao.create(new EstadoLote(bean)).getBean();
    }

    @Override
    public void update(EstadoLoteBean bean) throws SIGEPException {
        dao.update(new EstadoLote(bean));
    }

    @Override
    public void bussinessValidation(EstadoLote entity) throws SIGEPValidationException {

    }
}
