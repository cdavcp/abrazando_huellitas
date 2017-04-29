package sigep.beans;

import sigep.model.EstadoReserva;
import sigep.model.Usuario;

import java.sql.Time;
import java.util.Date;

public class ReservaBean {

    private Long id;
    private UsuarioBean usuario;
    private EventoBean evento;
//    private EstadoReservaBean estadoReserva;
    private String cantidad;
    private String nombre;
    private Time horaReserva;
    private Date fechaReserva;
    private Date fechaBaja;
    private Boolean asistio;

    public Boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(Boolean asistio) {
        this.asistio = asistio;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
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

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

//    public EstadoReservaBean getEstadoReserva() {
//        return estadoReserva;
//    }

//    public void setEstadoReserva(EstadoReservaBean estadoReserva) {
//        this.estadoReserva = estadoReserva;
//    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Time getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(Time horaReserva) {
        this.horaReserva = horaReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
