package sigep.model.sigep;

import sigep.beans.sigep.ComisionIncompletaBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ComisionIncompleta extends EntityBase {

    private Date fecha;

    @NotNull
    @ManyToOne
    @JoinColumn(name="lote_id")
    private Lote lote;

    private Double montoTotal;

    @NotNull
    private Integer numeroFila;

    private String numeroPoliza;

    private String error;

    public ComisionIncompleta() {
    }

    public ComisionIncompleta(ComisionIncompletaBean b) {
        setId(b.getId());
        setFecha(b.getFecha());
        setLote((b.getLote() == null) ? null : new Lote(b.getLote()));
        setMontoTotal(b.getMontoTotal());
        setNumeroFila(b.getNumeroFila());
        setNumeroPoliza(b.getNumeroPoliza());
        setError(b.getError());
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getNumeroFila() {
        return numeroFila;
    }

    public void setNumeroFila(Integer numeroFila) {
        this.numeroFila = numeroFila;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ComisionIncompletaBean getBean(){
        ComisionIncompletaBean b = new ComisionIncompletaBean();
        b.setId(getId());
        b.setFecha(getFecha());
        b.setLote((getLote() == null) ? null : getLote().getBean());
        b.setMontoTotal(getMontoTotal());
        b.setNumeroFila(getNumeroFila());
        b.setNumeroPoliza(getNumeroPoliza());
        b.setError(getError());
        return b;
    }
}
