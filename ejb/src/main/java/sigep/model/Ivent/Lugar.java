package sigep.model.Ivent;

import sigep.beans.Ivent.LugarBean;
import sigep.beans.Ivent.TagBean;
import sigep.beans.Ivent.TipoShowBean;
import sigep.model.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lugar extends EntityBase {

    @Size(max = 50)
    @NotNull
    private String nombre;

    @OneToOne
    private Usuario usuario;

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "lugar_id")}, inverseJoinColumns = {@JoinColumn(name = "tipoShow_id")})
    private List<TipoShow> tipoShows;

    @Size(max = 200)
    private String foto;

    @Size(max = 500)
    @NotNull
    private String descripcion;

    @Size(max = 4)
    @NotNull
    private String anioCreacion;

    @Size(max = 100)
    @NotNull
    private String ubicacion;

    @Size(max = 100)
    @NotNull
    private String mail;

    @Size(max = 25)
    private String telefono;

    @Size(max = 25)
    private String puntuacion;

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "lugar_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;


    public Lugar() {
    }

    public Lugar(LugarBean l) {
        setId(l.getId());
        this.nombre = l.getNombre();
        this.usuario = (l.getUsuario() == null) ? null : new Usuario(l.getUsuario());
        this.foto = l.getFoto();
        this.descripcion = l.getDescripcion();
        this.anioCreacion = l.getAnioCreacion();
        this.ubicacion = l.getUbicacion();
        this.mail = l.getMail();
        this.telefono = l.getTelefono();
        this.puntuacion = l.getPuntuacion();

        if (l.getTags() != null && !l.getTags().isEmpty()) {
            ArrayList<Tag> tags = new ArrayList<Tag>(l.getTags().size());
            for (TagBean tagBean : l.getTags()) {
                tags.add(new Tag(tagBean));
            }
            setTags(tags);
        }

        if (l.getTipoShow() != null && !l.getTipoShow().isEmpty()) {
            ArrayList<TipoShow> tipoShows = new ArrayList<TipoShow>(l.getTipoShow().size());
            for (TipoShowBean tipoShowBean : l.getTipoShow()) {
                tipoShows.add(new TipoShow(tipoShowBean));
            }
            setTipoShows(tipoShows);
        }
    }

    public LugarBean getBeanPlano() {
        LugarBean l = new LugarBean();
        l.setId(getId());
        l.setMail(getMail());
        l.setNombre(getNombre());
        l.setTelefono(getTelefono());
        l.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        l.setFoto(getFoto());
        l.setDescripcion(getDescripcion());
        l.setAnioCreacion(getAnioCreacion());
        l.setUbicacion(getUbicacion());
        return l;
    }

    public LugarBean getBeanCompleto() {
        LugarBean l = new LugarBean();
        l.setId(getId());
        l.setMail(getMail());
        l.setNombre(getNombre());
        l.setTelefono(getTelefono());
        l.setPuntuacion(getPuntuacion());
        l.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        l.setFoto(getFoto());
        l.setDescripcion(getDescripcion());
        l.setAnioCreacion(getAnioCreacion());
        l.setUbicacion(getUbicacion());

        if (getTags() != null && !getTags().isEmpty()) {
            List<Tag> tagEntities = getTags();
            List<TagBean> tagBeans = new ArrayList<TagBean>(tagEntities.size());
            for (Tag tag : tagEntities) {
                tagBeans.add(tag.getBean());
            }
            l.setTags(tagBeans);
        }

        if (getTipoShows() != null && !getTipoShows().isEmpty()) {
            List<TipoShow> tipoShowEntities = getTipoShows();

            List<TipoShowBean> tipoShowBeans = new ArrayList<TipoShowBean>(tipoShowEntities.size());
            for (TipoShow tipoShow : tipoShowEntities) {
                tipoShowBeans.add(tipoShow.getBean());
            }
            l.setTipoShow(tipoShowBeans);
        }
        return l;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(String anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<TipoShow> getTipoShows() {
        return tipoShows;
    }

    public void setTipoShows(List<TipoShow> tipoShows) {
        this.tipoShows = tipoShows;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}






