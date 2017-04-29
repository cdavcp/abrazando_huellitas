package sigep.rest;

import sigep.beans.*;
import sigep.data.dto.EventoDTO;
import sigep.exceptions.SIGEPException;
import sigep.service.EventoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.List;
import java.util.*;


@RequestScoped
@Path("/evento")
public class EventoRest {
    @Inject
    private EventoService eventoService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(EventoBean bean) {
        try{
            eventoService.create(bean);
            return Response.ok().build();
        }
        catch (SIGEPException e){
            return Response.serverError().build();
        }

    }



    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(EventoBean evento){
        try{
            eventoService.update(evento);
            return Response.ok().build();
        }
        catch(SIGEPException e){
            return Response.serverError().build();
        }
    }


    @POST
    @Path("/init")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response init(UsuarioBean usuario) {
        EventoDTO dto = new EventoDTO();
        dto.build(eventoService.findEventosByUsuario(usuario), usuario);
        return Response.ok(dto).build();
    }

    @POST
    @Path("/createMeGusta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MeGustaXEventoBean createMeGusta(MeGustaXEventoBean mg) {
        try{
            return eventoService.createMG(mg);
//            return Response.ok().build();
        }
        catch(SIGEPException e){
            return null;
        }
    }

    @POST
    @Path("/findMeGusta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MeGustaXEventoBean findMeGusta(MeGustaXEventoBean mg) {
        try{
            return eventoService.findByUsuarioMG(mg);
//            return Response.ok().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("/eventosProximos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eventosProximos() {
        EventoDTO dto = new EventoDTO();
        dto.build(eventoService.eventosProximos());
        return Response.ok(dto).build();
    }

    @GET
    @Path("/eventosMg")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eventosMg() {
        EventoDTO dto = new EventoDTO();
        dto.build(eventoService.eventosMg());
        return Response.ok(dto).build();
    }


    @POST
    @Path("/artistas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByArtista(ArtistaBean artista) {
        try{
            EventoDTO dto = new EventoDTO();
            dto.build(eventoService.findEventosByArtista(artista), artista);
            return Response.ok(dto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @POST
    @Path("/lugares")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByLugar(LugarBean lugar) {
        EventoDTO dto = new EventoDTO();
        dto.build(eventoService.findEventosByLugar(lugar), lugar);
        return Response.ok(dto).build();
    }

    @POST
    @Path("/todoFiltros")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTodosFiltros(EventoBean query) {
        try{
            EventoDTO dto = new EventoDTO();
            dto.build(eventoService.findTodosFiltros(query));
            return Response.ok(dto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
