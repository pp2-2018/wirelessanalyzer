package exceptions;

public class MacNotMatchedException extends RuntimeException {

    public MacNotMatchedException(){
        super("Mac not matched in the filename");
    }
}
