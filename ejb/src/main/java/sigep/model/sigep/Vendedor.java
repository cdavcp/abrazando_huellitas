package sigep.model.sigep;

import sigep.beans.sigep.VendedorBean;
import sigep.model.EntityBase;
import sigep.model.TipoDocumento;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Vendedor extends EntityBase {

    @Size( max = 10)
    @NotNull(message = "Ingrese un documento")
    private String documento;

    @Size( max = 50)
    @NotNull(message = "Ingrese un nombre")
    private String nombre;

    @Size( max = 50)
    @NotNull(message = "Ingrese un apellido")
    private String apellido;

    @Size( max = 25)
    private String telefono;

    @Size( max = 100)
    private String domicilio;

    @Size( max = 100)
    @NotNull(message = "Ingrese una direccion de mail")
    private String mail;

    @OneToOne
    private TipoDocumento tipoDocumento;

    @NotNull(message = "Ingrese una fecha de alta")
    private Date fechaAlta;

    private Date fechaBaja;

    public Vendedor() {
    }

    public Vendedor(VendedorBean b) {
        setId(b.getId());
        documento = b.getDocumento();
        nombre = b.getNombre();
        apellido = b.getApellido();
        telefono = b.getTelefono();
        domicilio = b.getDomicilio();
        mail = b.getMail();
        fechaAlta = b.getFechaAlta();
        fechaBaja = b.getFechaBaja();
        tipoDocumento = (b.getTipoDocumento() == null) ? null : new TipoDocumento(b.getTipoDocumento());
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public VendedorBean getBean(){
        VendedorBean b = new VendedorBean();
        b.setId(getId());
        b.setTipoDocumento((getTipoDocumento() == null)? null : getTipoDocumento().getBean());
        b.setDomicilio(getDomicilio());
        b.setDocumento(getDocumento());
        b.setApellido(getApellido());
        b.setNombre(getNombre());
        b.setMail(getMail());
        b.setTelefono(getTelefono());
        b.setFechaAlta(getFechaAlta());
        b.setFechaBaja(getFechaBaja());
        return b;
    }
}
