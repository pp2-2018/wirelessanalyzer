package model.device.roles;


import model.device.Device;

public class AccessPoint implements Role {
    private final Type type = Type.AccessPoint;
    private String SSID;
    private Device device;



    public AccessPoint(Device device, String SSID){
        this.device = device;
        this.SSID = SSID;
    }

    public String getSSID(){return SSID;}

    public Type getType() {
        return type;
    }


    public Device getDevice() {
        return device;
    }
}
