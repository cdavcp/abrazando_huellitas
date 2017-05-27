package sigep.model;

import sigep.beans.OpcionXEtiquetaBean;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class OpcionXEtiqueta extends EntityBase{

    @NotNull
    @OneToOne
    private Etiqueta etiqueta;

    @Size(max = 50)
    @NotNull
    private String descripcion;

    public OpcionXEtiqueta() {
    }

    public OpcionXEtiqueta(OpcionXEtiquetaBean ba) {
        setId(ba.getId());
        this.etiqueta = new Etiqueta(ba.getEtiqueta());
        this.descripcion = ba.getDescripcion();
    }

    public OpcionXEtiquetaBean getBeanCompleto() {
        OpcionXEtiquetaBean ba = new OpcionXEtiquetaBean();
        ba.setId(getId());
        ba.setEtiqueta(getEtiqueta().getBeanCompleto()  );
        ba.setDescripcion(getDescripcion());

        return ba;
    }

    public Etiqueta getEtiqueta() { return etiqueta;}

    public void setEtiqueta(Etiqueta etiqueta) { this.etiqueta = etiqueta;}

    public String getDescripcion() { return descripcion;}

    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}

}
