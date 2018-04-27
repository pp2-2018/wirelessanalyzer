package validator;

import fileReader.WrongExtensionException;

import java.io.File;

public class FileExtensionValidator {//TODO Esto tiene que extender de alguna cosa que lea un archivo de configuracion

    private String extension;
    private String pattern;

    public FileExtensionValidator(String s) {
        extension = s;
        pattern = ".*\\.{0,1}" + extension;
    }

    public boolean isValidExtension(File file) throws WrongExtensionException {
        return this.isValidExtension(file.getAbsolutePath());
    }

    public boolean isValidExtension(String fileName) throws WrongExtensionException{
        boolean validation = fileName.matches(pattern);

        if(!validation)
            throw new WrongExtensionException(extension);

        return validation;
    }
}
