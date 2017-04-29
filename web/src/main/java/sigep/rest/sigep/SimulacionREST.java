package sigep.rest.sigep;

import sigep.beans.*;
import sigep.beans.sigep.ClienteBean;
import sigep.beans.sigep.RubroBean;
import sigep.beans.sigep.SolicitudBeanOld;
import sigep.beans.sigep.TipoCoberturaBean;
//import sigep.data.dto.sigep.ScreenScrapingDTO;
//import sigep.data.dto.sigep.SimulacionDTO;
import sigep.data.dto.ScreenScrapingDTO;
import sigep.data.dto.SimulacionDTO;
import sigep.service.sigep.*;
import sigep.util.ScreenScrapingUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/simulacion")
@RequestScoped
public class SimulacionREST {

    @Inject
    private RubroService rubroService;
    @Inject
    private TipoCoberturaService tipoCoberturaService;
    @Inject
    private CoberturaService coberturaService;
    @Inject
    TipoDocumentoService tipoDocumentoService;
    @Inject
    ClienteService clienteService;
    @Inject
    SolicitudServiceOld solicitudServiceOld;
    @Inject
    SimulacionService simulacionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RubroBean> inicializarIndex() {
        return rubroService.findAll();
    }

    @GET
    @Path("/buscarTiposCobertura")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoCoberturaBean> buscarTiposCobertura() {
        return tipoCoberturaService.findAllConLogo();
    }

    @GET
    @Path("/getTiposDocumento")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoDocumentoBean> getTiposDocumento() {
        return tipoDocumentoService.findAll();
    }

    @POST
    @Path("/searchCoberturas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SimulacionDTO searchCoberturas(SimulacionDTO dto){
        return simulacionService.simular(dto.getRubroId(), dto.getTipoCoberturaId(), dto.getValorAuto());
    }

    @POST
    @Path("/ingresar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ClienteBean ingresar(ClienteBean cliente){
        return clienteService.findByDocumentoYTipoDocumento(cliente.getDocumento(), cliente.getTipoDocumento().getId());
    }

    @POST
    @Path("/registrarNuevaCuenta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ClienteBean registrarNuevaCuenta(ClienteBean cliente){
        return clienteService.createIfNotExists(cliente);
    }

    @POST
    @Path("/enviarSolicitud")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enviarSolicitud(SolicitudBeanOld solicitud){
        solicitudServiceOld.create(solicitud);
        return Response.ok().build();
    }

    @GET
    @Path("/searchMarcasAuto")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SimulacionDTO> searchMarcasAuto() {
        return ScreenScrapingUtil.findAllMarcasAuto();
    }

    @POST
    @Path("/searchModelos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScreenScrapingDTO> searchModelos(String marca) {
        return ScreenScrapingUtil.findModelos(marca);
    }

    @POST
    @Path("/searchAnios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScreenScrapingDTO> searchAnios(SimulacionDTO dto) {
        return ScreenScrapingUtil.findAnios(dto.getMarca(), dto.getModelo());
    }

    @POST
    @Path("/searchVersionesYPrecios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SimulacionDTO> searchVersionesYPrecios(SimulacionDTO dto) {
        return ScreenScrapingUtil.findVersionesYPrecios(dto.getMarca(), dto.getModelo(),dto.getAnio());
    }

//
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public AseguradoraBean update(AseguradoraBean aseguradora){
//        try{
//            return service.updateReturn(aseguradora);
//        }
//        catch(SIGEPException e){
//            Logger log = Logger.getLogger("myLogger");
//            log.log(Level.ALL, "Error actualizando aseguradora.");
//            return null;
//        }
//    }

//    @GET
//    @Path("/create")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<TipoDocumento> inicializarCreate() {
//        return tipoDocumentoService.findAll();
//    }

}
