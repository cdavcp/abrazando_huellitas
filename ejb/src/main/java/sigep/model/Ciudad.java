package sigep.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import sigep.beans.CiudadBean;

public class Ciudad extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public  Ciudad(){
    }

    public Ciudad(CiudadBean ci) {
        setId(ci.getId());
        this.descripcion = ci.getDescripcion();
    }

    public CiudadBean getBeanCompleto() {
        CiudadBean ci = new CiudadBean();
        ci.setId(getId());
        ci.setDescripcion(getDescripcion());

        return ci;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
