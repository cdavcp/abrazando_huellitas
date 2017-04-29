package sigep.model.sigep;

import sigep.beans.sigep.EstadoLoteBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class EstadoLote extends EntityBase {
    public static final Long ID_ESTADO_IMPORTADO = new Long(1);
    public static final Long ID_ESTADO_RECHAZADO= new Long(2);
    public static final Long ID_ESTADO_PROCESADO = new Long(3);
    public static final Long ID_ESTADO_PROCESADO_PARCIAL = new Long(4);
    public static final Long ID_ESTADO_ANULADO = new Long(5);

    @NotNull
    private String nombre;

    private String descripcion;

    public EstadoLote() {
    }

    public EstadoLote(EstadoLoteBean b) {
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

    public EstadoLoteBean getBean(){
        EstadoLoteBean b = new EstadoLoteBean();
        b.setId(getId());
        b.setNombre(getNombre());
        b.setDescripcion(getDescripcion());
        return b;
    }
}
