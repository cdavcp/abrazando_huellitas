package sigep.beans.Ivent;

import java.util.Date;

public class ComentarioXEventoBean {

    private Long id;
    private EventoBean evento;
    private UsuarioBean usuario;
//    private Time horaComentario;
    private Date fechaComentario;
    private String comentario;

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventoBean getEvento() {
        return evento;
    }

    public void setEvento(EventoBean evento) {
        this.evento = evento;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

//    public Time getHoraComentario() {
//        return horaComentario;
//    }

//    public void setHoraComentario(Time horaComentario) {
//        this.horaComentario = horaComentario;
//    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
