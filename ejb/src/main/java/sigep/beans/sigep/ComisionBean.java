package sigep.beans.sigep;

import java.util.Date;

public class ComisionBean {
    private Long id;
    private Double comisionComercializadora;
    private Double comisionProductor;
    private Double comisionVendedor;
    private Date fecha;
    private PolizaBean poliza;
    private LoteBean lote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getComisionComercializadora() {
        return comisionComercializadora;
    }

    public void setComisionComercializadora(Double comisionComercializadora) {
        this.comisionComercializadora = comisionComercializadora;
    }

    public Double getComisionProductor() {
        return comisionProductor;
    }

    public void setComisionProductor(Double comisionProductor) {
        this.comisionProductor = comisionProductor;
    }

    public Double getComisionVendedor() {
        return comisionVendedor;
    }

    public void setComisionVendedor(Double comisionVendedor) {
        this.comisionVendedor = comisionVendedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PolizaBean getPoliza() {
        return poliza;
    }

    public void setPoliza(PolizaBean poliza) {
        this.poliza = poliza;
    }

    public LoteBean getLote() {
        return lote;
    }

    public void setLote(LoteBean lote) {
        this.lote = lote;
    }
}
