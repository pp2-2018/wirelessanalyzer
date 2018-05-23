package validator;


import java.util.function.Predicate;

public class SyntaxValidator implements Predicate<String> {
    private final String sintaxRegex = "(\\w+(\\([^()]+\\))?)";


    @Override
    public boolean test(String candidate) {
        return (candidate).matches(sintaxRegex);
    }
}
