package sigep.service;

import sigep.data.dao.ArchivoDao;
import sigep.model.Archivo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

@Stateless
public class ArchivoService{

    @Inject
    ArchivoDao dao;

    public void saveFile(File file, String nombre){

        byte[] bFile = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Archivo archivo = new Archivo();
        archivo.setNombre(nombre);
        archivo.setArchivo(bFile);
        dao.create(archivo);

    }

    public Archivo getArchivoByNombre(String nombre){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("nombre", nombre);
        List<Archivo> list= dao.findAll(filter);
        return list.isEmpty() ? null : list.get(0);
    }

    public void deleteByNombre(String nombre){
        HashMap<String, Object> filter = new HashMap<String, Object>();
        filter.put("nombre", nombre);
        List<Archivo> list= dao.findAll(filter);
        dao.delete(list.get(0).getId());
    }

}
