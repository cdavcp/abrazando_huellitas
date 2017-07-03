package sigep.beans;

public class BarrioBean {

    private Long id;
    private CiudadBean ciudad;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CiudadBean getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadBean ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
