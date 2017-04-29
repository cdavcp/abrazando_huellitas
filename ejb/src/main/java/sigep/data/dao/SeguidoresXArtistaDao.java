package sigep.data.dao;


import sigep.beans.SeguidoresXArtistaBean;
import sigep.beans.UsuarioBean;
import sigep.model.SeguidoresXArtista;
import sigep.model.Usuario;

import javax.persistence.Query;
import java.util.List;

public class SeguidoresXArtistaDao extends DaoBase<SeguidoresXArtista> {

//    public UsuarioBean findByUsuario(Long artistaId, Long usuarioId)
//    {
//        Query query =  em.createQuery("SELECT s.usuario FROM SeguidoresXArtista s WHERE s.usuario.id = :idUsuario and s.artista.id =:idArtista");
//        query.setParameter("idUsuario", seguidor.getUsuario().getId());
//        List<Usuario> listResult = query.getResultList();
//        return (listResult.isEmpty()) ? null : listResult;
//    }


    public Usuario finBySeguidor(Long artistaId, Long usuarioId){
        Query query = em.createNativeQuery("select s.id from SeguidoresXArtista s where (s.usuario_id = ? and  s.artista_id = ?)");
        query.setParameter(1, usuarioId);
        query.setParameter(2, artistaId);
        Usuario usuario = new Usuario();
        String id = query.getResultList().get(0).toString();
        usuario.setId(Long.parseLong(id));
        return usuario;
    }

    public List<Usuario>  findByArtista(Long idArtista)
    {
        Query query =  em.createQuery("SELECT p.usuario FROM SeguidoresXArtista p WHERE p.artista.id = :idArtista");
        query.setParameter("idArtista", idArtista);
        List<Usuario> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }



//    public void deleteBySeguidor(Long artistaId, Long usuarioId){
//        Query query = em.createNativeQuery("Delete from SeguidoresXArtista  where (usuario_id = ? and artista_id = ?)");
//        query.setParameter(1, usuarioId);
//        query.setParameter(2, artistaId);
//        query.getResultList();
//    }


  /*  public List<Tag>  findTagByTipoShow(Long idTipoShow)
    {
        Query query =  em.createQuery("SELECT f FROM FrecuenciaTag f WHERE f.tipoShow_id = :idTipoShow");
        query.setParameter("idTipoShow", idTipoShow);
        List<Tag> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }  */


}