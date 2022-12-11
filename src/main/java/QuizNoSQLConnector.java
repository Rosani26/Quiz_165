import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuizNoSQLConnector {
    public static List<Quiz> getFragenfromDB() {
        List<Quiz> result = new ArrayList<>();

        try (MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb://root:root@localhost/carCards")).build()
        )) {
            MongoDatabase database = mongoClient.getDatabase("Quiz");
            try {
                MongoCollection<Document> quizDocs = database.getCollection("Quiz");
                FindIterable<Document> iterDoc = quizDocs.find();
                Iterator<Document> it = iterDoc.iterator();
                while(it.hasNext()){
                    Document doc = it.next();
                    Gson gson = new GsonBuilder().create();
                    Quiz quiz = gson.fromJson(doc.toJson(), Quiz.class);
                    Quiz c = new Quiz();
                    result.add(quiz);

                }
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
    }
            System.out.println("Found " + result.size());
            return result;
        }
    }
}
