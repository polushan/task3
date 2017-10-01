package dbservices;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import dao.AeroDAO;
import util.Config;

public class AeroService implements DBService{

    private AerospikeClient client;
    private Key key;

    public AeroService(Config config) {
        client = new AerospikeClient(config.AERO_HOST, config.AERO_PORT);
        key = new Key(config.AERO_NAMESPACE, config.AERO_SET_NAME, config.AERO_KEY);
    }

    public byte[] getImage(String imageName) {
        return new AeroDAO(client, key).getImage(imageName);
    }
}
