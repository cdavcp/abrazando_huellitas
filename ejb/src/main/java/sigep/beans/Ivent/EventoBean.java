package sigep.beans.Ivent;

import java.util.Date;

public class EventoBean {

    private Long id;
    private ArtistaBean artista;
    private LugarBean lugar;
    private Boolean aceptadoPorArtista;
    private Boolean aceptadoPorLugar;
    private String horaEvento;
    private Date fechaEvento;
    private Date fechaEmision;
    private String cantMg;
    private String comentarios;
    private Double cupoEvento;
    private Double duracionEvento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArtistaBean getArtista() {
        return artista;
    }

    public void setArtista(ArtistaBean artista) {
        this.artista = artista;
    }

    public LugarBean getLugar() {
        return lugar;
    }

    public void setLugar(LugarBean lugar) {
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
