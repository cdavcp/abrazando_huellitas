package sigep.data.dao;

import sigep.model.Usuario;

import javax.persistence.Query;
import java.util.List;

public class UsuarioDao extends DaoBase<Usuario> {

    public Usuario findByLogin(String usuario, String pass) {
        String query = "SELECT u FROM Usuario u WHERE u.nombre LIKE '" + usuario + "' AND u.pass LIKE '" + pass + "'";
        List<Usuario> listResult = em.createQuery(query, Usuario.class).getResultList();
        return (listResult.isEmpty()) ? null : listResult.get(0);
    }

    public Usuario findBySession(String session) {

        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.sessionId LIKE :session",
                Usuario.class);
        query.setParameter("session", session);
        List<Usuario> listResult = query.getResultList();
        return (listResult.isEmpty()) ? null : listResult.get(0);
    }

    public List<Usuario> findByNombre(String nombreUsuario) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombreUsuario",
                Usuario.class);
        query.setParameter("nombreUsuario", nombreUsuario);
        List<Usuario> listResult = query.getResultList();
        return listResult;
    }
}
