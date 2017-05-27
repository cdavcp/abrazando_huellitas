package sigep.model.Ivent;

import sigep.beans.Ivent.EventoBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Evento extends EntityBase {

    @OneToOne
    @NotNull
    private Artista artista;

    @OneToOne
    @NotNull
    private Lugar lugar;

    @NotNull
    private Boolean aceptadoPorArtista;

    @NotNull
    private Boolean aceptadoPorLugar;


    private String horaEvento;

    @NotNull
    private Date fechaEvento;

    @NotNull
    private Date fechaEmision;
    
    @Size(max = 100)
    private String cantMg;

    @Size(max = 400)
    private String comentarios;

    private Double cupoEvento;

    private Double duracionEvento;

    public Evento() {
    }

    public Evento(EventoBean b) {
        this.setId(b.getId());
        this.artista = (b.getArtista() == null) ? null : new Artista(b.getArtista());
        this.lugar = (b.getLugar() == null) ? null : new Lugar(b.getLugar());
        this.aceptadoPorArtista = b.getAceptadoPorArtista();
        this.aceptadoPorLugar = b.getAceptadoPorLugar();
        this.horaEvento = b.getHoraEvento();
        this.fechaEvento = b.getFechaEvento();
        this.fechaEmision = b.getFechaEmision();
        this.cantMg = b.getAsunto();
        this.comentarios = b.getComentarios();
        this.cupoEvento = b.getCupoEvento();
        this.duracionEvento = b.getDuracionEvento();
    }

    public EventoBean getBean(){
        EventoBean b = new EventoBean();
        b.setId(getId());
        b.setArtista((getArtista() == null) ? null : getArtista().getBeanCompleto());
        b.setLugar((getLugar() == null) ? null : getLugar().getBeanCompleto());
        b.setAceptadoPorArtista(getAceptadoPorArtista());
        b.setAceptadoPorLugar(getAceptadoPorLugar());
        b.setHoraEvento(getHoraEvento());
        b.setFechaEvento(getFechaEvento());
        b.setFechaEmision(getFechaEmision());
        b.setAsunto(getAsunto());
        b.setComentarios(getComentarios());
        b.setCupoEvento(getCupoEvento());
        b.setDuracionEvento(getDuracionEvento());
        return b;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Boolean getAceptadoPorArtista() {
        return aceptadoPorArtista;
    }

    public void setAceptadoPorArtista(Boolean aceptadoPorArtista) {
        this.aceptadoPorArtista = aceptadoPorArtista;
    }

    public Boolean getAceptadoPorLugar() {
        return aceptadoPorLugar;
    }

    public void setAceptadoPorLugar(Boolean aceptadoPorLugar) {
        this.aceptadoPorLugar = aceptadoPorLugar;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getAsunto() {
        return cantMg;
    }

    public void setAsunto(String asunto) {
        this.cantMg = asunto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Double getCupoEvento() {
        return cupoEvento;
    }

    public void setCupoEvento(Double cupoEvento) {
        this.cupoEvento = cupoEvento;
    }

    public Double getDuracionEvento() {
        return duracionEvento;
    }

    public void setDuracionEvento(Double duracionEvento) {
        this.duracionEvento = duracionEvento;
    }
}
