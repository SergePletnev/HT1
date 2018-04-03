package config;

import utils.Browser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final String PATH_TO_CONFIG = "src/main/resources/config.properties";

    private static Properties props = new Properties();

    private Config() throws IOException {
        InputStream is = new FileInputStream(PATH_TO_CONFIG);
        props.load(is);
        is.close();
    }

    public static void loadConfig() throws IOException {
        new Config();
    }

    public static Browser getBrowser() {
        switch (props.getProperty("browser").toLowerCase()) {
            case "firefox":
                return Browser.FIREFOX;
            case "chrome":
                return Browser.CHROME;
            default:
                return Browser.UNKNOWN;
        }
    }

    public static int getImplicityWait() {
        return Integer.parseInt(props.getProperty("implicitlyWait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(props.getProperty("pageLoadTimeout"));
    }
}
