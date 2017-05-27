package sigep.model.Ivent;

import sigep.beans.Ivent.ArtistaBean;
import sigep.beans.Ivent.TagBean;
import sigep.model.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Artista extends EntityBase {

    @Size(max = 50)
    @NotNull
    private String nombre;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private TipoShow tipoShow;

    @Size(max = 999)
    private String foto;

    @Size(max = 500)
    private String descripcion;

    @Size(max = 4)
    private String anioCreacion;

    @Size(max = 4)
    private String cantidadIntegrantes;

    private String puntuacion;

    @Size(max = 100)
    private String lugarOrigen;

    @Size(max = 100)
    private String lugarActual;

    @Size(max = 100)
    private String mail;

    @Size(max = 25)
    private String telefono;

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "artista_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

//    @ManyToMany
//    @JoinTable(joinColumns = {@JoinColumn(name = "artista_id")}, inverseJoinColumns = {@JoinColumn(name = "usuario_id")})
//    private List<Usuario> seguidores;


    public Artista() {
    }

    public Artista(ArtistaBean a) {
        setId(a.getId());
        this.nombre = a.getNombre();
        this.usuario = (a.getUsuario() == null) ? null : new Usuario(a.getUsuario());
        this.tipoShow = (a.getTipoShow() == null) ? null : new TipoShow(a.getTipoShow());
        this.foto = a.getFoto();
        this.descripcion = a.getDescripcion();
        this.anioCreacion = a.getAnioCreacion();
        this.cantidadIntegrantes = a.getCantidadIntegrantes();
        this.lugarActual = a.getLugarActual();
        this.lugarOrigen = a.getLugarOrigen();
        this.mail = a.getMail();
        this.telefono = a.getTelefono();
        this.puntuacion = a.getPuntuacion();

        if (a.getTags() != null && !a.getTags().isEmpty()) {
            ArrayList<Tag> tags = new ArrayList<Tag>(a.getTags().size());
            for (TagBean tagBean : a.getTags()) {
                tags.add(new Tag(tagBean));
            }
            setTags(tags);
        }

//        if (a.getSeguidores() != null && !a.getSeguidores().isEmpty()) {
//            ArrayList<Usuario> seguidores = new ArrayList<Usuario>(a.getSeguidores().size());
//            for (UsuarioBean seguidorBean : a.getSeguidores()) {
//                seguidores.add(new Usuario(seguidorBean));
//            }
//            setSeguidores(seguidores);
//        }

    }

    public ArtistaBean getBeanPlano() {
        ArtistaBean a = new ArtistaBean();
        a.setId(getId());
        a.setMail(getMail());
        a.setNombre(getNombre());
        a.setTelefono(getTelefono());
        a.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        a.setFoto(getFoto());
        a.setDescripcion(getDescripcion());
        a.setAnioCreacion(getAnioCreacion());
        a.setCantidadIntegrantes(getCantidadIntegrantes());
        a.setLugarActual(getLugarActual());
        a.setLugarOrigen(getLugarOrigen());
        a.setPuntuacion(getPuntuacion());
        return a;
    }

    public ArtistaBean getBeanCompleto() {
        ArtistaBean a = new ArtistaBean();
        a.setId(getId());
        a.setMail(getMail());
        a.setNombre(getNombre());
        a.setTelefono(getTelefono());
        a.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        a.setTipoShow((getTipoShow() == null) ? null : getTipoShow().getBean());
        a.setFoto(getFoto());
        a.setDescripcion(getDescripcion());
        a.setAnioCreacion(getAnioCreacion());
        a.setCantidadIntegrantes(getCantidadIntegrantes());
        a.setLugarActual(getLugarActual());
        a.setLugarOrigen(getLugarOrigen());
        a.setPuntuacion(getPuntuacion());

        if (getTags() != null && !getTags().isEmpty()) {
            List<Tag> tagEntities = getTags();
            List<TagBean> tagBeans = new ArrayList<TagBean>(tagEntities.size());
            for (Tag tag : tagEntities) {
                tagBeans.add(tag.getBean());
            }
            a.setTags(tagBeans);
        }


//        if (getSeguidores() != null && !getSeguidores().isEmpty()) {
//            List<Usuario> tagEntities = getSeguidores();
//            List<UsuarioBean> tagBeans = new ArrayList<UsuarioBean>(tagEntities.size());
//            for (Usuario tag : tagEntities) {
//                tagBeans.add(tag.getBean());
//            }
//            a.setSeguidores(tagBeans);
//        }



        return a;
    }


//    public List<Usuario> getSeguidores() {
//        return seguidores;
//    }
//
//    public void setSeguidores(List<Usuario> seguidores) {
//        this.seguidores = seguidores;
//    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoShow getTipoShow() {
        return tipoShow;
    }

    public void setTipoShow(TipoShow tipoShow) {
        this.tipoShow = tipoShow;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(String anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public String getCantidadIntegrantes() {
        return cantidadIntegrantes;
    }

    public void setCantidadIntegrantes(String cantidadIntegrantes) {
        this.cantidadIntegrantes = cantidadIntegrantes;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
    }

    public String getLugarActual() {
        return lugarActual;
    }

    public void setLugarActual(String lugarActual) {
        this.lugarActual = lugarActual;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
