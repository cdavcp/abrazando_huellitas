package sigep.model.sigep;

import sigep.beans.sigep.RiesgoBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Riesgo extends EntityBase {

    @Size( max = 100)
    @NotNull(message = "Ingrese un nombre")
    private String nombre;

    @Size( max = 300)
    private String descripcion;

    @OneToOne
    private Rubro rubro;

    public Riesgo() {
    }

    public Riesgo(RiesgoBean bean){
        setId(bean.getId());
        setNombre(bean.getNombre());
        setDescripcion(bean.getDescripcion());
        if(bean.getRubro() != null){
            setRubro(new Rubro(bean.getRubro()));
        }
    }

    public Riesgo(Long id){
        setId(id);
    }

    public RiesgoBean getBean(){
        RiesgoBean bean = new RiesgoBean();
        bean.setId(getId());
        bean.setNombre(getNombre());
        bean.setDescripcion(getDescripcion());
        if(getRubro() != null){
            bean.setRubro(getRubro().getBean());
        }
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

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public boolean equals(Object riesgo){
        Riesgo r = (Riesgo) riesgo;
        return (r.getId() != null && this.getId() != null) ? r.getId().equals(this.getId()) : false;
    }

}
