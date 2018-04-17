package model.device;

import model.Coordinates;
import model.device.roles.Sniffer;

import java.io.Serializable;
import java.util.HashMap;

public class ConfigurationMap implements Serializable {
    private static final long serialVersionUID = 1L;
    HashMap<Sniffer, Coordinates> locations;

    public ConfigurationMap(){
        locations = new HashMap<>();

    }

    public void register(Sniffer Sniffer, Coordinates coords){
        locations.put(Sniffer, coords);
    }
    public boolean unregister(Sniffer Sniffer){
        return locations.remove(Sniffer)!=null;
    }
    public Coordinates getCoordinates(Sniffer Sniffer){
        return locations.get(Sniffer);

    }


}
