package sigep.model.sigep;

import sigep.beans.sigep.OrigenPolizaBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class OrigenPoliza extends EntityBase {

    @NotNull(message = "Ingrese un nombre")
    @Size(max = 50)
    private String nombre;

    @Size(max = 200)
    private String descripcion;

    public OrigenPoliza() {
    }

    public OrigenPoliza(OrigenPolizaBean b) {
        setId(b.getId());
        setNombre(b.getNombre());
        setDescripcion(b.getDescripcion());
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

    public OrigenPolizaBean getBean(){
        OrigenPolizaBean b = new OrigenPolizaBean();
        b.setId(getId());
        b.setNombre(getNombre());
        b.setDescripcion(getDescripcion());
        return b;
    }
}
