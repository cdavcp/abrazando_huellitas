package sigep.beans;

public class RazaMascotaBean {

    private Long id;
    private TipoMascotaBean tipoMascota;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMascotaBean getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascotaBean tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
