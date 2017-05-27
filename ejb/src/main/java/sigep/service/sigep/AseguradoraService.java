package sigep.service.sigep;

import sigep.beans.sigep.AseguradoraBean;
import sigep.data.dao.sigep.AseguradoraDao;
import sigep.data.dao.sigep.ParametroImportacionDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.Archivo;
import sigep.model.sigep.Aseguradora;
import sigep.model.sigep.ParametroImportacion;
import sigep.service.ArchivoService;
import sigep.util.FileUtil;
import sigep.util.MockUtil.AseguradoraMock;
import sigep.util.MockUtil.OmbrelloMock;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AseguradoraService extends BaseService<Aseguradora, AseguradoraBean>{
    @Inject
    AseguradoraDao aseguradoraDao;
    @Inject
    ParametroImportacionDao parametroImportacionDao;
    @Inject
    ArchivoService archivoService;

    @Override
    public AseguradoraBean findById(Long idEntity) {
        return aseguradoraDao.findById(idEntity).getBean();
    }

    public AseguradoraBean findCompletoById(Long idEntity) {
        return aseguradoraDao.findById(idEntity).getBeanCompleto();
    }

    @Override
    public AseguradoraBean create(AseguradoraBean bean) throws SIGEPException {
        Aseguradora entity = new Aseguradora(bean);
        parametroImportacionDao.create(entity.getParametroImportacion());
        return aseguradoraDao.create(entity).getBean();
    }

    public AseguradoraBean create(AseguradoraBean bean, File file) throws SIGEPException {
        if(file != null)
            archivoService.saveFile(file, bean.getRazonSocial());
        Aseguradora entity = new Aseguradora(bean);
        if(entity.getParametroImportacion() != null)
            parametroImportacionDao.create(entity.getParametroImportacion());
        return aseguradoraDao.create(entity).getBean();
    }



    public AseguradoraBean updateReturn(AseguradoraBean bean)throws SIGEPException {
        parametroImportacionDao.update(new ParametroImportacion(bean.getParametroImportacion()));
        return aseguradoraDao.update(new Aseguradora(bean)).getBean();
    }

    @Override
    public void update(AseguradoraBean bean)throws SIGEPException {
    }

    @Override
    public void bussinessValidation(Aseguradora entity) throws SIGEPValidationException {

    }

    public List<AseguradoraBean> findAll() {
        List<Aseguradora> entities = aseguradoraDao.findAll();
        List<AseguradoraBean> beans = new ArrayList<AseguradoraBean>(entities.size());
        for(Aseguradora a : entities){
            beans.add(a.getBean());
        }
        return beans;
    }

    public List<AseguradoraBean> findAllCompleto() {
        List<Aseguradora> entities = aseguradoraDao.findAll();
        List<AseguradoraBean> beans = new ArrayList<AseguradoraBean>(entities.size());
        for(Aseguradora a : entities){
            beans.add(a.getBeanCompleto());
        }
        return beans;
    }

    public List<AseguradoraBean> findAllConLogos() {
        return setearLogos(findAll());
    }

    public List<AseguradoraBean> findAllCompletoConLogos() {
        return setearLogos(findAllCompleto());
    }

    public List<AseguradoraBean> setearLogos(List<AseguradoraBean> all){
        for(AseguradoraBean aseguradora : all){
            setearLogo(aseguradora);
        }
        return all;
    }

    public AseguradoraBean setearLogo(AseguradoraBean aseguradora){
        switch(aseguradora.getId().intValue()){
            //MOCK PARA TRAER IMAGENES 1,2,3 (AUTO, CASA, HOMBRE)
            case 1:
                aseguradora.setLogo(AseguradoraMock.LACAJA);
                break;
            case 2:
                aseguradora.setLogo(AseguradoraMock.FEDERACIONPATRONAL);
                break;
            case 3:
                aseguradora.setLogo(AseguradoraMock.SANCRISTOBAL);
                break;
            case 4:
                aseguradora.setLogo(AseguradoraMock.SANCOR);
                break;
            case 5:
                aseguradora.setLogo(AseguradoraMock.MERCANTIL);
                break;
            //PARA EL RESTO BUSCA ARCHIVO BLOB EN BD Y CONVIERTE A BASE64
            default:
                Archivo archivo = archivoService.getArchivoByNombre(aseguradora.getRazonSocial());
                aseguradora.setLogo(archivo != null ? FileUtil.getImageBase64(archivo) : OmbrelloMock.COMMON_UMBRELLA_ICON);
                break;
        }
        return aseguradora;
    }
}
