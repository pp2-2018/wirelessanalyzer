package validator;

import fileReader.WrongExtensionException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileExtensionValidatorTest {

    FileExtensionValidator validator;

    @Before
    public void setUp(){
        validator = new FileExtensionValidator(".pcap");
    }

    @Ignore
    public void validatePcapExtension(){
        try {
            assertTrue(validator.isValidExtension("/home/test/.pcapwireless.pcap"));
        } catch (WrongExtensionException e) {
            e.printStackTrace();
        }

    }

    @Ignore
    public void validateWrongPcapExtension(){
        try {
            assertFalse(validator.isValidExtension("/home/test/.pcapwireless.pcap."));
        } catch (WrongExtensionException e) {
            e.printStackTrace();
        }
    }

}