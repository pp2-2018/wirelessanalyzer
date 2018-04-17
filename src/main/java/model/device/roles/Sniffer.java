package model.device.roles;
import model.device.Device;


public class Sniffer implements Role {

    private final Type type = Type.Sniffer;
    private Device device;

    public Sniffer(Device device){
        this.device = device;
    }

    public Type getType() {
        return type;
    }


    public Device getDevice() {
        return device;
    }


}
