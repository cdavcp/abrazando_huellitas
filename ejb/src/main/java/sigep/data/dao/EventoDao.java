package sigep.data.dao;

import sigep.beans.EventoBean;
import sigep.model.Artista;
import sigep.model.Evento;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventoDao extends DaoBase<Evento> {

    public List<Evento> findByManager(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT e FROM Evento e WHERE e.artista.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Evento> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<Evento> findByAnfitrion(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT e FROM Evento e WHERE e.lugar.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Evento> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<Evento> findByArtista(Long idArtista)
    {
        Query query =  em.createQuery("SELECT e FROM Evento e WHERE e.artista.id = :idArtista");
        query.setParameter("idArtista", idArtista);
        List<Evento> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<Evento> findByLugar(Long idLugar)
    {
        Query query =  em.createQuery("SELECT e FROM Evento e WHERE e.lugar.id = :idLugar");
        query.setParameter("idLugar", idLugar);
        List<Evento> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }


//    public List<Evento> busquedaEvento(String buscar)
//    {
//        Query query =  em.createQuery("select * from evento join artista join lugar where \n" +
//                "evento.artista_id= artista.id and evento.lugar_id= lugar.id and\n" +
//                "((aceptadoPorArtista =1 or aceptadoPorLugar=1) ) \n" +
//                "and (artista.nombre like :buscar'%' or lugar.nombre like :buscar'%' or fechaEvento like :buscar'%'  )");
//        query.setParameter("buscar", buscar);
//        List<Evento> listResult = query.getResultList();
//        return (listResult.isEmpty()) ? null : listResult;
//    }
 public List<Evento> busquedaEvento(EventoBean evento)
    {
        String lugarEvento = evento.getLugar().getUbicacion();
        String nombreArtista = evento.getArtista().getNombre();
        String tipoShowEvento = evento.getArtista().getTipoShow().getNombre();
        String nombreLugar = evento.getLugar().getNombre();
        Query query =  em.createQuery("select e from Evento e where ( e.artista.nombre like :nombreArtista and  e.lugar.nombre like :nombreLugar and e.artista.tipoShow.nombre like :tipoShowEvento and e.lugar.ubicacion like :lugarEvento)");
        query.setParameter("lugarEvento",'%'+lugarEvento +'%');
        query.setParameter("nombreArtista",'%'+nombreArtista +'%');
        query.setParameter("tipoShowEvento",'%'+tipoShowEvento +'%');
        query.setParameter("nombreLugar",'%'+nombreLugar +'%');
        List<Evento> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<Evento> busquedaEventosProximos()
    {
        java.util.Date fechaActual = new Date();

        Query query =  em.createQuery("select  e  from Evento  e  where (e.fechaEvento  >= :fechaActual) ORDER BY e.fechaEvento  ASC  ");

        query.setParameter("fechaActual", fechaActual);
        List<Evento> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }



    public List<Evento> busquedaEventoRecientes()
    {
        java.util.Date fechaActual = new Date();

        Query query =  em.createQuery("select e from Evento e where (e.fechaEvento  <= :fechaActual )  ORDER BY e.fechaEvento DESC ")  ;

        query.setParameter("fechaActual", fechaActual);
        List<Evento> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<Evento> eventoMg()
    {
        java.util.Date fechaActual = new Date();

        Query query =  em.createQuery("select  e  from Evento  e  where (e.fechaEvento  >= :fechaActual) ORDER BY e.cantMg  DESC  ");

        query.setParameter("fechaActual", fechaActual);
        List<Evento> listResult = query.getResultList();
        List<Evento> resultado = new ArrayList<Evento>();
        resultado.add(listResult.get(0));
        resultado.add(listResult.get(1));
        return (listResult.isEmpty()) ? null : resultado;
    }




}