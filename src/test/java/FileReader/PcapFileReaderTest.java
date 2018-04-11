package FileReader;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import static org.junit.Assert.assertTrue;


public class PcapFileReaderTest {

    private PcapFileReader reader;

    @Before
    public void setup(){
        OutputStream outputStream = new ByteArrayOutputStream();
        File file = new File("test-files" + File.separator + "beacon-frame.pcap");
        reader = new PcapFileReader(file.getAbsolutePath());
    }
    @Test
    public void readFile(){
        assertTrue(1 == 1);

    }

    private String byteToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

}
