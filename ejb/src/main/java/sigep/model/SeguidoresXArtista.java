package sigep.model;

import sigep.beans.SeguidoresXArtistaBean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class SeguidoresXArtista extends EntityBase  {


    @OneToOne
    private Usuario usuario;


    @OneToOne
    private Artista artista;





    public SeguidoresXArtista() {
    }

 public SeguidoresXArtista(SeguidoresXArtistaBean sxb){
     setId(sxb.getId());
     this.usuario =  (sxb.getUsuario() == null)? null: new Usuario(sxb.getUsuario());
     this.artista = (sxb.getArtista() ==null) ? null: new Artista(sxb.getArtista());


 }

    public SeguidoresXArtistaBean getBeanCompleto(){
            SeguidoresXArtistaBean sxb = new SeguidoresXArtistaBean();
        sxb.setId(getId());
        sxb.setArtista((getArtista() == null) ? null : getArtista().getBeanCompleto());
        sxb.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());

        return sxb;
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
}
