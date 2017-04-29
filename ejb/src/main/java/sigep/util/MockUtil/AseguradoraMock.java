package sigep.util.MockUtil;

/**
 * Created by Asus PC on 16/07/2015.
 */
public class AseguradoraMock {

    private static final String BASE64_JPG = "data:image/jpeg;base64,";
    public static final String LACAJA = BASE64_JPG + "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";
    public static final String FEDERACIONPATRONAL = BASE64_JPG + "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";
    public static final String SANCRISTOBAL = BASE64_JPG + "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";
    public static final String SANCOR = BASE64_JPG + "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";
    public static final String MERCANTIL = BASE64_JPG + "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";
}