import controller.PcapFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.List;


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
        List<byte[]> byteList = reader.read();

        byteList.forEach(b -> System.out.println(byteToHexString(b)));

    }

    private String byteToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

}
