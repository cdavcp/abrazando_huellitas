package sigep.model.sigep;

import sigep.beans.sigep.EstadoSolicitudBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class EstadoSolicitud extends EntityBase {

    @Size( max = 50)
    private String nombre;

    @Size( max = 100)
    private String descripcion;

    public static final Long ID_ESTADO_REGISTRADA = new Long(1);
    public static final Long ID_ESTADO_ASIGNADA = new Long(2);
    public static final Long ID_ESTADO_VENDIDA = new Long(3);
    public static final Long ID_ESTADO_NO_VENDIDA = new Long(4);

    public EstadoSolicitud(){};

    public EstadoSolicitud(Long id){
        setId(id);
    }

    public EstadoSolicitud(EstadoSolicitudBean bean){
        setId(bean.getId());
        setNombre(bean.getNombre());
        setDescripcion(bean.getDescripcion());
    }

    public EstadoSolicitudBean getBean(){
        EstadoSolicitudBean bean = new EstadoSolicitudBean();
        bean.setId(getId());
        bean.setNombre(getNombre());
        bean.setDescripcion(getDescripcion());
        return bean;
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
