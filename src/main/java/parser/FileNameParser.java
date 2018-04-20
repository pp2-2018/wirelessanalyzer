package parser;

import exceptions.MacNotMatchedException;
import fileReader.HexStringToByteArray;
import model.device.MacAddress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameParser { //TODO Habría que ver como generalizar

    private String macAddressregex = "([a-f0-9]{2}-){5}[a-f0-9]{2}"; //TODO Habría que sacar esta regex de un archivo de config
    private HexStringToByteArray hexStringToByteArray;
    private Matcher matcher;
    private Pattern p;

    public FileNameParser() {
        p = Pattern.compile(macAddressregex);
        hexStringToByteArray = new HexStringToByteArray();
    }

    public MacAddress parse(String fileName) throws MacNotMatchedException{
        matcher = p.matcher(fileName);

        if (!matcher.find())
            throw new MacNotMatchedException();

        String cadena = matcher.group(0);
        byte[] byteMACAddress = stringTobyteArray(cadena);

        return new MacAddress(byteMACAddress); //TODO esto está ok??
    }

    private byte[] stringTobyteArray(String group) {
        String toConvert = group.replaceAll("-", "");
        return hexStringToByteArray.convert(toConvert);
    }

}
