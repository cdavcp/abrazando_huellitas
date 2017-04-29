package sigep.data.dto;

import sigep.beans.sigep.ProductorBean;
import sigep.beans.TipoDocumentoBean;
import sigep.beans.sigep.VendedorBean;

import java.util.List;

public class ProductorDTO {
    private List<TipoDocumentoBean> listaTiposDocumento;
    private List<VendedorBean> listaVendedores;
    private ProductorBean productor;
    private String idProductorStr;
    private String idUsuarioStr;

    public List<TipoDocumentoBean> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<TipoDocumentoBean> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public List<VendedorBean> getListaVendedores() {
        return listaVendedores;
    }

    public void setListaVendedores(List<VendedorBean> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }

    public ProductorBean getProductor() {
        return productor;
    }

    public void setProductor(ProductorBean productor) {
        this.productor = productor;
    }

    public String getIdProductorStr() {
        return idProductorStr;
    }

    public void setIdProductorStr(String idProductorStr) {
        this.idProductorStr = idProductorStr;
    }

    public String getIdUsuarioStr() {
        return idUsuarioStr;
    }

    public void setIdUsuarioStr(String idUsuarioStr) {
        this.idUsuarioStr = idUsuarioStr;
    }
}
