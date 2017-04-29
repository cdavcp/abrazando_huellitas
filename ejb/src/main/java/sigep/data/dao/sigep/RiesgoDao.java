package sigep.data.dao.sigep;


import sigep.data.dao.DaoBase;
import sigep.model.sigep.Riesgo;

import java.util.List;

public class RiesgoDao extends DaoBase<Riesgo> {

    public Riesgo findByNombreYRubro(String nombre, Long rubroId)
    {
        String query = "SELECT r FROM Riesgo r WHERE r.rubro.id LIKE '" + rubroId + "' AND r.nombre = '" + nombre + "'";
        List<Riesgo> listResult= em.createQuery(query, Riesgo.class).getResultList();
        return (listResult.isEmpty()) ? null : listResult.get(0);
    }

}
