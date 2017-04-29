package sigep.service.sigep;

import sigep.data.dao.sigep.EstadisticasProductorDao;
import sigep.data.dto.estadisticasProductor.EstadisticaDTO;
import sigep.data.dto.estadisticasProductor.Filtro;
import sigep.data.dto.estadisticasProductor.Mes;
import sigep.model.Estadisticas.Item;
import sigep.model.Estadisticas.ItemNomCantMes;
import sigep.util.DateUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Stateless
public class EstadisticasProductorService {

    @Inject
    private EstadisticasProductorDao dao;
    @Inject
    private ProductorService productorService;

    public List<Filtro> getFiltros() {
        List<Filtro> filtros = new ArrayList<Filtro>(4);
        filtros.add(new Filtro(Filtro.FILTRO_ASEGURADORA_ID, "Aseguradora"));
        filtros.add(new Filtro(Filtro.FILTRO_ORIGEN_ID, "Origen"));
        filtros.add(new Filtro(Filtro.FILTRO_ESTADO_ID, "Estado"));
        filtros.add(new Filtro(Filtro.FILTRO_RUBRO_ID, "Rubro"));
        return filtros;
    }

    public List<Item> generarEstadisticaPolizaPorFiltro(Integer idFiltro, Date fechaDesde, Date fechaHasta, Long usuarioId) {
        Long productorId = productorService.getIdProductorSiExiste(usuarioId);
        String desde = DateUtil.formatToMySQLDB(fechaDesde);
        String hasta = DateUtil.formatToMySQLDB(fechaHasta);
        List<Item> items = new ArrayList<Item>();
        switch (idFiltro) {
            case Filtro.FILTRO_ASEGURADORA_ID:
                items = dao.generarEstadisticaPolizasXAseguradora(productorId, desde, hasta);
                break;
            case Filtro.FILTRO_ESTADO_ID:
                items = dao.generarEstadisticaPolizasXEstado(productorId, desde, hasta);
                break;
            case Filtro.FILTRO_ORIGEN_ID:
                items = dao.generarEstadisticaPolizasXOrigen(productorId, desde, hasta);
                break;
            case Filtro.FILTRO_RUBRO_ID:
                items = dao.generarEstadisticaPolizasXRubro(productorId, desde, hasta);
                break;
        }
        return items;
    }

    public EstadisticaDTO generarEstadisticaVendedor(Integer anio, Long usuarioId) {
        Long productorId = productorService.getIdProductorSiExiste(usuarioId);
        return procesarEstadisticaMultilinea(dao.generarEstadisticaVendedores(anio, productorId));
    }

    public EstadisticaDTO inicializarEstadisticasSolicitudes(Integer anio, Long usuarioId) {
        Long productorId = productorService.getIdProductorSiExiste(usuarioId);
        return procesarEstadisticaMultilinea(dao.generarEstadisticaSolicitudes(anio, productorId));
    }

    public EstadisticaDTO generarEstadisticaProductor(Integer anio) {
        return procesarEstadisticaMultilinea(dao.generarEstadisticaProductores(anio));
    }

    public List<Item> generarEstadisticaRentabilidadPorFiltro(Integer idFiltro, Date fechaDesde, Date fechaHasta, Long usuarioId) {
        Long productorId = productorService.getIdProductorSiExiste(usuarioId);
        String desde = DateUtil.formatToMySQLDB(fechaDesde);
        String hasta = DateUtil.formatToMySQLDB(fechaHasta);
        List<Item> items = new ArrayList<Item>();
        switch (idFiltro) {
            case Filtro.FILTRO_ASEGURADORA_ID:
                items = dao.generarEstadisticaRentabilidadXAseguradora(productorId, desde, hasta);
                break;
            case Filtro.FILTRO_ESTADO_ID:
                items = dao.generarEstadisticaRentabilidadXEstado(productorId, desde, hasta);
                break;
            case Filtro.FILTRO_ORIGEN_ID:
                items = dao.generarEstadisticaRentabilidadXOrigen(productorId, desde, hasta);
                break;
            case Filtro.FILTRO_RUBRO_ID:
                items = dao.generarEstadisticaRentabilidadXRubro(productorId, desde, hasta);
                break;
        }
        return items;
    }

