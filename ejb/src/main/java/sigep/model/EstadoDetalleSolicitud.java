package sigep.model;

import sigep.beans.ArtistaBean;
import sigep.beans.EstadoDestalleSolicitudBean;
import sigep.beans.TagBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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