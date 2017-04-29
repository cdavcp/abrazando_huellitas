package sigep.model;

import sigep.beans.PermisoBean;
import sigep.beans.TipoUsuarioBean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipoUsuario extends EntityBase{
    public static final Long ID_MANAGER = 1L;
    public static final Long ID_ANFITRION = 2L;

    @NotNull
    private String nombre;

    @ManyToMany
    @JoinTable(joinColumns={@JoinColumn(name="tipousuario_id")}, inverseJoinColumns={@JoinColumn(name="permiso_id")})
    private List<Permiso> permisos;

    public TipoUsuario() {
    }

    public TipoUsuario(TipoUsuarioBean b) {
        setNombre(b.getNombre());
        setId(b.getId());
        if(b.getPermisos() != null && !b.getPermisos().isEmpty()){
            ArrayList<Permiso> permisos = new ArrayList<Permiso>(b.getPermisos().size());
            for(PermisoBean permisoBean : b.getPermisos()){
                permisos.add(new Permiso(permisoBean));
            }
            setPermisos(permisos);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public TipoUsuarioBean getBean(){
        TipoUsuarioBean b = new TipoUsuarioBean();
        b.setNombre(this.getNombre());
        b.setId(this.getId());
        if(getPermisos() != null && !getPermisos().isEmpty()){
            List<Permiso> permisosEntities = getPermisos();
            List<PermisoBean> permisosBeans = new ArrayList<PermisoBean>(permisosEntities.size());
            for(Permiso permiso : permisosEntities){
                permisosBeans.add(permiso.getBean());
            }
            b.setPermisos(permisosBeans);
        }
        return b;
    }
}
