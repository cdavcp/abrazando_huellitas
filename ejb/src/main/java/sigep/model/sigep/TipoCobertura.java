package sigep.model.sigep;

import sigep.beans.sigep.TipoCoberturaBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class TipoCobertura extends EntityBase {

    @Size( max = 50)
    private String nombre;

    @Size( max = 100)
    private String descripcion;

    public TipoCobertura() {
    }

    public TipoCobertura(TipoCoberturaBean bean){
        setId(bean.getId());
        setNombre(bean.getNombre());
        setDescripcion(bean.getDescripcion());
    }

    public TipoCoberturaBean getBean(){
        TipoCoberturaBean bean = new TipoCoberturaBean();
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
