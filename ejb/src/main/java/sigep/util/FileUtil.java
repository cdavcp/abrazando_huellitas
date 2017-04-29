package sigep.util;

import org.apache.commons.codec.binary.Base64;
import sigep.model.Archivo;

public class FileUtil {

    private static final String IMG_PREFIX = "data:image/jpeg;base64,";

    public static String getImageBase64(Archivo archivo){
        return IMG_PREFIX.concat(getBase64(archivo));
    }

    public static String getBase64 (Archivo archivo){
        return Base64.encodeBase64String(archivo.getArchivo());
    }

}
