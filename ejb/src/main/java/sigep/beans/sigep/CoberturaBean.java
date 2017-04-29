package sigep.beans.sigep;

import java.util.List;

public class CoberturaBean {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double porcentajePrecio;
    private Double precio;
    private TipoCoberturaBean tipoCobertura;
    private List<RiesgoBean> riesgos;
    private AseguradoraBean aseguradora;
    private RubroBean rubro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TipoCoberturaBean getTipoCobertura() {
        return tipoCobertura;
    }

    public void setTipoCobertura(TipoCoberturaBean tipoCobertura) {
        this.tipoCobertura = tipoCobertura;
    }

    public List<RiesgoBean> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<RiesgoBean> riesgos) {
        this.riesgos = riesgos;
    }

    public AseguradoraBean getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(AseguradoraBean aseguradora) {
        this.aseguradora = aseguradora;
    }

    public RubroBean getRubro() {
        return rubro;
    }

    public void setRubro(RubroBean rubro) {
        this.rubro = rubro;
    }
}
