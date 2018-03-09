package controller;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.OutputStream;
import java.net.NetworkInterface;
import java.net.SocketException;

public class Antenna {

    private OutputStream stream;
    private NetworkInterface networkInterface;

    public Antenna(OutputStream outputStream, String interfaceName) {
        this.stream = outputStream;
        try {
            networkInterface = NetworkInterface.getByName(interfaceName);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void startListening(){
        throw new NotImplementedException();
    }

    public void stopListening(){
        throw new NotImplementedException();
    }

}
