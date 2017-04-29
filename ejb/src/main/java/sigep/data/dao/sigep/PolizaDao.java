package sigep.data.dao.sigep;

import sigep.data.dao.DaoBase;
import sigep.model.sigep.Poliza;

import javax.persistence.Query;
import java.util.List;

public class PolizaDao extends DaoBase<Poliza> {
    public Poliza findByNumeroYAseguradora(String numeroPoliza, Long idAseguradora) {
        Query query = em.createQuery("SELECT p FROM Poliza p WHERE p.numero LIKE :numeroPoliza AND p.cobertura.aseguradora.id = :idAseguradora",
                Poliza.class);
        query.setParameter("numeroPoliza", numeroPoliza);
        query.setParameter("idAseguradora", idAseguradora);
        List<Poliza> listResult = query.getResultList();
        return (listResult.size() == 0) ? null : listResult.get(0);
    }

    public List<Poliza> findByFilters(Long productorId, Long aseguradoraId, Long rubroId, String numero, Long clienteId, Long vendedorId, Long origenId) {
        StringBuilder consulta = new StringBuilder();
        consulta.append("SELECT p FROM Poliza p WHERE p.productor.id = :productorId");

        if (aseguradoraId != null)
            consulta.append(" AND p.cobertura.aseguradora.id = :aseguradoraId");
        if (rubroId != null)
            consulta.append(" AND p.cobertura.rubro.id = :rubroId");
        if (numero != null)
            consulta.append(" AND p.numero LIKE :numero");
        if (clienteId != null)
            consulta.append(" AND p.cliente.id = :clienteId");
        if (vendedorId != null)
            consulta.append(" AND p.vendedor.id = :vendedorId");
        if (origenId != null)
            consulta.append(" AND p.origen.id = :origenId");

        Query query = em.createQuery(consulta.toString(), Poliza.class);
        query.setParameter("productorId", productorId);
        if (aseguradoraId != null)
            query.setParameter("aseguradoraId", aseguradoraId);
        if (rubroId != null)
            query.setParameter("rubroId", rubroId);
        if (numero != null)
            query.setParameter("numero", numero);
        if (clienteId != null)
            query.setParameter("clienteId", clienteId);
        if (vendedorId != null)
            query.setParameter("vendedorId", vendedorId);
        if (origenId != null)
            query.setParameter("origenId", origenId);

        return query.getResultList();
    }
}