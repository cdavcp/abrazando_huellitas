package sigep.model.sigep;

import sigep.beans.sigep.CoberturaBean;
import sigep.beans.sigep.RiesgoBean;
import sigep.model.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cobertura extends EntityBase {

    @Size( max = 50)
    @NotNull(message = "Ingrese un nombre")
    private String nombre;

    @Size( max = 300)
    private String descripcion;

    @Min(0)
    @Max(100)
    private Double porcentajePrecio;

    @Min(0)
    private Double precio;

    @OneToOne
    private TipoCobertura tipoCobertura;

    @ManyToOne
    @JoinColumn(name="aseguradora_id")
    private Aseguradora aseguradora;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(joinColumns={@JoinColumn(name="cobertura_id")}, inverseJoinColumns={@JoinColumn(name="riesgo_id")})
    private List<Riesgo> riesgos;

    @OneToOne
    private Rubro rubro;

    public Cobertura(){}

    public Cobertura(CoberturaBean bean){
        setId(bean.getId());
        setNombre(bean.getNombre());
        setDescripcion(bean.getDescripcion());
        setPorcentajePrecio(bean.getPorcentajePrecio());
        setPrecio(bean.getPrecio());
        setTipoCobertura((bean.getTipoCobertura() == null) ? null : new TipoCobertura(bean.getTipoCobertura()));
        setRubro((bean.getRubro() == null) ? null : new Rubro(bean.getRubro()));
        setAseguradora((bean.getAseguradora() == null) ? null : new Aseguradora(bean.getAseguradora()));

        if(bean.getRiesgos() != null && !bean.getRiesgos().isEmpty()){
            ArrayList<Riesgo> riesgos = new ArrayList<Riesgo>(bean.getRiesgos().size());
            for(RiesgoBean riesgoBean : bean.getRiesgos()){
                riesgos.add(new Riesgo(riesgoBean));
            }
            setRiesgos(riesgos);
        }
    }

    public CoberturaBean getBean(){
        CoberturaBean bean = new CoberturaBean();
        bean.setId(getId());
        bean.setNombre(getNombre());
        bean.setDescripcion(getDescripcion());
        bean.setPrecio(getPrecio());
        bean.setPorcentajePrecio(getPorcentajePrecio());
        bean.setTipoCobertura((getTipoCobertura() == null) ? null : getTipoCobertura().getBean());
        bean.setAseguradora((getAseguradora() == null) ? null : getAseguradora().getBean());
        bean.setRubro((getRubro() == null) ? null : getRubro().getBean());
        return bean;
    }

    public CoberturaBean getBeanCompleto(){
        CoberturaBean bean = getBean();

        if(getRiesgos() != null && !getRiesgos().isEmpty()){
            List<Riesgo> riesgosEntities = getRiesgos();
            List<RiesgoBean> riesgosBeans = new ArrayList<RiesgoBean>(riesgosEntities.size());
            for(Riesgo riesgo : riesgosEntities){
                riesgosBeans.add(riesgo.getBean());
            }
            bean.setRiesgos(riesgosBeans);
        }
        return bean;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPorcentajePrecio() {
        return porcentajePrecio;
    }

    public void setPorcentajePrecio(Double porcentajePrecio) {
        this.porcentajePrecio = porcentajePrecio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public TipoCobertura getTipoCobertura() {
        return tipoCobertura;
    }

    public void setTipoCobertura(TipoCobertura tipoCobertura) {
        this.tipoCobertura = tipoCobertura;
    }

    public List<Riesgo> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<Riesgo> riesgos) {
        this.riesgos = riesgos;
    }

    public Aseguradora getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(Aseguradora aseguradora) {
        this.aseguradora = aseguradora;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }
}
