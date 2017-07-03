package sigep.data.dao;

import sigep.model.Barrio;

import javax.persistence.Query;
import java.util.List;

public class BarrioDao extends DaoBase<Barrio> {

    public List<Barrio> findByCiudad(Long idCiudad)
    {
        Query query =  em.createQuery("SELECT b FROM Barrio b WHERE b.ciudad.id = :idCiudad");
        query.setParameter("idCiudad", idCiudad);
        List<Barrio> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

}
