package pipeAndFilter.filters.associateMetadata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pipeAndFilter.Pipe;
import pipeAndFilter.impl.SimpleFilterImpl;

public class StringParserFilter extends SimpleFilterImpl<String, String> {
	private String regex;
	
	public StringParserFilter(Pipe<String> inputPipe, Pipe<String> outputPipe, String regex) {
		super(inputPipe, outputPipe);
		this.regex = regex;
	}

	@Override
	public void transform(Pipe<String> input, Pipe<String> output) {
		String fileName = input.retireve();
		output.accept(parse(fileName));
	}
	
    private String parse(String fileName){
        Pattern p = Pattern.compile(this.regex); 
        Matcher matcher = p.matcher(fileName);	

        if (!matcher.find())
            throw new IllegalStateException("String " + fileName + " not match");
//        String cadena = matcher.group(0);
//        byte[] byteMACAddress = stringTobyteArray(cadena);
        return matcher.group(0);
    }
	

}
