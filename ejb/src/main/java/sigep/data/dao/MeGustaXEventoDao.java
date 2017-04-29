package sigep.data.dao;


import sigep.beans.MeGustaXEventoBean;
import sigep.model.MeGustaXEvento;
import sigep.model.Solicitud;

import javax.persistence.Query;
import java.util.List;

public class MeGustaXEventoDao extends DaoBase<MeGustaXEvento> {

/*    public List<Solicitud>  findByUsuario(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Solicitud p WHERE p.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Solicitud> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }*/


//    public MeGustaXEvento findMG(MeGustaXEventoBean mg)
//    {
//        Long idEvento = mg.getEvento().getId();
//        Long idUsuario = mg.getUsuario().getId();
//        Query query =  em.createQuery("select e from MeGustaXEvento e where e.evento.id = :idEvento and  e.usuario.id = :idUsuario ");
//        query.setParameter("idEvento",idEvento);
//        query.setParameter("idUsuario",idUsuario);
//        List<MeGustaXEvento> listResult = query.getResultList();
//        return (listResult.isEmpty()) ? null : listResult.get(0);
//    }


    public MeGustaXEvento findMG(Long idEvento, Long idUsuario){
        Query query = em.createNativeQuery("select s.id from megustaxevento s where (s.usuario_id = ? and  s.evento_id = ?)");
        query.setParameter(1, idUsuario);
        query.setParameter(2, idEvento);
        MeGustaXEvento usuario = new MeGustaXEvento();
        String id = query.getResultList().get(0).toString();
        usuario.setId(Long.parseLong(id));
        return usuario;
    }


}