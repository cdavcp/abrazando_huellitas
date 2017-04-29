package sigep.rest;

import sigep.beans.SeguidoresXArtistaBean;
import sigep.beans.UsuarioBean;
import sigep.beans.VotacionArtistaBean;
import sigep.exceptions.SIGEPException;
import sigep.service.SeguidoresXArtistaService;
import sigep.service.VotacionArtistaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;


@Path("/votacionArtista")
@RequestScoped
public class VotacionArtistaRest implements Serializable {
    @Inject
    private VotacionArtistaService service;



    @POST
    @Path("/consultarVotacionArtistaInteresado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VotacionArtistaBean consultarVotacionArtistaInteresado(VotacionArtistaBean votacion){

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
    public VotacionArtistaBean getVotacion(Long idUsuario){
        VotacionArtistaBean votacion = service.findById(idUsuario);
        return votacion;
    }


    @POST
    @Path("/updateVotacionArtistaInteresado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVotacionArtistaInteresado(VotacionArtistaBean votacion){

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
    public Response create(VotacionArtistaBean bean) {
        try{
            service.create(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }




}
