package sigep.service.sigep;

import sigep.beans.sigep.ProductorBean;
import sigep.data.dao.sigep.ProductorDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Archivo;
import sigep.model.sigep.Productor;
import sigep.service.ArchivoService;
import sigep.service.UsuarioService;
import sigep.util.DateUtil;
import sigep.util.FileUtil;
import sigep.util.MockUtil.ProductorMock;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductorService extends BaseService<Productor, ProductorBean>{
    @Inject
    private ProductorDao dao;
    @Inject
    private UsuarioService usuarioService;
    @Inject
    ArchivoService archivoService;

    public static final String PREFIJO_ARCHIVO = "PRODUCTOR";

    @Override
    public ProductorBean findById(Long idEntity) {
        return dao.findById(idEntity).getBeanCompleto();
    }

    public ProductorBean findByIdConFoto(Long idEntity) {
        ProductorBean productor = dao.findById(idEntity).getBeanCompleto();
        String nombreArchivo = PREFIJO_ARCHIVO + productor.getId();
        Archivo a = archivoService.getArchivoByNombre(nombreArchivo);
        productor.setFoto(a != null ? FileUtil.getImageBase64(a) : ProductorMock.AVATAR);
        return productor;
    }

    @Override
    public ProductorBean create(ProductorBean bean) throws SIGEPException {
        bean.setUsuario(usuarioService.create(bean.getUsuario()));
        bean.setFechaAlta(DateUtil.currentDate());
        return dao.create(new Productor(bean)).getBeanCompleto();
    }

    @Override
    public void update(ProductorBean bean) throws SIGEPException {
        dao.update(new Productor(bean));
    }

    public void update(ProductorBean bean, File file) throws SIGEPException {
        if(file != null){
            String nombre = PREFIJO_ARCHIVO + bean.getId();
            Archivo a = archivoService.getArchivoByNombre(nombre);
            if(a != null){
                archivoService.deleteByNombre(nombre);
            }
            archivoService.saveFile(file, nombre);
        }

        dao.update(new Productor(bean));
    }

    @Override
    public void bussinessValidation(Productor entity) throws SIGEPValidationException {

    }

    public void delete(ProductorBean bean)throws SIGEPException {
        bean.setFechaBaja(DateUtil.currentDate());
        dao.update(new Productor(bean));
    }

    public List<ProductorBean> findAll(){
        List<Productor> productorList = dao.findAllActives();
        List<ProductorBean> beanList = new ArrayList<ProductorBean>();
        for(Productor productor : productorList)
        {
            beanList.add(productor.getBeanCompleto());
        }
        return beanList;
    }

    public ProductorBean findByUsuario(Long idUsuario) {
        return dao.findByUsuario(idUsuario).getBeanCompleto();
    }

    public ProductorBean findByUsuarioConFoto(Long idUsuario) {
        ProductorBean productor = dao.findByUsuario(idUsuario).getBeanCompleto();
        String nombreArchivo = PREFIJO_ARCHIVO + productor.getId();
        Archivo a = archivoService.getArchivoByNombre(nombreArchivo);
        productor.setFoto(a != null ? FileUtil.getImageBase64(a) : ProductorMock.AVATAR);
        return productor;
    }

    public Long getIdProductorSiExiste(Long idUsuario){
        Productor prod = dao.findByUsuario(idUsuario);
        return (prod != null) ? prod.getId() : null;
    }
}
