package sigep.service.sigep;

import sigep.beans.sigep.VendedorBean;
import sigep.data.dao.sigep.VendedorDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.Vendedor;
import sigep.util.DateUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VendedorService extends BaseService<Vendedor, VendedorBean>{
    @Inject
    VendedorDao vendedorDao;

    @Override
    public VendedorBean findById(Long idEntity) {
        return vendedorDao.findById(idEntity).getBean();
    }

    @Override
    public VendedorBean create(VendedorBean bean) throws SIGEPException {
        bean.setFechaAlta(DateUtil.currentDate());
        return vendedorDao.create(new Vendedor(bean)).getBean();
    }

    @Override
    public void update(VendedorBean bean)throws SIGEPException {
        vendedorDao.update(new Vendedor(bean));
    }

    @Override
    public void bussinessValidation(Vendedor entity) throws SIGEPValidationException {

    }

    public void delete(VendedorBean bean)throws SIGEPException {
        bean.setFechaBaja(DateUtil.currentDate());
        vendedorDao.update(new Vendedor(bean));
    }

    public List<VendedorBean> findAll() {
        List<Vendedor> vendedorList = vendedorDao.findAllActives();
        List<VendedorBean> beanList = new ArrayList<VendedorBean>();
        for(Vendedor vendedor : vendedorList)
        {
            beanList.add(vendedor.getBean());
        }
        return beanList;
    }

    public List<VendedorBean> findPotencialesVendedores(List<VendedorBean> vendedoresDeProductor) {
        List<Vendedor> vendedoresTodosEntityList = vendedorDao.findAllActives();
        List<VendedorBean> vendedoresTodos = new ArrayList<VendedorBean>();
        List<VendedorBean> vendedoresResult = new ArrayList<VendedorBean>();
        for(Vendedor vendedor : vendedoresTodosEntityList)
        {
            vendedoresTodos.add(vendedor.getBean());
        }

        for(VendedorBean v : vendedoresTodos){
            if(!vendedoresDeProductor.contains(v))
                vendedoresResult.add(v);
        }
        return vendedoresResult;
    }

    public List<VendedorBean> findVendedoresDeProductor(Long productorId){
        List<Vendedor> vendedoresBus = vendedorDao.findVendedoresDeProductor(productorId);
        List<VendedorBean> vendedoresBean = new ArrayList<VendedorBean>(vendedoresBus.size());
        for (Vendedor v : vendedoresBus){
            vendedoresBean.add(v.getBean());
        }
        return vendedoresBean;
    }
}
