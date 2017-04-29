package sigep.service.sigep;

import sigep.beans.sigep.OrigenPolizaBean;
import sigep.data.dao.sigep.OrigenPolizaDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.OrigenPoliza;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrigenPolizaService extends BaseService<OrigenPoliza, OrigenPolizaBean>{
    @Inject
    private OrigenPolizaDao dao;

    @Override
    public OrigenPolizaBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public OrigenPolizaBean create(OrigenPolizaBean bean) throws SIGEPException {
        return dao.create(new OrigenPoliza(bean)).getBean();
    }

    @Override
    public void update(OrigenPolizaBean bean) throws SIGEPException {
        dao.update(new OrigenPoliza(bean));
    }

    @Override
    public void bussinessValidation(OrigenPoliza entity) throws SIGEPValidationException {

    }

    public List<OrigenPolizaBean> findAll(){
        List<OrigenPoliza> origenPolizaList = dao.findAll();
        List<OrigenPolizaBean> beanList = new ArrayList<OrigenPolizaBean>();
        for(OrigenPoliza origenPoliza : origenPolizaList)
        {
            beanList.add(origenPoliza.getBean());
        }
        return beanList;
    }
}
