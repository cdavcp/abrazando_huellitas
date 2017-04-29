package sigep.data.dto;

import sigep.beans.sigep.SolicitudBeanOld;

/**
 * Created by David on 18/08/2015.
 */
public class BandejaSolicitudDTO {

    private String numeroPoliza;
    private Long aseguradoraId;
    private SolicitudBeanOld solicitud;

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public Long getAseguradoraId() {
        return aseguradoraId;
    }

    public void setAseguradoraId(Long aseguradoraId) {
        this.aseguradoraId = aseguradoraId;
    }

    public SolicitudBeanOld getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudBeanOld solicitud) {
        this.solicitud = solicitud;
    }
}
