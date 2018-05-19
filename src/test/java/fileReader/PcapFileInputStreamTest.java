package fileReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;


public class PcapFileInputStreamTest { //TODO Agregaer más test prvenientes de la planificación
    private PcapFileInputStream fileInputStream;
    private HexStringToByteArray hexStringToByte;

    @Before
    public void setup(){
        File file = new File("test-files" + File.separator + "test-capture.pcap");
        hexStringToByte = new HexStringToByteArray();
        try {
            fileInputStream = new PcapFileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readPcapFile(){
        String file = "44000000900000000000000000000000"+
                    "00000000000000004400010000000400"+
                    "00000000440002000000040000000000"+
                    "44000300000004000100000044000400"+
                    "000004002a0000000000000000000000"+
                    "0000000044000600000004002a000000"+
                    "00000000000000000000000044000800"+
                    "00000400020000004400090000000400"+
                    "0000000044000a00000004005c000000"+
                    "80000000ffffffffffff00239c3bd020"+
                    "00239c3bd020a0e64cb3455709000000"+
                    "6400210400124a756e697065725f5353"+
                    "47355f6261736963010882848b960c18"+
                    "30480301010504000100000706555320"+
                    "010b1b2a010032041224606c";

        byte[] toCompare = hexStringToByte.convert(file);
        byte[] result = new byte[10000];
        fileInputStream.read(result);

        boolean flag = true;

        for (int i = 0; i < toCompare.length; i++)
            flag &= toCompare[i] == result[i];

        assertTrue(flag);
    }

    @Ignore
    public void otherTest(){
        byte[] allFile = new byte[10000];

        fileInputStream.read(allFile);

        for(int i = 0; i < allFile.length; i++)
            System.out.print(String.format("%02X", allFile[i]));

    }
    
    @Test
    public void fromStringTest() {
    	
    	try {
			PcapFileInputStream pcap = new PcapFileInputStream("test-files" + File.separator + "test-capture.pcap");
			
			Assert.assertEquals(fileInputStream.read(), pcap.read());
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
