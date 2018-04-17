package model.device.roles;
import model.device.Device;

import java.util.Objects;


public class Sniffer implements Role {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sniffer sniffer = (Sniffer) o;
        return type == sniffer.type &&
                Objects.equals(device, sniffer.device);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, device);
    }

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


    @Override
    public String toString() {
        return "Sniffer{" +
                "type=" + type +
                ", device=" + device +
                '}';
    }
}
