package sigep.util.MockUtil;

/**
 * Created by Asus PC on 17/07/2015.
 */
public class OmbrelloMock {

    private static final String BASE64_JPG = "data:image/jpeg;base64,";
    public static final String COMMON_UMBRELLA_ICON = BASE64_JPG + "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";

}
