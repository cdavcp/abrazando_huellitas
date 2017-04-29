package sigep.util;

import java.util.UUID;

public class SessionGenerator {

    public static String getRandomId()
    {
        return UUID.randomUUID().toString();
    }
}
