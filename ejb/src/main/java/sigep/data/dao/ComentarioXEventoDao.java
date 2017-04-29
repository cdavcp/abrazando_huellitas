package sigep.data.dao;


import sigep.model.ComentarioXEvento;
import sigep.model.Evento;

import javax.persistence.Query;
import java.util.List;

public class ComentarioXEventoDao extends DaoBase<ComentarioXEvento> {

//    public List<Evento>  findByUsuario(Long idUsuario)
//    {
//        Query query =  em.createQuery("SELECT p FROM Artista p WHERE p.usuario.id = :idUsuario");
//        query.setParameter("idUsuario", idUsuario);
//        List<Evento> listResult = query.getResultList();
//        return (listResult.isEmpty()) ? null : listResult;
//    }

    public List<ComentarioXEvento> findByEvento(Long id)
    {
        Query query =  em.createQuery("SELECT p FROM ComentarioXEvento p WHERE p.evento.id = :id  ORDER BY p.fechaComentario DESC");
        query.setParameter("id", id);
        List<ComentarioXEvento> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }



}