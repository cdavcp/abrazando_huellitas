package sigep.model;

import sigep.beans.EdadMascotaBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EdadMascota extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public EdadMascota(){
    }

    public EdadMascota(EdadMascotaBean em) {
        setId(em.getId());
        this.descripcion = em.getDescripcion();
    }

    public EdadMascotaBean getBeanCompleto() {
        EdadMascotaBean em = new EdadMascotaBean();
        em.setId(getId());
        em.setDescripcion(getDescripcion());

        return em;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
