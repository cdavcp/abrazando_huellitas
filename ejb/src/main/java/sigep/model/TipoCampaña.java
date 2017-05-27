package sigep.model;

import sigep.beans.TipoCampañaBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoCampaña extends EntityBase{

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public TipoCampaña(){
    }

    public TipoCampaña(TipoCampañaBean tc) {
        setId(tc.getId());
        this.descripcion = tc.getDescripcion();
    }

    public TipoCampañaBean getBeanCompleto() {
        TipoCampañaBean tc = new TipoCampañaBean();
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
