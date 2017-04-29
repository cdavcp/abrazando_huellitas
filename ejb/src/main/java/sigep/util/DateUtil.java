package sigep.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil
{

	public static final long MILISEGUNDOS_A_SEGUNDO = 1000;
	public static final long MILISEGUNDOS_A_MINUTO = MILISEGUNDOS_A_SEGUNDO * 60;
	public static final long MILISEGUNDOS_A_HORA = MILISEGUNDOS_A_MINUTO * 60;
	public static final long MILISEGUNDOS_A_DIA = MILISEGUNDOS_A_HORA * 24;
	public static final long MILISEGUNDOS_A_ANIO = MILISEGUNDOS_A_HORA * 24;

	public static Date currentDate()
	{
		return currentCalendarDate().getTime();
	}
	
	public static Calendar currentCalendarDate() 
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal;
	}

	/***
	 * Crea una nueva instancia de la clase fecha
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static Date create(int year, int month, int date)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
	
	/**
	 * Genera una fecha con el valor mínimo, equivalente a un valor nulo para la base de datos
	 * @return
	 */
	public static Date createMinDate() 
	{
		return DateUtil.create(1900, 01, 01);
	}
	
	/**
	 * Genera una fecha con un valor muy grande (año 3000).
	 * @return
	 */
	public static Date createMaxDate() 
	{
		return DateUtil.create(3000, 01, 01);
	}

	/***
	 * Convierte una fecha al formato corto de visualizacion (dd/mm/aaaa)
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date)
	{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleFormat.format(date);
	}
	
	/***
	 * Convierte una fecha al formato de visualizacion (dd/mm/aaaa HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date)
	{
		SimpleDateFormat sF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sF.format(date);
	}
	
	/***
	 * Convierte una fecha al formato de visualizacion (aaaa)
	 * 
	 * @param date
	 * @return
	 */
	public static String formatYear(Date date)
	{
		SimpleDateFormat sF = new SimpleDateFormat("yyyy");
		return sF.format(date);
	}

	public static Date format(String date)
	{
		try
		{
			SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
			return simpleFormat.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return null;
	}

    public static Date format(String date, String format)
    {
        try
        {
            SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
            return simpleFormat.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String formatToMySQLDB(Date date)
    {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return simpleFormat.format(date);
    }

	public static String formatToDB(Date date)
	{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return simpleFormat.format(date);
	}

	public static Date formatFromDB(String text)
	{
		Date date = null;

		if (text != null)
		{
			SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			try
			{
				date = simpleFormat.parse(text);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}

		return date;
	}

	public static Calendar convertToCalendar(Date date)
	{
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar;
	}

	public static Calendar convertToCalendar(String shortFormatDate)
	{
		Date date = format(shortFormatDate);
		return convertToCalendar(date);
	}

	/***
	 * Calcula la diferencia en milisegundos entre dos fechas y retorna el
	 * resultado en la unidad especificada
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long diff(Date date1, Date date2, long unidad)
	{
		long diferencia = date1.getTime() - date2.getTime();
		return diferencia / unidad;
	}

    public static String getTimeDiff(Date dateOne, Date dateTwo) {
        String diff = "";
        long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());
        long minutos = TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff));
        long segundos = TimeUnit.MILLISECONDS.toSeconds(timeDiff) - TimeUnit.MINUTES.toSeconds(minutos) - TimeUnit.HOURS.toSeconds(TimeUnit.MILLISECONDS.toHours(timeDiff));
        diff = String.format("%d hora(s) %d minuto(s) %d segundo(s)", TimeUnit.MILLISECONDS.toHours(timeDiff),
                minutos, segundos);
        return diff;
    }

	/***
	 * Calcula la diferencia en milisegundos entre dos fechas
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long diff(Date date1, Date date2)
	{
		return diff(date1, date2, 1);
	}
	
	public static String dateInLoteRechazadoFormat()
	{
		Calendar calendar = Calendar.getInstance();
		StringBuilder builder = new StringBuilder();
		NumberFormat formatter = new DecimalFormat("00");
		
		builder.append("R").append(calendar.get(Calendar.YEAR)).append(formatter.format(calendar.get(Calendar.MONTH)+1)).append(formatter.format(calendar.get(Calendar.DAY_OF_MONTH)));
		builder.append(formatter.format(calendar.get(Calendar.HOUR_OF_DAY))).append(formatter.format(calendar.get(Calendar.MINUTE))).append(formatter.format(calendar.get(Calendar.SECOND)));
		
		return builder.toString();
		
	}

    public static Date currentDateWithTime()
    {
        Calendar calendar = Calendar.getInstance();

        return calendar.getTime();
    }


    public static int daysBetween(Date dateA,Date dateB){
        long diff = dateB.getTime() - dateA.getTime();
        int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return days;
    }

    /***
     * Recibe un objeto Date y setea la hora en 00:00:00
     *
     * @param date El objeto Date a procesar
     * @return El objeto Date con la hora modificada
     */
    public static Date removeTime(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static int getDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static int getYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
}
