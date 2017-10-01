package dbservices;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import dao.MongoDAO;
import util.Config;

public class MongoService implements DBService{

    private MongoDatabase mongoDB;
    private MongoClient client;

    public MongoService(Config config) {
        client = new MongoClient(config.MONGO_HOST, config.MONGO_PORT);
        mongoDB = client.getDatabase(config.MONGO_DB_NAME);
    }

    public byte[] getImage(String imageName) {
        return new MongoDAO(mongoDB, client).getImage(imageName);
    }
}
