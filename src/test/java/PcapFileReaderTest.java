import controller.PcapFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.OutputStream;


public class PcapFileReaderTest {

    private PcapFileReader reader;
    //@Before
    @Ignore
    public void setup(){
        String filename = "/home/lucas/Documentos/wireless-capture.pcap";
        reader = new PcapFileReader(filename);
    }
    //@Test
    @Ignore
    public void readFile(){
        OutputStream stream = reader.read();
        System.out.println(stream.toString());
    }




}
