package sigep.model;

import sigep.beans.ArtistaBean;
import sigep.beans.SolicitudBean;
import sigep.beans.TagBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Solicitud extends EntityBase {


    @OneToOne
    private Artista artista;

    @OneToOne
    private Lugar lugar;


    public Solicitud() {
    }

    public Solicitud(SolicitudBean s) {
        setId(s.getId());
        this.artista = (s.getArtista() == null) ? null : new Artista(s.getArtista());
        this.lugar = (s.getLugar() == null) ? null : new Lugar(s.getLugar());

            }


    public SolicitudBean getBeanCompleto() {
        SolicitudBean s = new SolicitudBean();
        s.setId(getId());
        s.setLugar((getLugar() == null) ? null : getLugar().getBeanCompleto());
        s.setArtista((getArtista() == null) ? null : getArtista().getBeanCompleto());

        return s;
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
}