package sigep.model;

import sigep.beans.EventoBean;
import sigep.beans.ReservaBean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;

@Entity
public class Reserva extends EntityBase  {


    @OneToOne
    private Usuario usuario;


    @OneToOne
    private Evento evento;


//    @OneToOne
//    private EstadoReserva estadoReserva;


    @Size(max = 4)
    @NotNull
    private String cantidad;

    @NotNull
    private String nombre;


    private Time horaReserva;

    private Date fechaReserva;

    private Date fechaBaja;

    private Boolean asistio;



    public Reserva() {
    }

 public Reserva(ReservaBean r){
     setId(r.getId());
     this.usuario =  (r.getUsuario() == null)? null: new Usuario(r.getUsuario());
     this.evento = (r.getEvento() ==null) ? null: new Evento(r.getEvento());
//     this.estadoReserva= (r.getEstadoReserva() == null) ? null: new EstadoReserva(r.getEstadoReserva());
     this.cantidad = r.getCantidad();
     this.nombre = r.getNombre();
     this.horaReserva = r.getHoraReserva();
     this.fechaReserva = r.getFechaReserva();
     this.fechaBaja = r.getFechaBaja();
     this.asistio = r.getAsistio();

 }

    public ReservaBean getBeanCompleto(){
            ReservaBean r = new ReservaBean();
        r.setId(getId());
        r.setEvento((getEvento() == null) ? null : getEvento().getBean());
        r.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
//        r.setEstadoReserva((getEstadoReserva() == null) ? null : getEstadoReserva().getBeanCompleto());
        r.setCantidad(getCantidad());
        r.setFechaReserva(getFechaReserva());
        r.setFechaBaja(getFechaBaja());
        r.setHoraReserva(getHoraReserva());
        r.setNombre(getNombre());
        r.setAsistio(getAsistio());

        return r;
    }

    public Boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(Boolean asistio) {
        this.asistio = asistio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

//    public EstadoReserva getEstadoReserva() {
//        return estadoReserva;
//    }

//    public void setEstadoReserva(EstadoReserva estadoReserva) {
//        this.estadoReserva = estadoReserva;
//    }

    public Time getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(Time horaReserva) {
        this.horaReserva = horaReserva;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
