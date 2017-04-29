package sigep.model;

import sigep.beans.ArtistaBean;
import sigep.beans.TagBean;
import sigep.beans.VotacionArtistaBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class VotacionArtista extends EntityBase  {

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Artista artista;

    @Size(max = 1)
    @NotNull
    private String puntuacion;


    public VotacionArtista() {
    }

 public VotacionArtista(VotacionArtistaBean va){
     setId(va.getId());
     this.usuario =  (va.getUsuario() == null)? null: new Usuario(va.getUsuario());
     this.artista = (va.getArtista() == null)? null: new Artista(va.getArtista());
     this.puntuacion = va.getPuntuacion();

 }

    public  VotacionArtistaBean getBeanCompleto(){
        VotacionArtistaBean va = new VotacionArtistaBean();
        va.setId(getId());
        va.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        va.setArtista((getArtista() == null) ? null : getArtista().getBeanCompleto());
        va.setPuntuacion(getPuntuacion());

        return va;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }
}