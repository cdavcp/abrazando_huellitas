package sigep.rest.Ivent;

import sigep.beans.Ivent.ArtistaBean;
import sigep.beans.Ivent.TipoShowBean;
import sigep.exceptions.SIGEPException;
import sigep.service.Ivent.ArtistaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Path("/artista")
@RequestScoped
public class ArtistaRest implements Serializable {
    @Inject
    private ArtistaService service;



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ArtistaBean bean) {
        try{
            service.create(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }

    @POST
    @Path("/datosArtista")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArtistaBean> getArtista(Long idUsuario){
        List<ArtistaBean> artistas= service.findByUsuario(idUsuario);
        return artistas;
    }

    @POST
    @Path("/tagsByTipoShow")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTag(Long idTipoShow){
        try{
            return Response.ok(service.findTagByTipoShow(idTipoShow)).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ArtistaBean bean) {
        try{
            service.update(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }


    @GET
    @Path("/artistaMg")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArtistaBean> artistaMg() {
//        EventoDTO dto = new EventoDTO();
//        dto.build(eventoService.eventosMg());
//        return Response.ok(dto).build();
        List<ArtistaBean> artistas= service.findArtistaMG();
        return artistas;
    }

    @POST
    @Path("/artistaByFilters")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArtistaBean> getLugarByFilters(ArtistaBean artista){
        try{
            return service.findByFilters(artista);

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @POST
    @Path("/datosLugarMenu")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArtistaBean> getLugarMenu(Long idUsuario){
        try{
            return service.findByUsuarioMenu(idUsuario);

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }
//    @GET
//    @Path("/tiposShow")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/tiposShow")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoShowBean> getTiposShow(){
        List<TipoShowBean> tiposShow= service.findTiposShow();
        return tiposShow;
    }
}
