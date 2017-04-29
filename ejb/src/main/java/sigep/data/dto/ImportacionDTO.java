package sigep.data.dto;

import sigep.beans.sigep.AseguradoraBean;
import sigep.beans.sigep.ComisionIncompletaBean;
import sigep.beans.sigep.LoteBean;
import sigep.beans.sigep.ProductorBean;
import sigep.model.sigep.EstadoLote;

import java.util.List;

public class ImportacionDTO {
    private List<AseguradoraBean> listaAseguradoras;
    private ProductorBean productor;
    private List<LoteBean> listaLotes;
    private LoteBean lote;
    private List<ComisionIncompletaBean> listaNuevasComisiones;
    private List<Long> listaComisionesBorradas;
    private List<ComboDTO> listaPolizas;

    public Long getIdEstadoImportado() {
        return EstadoLote.ID_ESTADO_IMPORTADO;
    }

    public Long getIdEstadoRechazado() {
        return EstadoLote.ID_ESTADO_RECHAZADO;
    }

    public Long getIdEstadoProcesado() {
        return EstadoLote.ID_ESTADO_PROCESADO;
    }

    public Long getIdEstadoProcesadoParcial() {
        return EstadoLote.ID_ESTADO_PROCESADO_PARCIAL;
    }

    public Long getIdEstadoAnulado() {
        return EstadoLote.ID_ESTADO_ANULADO;
    }

    public List<AseguradoraBean> getListaAseguradoras() {
        return listaAseguradoras;
    }

    public void setListaAseguradoras(List<AseguradoraBean> listaAseguradoras) {
        this.listaAseguradoras = listaAseguradoras;
    }

    public ProductorBean getProductor() {
        return productor;
    }

    public void setProductor(ProductorBean productor) {
        this.productor = productor;
    }

    public List<LoteBean> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(List<LoteBean> listaLotes) {
        this.listaLotes = listaLotes;
    }

    public LoteBean getLote() {
        return lote;
    }

    public void setLote(LoteBean lote) {
        this.lote = lote;
    }

    public List<ComisionIncompletaBean> getListaNuevasComisiones() {
        return listaNuevasComisiones;
    }

    public void setListaNuevasComisiones(List<ComisionIncompletaBean> listaNuevasComisiones) {
        this.listaNuevasComisiones = listaNuevasComisiones;
    }

    public List<ComboDTO> getListaPolizas() {
        return listaPolizas;
    }

    public void setListaPolizas(List<ComboDTO> listaPolizas) {
        this.listaPolizas = listaPolizas;
    }

    public List<Long> getListaComisionesBorradas() {
        return listaComisionesBorradas;
    }

    public void setListaComisionesBorradas(List<Long> listaComisionesBorradas) {
        this.listaComisionesBorradas = listaComisionesBorradas;
    }
}
