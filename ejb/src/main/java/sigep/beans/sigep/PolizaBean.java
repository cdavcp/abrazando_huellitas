package sigep.beans.sigep;

import java.util.Date;
import java.util.List;

public class PolizaBean {
    private Long id;
    private String numero;
    private CoberturaBean cobertura;
    private String descripcion;
    private Date fecha;
    private Date fechaAlta;
    private Date fechaBaja;
    private Date fechaUltimaComision;
    private ProductorBean productor;
    private VendedorBean vendedor;
    private ClienteBean cliente;
    private OrigenPolizaBean origen;
    private List<ComisionBean> comisiones;

    //Se setea en el mapeo
    private Integer estadoId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public CoberturaBean getCobertura() {
        return cobertura;
    }

    public void setCobertura(CoberturaBean cobertura) {
        this.cobertura = cobertura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaUltimaComision() {
        return fechaUltimaComision;
    }

    public void setFechaUltimaComision(Date fechaUltimaComision) {
        this.fechaUltimaComision = fechaUltimaComision;
    }

    public ProductorBean getProductor() {
        return productor;
    }

    public void setProductor(ProductorBean productor) {
        this.productor = productor;
    }

    public VendedorBean getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorBean vendedor) {
        this.vendedor = vendedor;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public OrigenPolizaBean getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenPolizaBean origen) {
        this.origen = origen;
    }

    public List<ComisionBean> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<ComisionBean> comisiones) {
        this.comisiones = comisiones;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }
}
