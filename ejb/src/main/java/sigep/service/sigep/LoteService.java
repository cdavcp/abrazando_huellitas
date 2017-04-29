package sigep.service.sigep;

import sigep.beans.sigep.LoteBean;
import sigep.data.dao.sigep.LoteDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.Lote;
import sigep.util.JndiProvider;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class LoteService extends BaseService<Lote, LoteBean>{
    @Inject
    private LoteDao dao;
    @Inject
    private ComisionIncompletaService comisionIncompletaService;

    @Override
    public LoteBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    public LoteBean findByIdConIncompletas(Long idEntity) {
        return dao.findById(idEntity).getBeanConComisionesIncompletas();
    }

    public LoteBean findCompletoById(Long idEntity) {
        return dao.findById(idEntity).getBeanConComisionesTodas();
    }

    @Override
    public LoteBean create(LoteBean bean) throws SIGEPException {
        return dao.create(new Lote(bean)).getBean();
    }

    @Override
    public void update(LoteBean bean) throws SIGEPException {
        dao.update(new Lote(bean));
    }

    public void updateBO(Lote entity) throws SIGEPException {
        dao.update(entity);
    }

    public Lote createBO(Lote entity) throws SIGEPException {
        return dao.create(entity);
    }

    public List<LoteBean> findByProductor(Long productorId){
        List<Lote> lotes = dao.findByProductor(productorId);
        List<LoteBean> beanList = new ArrayList<LoteBean>();
        for(Lote lote : lotes)
        {
            beanList.add(lote.getBean());
        }
        return beanList;
    }

    public void delete(Long loteId){
        comisionIncompletaService.deleteAllByLote(loteId);
        getComisionService().deleteAllByLote(loteId);
        dao.delete(loteId);
    }

    public Lote findByMesYAseguradora(int mes, int anio, Long aseguradoraId, Long productorId){
        return dao.findByMesYAseguradora(mes, anio, aseguradoraId, productorId);
    }

    @Override
    public void bussinessValidation(Lote entity) throws SIGEPValidationException {

    }

    public ComisionService getComisionService(){
        try {
            return JndiProvider.getService(ComisionService.class);
        } catch (NamingException e) {
            Logger.getLogger(LoteService.class.getName()).log(Level.INFO, null, e);
            return null;
        }
    }
}
