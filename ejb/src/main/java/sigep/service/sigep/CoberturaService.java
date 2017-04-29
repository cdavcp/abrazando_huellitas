package sigep.service.sigep;

import sigep.beans.sigep.CoberturaBean;
import sigep.data.dao.sigep.CoberturaDao;
import sigep.data.dao.sigep.ParametroImportacionDao;
//import sigep.data.dto.sigep.CoberturaDTO;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.Cobertura;
import sigep.model.sigep.Riesgo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class CoberturaService extends BaseService<Cobertura, CoberturaBean>{

    @Inject
    CoberturaDao coberturaDao;
    @Inject
    ParametroImportacionDao parametroImportacionDao;
    @Inject
    TipoCoberturaService tipoCoberturaService;
    @Inject
    RubroService rubroService;
    @Inject
    AseguradoraService aseguradoraService;
    @Inject
    RiesgoService riesgoService;

    @Override
    public CoberturaBean findById(Long idEntity) {
        return coberturaDao.findById(idEntity).getBean();
    }

    public CoberturaBean findCompletoById(Long idEntity) {
        return coberturaDao.findById(idEntity).getBeanCompleto();
    }

    @Override
    public CoberturaBean create(CoberturaBean bean) throws SIGEPException {

//        parametroImportacionDao.create(new ParametroImportacion(bean);
        return coberturaDao.create(new Cobertura(bean)).getBean();
    }

    @Override
    public void update(CoberturaBean bean)throws SIGEPException {
//        parametroImportacionDao.update(entity.getParametroImportacion());
        coberturaDao.update(new Cobertura(bean));
    }

    @Override
    public void bussinessValidation(Cobertura entity) throws SIGEPValidationException {

    }

    public List<CoberturaBean> findAllPlano() {
        List<Cobertura> entities = coberturaDao.findAll();
        List<CoberturaBean> beans = new ArrayList<CoberturaBean>(entities.size());
        for (Cobertura entity : entities){
            beans.add(entity.getBean());
        }
        return beans;
    }

//    public CoberturaDTO inicializar(){
//        CoberturaDTO dto = new CoberturaDTO();
//        dto.setListaTiposCobertura(tipoCoberturaService.findAll());
//        dto.setListaRubros(rubroService.findAll());
//        dto.setListaAseguradoras(aseguradoraService.findAll());
//        return dto;
//    }
//
//    public CoberturaDTO inicializarEdit(Long coberturaId){
//        CoberturaDTO dto = inicializar();
//        dto.setListaTiposCobertura(tipoCoberturaService.findAll());
//        dto.setListaRubros(rubroService.findAll());
//        dto.setListaAseguradoras(aseguradoraService.findAll());
//        dto.setCobertura(findCompletoById(coberturaId));
//        dto.setListaRiesgos(riesgoService.findPotencialesRiesgos(dto.getCobertura().getRiesgos(), dto.getCobertura().getRubro().getId()));
//        return dto;
//    }

    public void deleteRiesgo(Long riesgoId){
        List<Cobertura> coberturas = coberturaDao.findAll();
        Riesgo r = new Riesgo(riesgoId);
        List<Riesgo> riesgosAux = new ArrayList<Riesgo>();
        for(Cobertura c : coberturas){
            riesgosAux = c.getRiesgos();
            if(riesgosAux.contains(r)){
                riesgosAux.remove(r);
                coberturaDao.update(c);
            }
        }
    }

    public List<CoberturaBean> findAllByRubro(Long rubroId){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("rubro_id", rubroId);
        List<Cobertura> entities = coberturaDao.findAll(filter);
        List<CoberturaBean> beans = new ArrayList<CoberturaBean>(entities.size());
        for(Cobertura c : entities){
            beans.add(c.getBean());
        }
        return beans;
    }

    public List<CoberturaBean> findByAseguradoraRubro(Long aseguradoraId, Long rubroId){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("aseguradora_id", aseguradoraId);
        filter.put("rubro_id", rubroId);
        List<Cobertura> entities = coberturaDao.findAll(filter);
        List<CoberturaBean> beans = new ArrayList<CoberturaBean>(entities.size());
        for(Cobertura c : entities){
            beans.add(c.getBean());
        }
        return beans;
    }

    public List<CoberturaBean> findCompletoByRubroYTipoCobertura(Long rubroId, Long tipoCoberturaId){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("tipoCobertura_id", tipoCoberturaId);
        filter.put("rubro_id", rubroId);
        List<Cobertura> entities = coberturaDao.findAll(filter);
        List<CoberturaBean> beans = new ArrayList<CoberturaBean>(entities.size());
        for(Cobertura c : entities){
            beans.add(c.getBeanCompleto());
        }
        return beans;
    }

    public List<CoberturaBean> findCompletoByRubroYTipoCoberturaConRiesgos(Long rubroId, Long tipoCoberturaId){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("tipoCobertura_id", tipoCoberturaId);
        filter.put("rubro_id", rubroId);
        List<Cobertura> entities = coberturaDao.findAll(filter);
        List<CoberturaBean> beans = new ArrayList<CoberturaBean>(entities.size());
        for(Cobertura c : entities){
            if(c.getRiesgos() != null && !c.getRiesgos().isEmpty())
                beans.add(c.getBeanCompleto());
        }
        return beans;
    }

    public List<CoberturaBean> findAllIncluyenRiesgo(Long riesgoId){
        Riesgo r = new Riesgo(riesgoId);
        List<Cobertura> coberturas = coberturaDao.findAll();
        List<CoberturaBean> result = new ArrayList<CoberturaBean>();
        for(Cobertura c : coberturas){
            if(c.getRiesgos().contains(r)){
                result.add(c.getBean());
            }
        }
        return result;
    }

    public void addRiesgoACoberturas(List<CoberturaBean> coberturas, Riesgo riesgo){
        for(CoberturaBean c : coberturas){
            Cobertura entity = coberturaDao.findById(c.getId());
            entity.getRiesgos().add(riesgo);
            coberturaDao.update(entity);
        }
    }

    public boolean esNombreValido(CoberturaBean bean){
        return (coberturaDao.findByNombreYAseguradora(bean.getNombre(), bean.getAseguradora().getId()) == null);
    }

    public boolean esNombreValidoEdit(CoberturaBean bean){
        Cobertura entity = coberturaDao.findByNombreYAseguradora(bean.getNombre(), bean.getAseguradora().getId());
        return (entity == null || entity.getId().equals(bean.getId()));
    }


}
