package controller;

import java.net.*;

public class PacketReciver {
    private static PacketReciver ourInstance;

    private NetworkInterface networkInterface;

    private PacketReciver(String interfaceName) {
        try {
            networkInterface = NetworkInterface.getByName(interfaceName);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public PacketReciver getInstance(String interfaceName) {
        if (ourInstance == null)
            return new PacketReciver(interfaceName);
        return ourInstance;
    }

    public void startListening(){}

}
