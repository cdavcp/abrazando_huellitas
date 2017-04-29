package sigep.data.dto.sigep;

import sigep.beans.sigep.*;

import java.util.List;

public class CoberturaDTO {
    private List<TipoCoberturaBean> listaTiposCobertura;
    private List<RubroBean> listaRubros;
    private List<AseguradoraBean> listaAseguradoras;
    private CoberturaBean cobertura;
    private List<RiesgoBean> listaRiesgos;


    public List<TipoCoberturaBean> getListaTiposCobertura() {
        return listaTiposCobertura;
    }

    public void setListaTiposCobertura(List<TipoCoberturaBean> listaTiposCobertura) {
        this.listaTiposCobertura = listaTiposCobertura;
    }

    public List<RubroBean> getListaRubros() {
        return listaRubros;
    }

    public void setListaRubros(List<RubroBean> listaRubros) {
        this.listaRubros = listaRubros;
    }

    public List<AseguradoraBean> getListaAseguradoras() {
        return listaAseguradoras;
    }

    public void setListaAseguradoras(List<AseguradoraBean> listaAseguradoras) {
        this.listaAseguradoras = listaAseguradoras;
    }

    public CoberturaBean getCobertura() {
        return cobertura;
    }

    public void setCobertura(CoberturaBean cobertura) {
        this.cobertura = cobertura;
    }

    public List<RiesgoBean> getListaRiesgos() {
        return listaRiesgos;
    }

    public void setListaRiesgos(List<RiesgoBean> listaRiesgos) {
        this.listaRiesgos = listaRiesgos;
    }
}
