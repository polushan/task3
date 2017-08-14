package util;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.io.IOUtils;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

public class ImageUploader {

    private static final String DEFAULT_PORT = "8080";

    /*public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            prop.load(ImageUploader.class.getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            System.out.println("can't find config file");
        }
        try {

            Mongo mongo = new Mongo(prop.getProperty("host"), Integer.valueOf(prop.getProperty("port")) );
            DB db = mongo.getDB(prop.getProperty("dbname"));
            MongoCredential credential = MongoCredential.createMongoCRCredential(prop.getProperty("login"),
                    prop.getProperty("dbname"), prop.getProperty("password").toCharArray());
            DBCollection collection = db.getCollection(prop.getProperty("table"));

            String newName = "lenta";



            File imageFile = new File("/home/poll/lenta.jpg");

            // create a "photo" namespace
            GridFS gfsPhoto = new GridFS(db, "photo");

            // get image file from local drive
            GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);

            gfsFile.setFilename(newName);


            // save the image file into mongoDB
            gfsFile.save();

            // print the result
            DBCursor cursor = gfsPhoto.getFileList();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            // get image file by it's filename
            GridFSDBFile imageForOutput = gfsPhoto.findOne(newName);
            byte[] b;
            b = IOUtils.toByteArray(imageForOutput.getInputStream());

            System.out.println(b.length);

            // remove the image file from mongoDB
            //gfsPhoto.remove(gfsPhoto.findOne(newName));

            System.out.println("Done");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
