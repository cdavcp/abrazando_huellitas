package sigep.model;

import sigep.beans.TipoCampaniaBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoCampania extends EntityBase {

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public TipoCampania() {
    }

    public TipoCampania(TipoCampaniaBean tc) {
        setId(tc.getId());
        this.descripcion = tc.getDescripcion();
    }

    public TipoCampaniaBean getBeanCompleto() {
        TipoCampaniaBean tc = new TipoCampaniaBean();
        tc.setId(getId());
        tc.setDescripcion(getDescripcion());

        return tc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
