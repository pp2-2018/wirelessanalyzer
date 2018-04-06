import controller.PcapFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.OutputStream;

import static org.junit.Assert.*;

public class PcapFileReaderTest {

    private PcapFileReader reader;

    @Before
    public void setup(){
        String filename = "/home/lucas/Documentos/wireless.pcap";
        reader = new PcapFileReader(filename);
    }s

    @Test
    public void readFile(){
        OutputStream stream = reader.read();
        System.out.println(stream.toString());

    }

}