package fileReader;

public class WrongExtensionException extends Exception {

    public WrongExtensionException(String extension){
        super("Not valid extension. This should be " + extension);

    }
}
