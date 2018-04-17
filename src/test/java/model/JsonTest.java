package model;


import model.device.Device;
import model.device.InstantMap;
import model.device.MacAddress;
import model.device.roles.AccessPoint;
import model.device.roles.Role;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class JsonTest {

    Device dev0;
    Device dev1;
    byte[] macdev0 = {(byte) 0XAB, (byte) 0XCD, (byte) 0XEF, (byte) 0XAB, (byte) 0XCD, (byte) 0XEF};
    byte[] macdev1 = {(byte) 0XAA, (byte) 0XBB, (byte) 0XCC, (byte) 0XDD, (byte) 0XEE, (byte) 0XFF};
    Coordinates coords1 = new Coordinates(33.69201, -13.02367);
    Coordinates coords0 = new Coordinates(8.20609, 172.71046);
    Role role;
    Role role2;
    InstantMap instantMap;
    HashMap<String, Coordinates> map1;

    @Before
    public void setUp() {
        dev0 = new Device(new MacAddress(macdev0));
        dev1 = new Device(new MacAddress(macdev1));
        role = new AccessPoint(dev0,"Wi-Fi Gratis");
        role2 = new AccessPoint(dev1,"CAMPUS");
    /*    instantMap = new InstantMap();
        map1 = new HashMap<String, Coordinates>();
        instantMap.register(dev0.getMac(), coords0);
        instantMap.register(dev1.getMac(), coords1);
        map1.put(dev0.getMac().toString(), coords0);
        map1.put(dev1.getMac().toString(), coords1);*/
    }

    @Test
    public void persist() {

        JsonUtils<Map> jsonUtils = new JsonUtils<>();
        String json=jsonUtils.toJson(instantMap);
        Map parsed =jsonUtils.fromJson(json);
        HashMap<String,Coordinates> hasmap = (HashMap<String,Coordinates>) parsed;

      /*  HashMap<String,Coordinates> parsed =jsonUtils.fromJson(json);
        for (Coordinates coords:parsed.values()
             ) {
            System.out.println(coords);
        }*/




        //generarJSON("test.json", map1);
       // java.util.InstantMap<String, Coordinates> persistido = leerJSON("test.json");
      //  Assert.assertEquals(coords0.toString(),persistido.get(dev0.getMac().toString()).toString());
    }

 /*   void generarJSON(String archivo, Serializable objeto) {
        Gson gson = new Gson();

        try {
            FileWriter writer = new FileWriter("test.json");
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    java.util.InstantMap<String, Coordinates> leerJSON(String archivo) {

        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader("test.json"));
            Type type = new TypeToken<java.util.InstantMap<String, Coordinates>>() {
            }.getType();
            java.util.InstantMap<String, Coordinates> instantMap = gson.fromJson(br, type);
            return instantMap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }*/

    }




