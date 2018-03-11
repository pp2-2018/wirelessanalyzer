package controller;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.NetworkInterface;

public class Antenna {

    private OutputStream stream;
    private PcapNetworkInterface nif;
    private PcapHandle handle;

    //Estas configuraciones deberían poder ser indicadas por el usuario
    private final PcapNetworkInterface.PromiscuousMode mode = PcapNetworkInterface.PromiscuousMode.NONPROMISCUOUS;
    private final int snapLen = 65536; //No sé por qué este número (El valor indica el tamaño de lo capturado)
    private final int timeout = 10;


    public Antenna(OutputStream outputStream, String interfaceName) {
        try {
            nif = Pcaps.getDevByName(interfaceName);
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }

        this.stream = outputStream;
    }

    public void startListening(){
        try {
            handle = nif.openLive(snapLen, mode, timeout);
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }

        String pkg = "Yo have to put something here";

        try {
            this.stream.write(Byte.parseByte(pkg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopListening(){
        handle.close();
    }


}
