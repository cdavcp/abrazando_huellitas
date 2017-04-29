package sigep.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class DataTypeUtil
{
	private static HashMap<String, ParameterDataType> patterns;


	public static HashMap<String, ParameterDataType> getDataTypeValidationPattern()
	{
		if (patterns == null)
		{
            patterns = new HashMap<String, ParameterDataType>();

			patterns.put(EnumParameterDataTypes.LOGICO.getName(), new ParameterDataType(EnumParameterDataTypes.LOGICO.getName(),Pattern.compile("SI|NO"), true) );

			// Se asume que alfanumerico se refiere a letras del alfabeto (sin acentos) y numeros.
            patterns.put(EnumParameterDataTypes.ALFANUMERICO.getName(), new ParameterDataType(EnumParameterDataTypes.ALFANUMERICO.getName(),Pattern.compile("[_A-ZÑa-zñ0-9-\\s\\.]+"), true));

			// Se asume que texto se refiere a texto a escribir cualquier combinación de caracteres.
			patterns.put(EnumParameterDataTypes.TEXTO.getName(), new ParameterDataType(EnumParameterDataTypes.TEXTO.getName(), Pattern.compile(".+"), true));
            // Se asume para el caso de los directorios, como se va a usar ext4 como sistema de archivos que la única prohibición en cuanto a un path es que no incluya la palabra NUL.
			patterns.put(EnumParameterDataTypes.DIRECTORIO.getName(), new ParameterDataType(EnumParameterDataTypes.DIRECTORIO.getName(), Pattern.compile("\\b(NUL|nul)\\b"), false));

            List<Pattern> numericPatterns = new ArrayList<Pattern>(2);
            //Numérico con valores decimales opcionales sin puntos de mil
            numericPatterns.add(Pattern.compile("(\\d{1,3}(?:\\.\\d{3})*([,]\\d+)?)"));
            //Numérico con valores decimales opcionales con puntos de mil
            numericPatterns.add(Pattern.compile("(\\d+([,]\\d+)?)"));

			patterns.put(EnumParameterDataTypes.NUMERICO.getName(), new ParameterDataType(EnumParameterDataTypes.NUMERICO.getName(), numericPatterns, true));
			patterns.put(EnumParameterDataTypes.FECHA_HORA.getName(), new ParameterDataType(EnumParameterDataTypes.FECHA_HORA.getName(), Pattern.compile("[0-3][0-9][/-][0-1][0-9][/-][1-9][0-9][0-9][0-9]\\s[0-2][0-9]:[0-5][0-9]"), true));
			patterns.put(EnumParameterDataTypes.FECHA.getName(), new ParameterDataType(EnumParameterDataTypes.FECHA.getName(), Pattern.compile("[0-3][0-9][/-][0-1][0-9][/-][1-9][0-9][0-9][0-9]"), true));


		}

		return patterns;
	}
}
