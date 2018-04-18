package model;

import negocio.ConfigurationManager;
import model.device.ConfigurationMap;
import org.junit.Assert;
import org.junit.Test;

public class ConfigurationManagerTest {
    ConfigurationMap map;


    @Test
    public void saveAndLoad(){
        //Vacio la config
        ConfigurationManager.save(new ConfigurationMap());
        //Verifico Vaciado
        Assert.assertEquals(0,ConfigurationManager.load().getSniffers().size());
        //Prueba guardado
        ConfigurationManager.save(ConfigurationMap.mapInstance());
        //Verifico Guardado
        Assert.assertEquals(2,ConfigurationManager.load().getSniffers().size());
    }

    }
