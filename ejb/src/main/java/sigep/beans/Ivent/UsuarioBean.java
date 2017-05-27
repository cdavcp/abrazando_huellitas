package sigep.beans.Ivent;

import java.util.List;

public class UsuarioBean {

    private Long id;
    private String nombre;
    private String pass;
    private TipoUsuarioBean tipoUsuario;
    private String sessionId;
    private String foto;
    private List<NotificacionBean> notificaciones;

    public List<NotificacionBean> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<NotificacionBean> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public TipoUsuarioBean getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioBean tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
