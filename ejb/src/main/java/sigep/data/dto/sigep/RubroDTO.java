package sigep.data.dto.sigep;

import java.io.File;
public class RubroDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private File logo;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File getLogo() {
        return logo;
    }

    public void setLogo(File logo) {
        this.logo = logo;
    }
}
