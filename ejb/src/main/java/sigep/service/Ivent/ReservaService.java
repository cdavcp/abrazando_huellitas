package sigep.service.Ivent;

import sigep.beans.Ivent.EventoBean;
import sigep.beans.Ivent.ReservaBean;
import sigep.beans.Ivent.UsuarioBean;
import sigep.data.dao.Ivent.EventoDao;
import sigep.data.dao.Ivent.ReservaDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.Evento;
import sigep.model.Ivent.Reserva;
import sigep.service.sigep.BaseService;
import sigep.util.DateUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReservaService extends BaseService<Reserva, ReservaBean> {
    @Inject
    ReservaDao dao;

    @Inject
    EventoDao daoEvento;




    @Override
    public ReservaBean findById(Long idEntity) {
        return dao.findById(idEntity).getBeanCompleto();
    }

    @Override
    public ReservaBean create(ReservaBean bean) throws SIGEPException {


        bean.setFechaReserva(DateUtil.currentDate());
        ReservaBean reserva = dao.create(new Reserva(bean)).getBeanCompleto();
        daoEvento.update(new Evento(bean.getEvento()));

        return reserva;
    }

    @Override
    public void update(ReservaBean bean) throws SIGEPException {
        dao.update(new Reserva(bean));
    }

    @Override
    public void bussinessValidation(Reserva entity) throws SIGEPValidationException {

    }


    public List<ReservaBean> findByUsuario(UsuarioBean usuario){

        List<Reserva> reservas = dao.findByUsuario(usuario.getId());

        List<ReservaBean> beanList = new ArrayList<ReservaBean>();
        for(Reserva l : reservas)
        {
            beanList.add(l.getBeanCompleto());
        }

        return beanList;
    }

    public List<ReservaBean> findByEventos(EventoBean evento){

        List<Reserva> reservas = dao.findByEvento(evento.getId());

        List<ReservaBean> beanList = new ArrayList<ReservaBean>();
        for(Reserva l : reservas)
        {
            beanList.add(l.getBeanCompleto());
        }

        return beanList;
    }
}
