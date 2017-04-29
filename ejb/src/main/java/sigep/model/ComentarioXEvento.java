package sigep.model;

import sigep.beans.ComentarioXEventoBean;
import sigep.beans.EventoBean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;

@Entity
public class ComentarioXEvento extends EntityBase {


    @OneToOne
    private Evento evento;

    @OneToOne
    private Usuario usuario;

//    @Size(max = 34)
//    @NotNull
//    private Time horaComentario;

    @NotNull
    private Date fechaComentario;

    @Size(max = 100)
    @NotNull
    private String comentario;


    public ComentarioXEvento() {
    }

    public ComentarioXEvento(ComentarioXEventoBean c) {
        setId(c.getId());
        this.evento = (c.getEvento() == null) ? null : new Evento(c.getEvento());
        this.usuario = (c.getUsuario() == null) ? null : new Usuario(c.getUsuario());
//        this.horaComentario = c.getHoraComentario();
        this.fechaComentario = c.getFechaComentario();
        this.comentario = c.getComentario();
    }

    public ComentarioXEventoBean getBeanCompleto() {
        ComentarioXEventoBean c = new ComentarioXEventoBean();
        c.setId(getId());
        c.setEvento((getEvento() == null) ? null : getEvento().getBean());
        c.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        c.setFechaComentario(getFechaComentario());
//        c.setHoraComentario(getHoraComentario());
        c.setComentario(getComentario());



        return c;
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

//    public Time getHoraComentario() {
//        return horaComentario;
//    }

//    public void setHoraComentario(Time horaComentario) {
//        this.horaComentario = horaComentario;
//    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}