package sigep.beans.sigep;

import sigep.beans.TipoDocumentoBean;
import sigep.beans.UsuarioBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductorBean {
    private Long id;
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String domicilio;
    private String mail;
    private Date fechaAlta;
    private Date fechaBaja;
    private TipoDocumentoBean tipoDocumento;
    private List<VendedorBean> vendedores = new ArrayList<VendedorBean>();
    private UsuarioBean usuario;
    private String foto;

    //FOR ASIGNACION PURPOSES
    private List<SolicitudBeanOld> solicitudes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public TipoDocumentoBean getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoBean tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<VendedorBean> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<VendedorBean> vendedores) {
        this.vendedores = vendedores;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public List<SolicitudBeanOld> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudBeanOld> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
