package negocio;

import model.device.ConfigurationMap;
import parser.Converter;
import parser.JsonConverter;

import java.io.*;


public class ConfigurationManager {
        private static Converter<ConfigurationMap> converter = new JsonConverter<>();
        private static final String CONFIG_FILENAME = "config.json";

        private ConfigurationManager() {

        }
        public static void save(ConfigurationMap mapConfig) {

            try {
                InputStream is = converter.to(mapConfig);
                byte[] buffer ;
                buffer = new byte[is.available()];
                is.read(buffer);
                OutputStream outStream = new FileOutputStream(CONFIG_FILENAME);
                outStream.write(buffer);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public static ConfigurationMap load() {
            ConfigurationMap configurationMap = null;
            try {
                configurationMap = converter.from(new FileInputStream(CONFIG_FILENAME));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return configurationMap;
        }


}