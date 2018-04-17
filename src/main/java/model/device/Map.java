package model.device;

import model.Coordinates;
import model.device.MacAddress;

import java.io.Serializable;
import java.util.HashMap;

public class Map implements Serializable {
    private static final long serialVersionUID = 1L;
    HashMap<MacAddress, Coordinates> locations;

    public Map(){
        locations = new HashMap<MacAddress, Coordinates>();

    }


    public void register(MacAddress mac, Coordinates coords){
            locations.put(mac, coords);
    }
    public boolean unregister(MacAddress mac){
        return locations.remove(mac)!=null;
    }
    public Coordinates getCoordinates(MacAddress mac){
        return locations.get(mac);

    }


}
