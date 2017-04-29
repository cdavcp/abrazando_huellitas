package sigep.data.dao.sigep;

import sigep.data.dao.DaoBase;
import sigep.model.sigep.Vendedor;
import javax.persistence.Query;
import java.util.List;


public class VendedorDao extends DaoBase<Vendedor> {
    public List<Vendedor> findVendedoresDeProductor(Long idProductor){
        Query query =  em.createQuery("SELECT p.vendedores FROM Productor p WHERE p.id = :idProductor");
        query.setParameter("idProductor", idProductor);
        return query.getResultList();
    }
}
