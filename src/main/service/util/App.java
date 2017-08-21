package util;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.Document;

import java.io.IOException;
import java.util.Properties;

public class App {

    private static MongoDatabase mongoDB;
    private static MongoClient client;

    static {
        Properties prop = new Properties();
        try {
            prop.load(Test.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            System.out.println("can't find config file");
        }
        client = new MongoClient(prop.getProperty("host"), Integer.valueOf(prop.getProperty("port")));
        mongoDB = client.getDatabase(prop.getProperty("dbname"));
    }

    public byte[] getImageResponse(String imageName) {
        GridFSBucket gridFS = GridFSBuckets.create(mongoDB, "photo");
        GridFSDownloadStream downloadStream = gridFS.openDownloadStream(imageName);
        byte[] b = new byte[(int) downloadStream.getGridFSFile().getLength()];
        downloadStream.read(b);
        downloadStream.close();
        return b;
    }
}
