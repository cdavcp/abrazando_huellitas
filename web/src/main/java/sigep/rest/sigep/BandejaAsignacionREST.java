package sigep.rest.sigep;

//import sigep.data.dto.sigep.BandejaAsignacionDTO;
import sigep.data.dto.BandejaAsignacionDTO;
import sigep.service.sigep.ProductorService;
import sigep.service.sigep.SolicitudServiceOld;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bandejaAsignacion")
@RequestScoped
public class BandejaAsignacionREST {

    @Inject
    SolicitudServiceOld solicitudServiceOld;
    @Inject
    ProductorService productorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BandejaAsignacionDTO inicializarIndex() {
        BandejaAsignacionDTO dto = new BandejaAsignacionDTO();
        dto.setListaSolicitudes(solicitudServiceOld.findAllRegistradas());
        dto.setListaProductores(productorService.findAll());
        return dto;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void create(BandejaAsignacionDTO dto) {
        solicitudServiceOld.asignar(dto.getListaProductores());
    }
}
