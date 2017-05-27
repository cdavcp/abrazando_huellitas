package sigep.model.Ivent;

import sigep.beans.Ivent.NotificacionBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Notificacion extends EntityBase {


    @OneToOne
    private Evento evento;
    @Size(max = 300)
    private String mensaje;
    private Boolean leida;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;


    public Notificacion() {
    }

    public Notificacion(NotificacionBean n) {
        setId(n.getId());
        this.evento = (n.getEvento() == null) ? null : new Evento(n.getEvento());
        this.mensaje = n.getMensaje();
        this.leida = n.getLeida();
    }

    public NotificacionBean getBeanCompleto() {
        NotificacionBean n = new NotificacionBean();
        n.setId(getId());
        n.setEvento((getEvento() == null) ? null : getEvento().getBean());
        n.setMensaje(getMensaje());
        n.setLeida(getLeida());

        return n;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
