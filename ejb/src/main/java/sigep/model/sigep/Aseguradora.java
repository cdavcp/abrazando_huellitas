package sigep.model.sigep;

import sigep.beans.sigep.AseguradoraBean;
import sigep.beans.sigep.CoberturaBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aseguradora extends EntityBase {

    @Size( max = 15)
    @NotNull(message = "Ingrese un CUIL")
    private String cuil;

    @Size( max = 50)
    @NotNull(message = "Ingrese un nombre")
    private String nombre;

    @Size( max = 50)
    @NotNull(message = "Ingrese una razón social")
    private String razonSocial;

    @Size( max = 25)
    private String telefono;

    @Size( max = 25)
    private String fax;

    @Size( max = 100)
    private String domicilio;

    @Size( max = 100)
    @NotNull(message = "Ingrese una direccion de mail")
    private String mail;

    @OneToOne
    private ParametroImportacion parametroImportacion;

    @OneToMany(mappedBy = "aseguradora")
    private List<Cobertura> coberturas;

    public Aseguradora() {
    }

    public Aseguradora(AseguradoraBean bean){
        setId(bean.getId());
        setNombre(bean.getNombre());
        setCuil(bean.getCuil());
        setDomicilio(bean.getDomicilio());
        setFax(bean.getFax());
        setMail(bean.getMail());
        setRazonSocial(bean.getRazonSocial());
        setTelefono(bean.getTelefono());

        setParametroImportacion(bean.getParametroImportacion() != null ?  new ParametroImportacion(bean.getParametroImportacion()) : null);

        if(bean.getCoberturas() != null && !bean.getCoberturas().isEmpty()){
            List<CoberturaBean> coberturasBean = bean.getCoberturas();
            List<Cobertura> coberturas = new ArrayList<Cobertura>(coberturasBean.size());
            for(CoberturaBean cb : coberturasBean){
                coberturas.add(new Cobertura(cb));
            }
            setCoberturas(coberturas);
        }
    }

    public AseguradoraBean getBean(){
        AseguradoraBean bean = new AseguradoraBean();
        bean.setId(getId());
        bean.setNombre(getNombre());
        bean.setCuil(getCuil());
        bean.setDomicilio(getDomicilio());
        bean.setFax(getFax());
        bean.setRazonSocial(getRazonSocial());
        bean.setTelefono(getTelefono());
        bean.setMail(getMail());

        bean.setParametroImportacion(getParametroImportacion() != null ? getParametroImportacion().getBean() : null);
        return bean;
    }

    public AseguradoraBean getBeanCompleto(){
        AseguradoraBean bean = getBean();

        if(getCoberturas() != null && !getCoberturas().isEmpty()){
            List<Cobertura> coberturas = getCoberturas();
            List<CoberturaBean> coberturaBeans = new ArrayList<CoberturaBean>(coberturas.size());
            for(Cobertura c : coberturas){
                coberturaBeans.add(c.getBeanCompleto());
            }
            bean.setCoberturas(coberturaBeans);
        }
        return bean;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public ParametroImportacion getParametroImportacion() {
        return parametroImportacion;
    }

    public void setParametroImportacion(ParametroImportacion parametroImportacion) {
        this.parametroImportacion = parametroImportacion;
    }

    public List<Cobertura> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<Cobertura> coberturas) {
        this.coberturas = coberturas;
    }
}
