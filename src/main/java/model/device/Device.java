package model.device;

import java.util.Objects;

public class Device {
    MacAddress mac;
    public Device(MacAddress mac){
        this.mac=mac;
    }

    public MacAddress getMac() {
        return mac;
    }

    @Override
    public boolean equals(Object o) { //TODO Hay que ver hasta que punto tiene sentido esta igualdad
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(mac, device.mac);
    }

    @Override
    public int hashCode() { //TODO Lo mismo con esto

        return Objects.hash(mac);
    }
}
