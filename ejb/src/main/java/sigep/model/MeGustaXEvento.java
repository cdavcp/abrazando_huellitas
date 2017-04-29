package sigep.model;

import sigep.beans.MeGustaXEventoBean;
import sigep.beans.SolicitudBean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class MeGustaXEvento extends EntityBase {


    @OneToOne
    private Evento evento;

    @OneToOne
    private Usuario usuario;


    public MeGustaXEvento() {
    }

    public MeGustaXEvento(MeGustaXEventoBean mg) {
        setId(mg.getId());
        this.evento = (mg.getEvento() == null) ? null : new Evento(mg.getEvento());
        this.usuario = (mg.getUsuario() == null) ? null : new Usuario(mg.getUsuario());

            }


    public MeGustaXEventoBean getBeanCompleto() {
        MeGustaXEventoBean s = new MeGustaXEventoBean();
        s.setId(getId());
        s.setEvento((getEvento() == null) ? null : getEvento().getBean());
        s.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());

        return s;
    }


    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}