    public EstadisticaDTO generarEstadisticaRentabilidadMultilineaPorFiltro(Integer idFiltro, Integer anio, Long usuarioId) {
        Long productorId = productorService.getIdProductorSiExiste(usuarioId);
        EstadisticaDTO resultado = null;
        switch (idFiltro) {
            case Filtro.FILTRO_ASEGURADORA_ID:
                resultado = procesarEstadisticaMultilinea(dao.generarEstadisticaRentabilidadMultilineaXAseguradora(productorId, anio));
                break;
            case Filtro.FILTRO_ESTADO_ID:
                resultado = procesarEstadisticaMultilinea(dao.generarEstadisticaRentabilidadMultilineaEstado(productorId, anio));
                break;
            case Filtro.FILTRO_ORIGEN_ID:
                resultado = procesarEstadisticaMultilinea(dao.generarEstadisticaRentabilidadMultilineaXOrigen(productorId, anio));
                break;
            case Filtro.FILTRO_RUBRO_ID:
                resultado = procesarEstadisticaMultilinea(dao.generarEstadisticaRentabilidadMultilineaXRubro(productorId,anio));
                break;
        }
        return resultado;
    }

    public List<Item> generarEstadisticaComisionesProductor(Integer mes, Integer anio, Long usuarioId) {
        Long productorId = productorService.getIdProductorSiExiste(usuarioId);
        return dao.generarEstadisticaComisionProductor(mes, anio, productorId);
    }

    public List<Item> generarEstadisticaComisionesVendedor(Integer mes, Integer anio, Long usuarioId) {
        Long productorId = productorService.getIdProductorSiExiste(usuarioId);
        return dao.generarEstadisticaComisionVendedor(mes, anio, productorId);
    }

    private EstadisticaDTO procesarEstadisticaMultilinea(List<ItemNomCantMes> itemsCrudos){

        List<Item> items = new ArrayList<Item>();
        EstadisticaDTO dto = new EstadisticaDTO();
        if(itemsCrudos != null && !itemsCrudos.isEmpty()){
            Integer primerMes = itemsCrudos.get(0).getMes();
            for(ItemNomCantMes i : itemsCrudos){
                if(!i.getMes().equals(primerMes))break;
                items.add(new Item(i.getNombre()));
            }

            HashMap<Integer, Mes> hash = generateHashMeses();
            for(ItemNomCantMes itemCrudo : itemsCrudos){
                hash.get(itemCrudo.getMes()).agregarALista(itemCrudo.getCantidad());
            }


            dto.setItems(items);
            dto.setMeses(new ArrayList<Mes>(hash.values()));
        }
        return dto;
    }

    private HashMap<Integer, Mes> generateHashMeses()
    {
        HashMap<Integer, Mes> hash = new HashMap<Integer, Mes>();
        hash.put(1,new Mes(1, "Enero"));
        hash.put(2,new Mes(2, "Febrero"));
        hash.put(3,new Mes(3, "Marzo"));
        hash.put(4,new Mes(4, "Abril"));
        hash.put(5,new Mes(5, "Mayo"));
        hash.put(6,new Mes(6, "Junio"));
        hash.put(7,new Mes(7, "Julio"));
        hash.put(8,new Mes(8, "Agosto"));
        hash.put(9,new Mes(9, "Septiembre"));
        hash.put(10,new Mes(10, "Octubre"));
        hash.put(11,new Mes(11, "Noviembre"));
        hash.put(12,new Mes(12, "Diciembre"));
        return hash;
    }

}
