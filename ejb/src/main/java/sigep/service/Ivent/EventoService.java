package sigep.service.Ivent;

import sigep.beans.Ivent.*;
import sigep.data.dao.Ivent.*;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.*;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EventoService extends BaseService<Evento, EventoBean> {
    @Inject
    EventoDao dao;

    @Inject
    NotificacionesDao notificacionDao;

    @Inject
    SeguidoresXArtistaDao seguidoresArtistaDao;

    @Inject
    SeguidoresXLugarDao seguidoresLugarDao;

    @Inject
    UsuarioDao usuarioDao;

    @Inject
    MeGustaXEventoDao daoMG;

    @Override
    public EventoBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public EventoBean create(EventoBean bean) throws SIGEPException {
        return dao.create(new Evento(bean)).getBean();
    }

    @Override
    public void update(EventoBean bean) throws SIGEPException {
        dao.update(new Evento(bean));

        if (bean.getAceptadoPorArtista() && bean.getAceptadoPorLugar()) {
            List<Usuario> seguidoresArtista = seguidoresArtistaDao.findByArtista(bean.getArtista().getId());
            List<Usuario> seguidoresLugar = seguidoresLugarDao.findByLugar(bean.getLugar().getId());

            NotificacionBean notificacionBean = new NotificacionBean();
            notificacionBean.setEvento(bean);
            notificacionBean.setLeida(false);
            notificacionBean.setMensaje("Nuevo evento de " + bean.getArtista().getNombre() + " en " + bean.getLugar().getNombre());

            crearNotificacionesParaUsuarios(seguidoresArtista, notificacionBean);
            crearNotificacionesParaUsuarios(seguidoresLugar, notificacionBean);
        }
    }

    private void crearNotificacionesParaUsuarios(List<Usuario> seguidoresArtista, NotificacionBean notificacionBean) {
        if (seguidoresArtista != null) {
            for (Usuario usuario : seguidoresArtista) {
                Notificacion notificacion = new Notificacion(notificacionBean);
                notificacion.setUsuario(usuario);
                notificacionDao.create(notificacion);
            }
        }
    }

    public List<EventoBean> findEventosByUsuario(UsuarioBean usuario) {
        List<EventoBean> beans = new ArrayList<EventoBean>();
        List<Evento> models;
        if (usuario.getTipoUsuario().getId().equals(TipoUsuario.ID_MANAGER)) {
            models = dao.findByManager(usuario.getId());
        } else {
            models = dao.findByAnfitrion(usuario.getId());
        }

        for (Evento evento : models) {
            beans.add(evento.getBean());
        }
        return beans;
    }


    public MeGustaXEventoBean createMG(MeGustaXEventoBean bean) throws SIGEPException {


        if (bean.getId() != null) {
            daoMG.delete(bean.getId());
            Long cantMg = (Long.parseLong(bean.getEvento().getAsunto()));
            cantMg = cantMg - 1;
            bean.getEvento().setAsunto(cantMg.toString());
            dao.update(new Evento(bean.getEvento()));
            return null;
        } else {
            if (bean.getEvento().getAsunto() == null) {
                bean.getEvento().setAsunto("1");
            } else {
                Long cantMg = (Long.parseLong(bean.getEvento().getAsunto()));
                cantMg = cantMg + 1;
                bean.getEvento().setAsunto(cantMg.toString());
            }
            dao.update(new Evento(bean.getEvento()));

            return daoMG.create(new MeGustaXEvento(bean)).getBeanCompleto();


        }

    }

    public MeGustaXEventoBean findByUsuarioMG(MeGustaXEventoBean bean) throws SIGEPException {
        return daoMG.findMG(bean.getEvento().getId(), bean.getUsuario().getId()).getBeanCompleto();
    }

    public List<EventoBean> eventosProximos() {
        List<EventoBean> beans = new ArrayList<EventoBean>();
        List<Evento> eventos;
        eventos = dao.busquedaEventosProximos();
        if (eventos != null) {
            for (Evento evento : eventos) {
                beans.add(evento.getBean());
            }
        }
        return beans;
    }

    public List<EventoBean> eventosMg() {
        List<EventoBean> beans = new ArrayList<EventoBean>();
        List<Evento> eventos;
        eventos = dao.eventoMg();
        if (eventos != null) {
            for (Evento evento : eventos) {
                beans.add(evento.getBean());
            }
        }
        return beans;
    }


    public List<EventoBean> findEventosByArtista(ArtistaBean artista) {
        List<EventoBean> beans = new ArrayList<EventoBean>();
        List<Evento> eventos;
        eventos = dao.findByArtista(artista.getId());

        if (eventos != null) {
            for (Evento evento : eventos) {
                beans.add(evento.getBean());
            }
        }
        return beans;
    }

    public List<EventoBean> findEventosByLugar(LugarBean lugar) {
        List<EventoBean> beans = new ArrayList<EventoBean>();
        List<Evento> models;
        models = dao.findByLugar(lugar.getId());

        for (Evento evento : models) {
            beans.add(evento.getBean());
        }
        return beans;
    }

    public List<EventoBean> findTodosFiltros(EventoBean query) {
        List<EventoBean> beans = new ArrayList<EventoBean>();
        List<Evento> models;
        models = dao.busquedaEvento(query);

        if(models != null){
        for (Evento evento : models) {
            beans.add(evento.getBean());
        }}
        return beans;
    }


    @Override
    public void bussinessValidation(Evento entity) throws SIGEPValidationException {

    }
}

