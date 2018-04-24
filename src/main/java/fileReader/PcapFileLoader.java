package fileReader;

import validator.FileExtensionValidator;

import java.io.File;
import java.io.FileNotFoundException;

public class PcapFileLoader { //TODO PIenso que esto tendría que tener una bola de inyeccion de dependencias
    //FIXME Esto está totalmente horrible, preguntar a Javier

    private PcapFileInputStream fileInputStream;
    private FileExtensionValidator fileExtensionValidator;


    public PcapFileLoader(String fileName, String extension) {
        initAdaptee(fileName, extension);

    }

    public PcapFileLoader(File file, String extension){
        initAdaptee(file.getAbsolutePath(), extension);

    }

    private void initAdaptee(String fileName, String extension){
        this.fileExtensionValidator = new FileExtensionValidator(extension);

        try {
            fileExtensionValidator.isValidExtension(fileName);
        } catch (WrongExtensionException e) {
            e.printStackTrace();
            return;
        }

        try {
            this.fileInputStream = new PcapFileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public byte[] loadFile(){
        byte[] toRet = new byte[10000]; //TODO COmo podría modificar esto????
        this.fileInputStream.read(toRet);
        return toRet;
    }

}
