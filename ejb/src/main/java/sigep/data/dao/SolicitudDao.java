package sigep.data.dao;


import sigep.model.Artista;
import sigep.model.Solicitud;
import sigep.model.Tag;

import javax.persistence.Query;
import java.util.List;

public class SolicitudDao extends DaoBase<Solicitud> {

/*    public List<Solicitud>  findByUsuario(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Solicitud p WHERE p.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Solicitud> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }*/



}