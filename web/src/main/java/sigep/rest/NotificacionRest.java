package sigep.rest;

import sigep.beans.NotificacionBean;
import sigep.exceptions.SIGEPException;
import sigep.service.NotificacionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Path("/notificacion")
@RequestScoped
public class NotificacionRest implements Serializable {
    @Inject
    private NotificacionService service;

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(NotificacionBean bean) {
        try{
            service.update(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }

}
