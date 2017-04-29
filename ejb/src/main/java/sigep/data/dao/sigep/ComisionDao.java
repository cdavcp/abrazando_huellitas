package sigep.data.dao.sigep;

import sigep.data.dao.DaoBase;
import sigep.model.sigep.Comision;

import javax.persistence.Query;
import java.util.List;

public class ComisionDao extends DaoBase<Comision> {

    public void deleteByLote(Long loteId) {
        Query query = em.createNativeQuery("DELETE FROM comision WHERE comision.lote_id = ?", Comision.class);
        query.setParameter(1, loteId);
        query.executeUpdate();
    }

    public Comision findUltimaComisionPoliza(Long idPoliza){
        Query query = em.createQuery("SELECT c FROM Comision c WHERE c.poliza.id = :idPoliza AND c.fecha = (SELECT max(c.fecha) FROM Comision c where c.poliza.id = :idPoliza)", Comision.class);
        query.setParameter("idPoliza", idPoliza);
        List<Comision> resultList = query.getResultList();
        return (resultList.isEmpty()) ? new Comision() : resultList.get(0);
    }

    public Comision findByFechaYPoliza(int mes, int anio, Long idPoliza){
        Query query = em.createNativeQuery("SELECT * FROM comision where month(comision.fecha) = ? and year(comision.fecha) = ? and comision.poliza_id = ?", Comision.class);
        query.setParameter(1, mes);
        query.setParameter(2, anio);
        query.setParameter(3, idPoliza);
        List<Comision> resultList = query.getResultList();
        return (resultList.isEmpty()) ? null : resultList.get(0);
    }
}
