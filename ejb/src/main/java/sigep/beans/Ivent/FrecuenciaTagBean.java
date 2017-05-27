package sigep.beans.Ivent;

import sigep.model.Ivent.Tag;
import sigep.model.Ivent.TipoShow;

public class FrecuenciaTagBean {

    private Long id;
    private TipoShow tipoShow;
    private Tag tag;
    private Long frecuencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
