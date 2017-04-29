package sigep.util;

import org.apache.commons.lang.WordUtils;

import javax.validation.constraints.Size;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ReflectionUtil
{
	/* Generic Types */

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericTypeClass(Class<?> targetClass)
	{
		return (Class<T>) ((ParameterizedType) targetClass.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/* Annotations */

	public static ArrayList<Field> getFieldsByAnnotation(Object sourceObject, Class<? extends Annotation> annotationClass)
	{
		return getClassFieldsByAnnotation(sourceObject.getClass(), annotationClass);
	}
	
	public static ArrayList<Field> getClassFieldsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass)
	{
		ArrayList<Field> fields = new ArrayList<Field>();

		for (Field field : clazz.getDeclaredFields())
		{
			if ((field.isAnnotationPresent(annotationClass)))
			{
				fields.add(field);
			}
		}

		return fields;
	}

	/* Fields */

	public static Field getField(Class<?> sourceClass, String fieldName)
			throws NoSuchFieldException, SecurityException
	{
		return sourceClass.getDeclaredField(fieldName);
	}

	public static void setField(Object object, String fieldName, Object value)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException
	{
		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, value);
		field.setAccessible(false);
	}

	public static Object getFieldValue(Object object, Field field)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException
	{
		field.setAccessible(true);
		Object returnValue = (Object) field.get(object);
		field.setAccessible(false);

		return returnValue;
	}

	public static Object getFieldValue(Object object, String fieldName)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException
	{
		String methodName = getMethodNameFromField(fieldName);
		Method method = object.getClass().getMethod(methodName);
		method.setAccessible(true);
		Object value = getMethodValue(object, method);

		return value;
	}

//    public static Method getMethod(Class<?> targetClass, String fieldName)
//            throws NoSuchMethodException, SecurityException,
//            IllegalAccessException, IllegalArgumentException,
//            InvocationTargetException
//    {
//        String methodName = getMethodNameFromField(fieldName);
//        Method method = targetClass.getMethod(methodName);
//
//        return method;
//    }

	public static String getMethodNameFromField(String fieldName)
	{
		return String.format("get%s", WordUtils.capitalize(fieldName));
	}

	public static Object getMethodValue(Object object, Method method)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException
	{

		return method.invoke(object);
	}

//	public static Object copyValues(Object sourceObject, Class<?> targetClass)
//			throws Exception
//	{
//		Object targetValue = (Object) targetClass.newInstance();
//
//		for (Field field : sourceObject.getClass().getDeclaredFields())
//		{
//			setField(targetValue, field.getName(), getFieldValue(sourceObject, field.getName()));
//		}
//
//		return targetValue;
//	}

	/**
	 * Recibo un String con el nombre de una clase en mayuscula y/o con espacios
	 * y retorno un String con el nombre de la clase en notacion CamelCase.
	 * 
	 * 
	 * @param className
	 *            : Ej.: DATOS RECTIFICAR COMPLETO
	 * @return Ej.: DatosRectificarCompleto
	 */
	public static String getCamelCaseClassName(String className)
	{

		StringBuffer camelCaseClassName = new StringBuffer();

		for (String word : className.split(" "))
		{
			camelCaseClassName.append(word.substring(0, 1).toUpperCase());
			camelCaseClassName.append(word.substring(1, word.length()).toLowerCase());
		}

		return camelCaseClassName.toString();
	}

	@SuppressWarnings("rawtypes")
	public static Integer getAnnotationMaxSizeValue(Class c, String field)
	{

		int maxSizeValue = 0;
		Field fieldNombre;
		try
		{
			fieldNombre = c.getDeclaredField(field);
			maxSizeValue = fieldNombre.getAnnotation(Size.class).max();

		}
		catch (NoSuchFieldException e)
		{
			return null;
		}
		catch (NullPointerException e)
		{
			return null;
		}
		return maxSizeValue;
	}

	public static boolean implementsInterface(Class<?> interfaz, Object object)
	{
		Class<?>[] interfaces = object.getClass().getInterfaces();
		boolean result = false;
		for (Class<?> i : interfaces)
		{
			if (i.equals(interfaz))
				;
			{
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Actualiza un objeto a partir de otro. Ambos objetos deben implementar la
	 * misma interfaz.
	 * 
	 * @param interfaz
	 *            :Interfaz que contiene los métodos con los que se actualizará
	 *            el objeto destino
	 * @param source
	 *            :Objeto origen, debe implementar parametro interfaz.
	 * @param destination
	 *            :Objeto destino, debe implementar parametro interfaz
	 * @param updatedAttributes
	 *            :HashMap que se carga con los atributos (antes de modificarse)
	 *            de la clase destino que fueron actualizados.
	 * @return
	 */
	public static Object updateDestinationFromSource(Class<?> interfaz, Object source, Object destination, HashMap<String, Object> updatedAttributes)
	{
		if (interfaz.isInterface() && implementsInterface(interfaz, source)
				&& implementsInterface(interfaz, destination))
		{
			Method[] methods = interfaz.getDeclaredMethods();

			for (Method m : methods)
			{
				if (m.getName().startsWith("get"))
				{
					try
					{
						Object sourceValue = findMethod(m.getName(), source).invoke(source);
						Object destinationValue = findMethod(m.getName(), destination).invoke(destination);

//						if (sourceValue == null)
//							continue;

						if ((destinationValue != null
								&& destinationValue.equals(sourceValue)) || (sourceValue == null && destinationValue == null))
							continue;

						Method destMethod = findMethod(m.getName().replaceFirst("get", "set"), destination);
						if (destMethod != null)
						{
							updatedAttributes.put(m.getName().replaceFirst("get", ""), destinationValue);
							destMethod.invoke(destination, sourceValue);
						}
					}
					catch (Exception e)
					{
						throw new RuntimeException(e.getMessage());
					}
				}
			}
		}
		return destination;
	}


    public static Object updateDestinationFromSource(Class<?> interfaz, Object source, Object destination)
    {
        HashMap<String, Object> hMap = new HashMap<String, Object>();

        return updateDestinationFromSource(interfaz, source, destination, hMap);

    }

    public static Object updateDestinationFromSourceSinInterfazSource(Class<?> interfaz, Object source, Object destination)
    {
        if (interfaz.isInterface() && implementsInterface(interfaz, destination))
        {
            Method[] methods = interfaz.getDeclaredMethods();

            for (Method m : methods)
            {
                if (m.getName().startsWith("get"))
                {
                    try
                    {
                        Method methodSource = findMethod(m.getName(), source);
                        if(methodSource != null)
                        {
                            Object sourceValue = findMethod(m.getName(), source).invoke(source);
                            Object destinationValue = findMethod(m.getName(), destination).invoke(destination);

                            if (sourceValue == null)
                                continue;

                            if (destinationValue != null
                                    && destinationValue.equals(sourceValue))
                                continue;

                            Method destMethod = findMethod(m.getName().replaceFirst("get", "set"), destination);
                            if (destMethod != null)
                            {
                                Class<?>[] types = destMethod.getParameterTypes();

                                if(types[0].equals(boolean.class)
                                        && sourceValue instanceof Character)
                                {
                                    destMethod.invoke(destination, ((Character) sourceValue == 'S'));
                                }
                                else
                                {
                                    destMethod.invoke(destination, sourceValue);
                                }
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        }
        return destination;
    }

	public static Object fromObjectToObject(Object source, Object destination, String[] ignore, HashMap<String, Object> modifiedAttributes)
	{
		Method[] methods = source.getClass().getMethods();
		a: for (Method m : methods)
		{
			if (m.getName().startsWith("get"))
			{
				for (String s : ignore)
				{
					if (m.getName().equals(s))
						continue a;
				}
				try
				{
					Object sourceValue = m.invoke(source);
					Method destinationGetMethod = findMethod(m.getName(), destination);
					Object destinationValue = destinationGetMethod != null ? destinationGetMethod.invoke(destination)
							: null;

					if (sourceValue == null)
						continue;

					if (destinationValue != null
							&& destinationValue.equals(sourceValue))
						continue;

					Method destMethod = findMethod(m.getName().replaceFirst("get", "set"), destination);
					if (destMethod != null)
					{
						modifiedAttributes.put(m.getName().replaceFirst("get", ""), destinationValue);
						destMethod.invoke(destination, sourceValue);
					}
				}
				catch (Exception e)
				{
					continue;
				}
			}
		}
		return destination;
	}

	public static Method findMethod(String methodName, Object object)
	{
		Method[] methods = object.getClass().getMethods();
		Method result = null;
		for (Method m : methods)
		{
			if (m.getName().equals(methodName))
			{
				result = m;
				break;
			}
		}
		return result;
	}

}
