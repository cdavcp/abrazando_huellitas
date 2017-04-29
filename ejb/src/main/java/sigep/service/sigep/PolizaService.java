package sigep.service.sigep;

import sigep.beans.sigep.PolizaBean;
import sigep.data.dao.sigep.PolizaDao;
import sigep.data.dto.ComboDTO;
import sigep.data.dto.poliza.PolizaFilterDTO;
import sigep.data.dto.poliza.PolizaGrillaDTO;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.Poliza;
import sigep.util.DateUtil;
import sigep.util.JndiProvider;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class PolizaService extends BaseService<Poliza, PolizaBean>{
    public static final Integer ID_ESTADO_NUEVA = 1;
    public static final Integer ID_ESTADO_AL_DIA = 2;
    public static final Integer ID_ESTADO_ATRASADA = 3;
    public static final Integer ID_ESTADO_MOROSA = 4;
    public static final Integer ID_ESTADO_DADA_BAJA = 5;

    @Inject
    private PolizaDao dao;

    @Override
    public PolizaBean findById(Long idEntity) {
        Poliza poliza = dao.findById(idEntity);
        PolizaBean bean = poliza.getBean();
        bean.setEstadoId(calcularEstadoPoliza(poliza));
        return bean;
    }

    @Override
    public PolizaBean create(PolizaBean bean) throws SIGEPException {
        bean.setFechaAlta(DateUtil.currentDate());
        return dao.create(new Poliza(bean)).getBean();
    }

    @Override
    public void update(PolizaBean bean) throws SIGEPException {
        dao.update(new Poliza(bean));
    }

    public void delete(Long idPoliza) throws SIGEPException {
        Poliza entity = dao.findById(idPoliza);
        entity.setFechaBaja(DateUtil.currentDate());
        dao.deleteLogical(entity);
    }

    @Override
    public void bussinessValidation(Poliza entity) throws SIGEPValidationException {

    }

    public boolean validarNumeroPoliza(String numero, Long idAseguradora){
        return (dao.findByNumeroYAseguradora(numero, idAseguradora) == null);
    }

    public List<PolizaGrillaDTO> findByFilters(PolizaFilterDTO filtros){
        List<Poliza> entities = dao.findByFilters(filtros.getProductorId(), filtros.getAseguradoraId(), filtros.getRubroId(), filtros.getNumero(), filtros.getClienteId(), filtros.getVendedorId(), filtros.getOrigenId());
        List<PolizaGrillaDTO> beans = new ArrayList<PolizaGrillaDTO>(entities.size());
        for(Poliza entity : entities)
        {
            PolizaGrillaDTO bean = new PolizaGrillaDTO(entity);
            bean.setEstadoId(calcularEstadoPoliza(entity));
            if(filtros.getEstados() != null && filtros.getEstados().contains(bean.getEstadoId()))
                beans.add(bean);
        }
        return beans;
    }

    private Integer calcularEstadoPoliza(Poliza poliza){
        Date fechaPoliza = poliza.getFecha();
        Date fechaActual = DateUtil.currentDate();
        Date fechaUltimaComision = poliza.getFechaUltimaComision();
        Integer estado = null;

        if(poliza.getFechaBaja() != null)
            return ID_ESTADO_DADA_BAJA;

        if(fechaUltimaComision == null){
            Integer diff = DateUtil.daysBetween(fechaPoliza, fechaActual);
            if(diff <= 30)
                estado = ID_ESTADO_NUEVA;
            if(diff > 30 && diff <= 60)
                estado = ID_ESTADO_ATRASADA;
            if(diff > 60)
                estado = ID_ESTADO_MOROSA;
        } else {
            Integer diff = DateUtil.daysBetween(fechaUltimaComision, fechaActual);
            if(diff <= 30)
                estado = ID_ESTADO_AL_DIA;
            if(diff > 30 && diff <= 60)
                estado = ID_ESTADO_ATRASADA;
            if(diff > 60)
                estado = ID_ESTADO_MOROSA;
        }
        return estado;
    }

    public Poliza findBOByNumeroYAseguradora(String nroPoliza, Long idAseguradora){
        return dao.findByNumeroYAseguradora(nroPoliza, idAseguradora);
    }
    
    public PolizaBean findByNumeroYAseguradora(String numero, Long idAseguradora){
        Poliza p = findBOByNumeroYAseguradora(numero, idAseguradora);
        return p != null ? p.getBean() : null;
    }

    public List<ComboDTO> findByProductorYAseguradora(Long productorId, Long aseguradoraId){
        List<Poliza> entities = dao.findByFilters(productorId, aseguradoraId, null, null, null, null, null);
        List<ComboDTO> beans = new ArrayList<ComboDTO>(entities.size());
        for(Poliza entity : entities)
        {
            ComboDTO bean = new ComboDTO();
            bean.setId(entity.getId());
            bean.setNumero(entity.getNumero());
            beans.add(bean);
        }
        return beans;
    }
    
    private ComisionService getComisionService(){
        try {
            return JndiProvider.getService(ComisionService.class);
        } catch (NamingException e) {
            Logger.getLogger(PolizaService.class.getName()).log(Level.INFO, null, e);
            e.printStackTrace();
            return null;
        }
    }
}
