package sigep.service.sigep;

import sigep.beans.sigep.ClienteBean;
import sigep.data.dao.sigep.ClienteDao;
import sigep.data.dto.ComboDTO;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.Cliente;
import sigep.service.ArchivoService;
import sigep.util.DateUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class ClienteService extends BaseService<Cliente, ClienteBean>{

    @Inject
    ClienteDao dao;
    @Inject
    ArchivoService archivoService;

    @Override
    public ClienteBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public ClienteBean create(ClienteBean bean) {
        bean.setFechaAlta(DateUtil.currentDate());
        return dao.create(new Cliente(bean)).getBean();
    }

    public ClienteBean create(ClienteBean bean, File file) throws SIGEPException {
        if(file != null)
            archivoService.saveFile(file, bean.getNombre().concat(bean.getApellido()));
        return create(bean);
    }

    public ClienteBean createIfNotExists(ClienteBean bean){
        ClienteBean b = findActivoByDocumentoYTipoDocumento(bean.getDocumento(), bean.getTipoDocumento().getId());
        return  b != null ? b : create(bean);
    }

    @Override
    public void update(ClienteBean bean) throws SIGEPException {
        dao.update(new Cliente(bean));
    }

    @Override
    public void bussinessValidation(Cliente entity) throws SIGEPValidationException {

    }

    public void delete(ClienteBean bean)throws SIGEPException {
        bean.setFechaBaja(DateUtil.currentDate());
        dao.update(new Cliente(bean));
    }

    public List<ClienteBean> findAll() {
        List<Cliente> clienteList = dao.findAllActives();
        List<ClienteBean> beanList = new ArrayList<ClienteBean>();
        ClienteBean beanAux;
        for(Cliente cliente : clienteList)
        {
            beanAux = cliente.getBean();
            beanList.add(beanAux);
        }
        return beanList;
    }

    public ClienteBean findActivoByDocumentoYTipoDocumento(String documento, Long tipoDocumentoId){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("documento", documento);
        filter.put("tipoDocumento_id", tipoDocumentoId);
        filter.put("fechaBaja", null);
        return dao.findAll(filter).isEmpty() ? null : dao.findAll(filter).get(0).getBean();
    }

    public ClienteBean findByDocumentoYTipoDocumento(String documento, Long tipoDocumentoId){
        ClienteBean cliente = findActivoByDocumentoYTipoDocumento(documento, tipoDocumentoId);
        return cliente;
    }

    public List<ComboDTO> findAllComboBeans() {
        List<Cliente> clienteList = dao.findAllActives();
        List<ComboDTO> beanList = new ArrayList<ComboDTO>();
        ComboDTO beanAux;
        for(Cliente cliente : clienteList)
        {
            beanAux = new ComboDTO();
            beanAux.setNombre(cliente.getNombre() + " " + cliente.getApellido());
            beanAux.setId(cliente.getId());
            beanList.add(beanAux);
        }
        return beanList;
    }

    public boolean exists(ClienteBean cliente){
        return findActivoByDocumentoYTipoDocumento(cliente.getDocumento(), cliente.getTipoDocumento().getId()) != null;
    }
}
