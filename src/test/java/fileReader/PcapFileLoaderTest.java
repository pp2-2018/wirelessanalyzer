package fileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import validator.FileExtensionValidator;

import java.io.File;

import static org.junit.Assert.*;

public class PcapFileLoaderTest {

    private PcapFileLoader loader;

    @Before
    public void setUp(){
        File file = new File("test-files" + File.separator + "test-capture.pcap");
        String extension = "pcap";
        loader = new PcapFileLoader(file.getAbsolutePath(), extension);
    }


    @Test
    public void loadingFileTest(){

    }

    @Ignore //FIXME No funciona
    @Test(expected = WrongExtensionException.class)
    public void wrongExceptionTest(){
        File file = new File("test-files" + File.separator + "test-capture.txt");
        String extension = "pcap";

        PcapFileLoader gonnaExplode = new PcapFileLoader(file, extension);
    }

}