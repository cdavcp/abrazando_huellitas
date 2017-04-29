package sigep.service;

import sigep.beans.PersonaBean;
import sigep.data.dao.PersonaDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Archivo;
import sigep.model.Persona;
import sigep.service.sigep.BaseService;
import sigep.util.DateUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.util.List;

@Stateless
public class PersonaService extends BaseService<Persona, PersonaBean> {
    @Inject
    private PersonaDao dao;
    @Inject
    private UsuarioService usuarioService;
    @Inject
    ArchivoService archivoService;

    public static final String PREFIJO_ARCHIVO = "persona";

    @Override
    public PersonaBean findById(Long idEntity) {
//        return dao.findById(idEntity).getBeanCompleto();
        return null;
    }

    public PersonaBean findByIdConFoto(Long idEntity) {
//        PersonaBean persona = dao.findById(idEntity).getBeanCompleto();
//        String nombreArchivo = PREFIJO_ARCHIVO + persona.getIdPersona();
//        Archivo a = archivoService.getArchivoByNombre(nombreArchivo);
//        persona.setFoto(a != null ? FileUtil.getImageBase64(a) : PersonaMock.AVATAR);
//        return persona;
        return null;
    }

    public PersonaBean findByUsuario(Long idUsuario)
    {
        Persona temp = dao.findByUsuario(idUsuario);
        PersonaBean bean = null;
        if(temp != null){
            bean = temp.getBeanCompleto();
        }
        return bean;
    }

    @Override
    public PersonaBean create(PersonaBean bean) throws SIGEPException {
        bean.setUsuario(usuarioService.create(bean.getUsuario()));
        bean.setFechaAlta(DateUtil.currentDate());
        return dao.create(new Persona(bean)).getBeanCompleto();
    }

    public void create(PersonaBean bean, File file) throws SIGEPException {
        bean.setUsuario(usuarioService.create(bean.getUsuario()));
        bean.setFechaAlta(DateUtil.currentDate());
        Persona bus = dao.create(new Persona(bean));
        if(file != null){
            String nombre = PREFIJO_ARCHIVO + bus.getId();
            //REUTILIZAR PARA UPDATE
            Archivo a = archivoService.getArchivoByNombre(nombre);
            if(a != null){
                archivoService.deleteByNombre(nombre);
            }
            archivoService.saveFile(file, nombre);
        }

    }

    @Override
    public void update(PersonaBean bean) throws SIGEPException {
//        dao.update(new Persona(bean));
    }

    public void update(PersonaBean bean, File file) throws SIGEPException {
//        if(file != null){
//            String nombre = PREFIJO_ARCHIVO + bean.getIdPersonaPersona();
//            Archivo a = archivoService.getArchivoByNombre(nombre);
//            if(a != null){
//                archivoService.deleteByNombre(nombre);
//            }
//            archivoService.saveFile(file, nombre);
//        }
//
//        dao.update(new Persona(bean));
    }

    @Override
    public void bussinessValidation(Persona entity) throws SIGEPValidationException {

    }

    public void delete(PersonaBean bean)throws SIGEPException {
//        bean.setFechaBaja(DateUtil.currentDate());
//        dao.update(new Persona(bean));
    }

    public List<PersonaBean> findAll(){
//        List<Persona> personaList = dao.findAllActives();
//        List<PersonaBean> beanList = new ArrayList<PersonaBean>();
//        for(Persona persona : personaList)
//        {
//            beanList.add(persona.getBeanCompleto());
//        }
//        return beanList;
        return null;
    }

    public PersonaBean findPersonaByUsuario(Long idUsuario) {
       return dao.findByUsuario(idUsuario).getBeanCompleto();

    }

    public PersonaBean findByUsuarioConFoto(Long idUsuario) {
//        PersonaBean persona = dao.findByUsuario(idUsuario).getBeanCompleto();
//        String nombreArchivo = PREFIJO_ARCHIVO + persona.getIdPersona();
//        Archivo a = archivoService.getArchivoByNombre(nombreArchivo);
//        persona.setFoto(a != null ? FileUtil.getImageBase64(a) : PersonaMock.AVATAR);
//        return persona;
        return null;
    }

    public Long getIdPersonaSiExiste(Long idUsuario){
        Persona per = dao.findByUsuario(idUsuario);
        return (per != null) ? per.getId() : null;
    }
}
