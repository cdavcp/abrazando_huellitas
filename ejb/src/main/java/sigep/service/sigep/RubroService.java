package sigep.service.sigep;

import sigep.beans.sigep.RubroBean;
import sigep.data.dao.sigep.RubroDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.Ivent.Archivo;
import sigep.model.sigep.Rubro;
import sigep.service.ArchivoService;
import sigep.util.FileUtil;
import sigep.util.MockUtil.OmbrelloMock;
import sigep.util.MockUtil.RubroMock;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RubroService extends BaseService<Rubro, RubroBean>{

    @Inject
    private RubroDao dao;
    @Inject
    private ArchivoService archivoService;

    @Override
    public RubroBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public RubroBean create(RubroBean bean) throws SIGEPException {
        return dao.create(new Rubro(bean)).getBean();
    }

    public RubroBean create(RubroBean bean, File file) throws SIGEPException {
        if(file != null)
            archivoService.saveFile(file, bean.getNombre());
        return dao.create(new Rubro(bean)).getBean();
    }

    @Override
    public void update(RubroBean bean) throws SIGEPException {
        dao.update(new Rubro(bean));
    }

    @Override
    public void bussinessValidation(Rubro entity) throws SIGEPValidationException {

    }

    public List<RubroBean> findAll(){
        List<Rubro> RubroList = dao.findAll();
        List<RubroBean> beanList = new ArrayList<RubroBean>();
        for(Rubro Rubro : RubroList)
        {
            beanList.add(Rubro.getBean());
        }
        return setearLogos(beanList);
    }


    public List<RubroBean> setearLogos(List<RubroBean> all){
        for(RubroBean rubro : all){
            switch(rubro.getId().intValue()){
                //MOCK PARA TRAER IMAGENES 1,2,3 (AUTO, CASA, HOMBRE)
                case 1:
                    rubro.setLogo(RubroMock.LOGO_AUTO);
                    break;
                case 2:
                    rubro.setLogo(RubroMock.LOGO_HOGAR);
                    break;
                case 3:
                    rubro.setLogo(RubroMock.LOGO_VIDA);
                    break;
                //PARA EL RESTO BUSCA ARCHIVO BLOB EN BD Y CONVIERTE A BASE64
                default:
                    Archivo archivo = archivoService.getArchivoByNombre(rubro.getNombre());
                    rubro.setLogo(archivo != null ? FileUtil.getImageBase64(archivo) : OmbrelloMock.COMMON_UMBRELLA_ICON);
                    break;
            }
        }
        return all;
    }

    public boolean esNombreValido(String nombre){
        return (dao.findByNombre(nombre) == null);
    }
}
