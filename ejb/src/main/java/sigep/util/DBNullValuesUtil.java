package sigep.util;

import java.util.Date;

public class DBNullValuesUtil
{
	private static final int NULL_VALUE_INT = -1;
	private static final Date NULL_VALUE_DATE = DateUtil.createMinDate();
    private static final Date NULL_VALUE_CURRENT_DATE = DateUtil.currentDate();
	private static final String NULL_VALUE_STRING = null;
	private static final char NULL_VALUE_CHAR = 'N';
	
	
	public static Integer getValueOrNullDB(Integer value)
	{
		return value == null ? NULL_VALUE_INT : value;
	}
	
	public static Date getValueOrNullDB(Date value)
	{
		return value == null ? NULL_VALUE_DATE : value;
	}

    public static Date getValueOrNullDB(Date value, boolean esFechaHasta)
    {
        if(esFechaHasta)
            return value == null ? NULL_VALUE_CURRENT_DATE : value;
        else
            return value == null ? NULL_VALUE_DATE : value;
    }
	
	public static String getValueOrNullDB(String value)
	{
		return StringUtil.estaVacio(value) ? NULL_VALUE_STRING : value;
	}
	
	public static Character getValueOrNullDB(Character value)
	{
		return value == null ? NULL_VALUE_CHAR : value;
	}
	
	public static char getCharValueFromBoolean(boolean value)
	{
		return value ? 'S' : 'N';
	}
}
