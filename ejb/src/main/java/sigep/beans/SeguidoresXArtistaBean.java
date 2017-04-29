package sigep.beans;

import java.sql.Time;
import java.util.Date;

public class SeguidoresXArtistaBean {

    private Long id;
    private UsuarioBean usuario;
    private ArtistaBean artista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public ArtistaBean getArtista() {
        return artista;
    }

    public void setArtista(ArtistaBean artista) {
        this.artista = artista;
    }
}
