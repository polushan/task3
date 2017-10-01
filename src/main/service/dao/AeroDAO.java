package dao;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;


public class AeroDAO implements DAO {

    private AerospikeClient client;
    private Key key;

    public AeroDAO(AerospikeClient client, Key key) {
        this.client = client;
        this.key = key;
    }

    public byte[] getImage(String imageName) {
        Record record = client.get(null, key);
        if (!"".equals(imageName)) {
            return (byte[]) record.getValue(imageName);
        } else {
            return new byte[1];
        }

    }
}
