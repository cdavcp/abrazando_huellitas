package sigep.model;

import sigep.beans.TamanioMascotaBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TamanioMascota extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public TamanioMascota(){
    }

    public TamanioMascota(TamanioMascotaBean tm) {
        setId(tm.getId());
        this.descripcion = tm.getDescripcion();
    }

    public TamanioMascotaBean getBeanCompleto() {
        TamanioMascotaBean tm = new TamanioMascotaBean();
        tm.setId(getId());
        tm.setDescripcion(getDescripcion());

        return tm;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
