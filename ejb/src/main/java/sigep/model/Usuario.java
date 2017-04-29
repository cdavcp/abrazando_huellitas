package sigep.model;

import sigep.beans.NotificacionBean;
import sigep.beans.UsuarioBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String nombre;

    @Size(max = 20, min = 3)
    @NotNull
    private String pass;

    @OneToOne
    private TipoUsuario tipoUsuario;


    @OneToMany(mappedBy = "usuario")
    private List<Notificacion> notificaciones;

    private String sessionId;

    public Usuario() {
    }

    public Usuario(UsuarioBean b) {
        setId(b.getId());
        this.nombre = b.getNombre();
        this.pass = b.getPass();
        this.sessionId = b.getSessionId();
        this.tipoUsuario = (b.getTipoUsuario() == null)? null: new TipoUsuario(b.getTipoUsuario());

        if(b.getNotificaciones() != null && !b.getNotificaciones().isEmpty()){
            ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>(b.getNotificaciones().size());
            for(NotificacionBean notificacionBean : b.getNotificaciones()){
                notificaciones.add(new Notificacion(notificacionBean));
            }
//            setNotificaciones(notificaciones);
        }
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public UsuarioBean getBean(){
        UsuarioBean b = new UsuarioBean();
        b.setId(getId());
        b.setNombre(getNombre());
        b.setPass(getPass());
        b.setSessionId(getSessionId());
        b.setTipoUsuario((getTipoUsuario() == null) ? null : getTipoUsuario().getBean());

        if(getNotificaciones() != null && !getNotificaciones().isEmpty()){
            List<Notificacion> notificacionesEntities = getNotificaciones();
            List<NotificacionBean> notificacionBean = new ArrayList<NotificacionBean>(notificacionesEntities.size());
            for(Notificacion notificacion : notificacionesEntities){
                notificacionBean.add(notificacion.getBeanCompleto());
            }
            b.setNotificaciones(notificacionBean);
        }
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (!nombre.equals(usuario.nombre)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
