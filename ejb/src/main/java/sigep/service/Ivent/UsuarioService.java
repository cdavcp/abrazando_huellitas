package sigep.service.Ivent;

import sigep.beans.Ivent.UsuarioBean;
import sigep.data.dao.Ivent.UsuarioDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.Archivo;
import sigep.model.Ivent.Usuario;
import sigep.service.ArchivoService;
import sigep.service.sigep.BaseService;
import sigep.service.sigep.ProductorService;
import sigep.util.FileUtil;
import sigep.util.JndiProvider;
import sigep.util.MockUtil.ProductorMock;
import sigep.util.MockUtil.UsuarioMock;
import sigep.util.SessionGenerator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UsuarioService extends BaseService<Usuario, UsuarioBean> {
    @Inject
    private UsuarioDao dao;
    @Inject
    private ArchivoService archivoService;

    private ProductorService productorService;

    @Override
    public UsuarioBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public UsuarioBean create(UsuarioBean bean) throws SIGEPException {
        return dao.create(new Usuario(bean)).getBean();
    }

    @Override
    public void update(UsuarioBean bean) throws SIGEPException {
        dao.update(new Usuario(bean));
    }

    @Override
    public void bussinessValidation(Usuario entity) throws SIGEPValidationException {

    }

    public UsuarioBean login(String usuario, String pass) throws SIGEPException {
        String sessionId = SessionGenerator.getRandomId();
        Usuario bus = dao.findByLogin(usuario, pass);
        if(bus != null)
        {
            bus.setSessionId(sessionId);
            UsuarioBean bean = bus.getBean();
            setearFoto(bean);
            return bean;
        }
        return null;
    }

    public void logout(UsuarioBean usuario){
        usuario.setSessionId(null);
        dao.update(new Usuario(usuario));
    }

    public UsuarioBean findBySession(String session)
    {
        Usuario temp = dao.findBySession(session);
        UsuarioBean bean = null;
        if(temp != null){
            bean = temp.getBean();
            setearFoto(bean);
        }
        return bean;
    }



    public boolean changePassword(String newPass, String previousPass, Long usuarioId){
        Usuario usuario = dao.findById(usuarioId);
        if(usuario.getPass().equals(previousPass))
        {
            usuario.setPass(newPass);
            return true;
        } else {
            return false;
        }
    }

    public boolean validarNombreUsuario(String nombre){
        return (dao.findByNombre(nombre).size() == 0);
    }

    public PersonaService getPersonaService(){
        try {
            return JndiProvider.getService(PersonaService.class);
        } catch (NamingException e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.INFO, null, e);
            return null;
        }
    }

    private void setearFoto(UsuarioBean bean){
        Long personaId = getPersonaService().getIdPersonaSiExiste(bean.getId());
        if(personaId != null){
            if(personaId.equals(new Long(1))){
                bean.setFoto(UsuarioMock.FOTO_MARLEY);
            }else{
                String nombreArchivo = PersonaService.PREFIJO_ARCHIVO + personaId;
                Archivo a = archivoService.getArchivoByNombre(nombreArchivo);
                bean.setFoto(a != null ? FileUtil.getImageBase64(a) : ProductorMock.AVATAR);
            }
        }
    }
}
