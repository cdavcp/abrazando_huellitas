package sigep.data.dao;

import sigep.model.Persona;

import javax.persistence.Query;
import java.util.List;

public class PersonaDao extends DaoBase<Persona>{
    public Persona findByUsuario(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Persona p WHERE p.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Persona> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult.get(0);
    }
}
