package sigep.model;

import sigep.beans.TipoMascotaBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoMascota extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public TipoMascota(){
    }

    public TipoMascota(TipoMascotaBean tm) {
        setId(tm.getId());
        this.descripcion = tm.getDescripcion();
    }

    public TipoMascotaBean getBeanCompleto() {
        TipoMascotaBean tm = new TipoMascotaBean();
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
