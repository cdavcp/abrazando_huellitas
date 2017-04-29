package sigep.data.dto.sigep;

import sigep.beans.sigep.CoberturaBean;
import sigep.beans.sigep.RiesgoBean;

import java.util.List;

public class RiesgoDTO {

    private RiesgoBean riesgo;
    private List<CoberturaBean> coberturas;

    public RiesgoBean getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(RiesgoBean riesgo) {
        this.riesgo = riesgo;
    }

    public List<CoberturaBean> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<CoberturaBean> coberturas) {
        this.coberturas = coberturas;
    }
}
