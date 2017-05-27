package sigep.model.Ivent;

import sigep.beans.Ivent.EstadoDestalleSolicitudBean;
import sigep.model.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class EstadoDetalleSolicitud extends EntityBase {

    @Size(max = 50)
    @NotNull
    private String nombre;

    @Size(max = 50)
    @NotNull
    private String descripcion;


    public EstadoDetalleSolicitud() {
    }

    public EstadoDetalleSolicitud(EstadoDestalleSolicitudBean ed) {
        setId(ed.getId());
        this.nombre = ed.getNombre();
        this.descripcion = ed.getDescripcion();


    }


    public EstadoDestalleSolicitudBean getBeanCompleto() {
        EstadoDestalleSolicitudBean ed = new EstadoDestalleSolicitudBean();
        ed.setId(getId());
        ed.setDescripcion(getDescripcion());
        ed.setNombre(getNombre());

        return ed;

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
}