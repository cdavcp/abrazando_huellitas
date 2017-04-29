package sigep.beans.sigep;

import java.util.Date;

public class ComisionIncompletaBean {
    private Long id;
    private Date fecha;
    private LoteBean lote;
    private Double montoTotal;
    private Integer numeroFila;
    private String numeroPoliza;
    private String error;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LoteBean getLote() {
        return lote;
    }

    public void setLote(LoteBean lote) {
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
}
