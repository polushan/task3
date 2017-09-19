package util;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;


public class AeroApp {

    private static AerospikeClient client = new AerospikeClient(Config.AERO_HOST, Config.AERO_PORT);
    private static Key key = new Key(Config.AERO_NAMESPACE, Config.AERO_SET_NAME, Config.AERO_KEY);
    private static Record record = client.get(null, key);

    public byte[] getImageResponse(String imageName) {
        if (!"".equals(imageName)) {
            return (byte[]) record.getValue(imageName);
        } else {
            return new byte[1];
        }
    }
}
