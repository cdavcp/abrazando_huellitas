package sigep.model;

import sigep.beans.DetalleSolicitudBean;
import sigep.beans.MensajeBean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;

@Entity
public class Mensaje extends EntityBase  {


    @OneToOne
    private Conversacion conversacion;

    @OneToOne
    private Usuario usuarioEmisor;

    @OneToOne
    private EstadoMensaje estadoMensaje;


    @Size(max = 34)
    @NotNull
    private Time horaEmision;

    @Size(max = 34)
    @NotNull
    private Date fechaEmision;


    @Size(max = 100)
    @NotNull
    private String mensaje;



    public Mensaje() {
    }

 public Mensaje(MensajeBean m){
     setId(m.getId());
     this.conversacion =  (m.getConversacion() == null)? null: new Conversacion(m.getConversacion());
     this.usuarioEmisor =  (m.getUsuario() == null)? null: new Usuario(m.getUsuario());
     this.estadoMensaje = (m.getEstadoMensaje() == null)? null: new EstadoMensaje(m.getEstadoMensaje());
     this.horaEmision = m.getHoraEmision();
     this.fechaEmision = m.getFechaEmision();
     this.mensaje = m.getMensaje();



 }

    public MensajeBean getBeanCompleto(){
        MensajeBean m = new MensajeBean();
        m.setId(getId());
        m.setConversacion((getConversacion() == null) ? null : getConversacion().getBeanCompleto());
        m.setEstadoMensaje((getEstadoMensaje() == null) ? null : getEstadoMensaje().getBeanCompleto());
        m.setUsuario((getUsuarioEmisor() == null) ? null : getUsuarioEmisor().getBean());
        m.setHoraEmision(getHoraEmision());
        m.setFechaEmision(getFechaEmision());
        m.setMensaje(getMensaje());

        return m;
    }

    public Conversacion getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversacion conversacion) {
        this.conversacion = conversacion;
    }

    public Usuario getUsuarioEmisor() {
        return usuarioEmisor;
    }

    public void setUsuarioEmisor(Usuario usuarioEmisor) {
        this.usuarioEmisor = usuarioEmisor;
    }

    public EstadoMensaje getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(EstadoMensaje estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Time getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(Time horaEmision) {
        this.horaEmision = horaEmision;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
