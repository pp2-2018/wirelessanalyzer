package controller;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import java.io.OutputStream;

public class PcapFileReader {
    private PcapHandle pcapHandle;
    private OutputStream outputStream;

    public PcapFileReader(String fileName) {

        try {
            pcapHandle = Pcaps.openOffline(fileName);
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        Packet packet;
        try {
            while ((packet = pcapHandle.getNextPacket()) != null) {
                packet.getRawData();
            }
        } catch (NotOpenException e) {
            e.printStackTrace();
        }
    }

}
