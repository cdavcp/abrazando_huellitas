package sigep.data.dao;


import sigep.model.VotacionLugar;
import sigep.model.VotacionLugar;

import javax.persistence.Query;
import java.util.List;

public class VotacionLugarDao extends DaoBase<VotacionLugar> {

        public String  findPromedioLugar(Long idLugar)
    {
        Query query =  em.createQuery("SELECT AVG(va.puntuacion) FROM VotacionLugar va WHERE va.lugar.id = :idLugar");
        query.setParameter("idLugar", idLugar);
        String promedio = query.getResultList().get(0).toString();
        return promedio;
    }



    public VotacionLugar finBySeguidor(Long lugarId, Long usuarioId){
        Query query = em.createNativeQuery("select s.id from VotacionLugar s where (s.usuario_id = ? and  s.lugar_id = ?)");
        query.setParameter(1, usuarioId);
        query.setParameter(2, lugarId);
        VotacionLugar votacionLugar = new VotacionLugar();
        String id = query.getResultList().get(0).toString();
//        String puntuacion = query.getResultList().get(1).toString();
        votacionLugar.setId(Long.parseLong(id));
//        votacionLugar.setPuntuacion(puntuacion);

//        List<VotacionLugar> listResult = query.getResultList();
//        return (listResult.isEmpty()) ? null : listResult.get(0);
       return votacionLugar;
    }


//    public VotacionLugar finBySeguidor(Long lugarId, Long usuarioId)
//    {
//        String query = "SELECT s FROM VotacionLugar s WHERE  s.usuario.id LIKE '" + usuarioId + "' AND s.lugar.id = '" + lugarId + "'";
//        List<VotacionLugar> listResult= em.createQuery(query, VotacionLugar.class).getResultList();
//        return (listResult.isEmpty()) ? null : listResult.get(0);
//    }
    
   }