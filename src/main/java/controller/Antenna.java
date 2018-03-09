package controller;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.OutputStream;
import java.net.NetworkInterface;

public class Antenna {

    private OutputStream stream;
    private NetworkInterface networkInterface;

    public Antenna(OutputStream outputStream, String interfaceName) {
        try {
            PcapNetworkInterface nif = Pcaps.getDevByName(interfaceName);
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }
        this.stream = outputStream;
    }

    public void startListening(){
        throw new NotImplementedException();
    }

    public void stopListening(){
        throw new NotImplementedException();
    }

}
