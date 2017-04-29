package sigep.data.dao;

import sigep.model.EntityBase;
import sigep.util.DateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class DaoBase<T extends EntityBase> {


    @PersistenceContext(unitName = "primary")
    protected EntityManager em;

    private Class<T> entityClass;

    public DaoBase()
    {
        entityClass = getGenericTypeClass(getClass());
    }

    Class<T> getGenericParamater() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findById(Long idEntity) {
        return em.find(getGenericParamater(), idEntity);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(getGenericParamater());
        Root<T> entity = criteria.from(getGenericParamater());
        criteria.select(entity);
        return em.createQuery(criteria).getResultList();
    }

    public List<T> findAllActives(){
        String className = getEntityClass().getSimpleName();
        String activeOnly = "";

        try { activeOnly = getEntityClass().getDeclaredField("fechaBaja") != null ? " WHERE fechaBaja IS NULL" : ""; }
        catch ( NoSuchFieldException e ) { }

        Query query = em.createQuery("SELECT e FROM " + className + " e" + activeOnly);

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(HashMap<String, Object> filters)
    {
        try
        {
            String className = getEntityClass().getSimpleName();

            Iterator<String> it = filters.keySet().iterator();
            StringBuilder jpql = new StringBuilder();

            jpql.append("SELECT e FROM ").append(className).append(" e");

            if (!filters.isEmpty())
            {
                jpql.append(" WHERE ");

                while (it.hasNext())
                {
                    String key = it.next();
                    Object value = filters.get(key);

                    if (value == null)
                        jpql.append(key).append(" IS NULL");
                    else if(value.equals(-1) || value.equals(DateUtil.createMinDate())){
                        continue;
                    }else if (value instanceof String)
                        jpql.append("UPPER(").append(key).append(") LIKE :").append(key);

                    else
                        jpql.append(key).append("=:").append(key);

                    jpql.append(" AND ");
                }
                jpql = jpql.replace(jpql.length() - 5, jpql.length() - 1, "");
            }

            Query query = em.createQuery(jpql.toString());

            for (String key : filters.keySet())
            {
                Object value = filters.get(key);

                if (value != null)
                {
                    if (value.equals(-1) || value.equals(DateUtil.createMinDate()))
                    {
                        continue;
                    }
                    if (value instanceof String)
                        query.setParameter(key, ((String) value).toUpperCase() + "%");
                    else if (value instanceof Boolean)
                        query.setParameter(key, ((Boolean) value) ? 'S' : 'N');
                    else
                        query.setParameter(key, value);
                }
            }
            return query.getResultList();
        }
        catch (Exception ex)
        {
            Logger log = Logger.getLogger("myLogger");
            log.log(Level.ALL, "Error mapeando con HashMap");
        }
        return null;
    }

    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    public T update(T entity) {
        em.merge(entity);
        return entity;
    }

    public void delete(Long id) {
        em.remove(findById(id));
    }

    public void deleteLogical(T entity) {
        em.merge(entity);
    }

    protected Class<T> getEntityClass()
    {
        return entityClass;
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getGenericTypeClass(Class<?> targetClass)
    {
        return (Class<T>) ((ParameterizedType) targetClass.getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
