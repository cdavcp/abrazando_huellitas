package sigep.beans.sigep;

import java.util.List;

public class AseguradoraBean {
    private Long id;
    private String cuil;
    private String nombre;
    private String razonSocial;
    private String telefono;
    private String fax;
    private String domicilio;
    private String mail;
    private ParametroImportacionBean parametroImportacion;
    private List<CoberturaBean> coberturas;
    private String logo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ParametroImportacionBean getParametroImportacion() {
        return parametroImportacion;
    }

    public void setParametroImportacion(ParametroImportacionBean parametroImportacion) {
        this.parametroImportacion = parametroImportacion;
    }

    public List<CoberturaBean> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<CoberturaBean> coberturas) {
        this.coberturas = coberturas;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
