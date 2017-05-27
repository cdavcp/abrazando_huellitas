package sigep.data.dao.Ivent;


import sigep.data.dao.DaoBase;
import sigep.model.Ivent.VotacionArtista;

import javax.persistence.Query;

public class VotacionArtistaDao extends DaoBase<VotacionArtista> {

    public String  findPromedioArtista(Long idArtista)
    {
        Query query =  em.createQuery("SELECT AVG(va.puntuacion) FROM VotacionArtista va WHERE va.artista.id = :idArtista");
        query.setParameter("idArtista", idArtista);
        return query.getResultList().get(0).toString();
    }


    public VotacionArtista finBySeguidor(Long artistaId, Long usuarioId){
        Query query = em.createNativeQuery("select s.id from VotacionArtista s where (s.usuario_id = ? and  s.artista_id = ?)");
        query.setParameter(1, usuarioId);
        query.setParameter(2, artistaId);
        VotacionArtista votacionArtista = new VotacionArtista();
        String id = query.getResultList().get(0).toString();
//        String puntuacion = query.getResultList().get(1).toString();
        votacionArtista.setId(Long.parseLong(id));
//        votacionArtista.setPuntuacion(puntuacion);

//        List<VotacionArtista> listResult = query.getResultList();
//        return (listResult.isEmpty()) ? null : listResult.get(0);
        return votacionArtista;
    }

   }