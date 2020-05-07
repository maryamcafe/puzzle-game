package util;

import java.util.Properties;

 class Configs extends Properties {

     int readInt(String key){
        return Integer.parseInt(this.getProperty(key));
    }

}
