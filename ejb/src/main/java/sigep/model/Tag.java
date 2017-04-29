package sigep.model;

import sigep.beans.TagBean;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Tag extends EntityBase{
    @NotNull
    @Size(max = 50)
    private String nombre;

    public Tag(TagBean b) {
        this.setId(b.getId());
        this.setNombre(b.getNombre());
    }

    public Tag(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TagBean getBean(){
        TagBean b = new TagBean();
        b.setId(this.getId());
        b.setNombre(this.getNombre());
        return b;
    }
}
