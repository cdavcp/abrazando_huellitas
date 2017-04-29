package sigep.rest.sigep;

import sigep.data.dto.estadisticasProductor.EstadisticaDTO;
import sigep.data.dto.estadisticasProductor.Filtro;
import sigep.service.sigep.EstadisticasProductorService;
import sigep.service.sigep.ProductorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/estadisticasProductor")
@RequestScoped
public class EstadisticasProductorREST {

    @Inject
    private EstadisticasProductorService service;
    @Inject
    private ProductorService productorService;

    @POST
    @Path("/inicializarEstadisticasPolizas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstadisticaDTO inicializarEstadisticasPolizas(EstadisticaDTO dto) {
        dto.setFiltros(service.getFiltros());
        dto.setItems(service.generarEstadisticaPolizaPorFiltro(
                new Integer(dto.getIdFiltro()), dto.getFechaDesde(), dto.getFechaHasta(), new Long(dto.getIdUsuario())));
        return dto;
    }

    @POST
    @Path("/inicializarEstadisticasVendedores")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstadisticaDTO inicializarEstadisticasVendedores(EstadisticaDTO dto) {
        return service.generarEstadisticaVendedor(new Integer(dto.getAnio()), new Long(dto.getIdUsuario()));
    }

    @POST
    @Path("/inicializarEstadisticasSolicitudes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstadisticaDTO inicializarEstadisticasSolicitudes(EstadisticaDTO dto) {
        return service.inicializarEstadisticasSolicitudes(new Integer(dto.getAnio()), new Long(dto.getIdUsuario()));
    }

    @POST
    @Path("/inicializarEstadisticasRentabilidad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstadisticaDTO inicializarEstadisticasRentabilidad(EstadisticaDTO dto) {
        dto.setFiltros(service.getFiltros());
        dto.setItems(service.generarEstadisticaRentabilidadPorFiltro(
                new Integer(dto.getIdFiltro()), dto.getFechaDesde(), dto.getFechaHasta(), new Long(dto.getIdUsuario())));
        return dto;
    }

    @POST
    @Path("/inicializarEstadisticasComisionesProductor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstadisticaDTO inicializarEstadisticasComisionesProductor(EstadisticaDTO dto) {
        dto.setItems(service.generarEstadisticaComisionesProductor(new Integer(dto.getMes()), new Integer(dto.getAnio()), new Long(dto.getIdUsuario())));
        return dto;
    }

    @POST
    @Path("/inicializarEstadisticasComisionesVendedor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstadisticaDTO inicializarEstadisticasComisionesVendedor(EstadisticaDTO dto) {
        dto.setItems(service.generarEstadisticaComisionesVendedor(new Integer(dto.getMes()), new Integer(dto.getAnio()), new Long(dto.getIdUsuario())));
        return dto;
    }

    @POST
    @Path("/inicializarEstadisticasRentabilidadMultilinea")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstadisticaDTO inicializarEstadisticasRentabilidadMultilinea(EstadisticaDTO dto) {
        EstadisticaDTO resultDto = service.generarEstadisticaRentabilidadMultilineaPorFiltro(new Integer(dto.getIdFiltro()), new Integer(dto.getAnio()), new Long(dto.getIdUsuario()));
        resultDto.setAnio(dto.getAnio());
        resultDto.setFiltros(service.getFiltros());
        return resultDto;
    }

    @POST
    @Path("/inicializarEstadisticasProductores")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EstadisticaDTO inicializarEstadisticasProductores(EstadisticaDTO dto) {
        return service.generarEstadisticaProductor(new Integer(dto.getAnio()));
    }


    @GET
    @Path("/getFiltros")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Filtro> getFiltros() {
        return service.getFiltros();
    }

}
