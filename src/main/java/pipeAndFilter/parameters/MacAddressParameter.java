package pipeAndFilter.parameters;

import javax.xml.bind.DatatypeConverter;

import model.device.MacAddress;

public class MacAddressParameter implements FilterParameter<MacAddress>{

	@Override
	public MacAddress fromString(String paramStr) {
		
		if(!paramStr.matches("(([0-9A-Fa-f]{2}[-:]){5}[0-9A-Fa-f]{2})"))
			throw new IllegalArgumentException("Malformed Mac String " + paramStr);
		
		MacAddress toRet = new MacAddress(DatatypeConverter.parseHexBinary(paramStr.replace(":", "")));
		
		return toRet;
	}

}
