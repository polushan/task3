package dao;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSFile;


public class MongoDAO implements DAO {

    private MongoDatabase mongoDB;
    private MongoClient client;

    public byte[] getImage(String imageName) {
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

    public MongoDAO(MongoDatabase mongoDB, MongoClient client) {
        this.mongoDB = mongoDB;
        this.client = client;
    }
}
