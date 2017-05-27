package sigep.model.Ivent;

import sigep.beans.Ivent.EstadoMensajeBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class EstadoMensaje extends EntityBase {

    @Size(max = 50)
    @NotNull
    private String nombre;

    @Size(max = 50)
    @NotNull
    private String descripcion;


    public EstadoMensaje() {
    }

    public EstadoMensaje(EstadoMensajeBean em) {
        setId(em.getId());
        this.nombre = em.getNombre();
        this.descripcion = em.getDescripcion();


    }


    public EstadoMensajeBean getBeanCompleto() {
        EstadoMensajeBean em = new EstadoMensajeBean();
        em.setId(getId());
        em.setDescripcion(getDescripcion());
        em.setNombre(getNombre());

        return em;

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