package sigep.model;

import sigep.beans.EtiquetaBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Etiqueta extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public Etiqueta(){
    }

    public Etiqueta(EtiquetaBean et) {
        setId(et.getId());
        this.descripcion = et.getDescripcion();
    }

    public EtiquetaBean getBeanCompleto() {
        EtiquetaBean et = new EtiquetaBean();
        et.setId(getId());
        et.setDescripcion(getDescripcion());

        return et;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
