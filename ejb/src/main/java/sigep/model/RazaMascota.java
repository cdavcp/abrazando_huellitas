package sigep.model;

import sigep.beans.RazaMascotaBean;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RazaMascota extends EntityBase{

    @NotNull
    @OneToOne
    private TipoMascota tipoMascota;

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public RazaMascota() {
    }

    public RazaMascota(RazaMascotaBean rm) {
        setId(rm.getId());
        this.tipoMascota = new TipoMascota(rm.getTipoMascota());
        this.descripcion = rm.getDescripcion();
    }

    public RazaMascotaBean getBeanCompleto() {
        RazaMascotaBean rm = new RazaMascotaBean();
        rm.setId(getId());
        rm.setTipoMascota(getTipoMascota().getBeanCompleto());
        rm.setDescripcion(getDescripcion());

        return rm;
    }

    public TipoMascota getTipoMascota() { return tipoMascota;}

    public void setTipoMascota(TipoMascota tipoMascota) { this.tipoMascota = tipoMascota;}

    public String getDescripcion() { return descripcion;}

    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}

}
