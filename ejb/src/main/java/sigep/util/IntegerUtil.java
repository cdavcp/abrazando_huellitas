package sigep.util;

import java.util.List;

public class IntegerUtil
{
    public static Integer cast(String text) {
        Integer number = null;

        try
        {
            number = Integer.valueOf(text);
        }
        catch(NumberFormatException e) {}

        return number;
    }

    public static String join (List<Integer> list, String separator) {
        StringBuilder listStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++)
        {
            listStr.append(list.get(i));
            if (i < list.size() - 1)
            {
                listStr.append(separator);
            }
        }
        return listStr.toString();
    }
}