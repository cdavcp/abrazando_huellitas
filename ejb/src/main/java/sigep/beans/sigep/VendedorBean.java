package sigep.beans.sigep;

import sigep.beans.TipoDocumentoBean;

import java.util.Date;

public class VendedorBean{

    private Long id;
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String domicilio;
    private String mail;
    private TipoDocumentoBean tipoDocumento;
    private Date fechaAlta;
    private Date fechaBaja;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean equals(Object vendedor)
    {
        VendedorBean v = (VendedorBean) vendedor;
        return this.getId().equals(v.getId());
    }
}
