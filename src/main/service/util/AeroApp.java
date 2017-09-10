package util;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;


public class AeroApp {

    private static AerospikeClient client = new AerospikeClient("127.0.0.1", 3000);
    private static Key key = new Key("test", "test", "images");
    private static Record record = client.get(null, key);

    public byte[] getImageResponse(String imageName) {
        return  (byte[]) record.getValue(imageName);

    }
}
