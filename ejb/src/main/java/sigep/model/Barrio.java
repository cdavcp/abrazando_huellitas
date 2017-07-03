package sigep.model;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import sigep.beans.BarrioBean;


public class Barrio extends EntityBase{

    @NotNull
    @OneToOne
    private Ciudad ciudad;

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public Barrio() {
    }

    public Barrio(BarrioBean ba) {
        setId(ba.getId());
        this.ciudad = new Ciudad(ba.getCiudad());
        this.descripcion = ba.getDescripcion();
    }

    public BarrioBean getBeanCompleto() {
        BarrioBean ba = new BarrioBean();
        ba.setId(getId());
        ba.setCiudad(getCiudad().getBeanCompleto());
        ba.setDescripcion(getDescripcion());

        return ba;
    }

    public Ciudad getCiudad() { return ciudad;}

    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad;}

    public String getDescripcion() { return descripcion;}

    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}

}
