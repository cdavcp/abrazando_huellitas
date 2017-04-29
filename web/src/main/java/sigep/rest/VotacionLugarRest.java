package sigep.rest;

import sigep.beans.VotacionLugarBean;
import sigep.exceptions.SIGEPException;
import sigep.service.VotacionLugarService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;


@Path("/votacionLugar")
@RequestScoped
public class VotacionLugarRest implements Serializable {
    @Inject
    private VotacionLugarService service;



    @POST
    @Path("/consultarVotacionLugarInteresado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VotacionLugarBean consultarVotacionLugarInteresado(VotacionLugarBean votacion){

        try{
             return service.findBySeguidor(votacion);

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }


    @POST
    @Path("/datosVotacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VotacionLugarBean getVotacion(Long idUsuario){
        VotacionLugarBean votacion = service.findById(idUsuario);
        return votacion;
    }

    @POST
    @Path("/updateVotacionLugarInteresado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVotacionLugarInteresado(VotacionLugarBean votacion){

        try{
            service.update(votacion);
            return Response.ok().build();
        }
        catch(Exception e){
            return Response.serverError().build();
        }

    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(VotacionLugarBean bean) {
        try{
            service.create(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }




}
