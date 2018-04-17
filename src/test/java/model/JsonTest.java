package model;


import model.device.ConfigurationMap;
import model.device.Device;
import model.device.MacAddress;
import model.device.roles.Sniffer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.JsonUtils;
import java.io.IOException;

public class JsonTest {

    Device dev0;
    Device dev1;
    Sniffer sniff0;
    Sniffer sniff1;
    byte[] macdev0 = {(byte) 0XAB, (byte) 0XCD, (byte) 0XEF, (byte) 0XAB, (byte) 0XCD, (byte) 0XEF};
    byte[] macdev1 = {(byte) 0XAA, (byte) 0XBB, (byte) 0XCC, (byte) 0XDD, (byte) 0XEE, (byte) 0XFF};
    Coordinates coords1 = new Coordinates(33.69201, -13.02367);
    Coordinates coords0 = new Coordinates(8.20609, 172.71046);

    ConfigurationMap map1;

    @Before
    public void setUp() {
        dev0 = new Device(new MacAddress(macdev0));
        dev1 = new Device(new MacAddress(macdev1));
        sniff0 = new Sniffer(dev0);
        sniff1 = new Sniffer(dev1);
        map1 = new ConfigurationMap();
        map1.register(sniff0, coords0);
        map1.register(sniff1, coords1);
    }


    @Test
    public void generateAndParse() throws IOException {
        JsonUtils<ConfigurationMap> jsonUtils = new JsonUtils<>();
        String json=jsonUtils.toJson(map1);
        ConfigurationMap parsed = jsonUtils.fromJson(generatedConfigurationMap());
        Assert.assertEquals(map1,parsed);
        Assert.assertEquals(coords0,parsed.getCoordinates(sniff0));
        Assert.assertEquals(coords1,parsed.getCoordinates(sniff1));

    }


    String generatedConfigurationMap(){
        return "{\n" +
                "  \"@type\":\"model.device.ConfigurationMap\",\n" +
                "  \"locations\":{\n" +
                "    \"@keys\":[\n" +
                "      {\n" +
                "        \"@type\":\"model.device.roles.Sniffer\",\n" +
                "        \"type\":{\n" +
                "          \"name\":\"Sniffer\"\n" +
                "        },\n" +
                "        \"device\":{\n" +
                "          \"mac\":{\n" +
                "            \"address\":[\n" +
                "              -85,-51,-17,-85,-51,-17\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"@type\":\"model.device.roles.Sniffer\",\n" +
                "        \"type\":{\n" +
                "          \"name\":\"Sniffer\"\n" +
                "        },\n" +
                "        \"device\":{\n" +
                "          \"mac\":{\n" +
                "            \"address\":[\n" +
                "              -86,-69,-52,-35,-18,-1\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"@items\":[\n" +
                "      {\n" +
                "        \"@type\":\"model.Coordinates\",\n" +
                "        \"lat\":8.20609,\n" +
                "        \"lng\":172.71046\n" +
                "      },\n" +
                "      {\n" +
                "        \"@type\":\"model.Coordinates\",\n" +
                "        \"lat\":33.69201,\n" +
                "        \"lng\":-13.02367\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }



    }




