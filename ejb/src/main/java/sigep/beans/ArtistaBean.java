package sigep.beans;

import java.util.List;

public class ArtistaBean {

    private Long id;
    private String nombre;
    private UsuarioBean usuario;
    private TipoShowBean tipoShow;
    private String foto;
    private String descripcion;
    private String anioCreacion;
    private String cantidadIntegrantes;
    private String lugarOrigen;
    private String lugarActual;
    private String mail;
    private String telefono;
    private List<TagBean> tags;
//    private List<UsuarioBean> seguidores;
    private String puntuacion;

//    public List<UsuarioBean> getSeguidores() {
//        return seguidores;
//    }
//
//    public void setSeguidores(List<UsuarioBean> seguidores) {
//        this.seguidores = seguidores;
//    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<TagBean> getTags() {
        return tags;
    }

    public void setTags(List<TagBean> tags) {
        this.tags = tags;
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

    public TipoShowBean getTipoShow() {
        return tipoShow;
    }

    public void setTipoShow(TipoShowBean tipoShow) {
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
