package sigep.beans;

public class OpcionXEtiquetaBean {

    private Long id;
    private EtiquetaBean etiqueta;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtiquetaBean getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(EtiquetaBean etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
