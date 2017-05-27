package sigep.model;

import sigep.beans.ColorMascotaBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ColorMascota extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public ColorMascota(){
    }

    public ColorMascota(ColorMascotaBean cm) {
        setId(cm.getId());
        this.descripcion = cm.getDescripcion();
    }

    public ColorMascotaBean getBeanCompleto() {
        ColorMascotaBean cm = new ColorMascotaBean();
        cm.setId(getId());
        cm.setDescripcion(getDescripcion());

        return cm;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
