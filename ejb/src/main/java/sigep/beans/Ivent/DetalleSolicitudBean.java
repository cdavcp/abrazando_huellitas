package sigep.beans.Ivent;

import java.sql.Time;
import java.util.Date;

public class DetalleSolicitudBean {

    private Long id;
    private SolicitudBean solicitud;
    private EstadoDestalleSolicitudBean estadoDestalleSolicitud;
    private Time horaEmision;
    private Date fechaEmision;
    private String asunto;
    private String comentarios;
    private Integer cupoEvento;
    private Time horaEvento;
    private Date fechaEvento;
    private Integer duracionEvento;
    private String nombreUsuarioEmisor;
    private String getNombreUsuarioReceptor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SolicitudBean getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudBean solicitud) {
        this.solicitud = solicitud;
    }

    public EstadoDestalleSolicitudBean getEstadoDestalleSolicitud() {
        return estadoDestalleSolicitud;
    }

    public void setEstadoDestalleSolicitud(EstadoDestalleSolicitudBean estadoDestalleSolicitud) {
        this.estadoDestalleSolicitud = estadoDestalleSolicitud;
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

    public String getGetNombreUsuarioReceptor() {
        return getNombreUsuarioReceptor;
    }

    public void setGetNombreUsuarioReceptor(String getNombreUsuarioReceptor) {
        this.getNombreUsuarioReceptor = getNombreUsuarioReceptor;
    }
}
