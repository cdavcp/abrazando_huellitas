package sigep.service.sigep;


import sigep.beans.sigep.ProductorBean;
import sigep.beans.sigep.SolicitudBeanOld;
import sigep.data.dao.sigep.SolicitudDaoOld;
import sigep.model.sigep.EstadoSolicitud;
import sigep.model.sigep.Poliza;
import sigep.model.sigep.Productor;
import sigep.model.sigep.SolicitudOld;
import sigep.util.DateUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class SolicitudServiceOld {

    @Inject
    private SolicitudDaoOld dao;

    @Inject
    private ProductorService productorService;
    @Inject
    private PolizaService polizaService;

    public void create(SolicitudBeanOld bean){
        SolicitudOld entity = new SolicitudOld(bean);
        entity.setEstado(new EstadoSolicitud(EstadoSolicitud.ID_ESTADO_REGISTRADA));
        entity.setFecha(DateUtil.currentDate());
        dao.create(entity);
    }

    public List<SolicitudBeanOld> findAllRegistradas(){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("estado_id", EstadoSolicitud.ID_ESTADO_REGISTRADA);
        List<SolicitudOld> entities = dao.findAll(filter);
        List<SolicitudBeanOld> beans = new ArrayList<SolicitudBeanOld>(entities.size());
        for(SolicitudOld s : entities){
            beans.add(s.getBean());
        }
        return beans;
    }

    public List<SolicitudBeanOld> findAllByUsuarioId(Long usuarioId){

        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("productor_id", productorService.findByUsuario(usuarioId).getId());
        List<SolicitudOld> entities = dao.findAll(filter);
        List<SolicitudBeanOld> beans = new ArrayList<SolicitudBeanOld>(entities.size());
        for(SolicitudOld s : entities){
            beans.add(s.getBean());
        }
        return beans;
    }

    public void asignar(List<ProductorBean> productores){
        List<SolicitudBeanOld> solicitudesAux;
        SolicitudOld entityAux;
        for(ProductorBean productor: productores){
            solicitudesAux = productor.getSolicitudes();
            if(solicitudesAux != null && !solicitudesAux.isEmpty()){
                for(SolicitudBeanOld solicitudBean: solicitudesAux){
                    entityAux = new SolicitudOld(solicitudBean);
                    entityAux.setProductor(new Productor(productor));
                    entityAux.setEstado(new EstadoSolicitud(EstadoSolicitud.ID_ESTADO_ASIGNADA));
                    dao.update(entityAux);
                }
            }
        }
    }

    public void descartarSolicitud(Long idSolicitud){
        SolicitudOld solicitud = dao.findById(idSolicitud);
        solicitud.setEstado(new EstadoSolicitud(EstadoSolicitud.ID_ESTADO_NO_VENDIDA));
        dao.update(solicitud);
    }

    public void update(SolicitudBeanOld bean){
        SolicitudOld entity = new SolicitudOld(bean);
        dao.update(entity);
    }

    public void addPoliza(SolicitudBeanOld solicitud, String numeroPoliza, Long aseguradoraId){
        Poliza p = polizaService.findBOByNumeroYAseguradora(numeroPoliza, aseguradoraId);
        SolicitudOld s = new SolicitudOld(solicitud);
        s.setPoliza(p);
        s.setEstado(new EstadoSolicitud(EstadoSolicitud.ID_ESTADO_VENDIDA));
        dao.update(s);
    }

}
