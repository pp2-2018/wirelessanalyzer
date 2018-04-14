package Parser;

import java.util.Iterator;

import FileReader.FileLineReader;

public class PackageRulesFile implements Iterable<PackageRule>, Iterator<PackageRule>{

	FileLineReader lineReader;
	
	public PackageRulesFile(FileLineReader lineReader){
	
		this.lineReader = lineReader;
	}

	@Override
	public boolean hasNext() {
		
		return !this.lineReader.ifEof();
	}


	@Override
	public PackageRule next() {
		
		String line = this.lineReader.readLine();
		
		try{
			String[] rawRule = line.split(" ");

			int offset = Integer.parseInt(rawRule[0]);
			byte byteToCompare= Byte.parseByte(rawRule[1]);
			
			return new PackageRule(offset, byteToCompare);
			
		}catch(NumberFormatException e){
			throw new RuntimeException("FileFormat");
		
		}
		
	}

	@Override
	public Iterator<PackageRule> iterator() {
		return this;
	}

	
	
	
}
