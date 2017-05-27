package sigep.model;

import sigep.beans.TipoPublicacionBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoPublicacion extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public TipoPublicacion(){
    }

    public TipoPublicacion(TipoPublicacionBean tp) {
        setId(tp.getId());
        this.descripcion = tp.getDescripcion();
    }

    public TipoPublicacionBean getBeanCompleto() {
        TipoPublicacionBean tp = new TipoPublicacionBean();
        tp.setId(getId());
        tp.setDescripcion(getDescripcion());

        return tp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
