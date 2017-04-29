package sigep.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ParameterDataType implements Serializable
{

    private String name;
    private List<Pattern> patterns;
    private boolean validateForMatch;

    public ParameterDataType(String name, Pattern pattern, boolean validateForMatch)
    {
        this.name = name;
        this.patterns = new ArrayList<Pattern>(1);
        patterns.add(pattern);
        this.validateForMatch = validateForMatch;
    }

    public ParameterDataType(String name, List<Pattern> patterns, boolean validateForMath)
    {
        this.name = name;
        this.patterns = patterns;
        this.validateForMatch = validateForMath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pattern> getPattern() {
        return patterns;
    }

    public void setPattern(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public boolean getValidateForMatch() {
        return validateForMatch;
    }

    public void setValidateForMatch(boolean validateForMatch) {
        this.validateForMatch = validateForMatch;
    }
}
