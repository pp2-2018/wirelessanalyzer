package FileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class FileLineReader {
	
	private BufferedReader fileStream;
	private boolean isEof;
	
	public FileLineReader(Reader reader) {
		
		fileStream = new BufferedReader(reader);
	}
	
	public String readLine(){
		try {
			String line = fileStream.readLine();
			this.isEof = line == null;
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean ifEof(){
		return this.isEof;
	}
	
}
