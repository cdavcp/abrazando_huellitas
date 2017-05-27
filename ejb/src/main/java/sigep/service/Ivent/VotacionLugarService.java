package sigep.service.Ivent;

import sigep.beans.Ivent.LugarBean;
import sigep.beans.Ivent.VotacionLugarBean;
import sigep.data.dao.Ivent.LugarDao;
import sigep.data.dao.Ivent.VotacionLugarDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.Lugar;
import sigep.model.Ivent.VotacionLugar;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class VotacionLugarService extends BaseService<VotacionLugar, VotacionLugarBean> {

    @Inject
    VotacionLugarDao dao;

    @Inject
    LugarDao daoLugar;

    @Override
    public VotacionLugarBean findById(Long idEntity) {
        return dao.findById(idEntity).getBeanCompleto();
    }

    @Override
    public VotacionLugarBean create(VotacionLugarBean bean) throws SIGEPException {
        VotacionLugarBean votacion = dao.create(new VotacionLugar(bean)).getBeanCompleto();
        LugarBean lugar = bean.getLugar();
        lugar.setPuntuacion(dao.findPromedioLugar(bean.getLugar().getId()));
        daoLugar.update(new Lugar(lugar));
        return votacion;
    }

    @Override
    public void update(VotacionLugarBean bean) throws SIGEPException {
         dao.update(new VotacionLugar(bean)).getBeanCompleto();
        LugarBean lugar = bean.getLugar();
        lugar.setPuntuacion(dao.findPromedioLugar(bean.getLugar().getId()));
        daoLugar.update(new Lugar(lugar));
    }

    public VotacionLugarBean findBySeguidor(VotacionLugarBean votacion){
        return dao.finBySeguidor(votacion.getLugar().getId(), votacion.getUsuario().getId() ).getBeanCompleto();
    }

    @Override
    public void bussinessValidation(VotacionLugar entity) throws SIGEPValidationException {

    }
}
