package parser;

import exceptions.MacNotMatchedException;
import fileReader.HexStringToByteArray;
import model.device.MacAddress;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Mac;

import static org.junit.Assert.*;

public class FileNameParserTest {
    private FileNameParser fileNameParser;

    @Before
    public void setUp(){
        fileNameParser = new FileNameParser();
    }

    @Test
    public void parse() {

        String fileName = "/home/user/1_4a-88-7f-41-12-47.pcap";
        byte[] byteMacAddress = new byte[]{(byte) 0x4a, (byte) 0x88, 0x7f, 0x41, 0x12, 0x47};
        MacAddress toCompare = new MacAddress(byteMacAddress);

        assertEquals(toCompare, fileNameParser.parse(fileName));
    }

    @Test (expected = MacNotMatchedException.class)
    public void invalidParse() {

        String fileName = "/home/user12-1/1_4a-88-7fa-41-12-47a.pcap";
        byte[] byteMacAddress = new byte[]{(byte) 0x4a, (byte) 0x88, 0x7f, 0x41, 0x12, 0x47};
        MacAddress toCompare = new MacAddress(byteMacAddress);

        assertNotEquals(toCompare, fileNameParser.parse(fileName));
    }

    @Test
    public void validParse2(){

        String fileName = "/home/user12-1/1_4a-88-7f-41-12-47a.pcap";
        byte[] byteMacAddress = new byte[]{(byte) 0x4a, (byte) 0x88, 0x7f, 0x41, 0x12, 0x47};
        MacAddress toCompare = new MacAddress(byteMacAddress);

        assertEquals(toCompare, fileNameParser.parse(fileName));
    }

}