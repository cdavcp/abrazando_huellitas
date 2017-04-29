package sigep.rest.sigep;

import com.google.gson.Gson;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import sigep.beans.sigep.RubroBean;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.RubroService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.List;

@Path("/rubro")
@RequestScoped
public class RubroREST {

    @Inject
    private RubroService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RubroBean> inicializarIndex() throws FileNotFoundException{
        List<RubroBean> rubros = service.findAll();
        return rubros;
    }


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(MultipartInput input) {
        List<InputPart> inputs = input.getParts();
        try {
            String json = inputs.get(0).getBody(String.class, null);
            File file = inputs.size() > 1 ? inputs.get(1).getBody(File.class, null) : null;

            Gson gson = new Gson();
            RubroBean rubro = gson.fromJson(json, RubroBean.class);
            service.create(rubro, file);

        } catch (IOException e) {
            System.out.println("Error mapeando post multidata.");
        } catch (SIGEPException s) {
            System.out.println("Error guardando rubro");
        }
    }

    @POST
    @Path("/checkNombreRubro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkNombreCobertura(String nombre) {
        return service.esNombreValido(nombre);
    }
}
