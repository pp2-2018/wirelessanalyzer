package fileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PcapFileInputStream extends FileInputStream{

    private FileLineReader fileLineReader;
    private HexStringToByteArray hexToByte;

    public PcapFileInputStream(String s) throws FileNotFoundException {
        super(s);
        initAdaptees(s);
    }

    public PcapFileInputStream(File file) throws FileNotFoundException {
        super(file);
        initAdaptees(file.getAbsolutePath());

    }

    private void initAdaptees(String fileName){
        Reader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fileLineReader = new FileLineReader(reader);
        hexToByte = new HexStringToByteArray();
    }
    
    
    @Override
    public int read() throws IOException {
    	 
    	int b1 = super.read();
    	
        return b1;
    }

    @Override
    public int read(byte[] b){

        String line = "";

        while(!fileLineReader.ifEof()) {
        		String ln = fileLineReader.readLine();

            	line += ln == null? "" : ln;
            
        	}
        
        byte[] temp = hexToByte.convert(line);

        copyElements(temp, b);

        return temp.length;

    }

    private void copyElements(byte[] from, byte[] to) {
        for (int i = 0; i < from.length; i++)
            to[i] = from[i];
    }

}
