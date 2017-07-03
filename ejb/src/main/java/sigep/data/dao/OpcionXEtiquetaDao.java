package sigep.data.dao;

import sigep.model.OpcionXEtiqueta;

import javax.persistence.Query;
import java.util.List;

public class OpcionXEtiquetaDao extends DaoBase<OpcionXEtiqueta> {

    public List<OpcionXEtiqueta> findByEtiqueta(Long idEtiqueta)
    {
        Query query =  em.createQuery("SELECT o FROM OpcionXEtiqueta o WHERE o.etiqueta.id = :idEtiqueta");
        query.setParameter("idEtiqueta", idEtiqueta);
        List<OpcionXEtiqueta> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

}
