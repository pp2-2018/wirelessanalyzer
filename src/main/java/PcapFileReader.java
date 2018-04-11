package controller;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class PcapFileReader {
    private PcapHandle pcapHandle;

    public PcapFileReader(String fileName) {
        try {
            pcapHandle = Pcaps.openOffline(fileName);
        } catch (PcapNativeException e) {
            e.printStackTrace();
        }
    }

    public List<byte[]> read(){
//        Packet packet;
//        List byteArray = new ArrayList<Byte[]>();
//
//        try {
//            while ((packet = pcapHandle.getNextPacket()) != null) {
//                byteArray.add(packet.getRawData());
//            }
//        } catch (NotOpenException e) {
//            e.printStackTrace();
//        }
//
//        return byteArray;
        throw new NotImplementedException();
    }



}
