package model.device;

public class Device {
    MacAddress mac;
    public Device(MacAddress mac){
        this.mac=mac;
    }
    public MacAddress getMac() {
        return mac;
    }
}
