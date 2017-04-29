package sigep.service.sigep;

import sigep.beans.sigep.TipoCoberturaBean;
import sigep.data.dao.sigep.TipoCoberturaDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.TipoCobertura;
import sigep.util.MockUtil.TipoCoberturaMock;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TipoCoberturaService extends BaseService<TipoCobertura, TipoCoberturaBean>{

    @Inject
    private TipoCoberturaDao dao;

    @Override
    public TipoCoberturaBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public TipoCoberturaBean create(TipoCoberturaBean bean) throws SIGEPException {
        return dao.create(new TipoCobertura(bean)).getBean();
    }

    @Override
    public void update(TipoCoberturaBean bean) throws SIGEPException {
        dao.update(new TipoCobertura(bean));
    }

    @Override
    public void bussinessValidation(TipoCobertura entity) throws SIGEPValidationException {

    }

    public List<TipoCoberturaBean> findAll(){
        List<TipoCobertura> TipoCoberturaList = dao.findAll();
        List<TipoCoberturaBean> beanList = new ArrayList<TipoCoberturaBean>();
        for(TipoCobertura TipoCobertura : TipoCoberturaList)
        {
            beanList.add(TipoCobertura.getBean());
        }
        return beanList;
    }

    public List<TipoCoberturaBean> findAllConLogo(){
        List<TipoCoberturaBean> beanList = findAll();
        for(TipoCoberturaBean tcb : beanList)
        {
            switch(tcb.getId().intValue()){
                case 1:
                    tcb.setLogo(TipoCoberturaMock.LOGO_BASICA);
                    break;
                case 2:
                    tcb.setLogo(TipoCoberturaMock.LOGO_COMPLETA);
                    break;
                case 3:
                    tcb.setLogo(TipoCoberturaMock.LOGO_PREMIUM);
                    break;
            }
        }
        return beanList;
    }


}
