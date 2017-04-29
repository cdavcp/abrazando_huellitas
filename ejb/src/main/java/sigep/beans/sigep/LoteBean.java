package sigep.beans.sigep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoteBean {
    private Long id;
    private AseguradoraBean aseguradora;
    private List<ComisionBean> comisiones;
    private List<ComisionIncompletaBean> comisionesIncompletas;
    private EstadoLoteBean estado;
    private Date fecha;
    private String nombre;
    private String observaciones;
    private ProductorBean productor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AseguradoraBean getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(AseguradoraBean aseguradora) {
        this.aseguradora = aseguradora;
    }

    public List<ComisionBean> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<ComisionBean> comisiones) {
        this.comisiones = comisiones;
    }

    public List<ComisionIncompletaBean> getComisionesIncompletas() {
        return comisionesIncompletas;
    }

    public void setComisionesIncompletas(List<ComisionIncompletaBean> comisionesIncompletas) {
        this.comisionesIncompletas = comisionesIncompletas;
    }

    public EstadoLoteBean getEstado() {
        return estado;
    }

    public void setEstado(EstadoLoteBean estado) {
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

    public ProductorBean getProductor() {
        return productor;
    }

    public void setProductor(ProductorBean productor) {
        this.productor = productor;
    }

    public void addComisionIncompleta(ComisionIncompletaBean comisionInc){
        if(getComisionesIncompletas() == null)
            setComisionesIncompletas(new ArrayList<ComisionIncompletaBean>());

        getComisionesIncompletas().add(comisionInc);
    }
}
