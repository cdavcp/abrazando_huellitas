package sigep.data.dao.sigep;

import sigep.data.dao.DaoBase;
import sigep.model.sigep.ComisionIncompleta;

import javax.persistence.Query;

public class ComisionIncompletaDao extends DaoBase<ComisionIncompleta> {

    public void deleteByLote(Long loteId) {
        Query query = em.createNativeQuery("DELETE FROM comisionincompleta WHERE comisionincompleta.lote_id = ?", ComisionIncompleta.class);
        query.setParameter(1, loteId);
        query.executeUpdate();
    }
}
