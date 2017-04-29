package sigep.beans;

import java.util.List;

public class VotacionArtistaBean {

    private Long id;
    private UsuarioBean usuario;
    private ArtistaBean artista;
    private String puntuacion;


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

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }
}