package sigep.model.Ivent;

import sigep.beans.Ivent.DetalleSolicitudBean;
import sigep.model.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;

@Entity
public class DetalleSolicitud extends EntityBase {


    @OneToOne
    private Solicitud solicitud;

    @OneToOne
    private EstadoDetalleSolicitud estadoDetalleSolicitud;


    @Size(max = 34)
    @NotNull
    private Time horaEmision;

    @Size(max = 34)
    @NotNull
    private Date fechaEmision;


    @Size(max = 100)
    @NotNull
    private String asunto;

    @Size(max = 400)
    @NotNull
    private String comentarios;

    @Size(max = 4)
    @NotNull
    private Integer cupoEvento;

    @Size(max = 34)
    @NotNull
    private Time horaEvento;

    @Size(max = 34)
    @NotNull
    private Date fechaEvento;

    @Size(max = 4)
    @NotNull
    private Integer duracionEvento;

     @Size(max = 100)
    @NotNull
    private String nombreUsuarioEmisor;

    @Size(max = 100)
    @NotNull
    private String nombreUsuarioReceptor;


    public DetalleSolicitud() {
    }

 public DetalleSolicitud(DetalleSolicitudBean ds){
     setId(ds.getId());
     this.solicitud =  (ds.getSolicitud() == null)? null: new Solicitud(ds.getSolicitud());
     this.estadoDetalleSolicitud = (ds.getEstadoDestalleSolicitud() == null)? null: new EstadoDetalleSolicitud(ds.getEstadoDestalleSolicitud());
     this.horaEmision = ds.getHoraEmision();
     this.fechaEmision = ds.getFechaEmision();
     this.asunto = ds.getAsunto();
     this.comentarios = ds.getComentarios();
     this.cupoEvento = ds.getCupoEvento();
     this.horaEvento = ds.getHoraEvento();
     this.fechaEvento = ds.getFechaEvento();
     this.duracionEvento = ds.getDuracionEvento();
     this.nombreUsuarioEmisor = ds.getNombreUsuarioEmisor();
     this.nombreUsuarioReceptor = ds.getGetNombreUsuarioReceptor();


 }

    public DetalleSolicitudBean getBeanCompleto(){
        DetalleSolicitudBean ds = new DetalleSolicitudBean();
        ds.setId(getId());
        ds.setSolicitud((getSolicitud() == null) ? null : getSolicitud().getBeanCompleto());
        ds.setEstadoDestalleSolicitud((getEstadoDetalleSolicitud() == null) ? null : getEstadoDetalleSolicitud().getBeanCompleto());
        ds.setHoraEmision(getHoraEmision());
        ds.setFechaEmision(getFechaEmision());
        ds.setAsunto(getAsunto());
        ds.setComentarios(getComentarios());
        ds.setCupoEvento(getCupoEvento());
        ds.setHoraEvento(getHoraEvento());
        ds.setFechaEvento(getFechaEvento());
        ds.setDuracionEvento(getDuracionEvento());
        ds.setNombreUsuarioEmisor(getNombreUsuarioEmisor());
        ds.setGetNombreUsuarioReceptor(getNombreUsuarioReceptor());

        return ds;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public EstadoDetalleSolicitud getEstadoDetalleSolicitud() {
        return estadoDetalleSolicitud;
    }

    public void setEstadoDetalleSolicitud(EstadoDetalleSolicitud estadoDetalleSolicitud) {
        this.estadoDetalleSolicitud = estadoDetalleSolicitud;
    }

    public Time getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(Time horaEmision) {
        this.horaEmision = horaEmision;
    }

     public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getCupoEvento() {
        return cupoEvento;
    }

    public void setCupoEvento(Integer cupoEvento) {
        this.cupoEvento = cupoEvento;
    }

    public Time getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(Time horaEvento) {
        this.horaEvento = horaEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Integer getDuracionEvento() {
        return duracionEvento;
    }

    public void setDuracionEvento(Integer duracionEvento) {
        this.duracionEvento = duracionEvento;
    }

    public String getNombreUsuarioEmisor() {
        return nombreUsuarioEmisor;
    }

    public void setNombreUsuarioEmisor(String nombreUsuarioEmisor) {
        this.nombreUsuarioEmisor = nombreUsuarioEmisor;
    }

    public String getNombreUsuarioReceptor() {
        return nombreUsuarioReceptor;
    }

    public void setNombreUsuarioReceptor(String nombreUsuarioReceptor) {
        this.nombreUsuarioReceptor = nombreUsuarioReceptor;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}
