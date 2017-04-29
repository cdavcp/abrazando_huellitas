package sigep.rest.sigep;

import com.google.gson.Gson;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import sigep.beans.sigep.AseguradoraBean;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.AseguradoraService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/aseguradora")
@RequestScoped
public class AseguradoraREST {

    @Inject
    private AseguradoraService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AseguradoraBean> inicializarIndex() {
        return service.findAllCompletoConLogos();
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public AseguradoraBean create(AseguradoraBean aseguradora){
//        try{
//            return service.create(aseguradora);
//        }
//        catch(SIGEPException e){
//            Logger log = Logger.getLogger("myLogger");
//            log.log(Level.ALL, "Error creando aseguradora.");
//            return null;
//        }
//    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public AseguradoraBean create(MultipartInput input) throws Exception{
        List<InputPart> inputs = input.getParts();
        String json = inputs.get(0).getBody(String.class, null);
        File file = inputs.size() > 1 ? inputs.get(1).getBody(File.class, null) : null;
        Gson gson = new Gson();
        AseguradoraBean aseguradora = gson.fromJson(json, AseguradoraBean.class);
        return service.create(aseguradora, file);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AseguradoraBean update(AseguradoraBean aseguradora) {
        try {
            return service.updateReturn(aseguradora);
        } catch (SIGEPException e) {
            Logger log = Logger.getLogger("myLogger");
            log.log(Level.ALL, "Error actualizando aseguradora.");
            return null;
        }
    }

//    @GET
//    @Path("/create")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<TipoDocumento> inicializarCreate() {
//        return tipoDocumentoService.findAll();
//    }

}
