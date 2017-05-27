package sigep.rest.sigep;

import sigep.beans.sigep.AseguradoraBean;
import sigep.beans.sigep.PolizaBean;
import sigep.beans.sigep.SolicitudBeanOld;
import sigep.beans.Ivent.TipoDocumentoBean;
//import sigep.data.dto.sigep.BandejaSolicitudDTO;
import sigep.data.dto.BandejaSolicitudDTO;
import sigep.service.sigep.AseguradoraService;
import sigep.service.sigep.PolizaService;
import sigep.service.sigep.SolicitudServiceOld;
import sigep.service.sigep.TipoDocumentoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bandejaSolicitud")
@RequestScoped
public class BandejaSolicitudREST {

    @Inject
    SolicitudServiceOld solicitudServiceOld;
    @Inject
    TipoDocumentoService tipoDocumentoService;
    @Inject
    PolizaService polizaService;
    @Inject
    AseguradoraService aseguradoraService;

    @POST
    @Path("/inicializarIndex")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SolicitudBeanOld> inicializarIndex(String usuarioId) {
        return solicitudServiceOld.findAllByUsuarioId(new Long(usuarioId));
    }

    @GET
    @Path("/getTiposDocumento")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoDocumentoBean> getTiposDocumento() {
        return tipoDocumentoService.findAll();
    }

    @GET
    @Path("/getAseguradoras")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AseguradoraBean> getAseguradoras() {
        return aseguradoraService.findAll();
    }

    @POST
    @Path("/descartar")
    @Produces(MediaType.APPLICATION_JSON)
    public void descartar(String solicitudId) {
        solicitudServiceOld.descartarSolicitud(new Long(solicitudId));
    }

    @POST
    @Path("/checkNumero")
    @Produces(MediaType.APPLICATION_JSON)
    public PolizaBean checkNumero(BandejaSolicitudDTO dto) {
        return polizaService.findByNumeroYAseguradora(dto.getNumeroPoliza(), dto.getAseguradoraId());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(BandejaSolicitudDTO dto) {
        solicitudServiceOld.addPoliza(dto.getSolicitud(), dto.getNumeroPoliza(), dto.getAseguradoraId());
    }
}
