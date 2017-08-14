package util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoCredential;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

import java.io.IOException;
import java.util.Properties;

public class App {

    private Mongo mongo;
    private DB db;
    private MongoCredential credential;
    private DBCollection collection;


    public App() {
        Properties prop = new Properties();
        try {
            prop.load(ImageUploader.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            System.out.println("can't find config file");
        }
        mongo = new Mongo(prop.getProperty("host"), Integer.valueOf(prop.getProperty("port")) );
        db = mongo.getDB(prop.getProperty("dbname"));
        credential = MongoCredential.createMongoCRCredential(prop.getProperty("login"),
                prop.getProperty("dbname"), prop.getProperty("password").toCharArray());
        collection = db.getCollection(prop.getProperty("table"));

    }

    public GridFSDBFile getImageResponse(String imageName) {
        return new GridFS(db, "photo").findOne(imageName);
    }
}
