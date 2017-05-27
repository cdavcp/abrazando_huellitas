package sigep.rest.Ivent;

import sigep.beans.Ivent.TipoDocumentoBean;
import sigep.beans.sigep.VendedorBean;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.TipoDocumentoService;
import sigep.service.sigep.VendedorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vendedor")
@RequestScoped
public class VendedorREST {

    @Inject
    private VendedorService service;
    @Inject
    private TipoDocumentoService tipoDocumentoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VendedorBean> inicializarIndex() {
        return service.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(VendedorBean vendedor){
        try{
            service.create(vendedor);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(VendedorBean vendedor){
        try{
            service.update(vendedor);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(VendedorBean vendedor){
        try{
            service.delete(vendedor);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoDocumentoBean> inicializarCreate() {
        return tipoDocumentoService.findAll();
    }

}
