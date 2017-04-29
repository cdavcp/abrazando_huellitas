package sigep.data.dao.sigep;

import sigep.data.dao.DaoBase;
import sigep.model.sigep.Productor;

import javax.persistence.Query;
import java.util.List;

public class ProductorDao extends DaoBase<Productor> {
    public Productor findByUsuario(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Productor p WHERE p.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Productor> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult.get(0);
    }
}
