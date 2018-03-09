package controller;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.InputStream;

public class PcapPackageSaver {
    private InputStream inputStream;

    public PcapPackageSaver(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void save(){
        throw new NotImplementedException();
    }

}
