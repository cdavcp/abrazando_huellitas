package sigep.data.dao.Ivent;


import sigep.data.dao.DaoBase;
import sigep.model.Ivent.Reserva;

import javax.persistence.Query;
import java.util.List;

public class ReservaDao extends DaoBase<Reserva> {

    public List<Reserva> findByUsuario(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Reserva p WHERE p.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Reserva> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }


    public List<Reserva> findByEvento(Long idEvento)
    {
        Query query =  em.createQuery("SELECT p FROM Reserva p WHERE p.evento.id = :idEvento");
        query.setParameter("idEvento", idEvento);
        List<Reserva> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }



}