package sigep.rest.sigep;

import sigep.beans.sigep.CoberturaBean;
import sigep.beans.sigep.PolizaBean;
import sigep.data.dto.poliza.PolizaDTO;
import sigep.data.dto.poliza.PolizaFilterDTO;
import sigep.data.dto.poliza.PolizaGrillaDTO;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Path("/poliza")
@RequestScoped
public class PolizaREST implements Serializable{

    @Inject
    private PolizaService service;
    @Inject
    private AseguradoraService aseguradoraService;
    @Inject
    private ClienteService clienteService;
    @Inject
    private OrigenPolizaService origenPolizaService;
    @Inject
    private RubroService rubroService;
    @Inject
    private VendedorService vendedorService;
    @Inject
    private CoberturaService coberturaService;
    @Inject
    private ProductorService productorService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PolizaBean poliza){
        try{
            service.create(poliza);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/init")
    @Produces(MediaType.APPLICATION_JSON)
    public PolizaDTO inicializar(String idUsuarioLogueado) {
        PolizaDTO respuesta = new PolizaDTO();
        PolizaBean poliza = new PolizaBean();
        poliza.setProductor(productorService.findByUsuario(new Long(idUsuarioLogueado)));
        respuesta.setPoliza(poliza);
        respuesta.setListaAseguradoras(aseguradoraService.findAll());
        respuesta.setListaClientes(clienteService.findAllComboBeans());
        respuesta.setListaOrigenesPoliza(origenPolizaService.findAll());
        respuesta.setListaRubros(rubroService.findAll());
        respuesta.setListaVendedores(vendedorService.findVendedoresDeProductor(productorService.findByUsuario(new Long(idUsuarioLogueado)).getId()));
        return respuesta;
    }

    @POST
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    public PolizaBean view(String idPoliza) {
        return service.findById(new Long(idPoliza));
    }

    @POST
    @Path("/findCoberturas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CoberturaBean> findCoberturas(PolizaDTO param) {
        return coberturaService.findByAseguradoraRubro(param.getIdAseguradora(), param.getIdRubro());
    }

    @POST
    @Path("/checkNumeroPoliza")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkNombreUsuario(PolizaDTO param) {
        return service.validarNumeroPoliza(param.getPoliza().getNumero(), param.getIdAseguradora());
    }

    @POST
    @Path("/consultar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PolizaGrillaDTO> consultar(PolizaFilterDTO filtros) {
        return service.findByFilters(filtros);
    }

    @POST
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(String idPoliza) {
        try{
            service.delete(new Long(idPoliza));
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
