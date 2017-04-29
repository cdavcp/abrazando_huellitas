package sigep.service;

import sigep.beans.TipoUsuarioBean;
import sigep.data.dao.TipoUsuarioDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.TipoUsuario;
import sigep.service.sigep.BaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TipoUsuarioService extends BaseService<TipoUsuario, TipoUsuarioBean> {
    @Inject
    private TipoUsuarioDao dao;

    @Override
    public TipoUsuarioBean findById(Long idEntity) {
        return null;
    }

    @Override
    public TipoUsuarioBean create(TipoUsuarioBean bean) throws SIGEPException {
        return null;
    }

    @Override
    public void update(TipoUsuarioBean bean) throws SIGEPException {
    }

    @Override
    public void bussinessValidation(TipoUsuario entity) throws SIGEPValidationException {

    }

    public List<TipoUsuarioBean> findAll(){
        List<TipoUsuario> tipoUsuarioList = dao.findAll();
        List<TipoUsuarioBean> beanList = new ArrayList<TipoUsuarioBean>();
        for(TipoUsuario tipoUsuario : tipoUsuarioList)
        {
            beanList.add(tipoUsuario.getBean());
        }
        return beanList;
    }
}
