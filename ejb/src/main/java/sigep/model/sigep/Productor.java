package sigep.model.sigep;

import sigep.beans.sigep.ProductorBean;
import sigep.beans.sigep.VendedorBean;
import sigep.model.EntityBase;
import sigep.model.TipoDocumento;
import sigep.model.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Productor extends EntityBase {
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

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(joinColumns={@JoinColumn(name="productor_id")}, inverseJoinColumns={@JoinColumn(name="vendedor_id")})
    private List<Vendedor> vendedores;

    @OneToOne
    private Usuario usuario;


    public Productor() {
    }

    public Productor(ProductorBean b) {
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
        usuario = (b.getUsuario() == null) ? null : new Usuario(b.getUsuario());

        if(b.getVendedores() != null && !b.getVendedores().isEmpty()){
            ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>(b.getVendedores().size());
            for(VendedorBean vendedorBean : b.getVendedores()){
                vendedores.add(new Vendedor(vendedorBean));
            }
            setVendedores(vendedores);
        }
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

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ProductorBean getBeanPlano(){
        ProductorBean b = new ProductorBean();
        b.setId(getId());
        b.setApellido(getApellido());
        b.setDocumento(getDocumento());
        b.setDomicilio(getDomicilio());
        b.setMail(getMail());
        b.setNombre(getNombre());
        b.setTelefono(getTelefono());
        b.setFechaAlta(getFechaAlta());
        b.setFechaBaja(getFechaBaja());
        b.setTipoDocumento((getTipoDocumento() == null) ? null : getTipoDocumento().getBean());
        b.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        return b;
    }

    public ProductorBean getBeanCompleto()
    {
        ProductorBean b = getBeanPlano();

        if(getVendedores() != null && !getVendedores().isEmpty()){
            List<Vendedor> vendedoresEntities = getVendedores();
            List<VendedorBean> vendedoresBeans = new ArrayList<VendedorBean>(vendedoresEntities.size());
            for(Vendedor vendedor : vendedoresEntities){
                vendedoresBeans.add(vendedor.getBean());
            }
            b.setVendedores(vendedoresBeans);
        }
        return b;
    }
}
