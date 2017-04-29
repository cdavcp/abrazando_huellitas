package sigep.beans;

import java.util.List;

public class SolicitudBean {

    private Long id;
    private ArtistaBean artista;
    private LugarBean lugar;

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
}
