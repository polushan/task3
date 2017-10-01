package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final String CONFIG_ADDRESS = "src/main/service/config/config.properties";

    public final String MONGO_HOST;
    public final String ADDRESS;
    public final int SERVER_PORT;
    public final int MONGO_PORT;
    public final String MONGO_DB_NAME;
    public final String AERO_HOST;
    public final int AERO_PORT;
    public final String AERO_NAMESPACE;
    public final String AERO_SET_NAME;
    public final String AERO_KEY;


    public Config() {
        Properties prop = new Properties();
        try(InputStream input = new FileInputStream(CONFIG_ADDRESS)) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
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
