import controller.PcapFileReader;
import controller.PcapReader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class PcapFileReaderTest {

    private PcapFileReader reader;

    @Before
    public void setup(){
        String filename = "/home/lucas/Documentos/wireless.pcap";
        reader = new PcapFileReader(filename);
    }

    @Test
    public void test(){
        assertTrue(1 == 1);
    }

    @Test
    public void readFile(){

        }

    }

}