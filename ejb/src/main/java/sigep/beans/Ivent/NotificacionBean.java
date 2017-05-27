package sigep.beans.Ivent;

public class NotificacionBean {

    private Long id;
    private EventoBean evento;
    private String mensaje;
    private Boolean leida;
    private UsuarioBean usuario;

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

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }
}
