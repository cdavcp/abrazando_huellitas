package sigep.model;

import sigep.beans.TipoUsuarioBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoUsuario extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public TipoUsuario(){
    }

    public TipoUsuario(TipoUsuarioBean tu) {
        setId(tu.getId());
        this.descripcion = tu.getDescripcion();
    }

    public TipoUsuarioBean getBeanCompleto() {
        TipoUsuarioBean tu = new TipoUsuarioBean();
        tu.setId(getId());
        tu.setDescripcion(getDescripcion());

        return tu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
