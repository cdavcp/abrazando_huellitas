package sigep.model.sigep;

import sigep.beans.sigep.SolicitudBeanOld;
import sigep.model.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class SolicitudOld extends EntityBase {

    private Date fecha;

    @Size( max = 100)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="cobertura_id")
    private Cobertura cobertura;

    @ManyToOne
    @JoinColumn(name="estado_id")
    private EstadoSolicitud estado;

    @OneToOne
    private Poliza poliza;

    @ManyToOne
    @JoinColumn(name="productor_id")
    private Productor productor;

    public SolicitudOld(){};

    public SolicitudOld(SolicitudBeanOld bean){
        setId(bean.getId());
        setCliente(new Cliente(bean.getCliente()));
        setCobertura(new Cobertura(bean.getCobertura()));
        setEstado(bean.getEstado() != null ? new EstadoSolicitud(bean.getEstado()) : null);
        setFecha(bean.getFecha() != null ? bean.getFecha() : null);
        setObservaciones(bean.getObservaciones());
        setPoliza(bean.getPoliza() != null ? new Poliza(bean.getPoliza()) : null);
        setProductor(bean.getProductor() != null ? new Productor(bean.getProductor()) : null);
    }

    public SolicitudBeanOld getBean(){
        SolicitudBeanOld bean = new SolicitudBeanOld();
        bean.setObservaciones(getObservaciones());
        bean.setFecha(getFecha());
        bean.setId(getId());
        bean.setCliente(getCliente().getBean());
        bean.setCobertura(getCobertura().getBean());
        bean.setEstado(getEstado().getBean());
        bean.setProductor(getProductor() != null ? getProductor().getBeanPlano() : null);
        bean.setPoliza(getPoliza() != null ? getPoliza().getBean() : null);
        return bean;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }
}
