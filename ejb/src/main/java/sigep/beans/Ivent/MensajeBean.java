package sigep.beans.Ivent;

import java.sql.Time;
import java.util.Date;

public class MensajeBean {

    private Long id;
    private ConversacionBean conversacion;
    private UsuarioBean usuario;
    private EstadoMensajeBean estadoMensaje;
    private Time horaEmision;
    private Date fechaEmision;
    private String mensaje;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConversacionBean getConversacion() {
        return conversacion;
    }

    public void setConversacion(ConversacionBean conversacion) {
        this.conversacion = conversacion;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public EstadoMensajeBean getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(EstadoMensajeBean estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

    public Time getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(Time horaEmision) {
        this.horaEmision = horaEmision;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
