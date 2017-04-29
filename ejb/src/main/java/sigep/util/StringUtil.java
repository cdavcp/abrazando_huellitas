package sigep.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class StringUtil
{
	
	private static final HashMap<String, String> hMap;
    static
    {
        hMap = new HashMap<String, String>(20);
        hMap.put("Ã¡", "á");
		hMap.put("Ã©", "é");
		hMap.put("Ã*", "í");
		hMap.put("Ã³", "ó");
		hMap.put("Ãº", "ú");
		hMap.put("Ã", "Á");
		hMap.put("Ã‰", "É");
		hMap.put("Ã", "Í");
		hMap.put("Ã“", "Ó");
		hMap.put("Ãš", "Ú");
		hMap.put("Ã±", "ñ");
		hMap.put("Ã‘", "Ñ");
		hMap.put("Âº", "º");
		hMap.put("Âª", "ª");
		hMap.put("Â¿", "¿");
    }
	
	public static boolean contieneDigito(String cadena)
	{
		for (int i = 0; i < cadena.length(); i++)
		{
			if (Character.isDigit(cadena.charAt(i)))
				return true;
		}
		return false;
	}
	
	public static boolean contieneLetra(String cadena)
	{
		for (int i = 0; i < cadena.length(); i++)
		{
			if (Character.isAlphabetic(cadena.charAt(i)))
				return true;
		}
		return false;
	}
	
	public static boolean contieneDigito(String ... cadena)
	{
		boolean vacio = false;
		for (String c : cadena)
		{
			vacio |= contieneDigito(c);
		}
		return vacio;
	}

	public static boolean contieneDobleEspaciado(String cadena)
	{
		return cadena.contains("  ");
	}
	
	public static boolean contieneDobleEspaciado(String ... cadena)
	{
		boolean vacio = false;
		for (String c : cadena)
		{
			vacio |= contieneDobleEspaciado(c);
		}
		return vacio;
	}

	public static boolean estaVacio(String cadena)
	{
		return cadena == null || cadena.trim().isEmpty();
	}

    /**
     * Verifica que alguna de las cadenas recibida por parametro
     * esta vacia o sea null.
     * @param cadena
     * @return true si alguno esta vacio
     */
	public static boolean estaVacio(String ... cadena)
	{
		boolean vacio = false;
		for (String c : cadena)
		{
			vacio |= estaVacio(c);
		}
		return vacio;
	}
	
	public static boolean longitudCorrecta(String cadena, int longitud){
		
		return cadena.trim().length() <= longitud;
	}
	public static boolean longitudCorrectaCadenaMinima(String cadena, int longitud){
		
		return cadena.trim().length() >= longitud;
	}
	
	public static String replaceUTFSimbols(String string)
	{
		Iterator<Entry<String, String>> it = hMap.entrySet().iterator();
		
		while (it.hasNext())
		{
			Map.Entry<String, String> e = it.next();
			string = string.replace(e.getKey(), e.getValue());
		}
		return string;
	}

    public static void concatWordIfNotNullOrEmpty(StringBuilder b, String ... word)
    {
        for (String w : word)
        {
            if (!estaVacio(w))
            {
                b.append(b.length() == 0 ? w : " " + w);
            }
        }
    }

    public static String concatWordIfNotNullOrEmpty(String ... words)
    {
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < words.length; i++)
        {
            if (!estaVacio(words[i]))
            {
                if (i == words.length - 1)
                    b.append(words[i].trim());
                else
                    b.append(words[i].trim()).append(" ");
            }
        }

        return b.toString();
    }
    
    public static String quitarTabulaciones(String cadena)
    {
    	return cadena.replace("\n", "").replace("\r", "").replace("\t", "");    	
    }

    public static String trimDoubleQuotes(String text) {
        int textLength = text.length();

        if (textLength >= 2 && text.charAt(0) == '"' && text.charAt(textLength - 1) == '"') {
            return text.substring(1, textLength - 1);
        }

        return text;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static String excelToDoubleNumberFormat(String excelNumber){
        return excelNumber.replace(".", "").replace(",",".");
    }
}
