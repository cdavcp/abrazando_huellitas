package sigep.data.dao.sigep;
import sigep.model.Estadisticas.Item;
import sigep.model.Estadisticas.ItemNomCantMes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class EstadisticasProductorDao{

    @PersistenceContext(unitName = "primary")
    protected EntityManager em;

    //***************************************
    //****************POLIZA*****************
    //***************************************
    public List<Item> generarEstadisticaPolizasXAseguradora(Long productorId, String desde, String hasta){
        Query query = em.createNativeQuery("select a.nombre, count(p.id) as cantidad from poliza p inner join cobertura c on c.id=p.cobertura_id inner join aseguradora a on a.id=c.aseguradora_id where (productor_id= ? OR ? IS NULL) and p.fecha between ? and ? group by a.nombre,c.aseguradora_id", Item.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, desde);
        query.setParameter(4, hasta);
        return query.getResultList();
    }

    public List<Item> generarEstadisticaPolizasXOrigen(Long productorId, String desde, String hasta){
        Query query = em.createNativeQuery("select o.nombre, count(p.id) as cantidad from poliza p inner join origenPoliza o on o.id=p.origen_id where (productor_id=? OR ? IS NULL) and p.fecha between ? and ? group by o.nombre", Item.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, desde);
        query.setParameter(4, hasta);
        return query.getResultList();
    }

    public List<Item> generarEstadisticaPolizasXEstado(Long productorId, String desde, String hasta){
        Query query = em.createNativeQuery("select IF(fechaUltimaComision is null, (CASE WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) <= 30) THEN 'Nueva' WHEN (p.fechaBaja is null and (timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fecha,curdate()) <= 60)) THEN 'Atrasada' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 60) THEN 'Morosa' WHEN p.fechaBaja is not null then 'Dada de baja' END), (CASE WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) <= 30) THEN 'Nueva' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fechaUltimaComision,curdate()) <= 45) THEN 'Al dia' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and (timestampdiff(day,p.fechaUltimaComision,curdate()) > 45 and timestampdiff(day,p.fechaUltimaComision,curdate()) <= 60)) THEN 'Atrasada' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fechaUltimaComision,curdate()) > 60) THEN 'Morosa' WHEN p.fechaBaja is not null then 'Dada de baja' END)) as nombre, count(p.id) as cantidad from poliza p where (productor_id=? OR ? IS NULL) and p.fecha between ? and ? group by nombre", Item.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, desde);
        query.setParameter(4, hasta);
        return query.getResultList();
    }

    public List<Item> generarEstadisticaPolizasXRubro(Long productorId, String desde, String hasta){
        Query query = em.createNativeQuery("select r.nombre, count(p.id) as cantidad from poliza p inner join cobertura c on c.id=p.cobertura_id inner join rubro r on r.id=c.rubro_id where (productor_id=? OR ? IS NULL) and p.fecha between ? and ? group by r.nombre", Item.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, desde);
        query.setParameter(4, hasta);
        return query.getResultList();
    }


    //***************************************
    //*************VENDEDORES**************
    //***************************************

    public List<ItemNomCantMes> generarEstadisticaVendedores(Integer anio, Long productorId){
        Query query = em.createNativeQuery("select case when cantidad is null then 0  else cantidad  end as cantidad,  nombre,  mes from  (SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 1 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=1)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 2 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=2)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 3 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=3)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 4 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=4)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 5 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=5)  GROUP BY nombre, mes, anio,productor_id union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 6 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=6)  GROUP BY nombre, mes, anio,productor_id union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 7 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=7)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 8 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=8)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 9 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=9)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 10 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=10)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 11 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=11)  GROUP BY nombre, mes, anio,productor_id  union  SELECT COUNT(p.id) AS cantidad,  concat(v.nombre, ' ', v.apellido) as nombre,  case when month(p.fecha) is null then 12 else month(p.fecha) end as mes,  year(p.fecha)  as anio,  pv.productor_id  FROM vendedor  v  inner join productor_vendedor pv on pv.vendedor_id=v.id  left outer join poliza p on v.id=p.vendedor_id  where  (month(p.fecha)=12)  GROUP BY nombre, mes, anio,productor_id    ) dt where productor_id = ? and anio = ?", ItemNomCantMes.class);
        query.setParameter(1, productorId);
        query.setParameter(2, anio);
        return query.getResultList();
    }

    //***************************************
    //*************SOLICITUDES**************
    //***************************************

    public List<ItemNomCantMes> generarEstadisticaSolicitudes(Integer anio, Long productorId){
        Query query = em.createNativeQuery("select sum(if(s.estado_id=es.id,1,0)) as cantidad, es.nombre as nombre, month(s.fecha) as mes FROM solicitud s, estadosolicitud es where year(s.fecha)= ? and ((s.productor_id= ? or s.productor_id is null) or ? is null) GROUP BY nombre, mes order by mes, nombre", ItemNomCantMes.class);
        query.setParameter(1, anio);
        query.setParameter(2, productorId);
        query.setParameter(3, productorId);
        return query.getResultList();
    }


    //***************************************
    //*************RENTABILIDAD**************
    //***************************************
    public List<Item> generarEstadisticaRentabilidadXAseguradora(Long productorId, String desde, String hasta){
        Query query = em.createNativeQuery("SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where (productor_id=? OR ? IS NULL) and c.fecha between ? and ? GROUP BY nombre", Item.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, desde);
        query.setParameter(4, hasta);
        return query.getResultList();
    }

    public List<Item> generarEstadisticaRentabilidadXOrigen(Long productorId, String desde, String hasta){
        Query query = em.createNativeQuery("SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre FROM  poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where   (productor_id=? OR ? IS NULL) and c.fecha between ? and ? GROUP BY nombre", Item.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, desde);
        query.setParameter(4, hasta);
        return query.getResultList();
    }

    public List<Item> generarEstadisticaRentabilidadXRubro(Long productorId, String desde, String hasta){
        Query query = em.createNativeQuery("SELECT sum(c.comisionProductor) AS cantidad, r.nombre as nombre FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro r on r.id=co.rubro_id inner join comision c on p.id=c.poliza_id where   (productor_id=? OR ? IS NULL) and c.fecha between ? and ? GROUP BY nombre", Item.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, desde);
        query.setParameter(4, hasta);
        return query.getResultList();
    }

    public List<Item> generarEstadisticaRentabilidadXEstado(Long productorId, String desde, String hasta){
        Query query = em.createNativeQuery(" select IF(fechaUltimaComision is null, (CASE WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) <= 30) THEN 'Nueva' WHEN (p.fechaBaja is null and (timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fecha,curdate()) <= 60)) THEN 'Atrasada' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 60) THEN 'Morosa' WHEN p.fechaBaja is not null then 'Dada de baja' END), (CASE WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) <= 30) THEN 'Nueva' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fechaUltimaComision,curdate()) <= 45) THEN 'Al dia' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and (timestampdiff(day,p.fechaUltimaComision,curdate()) > 45 and timestampdiff(day,p.fechaUltimaComision,curdate()) <= 60)) THEN 'Atrasada' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fechaUltimaComision,curdate()) > 60) THEN 'Morosa' WHEN p.fechaBaja is not null then 'Dada de baja' END)) as nombre, count(p.id) as cantidad from poliza p where (productor_id=? OR ? IS NULL) and p.fecha between ? and ? group by nombre", Item.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, desde);
        query.setParameter(4, hasta);
        return query.getResultList();
    }

    //***************MULTILINEAS DE RENTABILIDAD*********************

    public List<ItemNomCantMes> generarEstadisticaRentabilidadMultilineaXAseguradora(Long productorId, Integer anio){
        Query query = em.createNativeQuery(" select sum(cantidad) as cantidad,  nombre,  mes from  (SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 1 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=1)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 2 else month(c.fecha) end as mes,   year(c.fecha)  as anio,    p.productor_id  FROM  poliza p inner join cobertura co on co.id=p.cobertura_id  inner join aseguradora a on a.id=co.aseguradora_id  inner join comision c on p.id=c.poliza_id  where  (month(c.fecha)=2)    GROUP BY nombre, mes, anio,productor_id   union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,   case when month(c.fecha) is null then 3 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id  FROM  poliza p inner join cobertura co on co.id=p.cobertura_id  inner join aseguradora a on a.id=co.aseguradora_id  inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=3)      GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 4 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=4)     GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 5 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=5)     GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 6 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=6)     GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 7 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=7)     GROUP BY nombre, mes, anio,productor_id union  SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 8 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=8)     GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 9 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=9)     GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 10 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=10)     GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 11 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=11)     GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad,  a.nombre as nombre,  case when month(c.fecha) is null then 12 else month(c.fecha) end as mes,   year(c.fecha)  as anio,   p.productor_id FROM  poliza p inner join cobertura co on co.id=p.cobertura_id inner join aseguradora a on a.id=co.aseguradora_id inner join comision c on p.id=c.poliza_id where  (month(c.fecha)=12)     GROUP BY nombre, mes, anio,productor_id  ) dt where (productor_id=? OR ? IS NULL)   and anio=? or anio is null group by mes, nombre;", ItemNomCantMes.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, anio);
        return query.getResultList();
    }

    public List<ItemNomCantMes> generarEstadisticaRentabilidadMultilineaXOrigen(Long productorId, Integer anio){
        Query query = em.createNativeQuery("select  sum(cantidad) as cantidad , nombre, mes from (  SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 1 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=1)  GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 2 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=2)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 3 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=3)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 4 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=4)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 5 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=5)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 6 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=6)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 7 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=7)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 8 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=8)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 9 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=9)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 10 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=10)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 11 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=11)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, o.nombre as nombre, case when month(c.fecha) is null then 12 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join origenPoliza o on o.id=p.origen_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=12)   GROUP BY nombre, mes, anio,productor_id ) dt where (productor_id=? OR ? IS NULL) and anio=? or anio is null group by dt.mes, dt.nombre", ItemNomCantMes.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, anio);
        return query.getResultList();
    }

    public List<ItemNomCantMes> generarEstadisticaRentabilidadMultilineaXRubro(Long productorId, Integer anio){
        Query query = em.createNativeQuery("select sum(cantidad) as cantidad, nombre, mes from (SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 1 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=1)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 2 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=2)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 3 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=3)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 4 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=4)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 5 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=5)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 6 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=6)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 7 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=7)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 8 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=8)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 9 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=9)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 10 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=10)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 11 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=11)   GROUP BY nombre, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre, case when month(c.fecha) is null then 12 else month(c.fecha) end as mes, year(c.fecha) as anio, p.productor_id FROM poliza p inner join cobertura co on co.id=p.cobertura_id inner join rubro a on a.id=co.rubro_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=12)   GROUP BY nombre, mes, anio,productor_id ) dt where (productor_id=? OR ? IS NULL) and anio=? or anio is null group by dt.mes, dt.nombre", ItemNomCantMes.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, anio);
        return query.getResultList();
    }

    public List<ItemNomCantMes> generarEstadisticaRentabilidadMultilineaEstado(Long productorId, Integer anio){
        Query query = em.createNativeQuery("select sum(cantidad) as cantidad, IF(p.fechaUltimaComision is null, (CASE WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) <= 30) THEN 'Nueva' WHEN (p.fechaBaja is null and (timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fecha,curdate()) <= 60)) THEN 'Atrasada' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 60) THEN 'Morosa' WHEN p.fechaBaja is not null then 'Dada de baja' END), (CASE WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) <= 30) THEN 'Nueva' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fechaUltimaComision,curdate()) <= 45) THEN 'Al dia' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and (timestampdiff(day,p.fechaUltimaComision,curdate()) > 45 and timestampdiff(day,p.fechaUltimaComision,curdate()) <= 60)) THEN 'Atrasada' WHEN (p.fechaBaja is null and timestampdiff(day,p.fecha,curdate()) > 30 and timestampdiff(day,p.fechaUltimaComision,curdate()) > 60) THEN 'Morosa' WHEN p.fechaBaja is not null then 'Dada de baja' END)) as nombre, mes from (SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 1 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=1) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 2 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=2) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 3 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=3) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 4 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=4) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 5 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=5) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 6 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=6) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 7 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=7) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 8 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=8) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 9 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=9) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 10 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=10) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 11 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=11) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id union SELECT sum(c.comisionProductor) AS cantidad, p.fechaBaja,p.fecha,p.fechaUltimaComision, case when month(p.fecha) is null then 12 else month(p.fecha) end as mes, year(p.fecha) as anio, p.productor_id FROM poliza p inner join comision c on p.id=c.poliza_id where (month(p.fecha)=12) and c.id = (select max(id) from comision where id=c.id) GROUP BY p.fechaBaja,p.fecha,p.fechaUltimaComision, mes, anio,productor_id ) p where (productor_id=? OR ? IS NULL) and anio=? or anio is null group by mes, nombre", ItemNomCantMes.class);
        query.setParameter(1, productorId);
        query.setParameter(2, productorId);
        query.setParameter(3, anio);
        return query.getResultList();
    }


    //***************************************
    //*************COMISIONES****************
    //***************************************

    public List<Item> generarEstadisticaComisionProductor(Integer mes, Integer anio, Long productorId){
        Query query = em.createNativeQuery("SELECT sum(c.comisionProductor) AS cantidad, a.nombre as nombre FROM poliza p inner join cobertura cob on cob.id=p.cobertura_id inner join aseguradora a on a.id=cob.aseguradora_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=?) and (year(c.fecha)=?) and (productor_id=? OR ? IS NULL) GROUP BY nombre", Item.class);
        query.setParameter(1, mes);
        query.setParameter(2, anio);
        query.setParameter(3, productorId);
        query.setParameter(4, productorId);
        return query.getResultList();
    }

    public List<Item> generarEstadisticaComisionVendedor(Integer mes, Integer anio, Long productorId){
        Query query = em.createNativeQuery("SELECT sum(c.comisionProductor) AS cantidad, concat(v.nombre, ' ', v.apellido) as nombre FROM vendedor v inner join productor_vendedor pv on pv.vendedor_id=v.id inner join poliza p on v.id=p.vendedor_id inner join comision c on p.id=c.poliza_id where (month(c.fecha)=?) and (year(c.fecha)=?) and (pv.productor_id=? OR ? IS NULL) GROUP BY nombre", Item.class);
        query.setParameter(1, mes);
        query.setParameter(2, anio);
        query.setParameter(3, productorId);
        query.setParameter(4, productorId);
        return query.getResultList();
    }

    //***************************************
    //*************PRODUCTORES***************
    //***************************************

    public List<ItemNomCantMes> generarEstadisticaProductores(Integer anio){
        Query query = em.createNativeQuery("  select concat(pr.nombre,' ',pr.apellido) as nombre, count(p.id) as cantidad, month(p.fecha) as mes from poliza p inner join productor pr on pr.id=p.productor_id where year(p.fecha)=? group by nombre , mes order by mes, nombre", ItemNomCantMes.class);
        query.setParameter(1, anio);
        return query.getResultList();
    }
}