import controller.PcapFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;


public class PcapFileReaderTest {

    private PcapFileReader reader;

    @Before
    public void setup(){
        OutputStream outputStream = new ByteArrayOutputStream();
        File file = new File("test-files" + File.separator + "wireless.pcap");
        reader = new PcapFileReader(file.getAbsolutePath(), outputStream);
    }
    @Test
    public void readFile(){
        OutputStream stream = reader.read();
        System.out.println(stream.toString());
    }


}
