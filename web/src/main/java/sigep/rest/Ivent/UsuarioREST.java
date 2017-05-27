package sigep.rest.Ivent;

import sigep.beans.Ivent.TipoUsuarioBean;
import sigep.beans.Ivent.UsuarioBean;
import sigep.data.dto.ChangePassDTO;
import sigep.exceptions.SIGEPException;
import sigep.service.Ivent.TipoUsuarioService;
import sigep.service.Ivent.UsuarioService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuario")
@RequestScoped
public class UsuarioREST {
    @Inject
    private UsuarioService service;
    @Inject
    private TipoUsuarioService tipoUsuarioService;

    @POST
    @Path("/checkSession")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioBean checkLogin(@FormParam("session") String session){
        UsuarioBean usuario = service.findBySession(session);
        return usuario;
    }







    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioBean login(UsuarioBean user) throws SIGEPException {
        return service.login(user.getNombre(), user.getPass());
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(UsuarioBean user) throws SIGEPException {
        service.logout(user);
        return Response.ok().build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioBean register(UsuarioBean user) throws SIGEPException {
        UsuarioBean usuario = service.create(user);
        return usuario;
    }


    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(UsuarioBean user) throws SIGEPException {
        try{
            service.update(user);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUsuarioBean> inicializarCreate() {
        return tipoUsuarioService.findAll();
    }

    @POST
    @Path("/checkNombreUsuairo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkNombreUsuario(String nombreUsuario) {
        return service.validarNombreUsuario(nombreUsuario);
    }

    @POST
    @Path("/changePass")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changePassword(ChangePassDTO contrasenias) {
        return service.changePassword(contrasenias.getNewPass(), contrasenias.getPreviousPass(), contrasenias.getUsuarioId());
    }
}
