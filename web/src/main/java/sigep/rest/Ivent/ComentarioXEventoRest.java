package sigep.rest.Ivent;

import sigep.beans.Ivent.ComentarioXEventoBean;
import sigep.exceptions.SIGEPException;
import sigep.service.Ivent.ComentarioXEventoService;

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

/**
 * Created by Fabrizio on 31/10/2016.
 */
@Path("/comentario")
@RequestScoped
public class ComentarioXEventoRest implements Serializable {
    @Inject
    private ComentarioXEventoService service;


    @POST
    @Path("/findComentariosByEvento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComentarioXEventoBean> findByIdEvento(Long idEvento){

        try{
            return service.findByIdEvento(idEvento);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ComentarioXEventoBean bean) {
        try{
            service.create(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }


}
