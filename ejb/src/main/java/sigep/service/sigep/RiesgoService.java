package sigep.service.sigep;

import sigep.beans.sigep.RiesgoBean;
import sigep.data.dao.sigep.RiesgoDao;
//import sigep.data.dto.sigep.RiesgoDTO;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.Riesgo;
import sigep.util.JndiProvider;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class RiesgoService extends BaseService<Riesgo, RiesgoBean>{
    @Inject
    private RiesgoDao dao;

    @Override
    public RiesgoBean findById(Long idEntity) {
        return null;
    }

    @Override
    public RiesgoBean create(RiesgoBean bean) {
        return dao.create(new Riesgo(bean)).getBean();
    }

    @Override
    public void update(RiesgoBean bean) throws SIGEPException {
        dao.update(new Riesgo(bean));
    }

    @Override
    public void bussinessValidation(Riesgo entity) throws SIGEPValidationException {

    }

    public List<RiesgoBean> findAll(){
        List<Riesgo> RiesgoList = dao.findAll();
        List<RiesgoBean> beanList = new ArrayList<RiesgoBean>();
        for(Riesgo Riesgo : RiesgoList)
        {
            beanList.add(Riesgo.getBean());
        }
        return beanList;
    }

    public List<RiesgoBean> findAllByRubro(Long rubroId){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("rubro_id", rubroId);
        List<Riesgo> entities = dao.findAll(filter);
        List<RiesgoBean> beans = new ArrayList<RiesgoBean>(entities.size());
        for(Riesgo e : entities){
            beans.add(e.getBean());
        }
        return beans;
    }

    public boolean esValidoByNombreYRubro(String nombre, Long rubroId){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("nombre", nombre);
        filter.put("rubro_id", rubroId);
        Riesgo entity = dao.findByNombreYRubro(nombre, rubroId);
        return (entity == null);
    }

    public List<RiesgoBean> findPotencialesRiesgos(List<RiesgoBean> beansActuales, Long rubroId){
        List<RiesgoBean> allRiesgosDelRubro = findAllByRubro(rubroId);
        List<RiesgoBean> potencialesRiesgos;
        if(beansActuales == null){
            potencialesRiesgos = allRiesgosDelRubro;
        }
        else{
            potencialesRiesgos = new ArrayList<RiesgoBean>();
            for(RiesgoBean r : allRiesgosDelRubro){
                if(!beansActuales.contains(r)){potencialesRiesgos.add(r);}
            }
        }
        return potencialesRiesgos;
    }

    public void delete(RiesgoBean bean) throws SIGEPException {
        getCoberturaService().deleteRiesgo(bean.getId());
        dao.delete(bean.getId());
    }

    public CoberturaService getCoberturaService(){
        try {
            return JndiProvider.getService(CoberturaService.class);
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public void create(RiesgoDTO dto){
//        Riesgo r = dao.create(new Riesgo(dto.getRiesgo()));
//        getCoberturaService().addRiesgoACoberturas(dto.getCoberturas(), r);
//    }
}
