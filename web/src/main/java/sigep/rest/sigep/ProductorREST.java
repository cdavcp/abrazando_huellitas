package sigep.rest.sigep;

import com.google.gson.*;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import sigep.beans.sigep.ProductorBean;
//import sigep.data.dto.sigep.ProductorDTO;
import sigep.data.dto.ProductorDTO;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.ProductorService;
import sigep.service.sigep.TipoDocumentoService;
import sigep.service.sigep.VendedorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.Serializable;
import java.util.List;

@Path("/productor")
@RequestScoped

public class ProductorREST implements Serializable{

    @Inject
    private ProductorService service;
    @Inject
    private TipoDocumentoService tipoDocumentoService;
    @Inject
    private VendedorService vendedorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductorBean> inicializarIndex() {
        return service.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProductorBean productor){
        try{
            service.create(productor);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

//    @PUT
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response update(ProductorBean productor){
//        try{
//            service.update(productor);
//            return Response.ok().build();
//        }
//        catch(SIGEPException e){
//            e.printStackTrace();
//            return Response.serverError().build();
//        }
//    }

    @POST
    @Path("/update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(MultipartInput input) throws Exception{
        List<InputPart> inputs = input.getParts();
        String json = inputs.get(0).getBody(String.class, null);
        File file = inputs.size() > 1 ? inputs.get(1).getBody(File.class, null) : null;

        Gson jsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        ProductorBean productor = jsonBuilder.fromJson(json, ProductorBean.class);
        try{
            service.update(productor, file);
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
    public ProductorDTO inicializarCreate() {
        ProductorDTO respuesta = new ProductorDTO();
        respuesta.setListaTiposDocumento(tipoDocumentoService.findAll());
        respuesta.setListaVendedores(vendedorService.findAll());
        return respuesta;
    }

    @POST
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductorDTO inicializarEdit(ProductorDTO productorDTO) {
        String idProductorStr = productorDTO.getIdProductorStr();
        String idUsuarioStr = productorDTO.getIdUsuarioStr();
        ProductorDTO respuesta = new ProductorDTO();
        respuesta.setProductor((idProductorStr != "") ? service.findByIdConFoto(new Long(idProductorStr)) : service.findByUsuarioConFoto(new Long(idUsuarioStr)));
        respuesta.setListaTiposDocumento(tipoDocumentoService.findAll());
        respuesta.setListaVendedores(vendedorService.findPotencialesVendedores(respuesta.getProductor().getVendedores()));
        return respuesta;
    }

    @POST
    @Path("/view")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductorDTO inicializarView(String idProductorStr, String idUsuarioStr) {
        ProductorDTO respuesta = new ProductorDTO();
        respuesta.setProductor(service.findById(new Long(idProductorStr)));
        respuesta.setListaTiposDocumento(tipoDocumentoService.findAll());
        return respuesta;
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(ProductorBean productor){
        try{
            service.delete(productor);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
