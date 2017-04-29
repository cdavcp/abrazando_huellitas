package sigep.model.sigep;

import sigep.beans.sigep.ComisionBean;
import sigep.beans.sigep.PolizaBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Poliza extends EntityBase {

    @NotNull
    @Size(max = 50)
    private String numero;

    @NotNull
    @OneToOne
    private Cobertura cobertura;

    @Size(max = 200)
    private String descripcion;

    @NotNull
    private Date fecha;

    @NotNull
    private Date fechaAlta;

    private Date fechaBaja;

    private Date fechaUltimaComision;

    @NotNull
    @OneToOne
    private Productor productor;

    @OneToOne
    private Vendedor vendedor;

    @NotNull
    @OneToOne
    private Cliente cliente;

    @OneToOne
    private OrigenPoliza origen;

    @OneToMany(mappedBy = "poliza")
    private List<Comision> comisiones;

    public Poliza() {
    }

    public Poliza(PolizaBean b) {
        setId(b.getId());
        setNumero(b.getNumero());
        setCobertura((b.getCobertura() == null) ? null : new Cobertura(b.getCobertura()));
        setDescripcion(b.getDescripcion());
        setFecha(b.getFecha());
        setFechaAlta(b.getFechaAlta());
        setFechaBaja(b.getFechaBaja());
        setFechaUltimaComision(b.getFechaUltimaComision());
        setProductor((b.getProductor() == null) ? null : new Productor(b.getProductor()));
        setVendedor((b.getVendedor()== null) ? null : new Vendedor(b.getVendedor()));
        setCliente((b.getCliente() == null) ? null : new Cliente(b.getCliente()));
        setOrigen((b.getOrigen()== null) ? null : new OrigenPoliza(b.getOrigen()));

        if(b.getComisiones() != null && !b.getComisiones().isEmpty()){
            ArrayList<Comision> comisiones = new ArrayList<Comision>(b.getComisiones().size());
            for(ComisionBean comisionBean : b.getComisiones()){
                comisiones.add(new Comision(comisionBean));
            }
            setComisiones(comisiones);
        }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
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

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public OrigenPoliza getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenPoliza origen) {
        this.origen = origen;
    }

    public List<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<Comision> comisiones) {
        this.comisiones = comisiones;
    }

    public PolizaBean getBean(){
        PolizaBean b = new PolizaBean();
        b.setId(getId());
        b.setNumero(getNumero());
        b.setCobertura((getCobertura() == null) ? null : getCobertura().getBean());
        b.setDescripcion(getDescripcion());
        b.setFecha(getFecha());
        b.setFechaAlta(getFechaAlta());
        b.setFechaBaja(getFechaBaja());
        b.setFechaUltimaComision(getFechaUltimaComision());
        b.setProductor((getProductor() == null) ? null : getProductor().getBeanPlano());
        b.setVendedor((getVendedor()== null) ? null : getVendedor().getBean());
        b.setCliente((getCliente() == null) ? null : getCliente().getBean());
        b.setOrigen((getOrigen() == null) ? null : getOrigen().getBean());
        return b;
    }

    public PolizaBean getBeanCompleto(){
        PolizaBean b = getBean();
        if(getComisiones() != null && !getComisiones().isEmpty()){
            List<Comision> comisiones = getComisiones();
            List<ComisionBean> comisionBeans = new ArrayList<ComisionBean>(comisiones.size());
            for(Comision c : comisiones){
                comisionBeans.add(c.getBean());
            }
            b.setComisiones(comisionBeans);
        }
        return b;
    }
}
