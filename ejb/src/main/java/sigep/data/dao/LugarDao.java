package sigep.data.dao;


import sigep.beans.ArtistaBean;
import sigep.beans.LugarBean;
import sigep.model.Artista;
import sigep.model.Lugar;
import sigep.model.Tag;

import javax.persistence.Query;
import java.util.List;

public class LugarDao extends DaoBase<Lugar> {

    public List<Lugar>  findByUsuario(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Lugar p WHERE p.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Lugar> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<Lugar>  findByUsuarioMenu(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Lugar p WHERE p.usuario.id != :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Lugar> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

//    public List<Artista> artistaByFilters(LugarBean lugar)
//    {
//        Query query =  em.createQuery("select l from Lugar l  where l.nombre like :buscar or l.fechaEvento like :buscar");
//        query.setParameter("buscar", buscar+'%');
//        List<Artista> listResult = query.getResultList();
//        return (listResult.isEmpty()) ? null : listResult;
//    }



    public List<Lugar> lugarByFilters(LugarBean lugar){
        String ubicacion = lugar.getUbicacion();
        String nombre = lugar.getNombre();
        Query query = em.createQuery("select l from Lugar l where (l.ubicacion like :ubicacion and  l.nombre like :nombre)");
        query.setParameter("nombre", '%'+nombre+'%');
        query.setParameter("ubicacion", '%'+ubicacion+'%');

       List<Lugar> listResult = query.getResultList();
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