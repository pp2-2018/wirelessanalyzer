package parser;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import model.device.MacAddress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameParser { //TODO Habría que ver como generalizar

    private final String macAddressregex = "";
    private Matcher matcher;
    private Pattern p;

    public FileNameParser() {
        p = Pattern.compile(macAddressregex);
    }

    public MacAddress parse(String fileName){
        matcher = p.matcher(fileName);

        if (!matcher.find()) //TODO Tiene que tirar excepcion de mac address not found
            return null;

        byte[] byteMACAddress = stringTobyteArray(matcher.group(0));

        return null;
    }

    private byte[] stringTobyteArray(String group) {
        return null;
    }

}
