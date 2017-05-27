package sigep.rest.Ivent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import sigep.beans.Ivent.PersonaBean;
import sigep.exceptions.SIGEPException;
import sigep.service.Ivent.PersonaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.Serializable;
import java.util.List;

@Path("/persona")
@RequestScoped
public class PersonaREST implements Serializable{
    @Inject
    private PersonaService service;

    @POST
    @Path("/register")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(MultipartInput input) throws Exception {
        List<InputPart> inputs = input.getParts();
        String json = inputs.get(0).getBody(String.class, null);
        File file = inputs.size() > 1 ? inputs.get(1).getBody(File.class, null) : null;

        Gson jsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        PersonaBean persona = jsonBuilder.fromJson(json, PersonaBean.class);

        try{
            service.create(persona, file);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


    @POST
    @Path("/datosPersona")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaBean getPersona(Long idUsuario){
        PersonaBean persona = service.findPersonaByUsuario(idUsuario);
        return persona;
    }

//    @GET
//    @Path("/create")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<TipoUsuarioBean> inicializarCreate() {
//        return tipoUsuarioService.findAll();
//    }
//
//    @POST
//    @Path("/checkNombreUsuairo")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public boolean checkNombreUsuario(String nombreUsuario) {
//        return service.validarNombreUsuario(nombreUsuario);
//    }
//
//    @POST
//    @Path("/changePass")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public boolean changePassword(ChangePassDTO contrasenias) {
//        return service.changePassword(contrasenias.getNewPass(), contrasenias.getPreviousPass(), contrasenias.getUsuarioId());
//    }
}
