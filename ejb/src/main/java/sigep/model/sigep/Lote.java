package sigep.model.sigep;

import sigep.beans.sigep.ComisionBean;
import sigep.beans.sigep.ComisionIncompletaBean;
import sigep.beans.sigep.LoteBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Lote extends EntityBase {
    @NotNull
    @OneToOne
    private Aseguradora aseguradora;

    @OneToMany(mappedBy = "lote" )
    private List<Comision> comisiones;

    @OneToMany(mappedBy = "lote")
    private List<ComisionIncompleta> comisionesIncompletas;

    @NotNull
    @OneToOne
    private EstadoLote estado;

    @NotNull
    private Date fecha;

    private String nombre;

    private String observaciones;

    @OneToOne
    private Productor productor;

    public Lote() {
    }

    public Lote(LoteBean b) {
        setId(b.getId());
        setFecha(b.getFecha());
        setNombre(b.getNombre());
        setObservaciones(b.getObservaciones());
        setProductor((b.getProductor() == null) ? null : new Productor(b.getProductor()));
        setAseguradora((b.getAseguradora() == null) ? null : new Aseguradora(b.getAseguradora()));
        setEstado((b.getEstado() == null) ? null : new EstadoLote(b.getEstado()));

        if(b.getComisiones() != null && !b.getComisiones().isEmpty()){
            ArrayList<Comision> comisiones = new ArrayList<Comision>(b.getComisiones().size());
            for(ComisionBean comisionBean : b.getComisiones()){
                comisiones.add(new Comision(comisionBean));
            }
            setComisiones(comisiones);
        }

        if(b.getComisionesIncompletas() != null && !b.getComisionesIncompletas().isEmpty()){
            ArrayList<ComisionIncompleta> comisionesInc = new ArrayList<ComisionIncompleta>(b.getComisionesIncompletas().size());
            for(ComisionIncompletaBean comisionIncBean : b.getComisionesIncompletas()){
                comisionesInc.add(new ComisionIncompleta(comisionIncBean));
            }
            setComisionesIncompletas(comisionesInc);
        }
    }

    public Aseguradora getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(Aseguradora aseguradora) {
        this.aseguradora = aseguradora;
    }

    public List<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<Comision> comisiones) {
        this.comisiones = comisiones;
    }

    public EstadoLote getEstado() {
        return estado;
    }

    public void setEstado(EstadoLote estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ComisionIncompleta> getComisionesIncompletas() {
        return comisionesIncompletas;
    }

    public void setComisionesIncompletas(List<ComisionIncompleta> comisionesIncompletas) {
        this.comisionesIncompletas = comisionesIncompletas;
    }

    public Productor getProductor() {
        return productor;
    }

    public void setProductor(Productor productor) {
        this.productor = productor;
    }

    public void addComisionIncompleta(ComisionIncompleta comisionInc){
        if(getComisionesIncompletas() == null)
            setComisionesIncompletas(new ArrayList<ComisionIncompleta>());

        getComisionesIncompletas().add(comisionInc);
    }

    public LoteBean getBean(){
        LoteBean b = new LoteBean();
        b.setId(getId());
        b.setFecha(getFecha());
        b.setNombre(getNombre());
        b.setObservaciones(getObservaciones());
        b.setProductor((getProductor() == null) ? null : getProductor().getBeanPlano());
        b.setAseguradora((getAseguradora() == null) ? null :getAseguradora().getBean());
        b.setEstado((getEstado() == null) ? null : getEstado().getBean());
        return b;
    }

    public LoteBean getBeanConComisiones(){
        LoteBean b = getBean();
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

    public LoteBean getBeanConComisionesIncompletas(){
        LoteBean b = getBean();
        if(getComisionesIncompletas() != null && !getComisionesIncompletas().isEmpty()){
            List<ComisionIncompleta> comisionesIncompletas = getComisionesIncompletas();
            List<ComisionIncompletaBean> comisionIncompletasBeans = new ArrayList<ComisionIncompletaBean>(comisionesIncompletas.size());
            for(ComisionIncompleta c : comisionesIncompletas){
                comisionIncompletasBeans.add(c.getBean());
            }
            b.setComisionesIncompletas(comisionIncompletasBeans);
        }
        return b;
    }

    public LoteBean getBeanConComisionesTodas(){
        LoteBean b = getBeanConComisiones();
        if(getComisionesIncompletas() != null && !getComisionesIncompletas().isEmpty()){
            List<ComisionIncompleta> comisionesIncompletas = getComisionesIncompletas();
            List<ComisionIncompletaBean> comisionIncompletasBeans = new ArrayList<ComisionIncompletaBean>(comisionesIncompletas.size());
            for(ComisionIncompleta c : comisionesIncompletas){
                comisionIncompletasBeans.add(c.getBean());
            }
            b.setComisionesIncompletas(comisionIncompletasBeans);
        }
        return b;
    }
}
