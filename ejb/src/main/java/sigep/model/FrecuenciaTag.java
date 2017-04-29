package sigep.model;

import sigep.beans.FrecuenciaTagBean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class FrecuenciaTag extends EntityBase {

    @OneToOne
    private TipoShow tipoShow;
    @OneToOne
    private Tag tag;
    @Size(max = 100)
    private Long frecuencia;


    public TipoShow getTipoShow() {
        return tipoShow;
    }

    public void setTipoShow(TipoShow tipoShow) {
        this.tipoShow = tipoShow;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Long getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Long frecuencia) {
        this.frecuencia = frecuencia;
    }
}

