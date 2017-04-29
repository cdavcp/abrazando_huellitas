package sigep.beans.sigep;

import java.util.Date;

public class SolicitudBeanOld {

    private Long id;
    private Date fecha;
    private String observaciones;
    private ClienteBean cliente;
    private CoberturaBean cobertura;
    private EstadoSolicitudBean estado;
    private PolizaBean poliza;
    private ProductorBean productor;

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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public CoberturaBean getCobertura() {
        return cobertura;
    }

    public void setCobertura(CoberturaBean cobertura) {
        this.cobertura = cobertura;
    }

    public EstadoSolicitudBean getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitudBean estado) {
        this.estado = estado;
    }

    public PolizaBean getPoliza() {
        return poliza;
    }

    public void setPoliza(PolizaBean poliza) {
        this.poliza = poliza;
    }

    public ProductorBean getProductor() {
        return productor;
    }

    public void setProductor(ProductorBean productor) {
        this.productor = productor;
    }
}
