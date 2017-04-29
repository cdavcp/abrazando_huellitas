package sigep.data.dao.sigep;

import sigep.data.dao.DaoBase;
import sigep.model.sigep.Lote;

import javax.persistence.Query;
import java.util.List;

public class LoteDao extends DaoBase<Lote> {

    public List<Lote> findByProductor(Long productorId) {
        Query query = em.createQuery("SELECT l FROM Lote l WHERE l.productor.id = :productorId ORDER BY l.id DESC", Lote.class);
        query.setParameter("productorId", productorId);
        List<Lote> listResult = query.getResultList();
        return listResult;
    }

    public Lote findByMesYAseguradora(int mes, int anio, Long aseguradoraId, Long productorId) {
        Query query = em.createNativeQuery("select * from lote where lote.aseguradora_id = ? and month(lote.fecha) = ? and year(lote.fecha) = ? and lote.productor_id = ?", Lote.class);
        query.setParameter(1, aseguradoraId);
        query.setParameter(2, mes);
        query.setParameter(3, anio);
        query.setParameter(4, productorId);
        List<Lote> listResult = query.getResultList();
        return (listResult.size() == 0) ? null : listResult.get(0);
    }
}
