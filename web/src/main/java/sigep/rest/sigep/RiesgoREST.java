package sigep.rest.sigep;

import sigep.beans.sigep.CoberturaBean;
import sigep.beans.sigep.RiesgoBean;
import sigep.beans.sigep.RubroBean;
//import sigep.data.dto.sigep.RiesgoDTO;
import sigep.data.dto.RiesgoDTO;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.CoberturaService;
import sigep.service.sigep.RiesgoService;
import sigep.service.sigep.RubroService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/riesgo")
@RequestScoped
public class RiesgoREST {

    @Inject
    private RiesgoService service;
    @Inject
    private CoberturaService coberturaService;
    @Inject
    private RubroService rubroService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RiesgoBean> inicializarIndex() {
        return service.findAll();
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(RiesgoBean cliente){
        try{
            service.delete(cliente);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/buscarCoberturasDelRiesgo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CoberturaBean> buscarCoberturasDelRiesgo(String riesgoId) {
        Long id = new Long(riesgoId);
        return coberturaService.findAllIncluyenRiesgo(id);
    }

    @POST
    @Path("/buscarCoberturasDelRubro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CoberturaBean> buscarCoberturasDelRubro(String rubroId) {
        Long id = new Long(rubroId);
        return coberturaService.findAllByRubro(id);
    }

    @GET
    @Path("/inicializarCreate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RubroBean> inicializarCreate() {
        return rubroService.findAll();
    }
//
//    @POST
//    @Path("/inicializarEdit")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public CoberturaDTO inicializarEdit(String coberturaId)
//    {
//        Long id = new Long(coberturaId);
//        return service.inicializarEdit(id);
//    }
//
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(RiesgoDTO riesgo){
        // service.create(riesgo);
            return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(RiesgoBean riesgo){
        try{
            service.update(riesgo);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
//
//    @POST
//    @Path("/buscarRiesgos")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<RiesgoBean> buscarRiesgos(String rubroId) {
//        Long id = new Long(rubroId);
//        return riesgoService.findAllByRubro(id);
//    }
//
    @POST
    @Path("/checkNombreRiesgo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkNombreRiesgo(RiesgoBean riesgo) {
        return service.esValidoByNombreYRubro(riesgo.getNombre(), riesgo.getRubro().getId());
    }
//
//    @POST
//    @Path("/createRiesgo")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public RiesgoBean createRiesgo(RiesgoBean riesgo) {
//        return riesgoService.create(riesgo);
//    }

}
