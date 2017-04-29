package sigep.util;

public class ComparisonUtil
{
	/**
	 * Se comparan 2 objetos para verificar si son distintos. En el caso de
	 * cadenas no se tienen en cuenta mayúsculas. El método está pensado para
	 * comparar 2 objetos del mismo tipo, sin embargo, si los tipos son
	 * distintos se devuelve true (son distintos)
	 * 
	 * @param obj1
	 * @param obj2
	 * @return true si los objetos son distintos, false si son iguales.
	 */
	public static boolean sonDistintos(Object obj1, Object obj2)
	{
		boolean twoNull = obj1 == null && obj2 == null;
		if (twoNull)
			return false;
		boolean oneNull = (obj1 == null && obj2 != null)
				|| (obj1 != null && obj2 == null);
		if (oneNull)
			return true;

		if (!obj1.getClass().equals(obj2.getClass()))
			return true;

		boolean equals = false;
		if (obj1.getClass().equals(String.class))
		{
			equals = ((String) obj1).equalsIgnoreCase((String) obj2);
		}
		else
			equals = obj1.equals(obj2);

		return !equals;
	}

    public static <T>  T ifNotNull(T nully,T oldValue){
         if(nully==null)
             return oldValue;
        else return nully;
    }
}
