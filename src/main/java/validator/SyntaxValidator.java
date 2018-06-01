package validator;


import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxValidator implements Predicate<String> {
    private final String sintaxRegex = "(\\w+(\\([^()]+\\))?)";
    private final String parethesisRegex = "\\(\\s*.+\\s*\\)";
    Pattern p;

    public SyntaxValidator(){
        this.p = Pattern.compile(parethesisRegex);
    }

    public boolean hasParameter(String filter){
        Matcher m = p.matcher(filter);
        return m.find();
    }

    public String extractParameter(String filter){
        Matcher m = p.matcher(filter);
        if (m.find()){
            return filter.substring(m.start()+1,m.end()-1).trim();
        }
        return "";
    }

    @Override
    public boolean test(String candidate) {
        return (candidate).matches(sintaxRegex);
    }
}
