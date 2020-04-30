package util;

import java.util.Properties;

public class ConfigReader extends Properties {
    public ConfigReader() {
        super();
    }

    public int readInt(String key){
        return Integer.parseInt(this.getProperty(key));
    }

}
