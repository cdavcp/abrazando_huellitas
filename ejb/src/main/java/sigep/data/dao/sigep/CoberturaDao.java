package sigep.data.dao.sigep;
import sigep.data.dao.DaoBase;
import sigep.model.sigep.Cobertura;

import javax.persistence.Query;
import java.util.List;


public class CoberturaDao extends DaoBase<Cobertura> {
    public Cobertura findByNombreYAseguradora(String nombre, Long aseguradoraId)
    {
        Query query = em.createQuery("SELECT c FROM Cobertura c WHERE c.aseguradora.id = :aseguradoraId AND c.nombre LIKE :nombre", Cobertura.class);
        query.setParameter("nombre", nombre);
        query.setParameter("aseguradoraId", aseguradoraId);
        List<Cobertura> listResult = query.getResultList();
        return (listResult.size() == 0) ? null : listResult.get(0);
    }
}
