package sigep.rest.Ivent;

import sigep.beans.Ivent.SeguidoresXLugarBean;
import sigep.beans.Ivent.UsuarioBean;
import sigep.exceptions.SIGEPException;
import sigep.service.Ivent.SeguidoresXLugarService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;


@Path("/seguidoresLugar")
@RequestScoped
public class SeguidoresXLugarRest implements Serializable {
    @Inject
    private SeguidoresXLugarService service;


    @POST
    @Path("/consultarSeguidor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioBean getLugar(SeguidoresXLugarBean seguidorXLugar){

        try{
            return service.findBySeguidor(seguidorXLugar);
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
    public Response deleteSeguidor(UsuarioBean seguidorXLugar){

        try{
             service.deleteBySeguidor(seguidorXLugar);
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
    public Response create(SeguidoresXLugarBean bean) {
        try{
            service.create(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }



}
