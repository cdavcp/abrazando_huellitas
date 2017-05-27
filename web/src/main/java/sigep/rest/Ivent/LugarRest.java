package sigep.rest.Ivent;

import sigep.beans.Ivent.LugarBean;
import sigep.beans.Ivent.TipoShowBean;
import sigep.exceptions.SIGEPException;
import sigep.service.Ivent.LugarService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Path("/lugar")
@RequestScoped
public class LugarRest implements Serializable {
    @Inject
    private LugarService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(LugarBean bean) {
        try{
            service.create(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/tiposShow")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoShowBean> getTiposShow(){
        List<TipoShowBean> tiposShow= service.findTiposShow();
        return tiposShow;
    }

    @POST
    @Path("/datosLugar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<LugarBean> getLugar(Long idUsuario){
        List<LugarBean> lugares= service.findByUsuario(idUsuario);
        return lugares;
    }


    @POST
    @Path("/datosLugarMenu")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<LugarBean> getLugarMenu(Long idUsuario){
        try{
            return service.findByUsuarioMenu(idUsuario);

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }


    }

    @POST
    @Path("/lugarByFilters")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<LugarBean> getLugarByFilters(LugarBean lugar){
        try{
            return service.findByFilters(lugar);

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /*@POST
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
    public List<TagBean> getTag(Long idTipoShow){
        List<TagBean> tags= service.findTagByTipoShow(idTipoShow);
        return tags;
    }*/

    //    @GET
//    @Path("/tiposShow")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)


 /*   @POST
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
    public List<TagBean> getTag(Long idTipoShow){
        List<TagBean> tags= service.findTagByTipoShow(idTipoShow);
        return tags;
    }

    @POST
    @Path("/updateTags+Agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void  UpdateFrecuenciaAgregar(Long idTag,Long idTipoShow){
     service.UpdateFrecuenciaAgregar(idTag,idTipoShow);

    }

    @POST
    @Path("/updateTags+Eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void  UpdateFrecuenciaEliminar(Long idTag,Long idTipoShow){
        service.UpdateFrecuenciaEliminar(idTag, idTipoShow);

    }*/

}
