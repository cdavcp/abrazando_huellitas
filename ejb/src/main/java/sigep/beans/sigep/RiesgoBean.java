package sigep.beans.sigep;

import java.util.List;

public class RiesgoBean implements Comparable<RiesgoBean>{
    private Long id;
    private String nombre;
    private String descripcion;
    private RubroBean rubro;

    //SOLO PARA USOS EN SIMULACION. REPRESENTA SI ESTA INCLUIDO/NO EN UNA COBERTURA.
    private List<Boolean> incluidos;

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

    public RubroBean getRubro() {
        return rubro;
    }

    public void setRubro(RubroBean rubro) {
        this.rubro = rubro;
    }

    public List<Boolean> getIncluidos() {
        return incluidos;
    }

    public void setIncluidos(List<Boolean> incluidos) {
        this.incluidos = incluidos;
    }

    @Override
    public boolean equals(Object riesgo){
        RiesgoBean r = (RiesgoBean)riesgo;
        return this.getId().equals(r.getId());
    }

    @Override
    public int hashCode(){
        return getId().intValue();
    }

    @Override
    public int compareTo(RiesgoBean r){
        return getNombre().compareTo(r.getNombre());
    }

}
