package sigep.util.MockUtil;

/**
 * Created by Asus PC on 16/07/2015.
 */
public class UsuarioMock {

    private static final String BASE64_JPG = "data:image/jpeg;base64,";
    public static final String FOTO_LIBERATORI = BASE64_JPG + "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";
    public static final String AVATAR = BASE64_JPG + "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";
    public static final String FOTO_MARLEY = BASE64_JPG + "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";
}
