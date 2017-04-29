package sigep.util;

public enum EnumParameterDataTypes {

    LOGICO("LOGICO"),
    ALFANUMERICO("ALFANUMERICO"),
    TEXTO("TEXTO"),
    DIRECTORIO("DIRECTORIO"),
    NUMERICO("NUMERICO"),
    FECHA("FECHA"),
    FECHA_HORA("FECHAHORA");


    final String name;

    EnumParameterDataTypes(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

}
