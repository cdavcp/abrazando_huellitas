package sigep.rest.sigep;

import sigep.beans.sigep.ClienteBean;
import sigep.beans.TipoDocumentoBean;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.ClienteService;
import sigep.service.sigep.TipoDocumentoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cliente")
@RequestScoped
public class ClienteREST {
    @Inject
    private ClienteService service;
    @Inject
    private TipoDocumentoService tipoDocumentoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClienteBean> inicializarIndex() {
        return service.findAll();
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response create(ClienteBean cliente){
//            service.create(cliente);
//            return Response.ok().build();
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ClienteBean bean) {
        service.create(bean);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ClienteBean cliente) {
        try {
            service.update(cliente);
            return Response.ok().build();
        } catch (SIGEPException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(ClienteBean cliente) {
        try {
            service.delete(cliente);
            return Response.ok().build();
        } catch (SIGEPException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/exists")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean exists(ClienteBean cliente) {
        return service.exists(cliente);
    }

    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoDocumentoBean> inicializarCreate() {
        return tipoDocumentoService.findAll();
    }

}
