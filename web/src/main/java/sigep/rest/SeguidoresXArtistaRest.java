package sigep.rest;

import sigep.beans.ArtistaBean;
import sigep.beans.SeguidoresXArtistaBean;
import sigep.beans.UsuarioBean;
import sigep.exceptions.SIGEPException;
import sigep.model.SeguidoresXArtista;
import sigep.model.Usuario;
import sigep.service.SeguidoresXArtistaService;

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


@Path("/seguidoresArtista")
@RequestScoped
public class SeguidoresXArtistaRest implements Serializable {
    @Inject
    private SeguidoresXArtistaService service;


    @POST
    @Path("/consultarSeguidor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioBean getArtista(SeguidoresXArtistaBean seguidorXArtista){

        try{
            return service.findBySeguidor(seguidorXArtista);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }


    @POST
    @Path("/borrarSeguidor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSeguidor(UsuarioBean seguidorXArtista){

        try{
             service.deleteBySeguidor(seguidorXArtista);
            return Response.ok().build();

        }
        catch(Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }

    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(SeguidoresXArtistaBean bean) {
        try{
            service.create(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }



}
