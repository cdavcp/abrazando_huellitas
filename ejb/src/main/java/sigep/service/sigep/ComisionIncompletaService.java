package sigep.service.sigep;

import sigep.beans.sigep.ComisionIncompletaBean;
import sigep.data.dao.sigep.ComisionIncompletaDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.ComisionIncompleta;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ComisionIncompletaService extends BaseService<ComisionIncompleta, ComisionIncompletaBean>{
    @Inject
    private ComisionIncompletaDao dao;

    @Override
    public ComisionIncompletaBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public ComisionIncompletaBean create(ComisionIncompletaBean bean) throws SIGEPException {
        return dao.create(new ComisionIncompleta(bean)).getBean();
    }

    @Override
    public void update(ComisionIncompletaBean bean) throws SIGEPException {
        dao.update(new ComisionIncompleta(bean));
    }

    @Override
    public void bussinessValidation(ComisionIncompleta entity) throws SIGEPValidationException {

    }

    public void deleteAllByLote(Long loteId){
        dao.deleteByLote(loteId);
    }

    public void deleteActualizadas(List<Long> listIds){
        for(Long id : listIds){
            dao.delete(id);
        }
    }
}
