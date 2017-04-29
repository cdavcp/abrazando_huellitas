package sigep.data.dto;


import sigep.beans.sigep.ProductorBean;
import sigep.beans.sigep.SolicitudBeanOld;

import java.util.List;

public class BandejaAsignacionDTO {

    private List<SolicitudBeanOld> listaSolicitudes;
    private List<ProductorBean> listaProductores;

    public List<SolicitudBeanOld> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<SolicitudBeanOld> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public List<ProductorBean> getListaProductores() {
        return listaProductores;
    }

    public void setListaProductores(List<ProductorBean> listaProductores) {
        this.listaProductores = listaProductores;
    }
}
