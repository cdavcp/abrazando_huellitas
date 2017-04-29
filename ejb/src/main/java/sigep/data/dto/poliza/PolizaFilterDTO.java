package sigep.data.dto.poliza;

import java.util.Date;
import java.util.List;

public class PolizaFilterDTO {
    private Long productorId;
    private Long aseguradoraId;
    private Long rubroId;
    private String numero;
    private Long clienteId;
    private Long vendedorId;
    private Long origenId;
    private Date fechaDesde;
    private Date fechaHasta;
    private List<Integer> estados;

    public Long getProductorId() {
        return productorId;
    }

    public void setProductorId(Long productorId) {
        this.productorId = productorId;
    }

    public Long getAseguradoraId() {
        return aseguradoraId;
    }

    public void setAseguradoraId(Long aseguradoraId) {
        this.aseguradoraId = aseguradoraId;
    }

    public Long getRubroId() {
        return rubroId;
    }

    public void setRubroId(Long rubroId) {
        this.rubroId = rubroId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Long getOrigenId() {
        return origenId;
    }

    public void setOrigenId(Long origenId) {
        this.origenId = origenId;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public List<Integer> getEstados() {
        return estados;
    }

    public void setEstados(List<Integer> estados) {
        this.estados = estados;
    }
}
