package model;


import model.device.Device;
import model.device.MacAddress;
import model.device.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
    Coordinates coords0 ;
    Coordinates coords1 ;
    Device dev0;
    Device dev1;
    Map map;


    @Before
    public void setUp(){
        byte [] macdev0= {(byte)0XAB,(byte)0XCD,(byte)0XEF,(byte)0XAB,(byte)0XCD,(byte)0XEF};
        byte [] macdev1= {(byte)0XAA,(byte)0XBB,(byte)0XCC,(byte)0XDD,(byte)0XEE,(byte)0XFF};
        coords1 = new Coordinates(33.69201,-13.02367);
        coords0 = new Coordinates(8.20609,172.71046);
        dev0 = new Device(new MacAddress(macdev0));
        dev1 = new Device(new MacAddress(macdev1));
        map = new Map();

    }

    @Test
    public void addDevice(){
        map.register(dev0.getMac(),coords0);
        Assert.assertEquals(map.getCoordinates(dev0.getMac()), coords0);
        map.register(dev0.getMac(),coords1);
        Assert.assertTrue(map.getCoordinates(dev0.getMac()).equals(coords1));
    }

    @Test
    public void removeDevice(){
        map.register(dev0.getMac(),coords1);
        map.unregister(dev1.getMac());
        Assert.assertNull(map.getCoordinates(dev1.getMac()));


    }









}
