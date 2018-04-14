package FileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileLineReader {
	
	private BufferedReader fileStream;
	
	public FileLineReader(File file) {
		
		try {
			fileStream = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public String readLine(){
		try {
			return fileStream.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean ifEof(){
		try {
			return fileStream.readLine() == null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
