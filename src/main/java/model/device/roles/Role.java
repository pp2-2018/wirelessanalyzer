package model.device.roles;

import model.device.Device;

public interface Role {
    enum Type {
        Sniffer,AccessPoint,Client
    }


    Type getType();
    Device getDevice();

}
