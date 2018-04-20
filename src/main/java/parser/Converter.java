package parser;

import java.io.InputStream;

public interface Converter <T> {

    InputStream to(T fromObject);
    T from(InputStream toObject);





}
