package sigep.util;

/**
 * Created by Asus PC on 11/07/2015.
 */
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JndiProvider
{
    private static Context context = null;



    public static Context getContext()	throws NamingException
    {
        if (context == null)
        {
            Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            context = new InitialContext(jndiProperties);
        }

        return context;
    }


    public static <T>  T getService(Class<T> srvClass) throws NamingException
    {
        String ejbResourceName = "java:module/" + srvClass.getSimpleName() + "!" + srvClass.getName();

        //System.out.println(ejbResourceName);

        return (T) getContext().lookup(ejbResourceName);

    }




}

