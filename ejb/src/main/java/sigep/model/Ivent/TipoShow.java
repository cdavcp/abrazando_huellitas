package sigep.model.Ivent;

import sigep.beans.Ivent.TipoShowBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TipoShow extends EntityBase {

    @NotNull
    @Size(max = 50)
    private String nombre;

    @Size(max = 100)
    private String descripcion;

    public TipoShow() {
    }

    public TipoShow(TipoShowBean b) {
        this.setId(b.getId());
        this.setNombre(b.getNombre());
        this.setDescripcion(b.getDescripcion());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoShowBean getBean(){
        TipoShowBean b = new TipoShowBean();
        b.setId(this.getId());
        b.setNombre(this.getNombre());
        b.setDescripcion(this.getDescripcion());
        return b;
    }
}
