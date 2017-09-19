package util;

import java.io.IOException;
import java.util.Properties;

public class Config {

    public static final String MONGO_HOST;
    public static final String ADDRESS;
    public static final int SERVER_PORT;
    public static final int MONGO_PORT;
    public static final String MONGO_DB_NAME;
    public static final String AERO_HOST;
    public static final int AERO_PORT;
    public static final String AERO_NAMESPACE;
    public static final String AERO_SET_NAME;
    public static final String AERO_KEY;


    static {
        Properties prop = new Properties();
        try {
            prop.load(Config.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("can't find config file");
        }
        MONGO_HOST = prop.getProperty("mongo_host", "localhost");
        ADDRESS = prop.getProperty("address", "/");
        SERVER_PORT = Integer.parseInt(prop.getProperty("server_port", "8080"));
        MONGO_PORT = Integer.parseInt(prop.getProperty("mongo_port", "27017"));
        MONGO_DB_NAME = prop.getProperty("mongo_db_name", "admin");
        AERO_HOST = prop.getProperty("aero_host", "127.0.0.1");
        AERO_PORT = Integer.parseInt(prop.getProperty("aero_port", "3000"));
        AERO_NAMESPACE = prop.getProperty("aero_namespace", "test");
        AERO_SET_NAME = prop.getProperty("aero_set_name", "test");
        AERO_KEY = prop.getProperty("aero_key", "images");
    }
}
