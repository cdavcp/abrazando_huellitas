package sigep.beans;

import java.util.List;

public class TipoUsuarioBean {

    private Long id;
    private String nombre;
    private List<PermisoBean> permisos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PermisoBean> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoBean> permisos) {
        this.permisos = permisos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
