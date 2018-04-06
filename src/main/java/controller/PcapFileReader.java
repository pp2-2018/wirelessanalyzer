package controller;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PcapFileReader {
    private PcapHandle pcapHandle;
    private OutputStream outputStream;

    public PcapFileReader(String fileName) {
        outputStream = new ByteArrayOutputStream();
        try {
            pcapHandle = Pcaps.openOffline(fileName);
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }
    }

    public OutputStream read(){
        Packet packet;
        try {
            while ((packet = pcapHandle.getNextPacket()) != null) {
                outputStream.write(packet.getRawData());
                System.out.println(packet);
            }
        } catch (NotOpenException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream;
    }


}
