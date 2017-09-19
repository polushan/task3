package util;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;

public class App {

    private static MongoDatabase mongoDB;
    private static MongoClient client;

    static {
        client = new MongoClient(Config.MONGO_HOST, Config.MONGO_PORT);
        mongoDB = client.getDatabase(Config.MONGO_DB_NAME);
    }

    public byte[] getImageResponse(String imageName) {
        if (!"".equals(imageName)) {
            GridFSBucket gridFS = GridFSBuckets.create(mongoDB, "photo");
            GridFSDownloadStream downloadStream = gridFS.openDownloadStream(imageName);
            byte[] b = new byte[(int) downloadStream.getGridFSFile().getLength()];
            downloadStream.read(b);
            downloadStream.close();
            return b;
        } else {
            return new byte[1];
        }
    }
}
