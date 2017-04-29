package sigep.data.dao;


import sigep.beans.ArtistaBean;
import sigep.model.Artista;
import sigep.model.Tag;
import sigep.model.TipoShow;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArtistaDao extends DaoBase<Artista> {

    public List<Artista>  findByUsuario(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Artista p WHERE p.usuario.id = :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Artista> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<TipoShow> FindTiposShow( )
    {
        Query query = em.createQuery("SELECT *  FROM TipoShow t ");
        List<TipoShow> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;

    }

    public List<Artista>  findByUsuarioMenu(Long idUsuario)
    {
        Query query =  em.createQuery("SELECT p FROM Artista p WHERE p.usuario.id != :idUsuario");
        query.setParameter("idUsuario", idUsuario);
        List<Artista> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<Artista> artistaByFilters(ArtistaBean artista){
        String lugarActual = artista.getLugarActual();
        String nombre = artista.getNombre();
        String tipoShow = artista.getTipoShow().getNombre();
        Query query = em.createQuery("select a from Artista a where (a.lugarActual like :lugarActual and  a.nombre like :nombre and a.tipoShow.nombre like :tipoShow)");
        query.setParameter("nombre", '%'+nombre+'%');
        query.setParameter("lugarActual", '%'+lugarActual+'%');
        query.setParameter("tipoShow", '%'+tipoShow+'%');

        List<Artista> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }

    public List<Artista> artistaMg()
    {
//        java.util.Date fechaActual = new Date();

        Query query =  em.createQuery("select  e  from Artista  e  ORDER BY e.puntuacion  DESC  ");

//        query.setParameter("fechaActual", fechaActual);
        List<Artista> listResult = query.getResultList();
        List<Artista> resultado = new ArrayList<Artista>();
        resultado.add(listResult.get(0));
        resultado.add(listResult.get(1));
        return (listResult.isEmpty()) ? null : resultado;
    }



    public List<Tag>  findTagByTipoShow(Long idTipoShow)
    {
        Query query =  em.createQuery("SELECT f FROM FrecuenciaTag f WHERE f.tipoShow_id = :idTipoShow");
        query.setParameter("idTipoShow", idTipoShow);
        List<Tag> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult;
    }





}