package sigep.model.sigep;

import sigep.beans.sigep.RubroBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Rubro extends EntityBase {

    @Size( max = 50)
    private String nombre;

    @Size( max = 100)
    private String descripcion;

    public static final Long ID_RUBRO_AUTO = new Long(1);

    public Rubro() {
    }

    public Rubro(RubroBean bean){
        setId(bean.getId());
        setNombre(bean.getNombre());
        setDescripcion(bean.getDescripcion());
    }

    public RubroBean getBean(){
        RubroBean bean = new RubroBean();
        bean.setNombre(getNombre());
        bean.setDescripcion(getDescripcion());
        bean.setId(getId());
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
