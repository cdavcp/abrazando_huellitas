package sigep.data.dao.sigep;

import sigep.data.dao.DaoBase;
import sigep.model.sigep.Rubro;

import javax.persistence.Query;
import java.util.List;

public class RubroDao extends DaoBase<Rubro> {
    public Rubro findByNombre(String nombre)
    {
        Query query = em.createQuery("SELECT r FROM Rubro r WHERE r.nombre like :nombre", Rubro.class);
        query.setParameter("nombre", nombre);
        List<Rubro> listResult = query.getResultList();
        return (listResult.size() == 0) ? null : listResult.get(0);
    }
}
