package sigep.service.sigep;

import sigep.beans.Ivent.TipoDocumentoBean;
import sigep.data.dao.sigep.TipoDocumentoDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.TipoDocumento;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TipoDocumentoService extends BaseService<TipoDocumento, TipoDocumentoBean>{

    @Inject
    private TipoDocumentoDao dao;

    @Override
    public TipoDocumentoBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public TipoDocumentoBean create(TipoDocumentoBean bean) throws SIGEPException {
        return dao.create(new TipoDocumento(bean)).getBean();
    }

    @Override
    public void update(TipoDocumentoBean bean) throws SIGEPException {
        dao.update(new TipoDocumento(bean));
    }

    @Override
    public void bussinessValidation(TipoDocumento entity) throws SIGEPValidationException {

    }

    public List<TipoDocumentoBean> findAll(){
        List<TipoDocumento> tipoDocumentoList = dao.findAll();
        List<TipoDocumentoBean> beanList = new ArrayList<TipoDocumentoBean>();
        for(TipoDocumento tipoDocumento : tipoDocumentoList)
        {
            beanList.add(tipoDocumento.getBean());
        }
        return beanList;
    }
}
