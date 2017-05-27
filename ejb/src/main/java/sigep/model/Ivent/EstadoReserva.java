package sigep.model.Ivent;

import sigep.beans.Ivent.EstadoReservaBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class EstadoReserva extends EntityBase {

    @Size(max = 50)
    @NotNull
    private String nombre;

    @Size(max = 50)
    private String descripcion;


    public EstadoReserva() {
    }

    public EstadoReserva(EstadoReservaBean er) {
        setId(er.getId());
        this.nombre = er.getNombre();
        this.descripcion = er.getDescripcion();


    }


    public EstadoReservaBean getBeanCompleto() {
        EstadoReservaBean er = new EstadoReservaBean();
        er.setId(getId());
        er.setDescripcion(getDescripcion());
        er.setNombre(getNombre());

        return er;

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