package sigep.data.dto.poliza;

import sigep.beans.sigep.*;
import sigep.data.dto.ComboDTO;
import sigep.service.sigep.PolizaService;

import java.util.List;

public class PolizaDTO {
    private PolizaBean poliza;
    private List<AseguradoraBean> listaAseguradoras;
    private List<RubroBean> listaRubros;
    private List<VendedorBean> listaVendedores;
    private List<ComboDTO> listaClientes;
    private List<OrigenPolizaBean> listaOrigenesPoliza;
    private Long idAseguradora;
    private Long idRubro;

    public PolizaBean getPoliza() {
        return poliza;
    }

    public void setPoliza(PolizaBean poliza) {
        this.poliza = poliza;
    }

    public List<AseguradoraBean> getListaAseguradoras() {
        return listaAseguradoras;
    }

    public void setListaAseguradoras(List<AseguradoraBean> listaAseguradoras) {
        this.listaAseguradoras = listaAseguradoras;
    }

    public List<RubroBean> getListaRubros() {
        return listaRubros;
    }

    public void setListaRubros(List<RubroBean> listaRubros) {
        this.listaRubros = listaRubros;
    }

    public List<VendedorBean> getListaVendedores() {
        return listaVendedores;
    }

    public void setListaVendedores(List<VendedorBean> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }

    public List<ComboDTO> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<ComboDTO> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<OrigenPolizaBean> getListaOrigenesPoliza() {
        return listaOrigenesPoliza;
    }

    public void setListaOrigenesPoliza(List<OrigenPolizaBean> listaOrigenesPoliza) {
        this.listaOrigenesPoliza = listaOrigenesPoliza;
    }

    public Long getIdAseguradora() {
        return idAseguradora;
    }

    public void setIdAseguradora(Long idAseguradora) {
        this.idAseguradora = idAseguradora;
    }

    public Long getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Long idRubro) {
        this.idRubro = idRubro;
    }

    public Integer getIdEstadoNueva() {
        return PolizaService.ID_ESTADO_NUEVA;
    }

    public Integer getIdEstadoAlDia() {
        return PolizaService.ID_ESTADO_AL_DIA;
    }

    public Integer getIdEstadoAtrasada() {
        return PolizaService.ID_ESTADO_ATRASADA;
    }

    public Integer getIdEstadoMorosa() {
        return PolizaService.ID_ESTADO_MOROSA;
    }

    public Integer getIdEstadoDadaBaja() {
        return PolizaService.ID_ESTADO_DADA_BAJA;
    }
}
