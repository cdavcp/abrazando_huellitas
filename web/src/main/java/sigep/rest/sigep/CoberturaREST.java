package sigep.rest.sigep;

import sigep.beans.sigep.CoberturaBean;
import sigep.beans.sigep.RiesgoBean;
//import sigep.data.dto.sigep.CoberturaDTO;
import sigep.data.dto.CoberturaDTO;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.CoberturaService;
import sigep.service.sigep.RiesgoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cobertura")
@RequestScoped
public class CoberturaREST {

    @Inject
    private CoberturaService service;
    @Inject
    private RiesgoService riesgoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CoberturaBean> inicializarIndex() {
        return service.findAllPlano();
    }

//    @GET
//    @Path("/inicializarCreate")
//    @Produces(MediaType.APPLICATION_JSON)
//    public CoberturaDTO inicializarCreate() {
//        return service.inicializar();
//    }
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CoberturaBean cobertura){
        try{
            service.create(cobertura);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            return Response.serverError().build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(CoberturaBean cobertura){
        try{
            service.update(cobertura);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/buscarRiesgos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RiesgoBean> buscarRiesgos(String rubroId) {
        Long id = new Long(rubroId);
        return riesgoService.findAllByRubro(id);
    }

    @POST
    @Path("/checkNombreRiesgo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkNombreRiesgo(RiesgoBean riesgo) {
        return riesgoService.esValidoByNombreYRubro(riesgo.getNombre(), riesgo.getRubro().getId());
    }

    @POST
    @Path("/checkNombreCobertura")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkNombreCobertura(CoberturaBean cobertura) {
        return service.esNombreValido(cobertura);
    }

    @POST
    @Path("/checkNombreCoberturaEdit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkNombreCoberturaEdit(CoberturaBean cobertura) {
        return service.esNombreValidoEdit(cobertura);
    }


    @POST
    @Path("/createRiesgo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RiesgoBean createRiesgo(RiesgoBean riesgo) {
        return riesgoService.create(riesgo);
    }

}
