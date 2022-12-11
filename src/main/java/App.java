import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;

import java.lang.constant.DynamicConstantDesc;


public class App {

    public static void main(String[] args) {
        ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("Quiz");
        MongoIterable<String> list = database.listCollectionNames();
//        for (String name : list) {
//            System.out.println(name);
//
//        }
        MongoCollection<Document> collection = database.getCollection("Quiz");

        for (Document document : collection.find()) {
            Gson gson = new Gson();
            String json = document.toJson();
            Quiz quiz = gson.fromJson(json, Quiz.class);
            System.out.println(quiz);
        }
    }
}

