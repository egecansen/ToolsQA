package driver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    public static Properties initialize(){
        Properties properties = new Properties();
        try {properties.load(new FileReader("src/test/resources/test.properties"));}
        catch (IOException notFoundException) {throw new RuntimeException("The Properties not exist!");}
        return properties;
    }
}
