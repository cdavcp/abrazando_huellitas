package sigep.rest.sigep;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import sigep.beans.sigep.LoteBean;
import sigep.data.dto.ComboDTO;
//import sigep.data.dto.sigep.ImportacionDTO;
import sigep.data.dto.ImportacionDTO;
import sigep.data.dto.poliza.PolizaFilterDTO;
import sigep.exceptions.SIGEPException;
import sigep.service.sigep.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Path("/comision")
@RequestScoped
public class ComisionREST implements Serializable{
    @Inject
    private ComisionService service;
    @Inject
    private ProductorService productorService;
    @Inject
    private AseguradoraService aseguradoraService;
    @Inject
    private LoteService loteService;
    @Inject
    private PolizaService polizaService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response procesarLoteArchivo(MultipartInput input) {
        List<InputPart> inputs = input.getParts();
        try {
            String json = inputs.get(0).getBody(String.class, null);
            File file = inputs.get(1).getBody(File.class, null);

            Gson jsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
            LoteBean lote = jsonBuilder.fromJson(json, LoteBean.class);

            Long idLote = service.procesarLoteArchivo(lote, file);
            return Response.ok(idLote).build();

        } catch (IOException e) {
            System.out.println("Error al recibir contenido Multiparte.");
            e.printStackTrace();
            return Response.serverError().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/procesarManual")
    @Produces(MediaType.APPLICATION_JSON)
    public Response procesarLoteManual(ImportacionDTO data) {
        try{
            if(service.procesarLoteManual(data.getLote(), data.getListaNuevasComisiones())){
                return Response.ok(true).build();
            } else {
                return Response.ok(false).build();
            }
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/procesarActualizacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response procesarActualizacion(ImportacionDTO data) {
        try{
            service.procesarActualizacion(data.getLote(), data.getListaNuevasComisiones(), data.getListaComisionesBorradas());
            return Response.ok().build();
        }
        catch(SIGEPException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/initArchivo")
    @Produces(MediaType.APPLICATION_JSON)
    public ImportacionDTO inicializarArchivo(String idUsuarioLogueado) {
        ImportacionDTO respuesta = new ImportacionDTO();
        respuesta.setProductor(productorService.findByUsuario(new Long(idUsuarioLogueado)));
        respuesta.setListaAseguradoras(aseguradoraService.findAll());
        return respuesta;
    }

    @POST
    @Path("/initManual")
    @Produces(MediaType.APPLICATION_JSON)
    public ImportacionDTO inicializarManual(String idUsuarioLogueado) {
        ImportacionDTO respuesta = new ImportacionDTO();
        respuesta.setProductor(productorService.findByUsuario(new Long(idUsuarioLogueado)));
        respuesta.setListaAseguradoras(aseguradoraService.findAll());
        return respuesta;
    }

    @POST
    @Path("/initIndex")
    @Produces(MediaType.APPLICATION_JSON)
    public ImportacionDTO inicializarIndex(String idUsuarioLogueado) {
        ImportacionDTO respuesta = new ImportacionDTO();
        Long idProductor = productorService.findByUsuario(new Long(idUsuarioLogueado)).getId();
        respuesta.setListaLotes(loteService.findByProductor(idProductor));
        return respuesta;
    }

    @POST
    @Path("/initEdit")
    @Produces(MediaType.APPLICATION_JSON)
    public ImportacionDTO inicializarEdit(String idLote) {
        ImportacionDTO respuesta = new ImportacionDTO();
        respuesta.setLote(loteService.findByIdConIncompletas(new Long(idLote)));
        respuesta.setListaPolizas(polizaService.findByProductorYAseguradora(respuesta.getLote().getProductor().getId(), respuesta.getLote().getAseguradora().getId()));
        return respuesta;
    }

    @POST
    @Path("/initView")
    @Produces(MediaType.APPLICATION_JSON)
    public LoteBean inicializarView(String idLote) {
        return loteService.findCompletoById(new Long(idLote));
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(String idLote) {
        try{
            loteService.delete(new Long(idLote));
            return Response.ok().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/findPolizas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComboDTO> findPolizas(PolizaFilterDTO filter) {
       return polizaService.findByProductorYAseguradora(filter.getProductorId(), filter.getAseguradoraId());
    }
}
