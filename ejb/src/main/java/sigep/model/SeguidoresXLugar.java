package sigep.model;

import sigep.beans.SeguidoresXLugarBean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class SeguidoresXLugar extends EntityBase  {


    @OneToOne
    private Usuario usuario;


    @OneToOne
    private Lugar lugar;





    public SeguidoresXLugar() {
    }

 public SeguidoresXLugar(SeguidoresXLugarBean sxl){
     setId(sxl.getId());
     this.usuario =  (sxl.getUsuario() == null)? null: new Usuario(sxl.getUsuario());
     this.lugar = (sxl.getLugar() ==null) ? null: new Lugar(sxl.getLugar());


 }

    public SeguidoresXLugarBean getBeanCompleto(){
            SeguidoresXLugarBean sxl = new SeguidoresXLugarBean();
        sxl.setId(getId());
        sxl.setLugar((getLugar() == null) ? null : getLugar().getBeanCompleto());
        sxl.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());

        return sxl;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
}
