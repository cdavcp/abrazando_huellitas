package sigep.model.Ivent;

import sigep.beans.Ivent.VotacionLugarBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class VotacionLugar extends EntityBase {

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Lugar lugar;

    @Size(max = 1)
    @NotNull
    private String puntuacion;


    public VotacionLugar() {
    }

 public VotacionLugar(VotacionLugarBean vl){
     setId(vl.getId());
     this.usuario =  (vl.getUsuario() == null)? null: new Usuario(vl.getUsuario());
     this.lugar = (vl.getLugar() == null)? null: new Lugar(vl.getLugar());
     this.puntuacion = vl.getPuntuacion();

 }

    public  VotacionLugarBean getBeanCompleto(){
        VotacionLugarBean vl = new VotacionLugarBean();
        vl.setId(getId());
        vl.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        vl.setLugar((getLugar() == null) ? null : getLugar().getBeanCompleto());
        vl.setPuntuacion(getPuntuacion());

        return vl;
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

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }
}