package sigep.data.dto.estadisticasProductor;

import sigep.model.Estadisticas.Item;
import sigep.model.Estadisticas.ItemNomCantMes;

import java.util.Date;
import java.util.List;

/**
 * Created by Asus PC on 29/9/2015.
 */
public class EstadisticaDTO {

    private List<Filtro> filtros;
    private List<Item> items;
    private List<Mes> meses;
    private String idUsuario;
    private String idFiltro;
    private Date fechaDesde;
    private Date fechaHasta;
    private String anio;
    private String mes;

    public List<Filtro> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(String idFiltro) {
        this.idFiltro = idFiltro;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
