package sigep.data.dao;

import sigep.model.RazaMascota;

import javax.persistence.Query;
import java.util.List;

public class RazaMascotaDao extends DaoBase<RazaMascota> {

    public List<RazaMascota> findByTipoMascota(Long idTipoMascota)
    {
        Query query =  em.createQuery("SELECT r FROM RazaMascota r WHERE r.tipomascota.id = :idTipoMascota");
        query.setParameter("idTipoMascota", idTipoMascota);
        List<RazaMascota> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

}
