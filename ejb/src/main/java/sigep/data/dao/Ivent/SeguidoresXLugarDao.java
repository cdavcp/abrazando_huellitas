package sigep.data.dao.Ivent;


import sigep.data.dao.DaoBase;
import sigep.model.Ivent.SeguidoresXLugar;
import sigep.model.Ivent.Usuario;

import javax.persistence.Query;
import java.util.List;

public class SeguidoresXLugarDao extends DaoBase<SeguidoresXLugar> {

   /* public List<Lugar>  findByUsuario(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Lugar p WHERE p.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Lugar> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }*/

    public Usuario finBySeguidor(Long lugarId, Long usuarioId){
        Query query = em.createNativeQuery("select s.id from SeguidoresXLugar s where (s.usuario_id = ? and  s.lugar_id = ?)");
        query.setParameter(1, usuarioId);
        query.setParameter(2, lugarId);
        Usuario usuario = new Usuario();
        String id = query.getResultList().get(0).toString();
        usuario.setId(Long.parseLong(id));

//        List<SeguidoresXLugar> listResult = query.getResultList();
//        return (listResult.isEmpty()) ? null : listResult.get(0);
        return usuario;
    }

    public List<Usuario> findByLugar(Long idLugar)
    {
        Query query =  em.createQuery("SELECT p.usuario FROM SeguidoresXLugar p WHERE p.lugar.id = :idLugar");
        query.setParameter("idLugar", idLugar);
        List<Usuario> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }


  /*  public List<Tag>  findTagByTipoShow(Long idTipoShow)
    {
        Query query =  em.createQuery("SELECT f FROM FrecuenciaTag f WHERE f.tipoShow_id = :idTipoShow");
        query.setParameter("idTipoShow", idTipoShow);
        List<Tag> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }  */


}