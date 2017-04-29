package sigep.data.dto;

import sigep.beans.sigep.CoberturaBean;
import sigep.beans.sigep.RiesgoBean;

import java.util.List;

/**
 * Created by Asus PC on 15/07/2015.
 */
public class SimulacionDTO {

    private Long rubroId;
    private Long tipoCoberturaId;
    private String nombre;
    private String value;
    private Integer id;
    private String marca;
    private String modelo;
    private String version;
    private String anio;
    private String precio;
    private String valorAuto;
    private String moneda;

    //SIMULACION EFECTIVA
    private List<RiesgoBean> riesgos;
    private List<CoberturaBean> coberturas;

    public Long getRubroId() {
        return rubroId;
    }

    public void setRubroId(Long rubroId) {
        this.rubroId = rubroId;
    }

    public Long getTipoCoberturaId() {
        return tipoCoberturaId;
    }

    public void setTipoCoberturaId(Long tipoCoberturaId) {
        this.tipoCoberturaId = tipoCoberturaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getValorAuto() {
        return valorAuto;
    }

    public void setValorAuto(String valorAuto) {
        this.valorAuto = valorAuto;
    }

    public List<RiesgoBean> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<RiesgoBean> riesgos) {
        this.riesgos = riesgos;
    }

    public List<CoberturaBean> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<CoberturaBean> coberturas) {
        this.coberturas = coberturas;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
