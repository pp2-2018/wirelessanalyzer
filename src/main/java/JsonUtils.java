import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import java.util.UnknownFormatConversionException;


public class JsonUtils <T> {

    public T fromJson(String message){
            T parsed = null;

                try{
          parsed = (T) JsonReader.jsonToJava(message);
        }
        catch (Exception e){
            throw new UnknownFormatConversionException("The message that was attempted to parse" +
                " appears to be corrupted or an incorrect class was specified");
    }
       return parsed;
    }
    public String toJson(Object javaObject){

        return JsonWriter.objectToJson(javaObject);

    }

}

