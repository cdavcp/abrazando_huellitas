package sigep.beans;

import java.util.List;

public class LugarBean {

    private Long id;
    private String nombre;
    private UsuarioBean usuario;
    private List<TipoShowBean> tipoShows;
    private String foto;
    private String descripcion;
    private String anioCreacion;
    private String ubicacion;
    private String mail;
    private String telefono;
    private List<TagBean> tags;
    private String puntuacion;

    public List<TipoShowBean> getTipoShows() {
        return tipoShows;
    }

    public void setTipoShows(List<TipoShowBean> tipoShows) {
        this.tipoShows = tipoShows;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

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

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public List<TipoShowBean> getTipoShow() {
        return tipoShows;
    }

    public void setTipoShow(List<TipoShowBean> tipoShow) {
        this.tipoShows = tipoShow;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public List<TagBean> getTags() {
        return tags;
    }

    public void setTags(List<TagBean> tags) {
        this.tags = tags;
    }
}