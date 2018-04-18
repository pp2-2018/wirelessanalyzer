package model.device;

import model.Coordinates;
import model.device.roles.Sniffer;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ConfigurationMap {
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



    public Set<Sniffer> getSniffers(){
        return new HashSet<>(locations.keySet());

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationMap that = (ConfigurationMap) o;
        return Objects.equals(locations, that.locations);
    }

    @Override
    public int hashCode() {

        return Objects.hash(locations);
    }

    @Override
    public String toString() {
        return "ConfigurationMap{" +
                "locations=" + locations +
                '}';
    }


    public static ConfigurationMap mapInstance (){
        Device dev0;
        Device dev1;
        Sniffer sniff0;
        Sniffer sniff1;
        byte[] macdev0 = {(byte) 0XAB, (byte) 0XCD, (byte) 0XEF, (byte) 0XAB, (byte) 0XCD, (byte) 0XEF};
        byte[] macdev1 = {(byte) 0XAA, (byte) 0XBB, (byte) 0XCC, (byte) 0XDD, (byte) 0XEE, (byte) 0XFF};
        Coordinates coords1 = new Coordinates(33.69201, -13.02367);
        Coordinates coords0 = new Coordinates(8.20609, 172.71046);
        ConfigurationMap map1;
        dev0 = new Device(new MacAddress(macdev0));
        dev1 = new Device(new MacAddress(macdev1));
        sniff0 = new Sniffer(dev0,100);
        sniff1 = new Sniffer(dev1,100);
        map1 = new ConfigurationMap();
        map1.register(sniff0, coords0);
        map1.register(sniff1, coords1);
        return map1;
    }
}
