package sigep.model.sigep;

import sigep.beans.sigep.ComisionBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Comision extends EntityBase {

    private Double comisionComercializadora;

    @NotNull
    private Double comisionProductor;

    private Double comisionVendedor;

    @NotNull
    private Date fecha;

    @NotNull
    @ManyToOne
    @JoinColumn(name="lote_id")
    private Lote lote;

    @NotNull
    @ManyToOne
    @JoinColumn(name="poliza_id")
    private Poliza poliza;

    public Comision() {
    }

    public Comision(ComisionBean b) {
        setId(b.getId());
        setComisionComercializadora(b.getComisionComercializadora());
        setComisionProductor(b.getComisionProductor());
        setComisionVendedor(b.getComisionVendedor());
        setFecha(b.getFecha());
        setLote((b.getLote() == null) ? null : new Lote(b.getLote()));
        setPoliza((b.getPoliza() == null) ? null : new Poliza(b.getPoliza()));
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

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public ComisionBean getBean() {
        ComisionBean b = new ComisionBean();
        b.setId(getId());
        b.setFecha(getFecha());
        b.setComisionVendedor(getComisionVendedor());
        b.setComisionProductor(getComisionProductor());
        b.setComisionComercializadora(getComisionComercializadora());
        b.setPoliza((getPoliza() == null) ? null : getPoliza().getBean());
        b.setLote((getLote() == null) ? null : getLote().getBean());
        return b;
    }
}
