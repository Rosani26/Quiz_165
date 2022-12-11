import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.BsonTimestamp;
import org.bson.Document;

import java.lang.constant.DynamicConstantDesc;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class App {
    private static final int ANZAHL_FRAGEN = 5;


    public static void main(String[] args) throws InterruptedException {
        ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("Quiz");


        Gson gson = new Gson();

        Thread.sleep(2000);

        MongoCollection<Document> collection = database.getCollection("Quiz");

        System.out.println("Willkommen beim QUiz");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Bitte Namen: ");
        String userName = scanner.next();
        System.out.println();


        LocalTime start = LocalTime.now();

        int punkteZaehler = 0;

        FindIterable<Document> documents = collection.find();


        List<Quiz> quizList = new ArrayList<>();

        for (Document document : documents) {
            String json = document.toJson();
            Quiz quiz = gson.fromJson(json, Quiz.class);
            quizList.add(quiz);
        }

        Collections.shuffle(quizList);

        for (int i = 0; i < ANZAHL_FRAGEN; i++) {
            Quiz quiz = quizList.get(i);
            System.out.println(quiz.getFrage() + "?");
            System.out.println("a: " + quiz.getA());
            System.out.println("b: " + quiz.getB());
            System.out.println("c: " + quiz.getC());
            System.out.println();

            System.out.print("Deine Antwort: ");
            String userInput = scanner.next();

            if (quiz.getAntwort().equals(userInput)) {
                System.out.println("Korrekt");
                punkteZaehler++;
            } else {
                System.out.println("Nicht korrekt");
            }
        }

        LocalTime end = LocalTime.now();

        scanner.close();


        // Speichere Daten in DB, Name, Punkte und Zeit
        Statistik statistik = new Statistik(userName, punkteZaehler, Duration.between(start, end).toMillis());  // Java Object
        Document document = Document.parse(gson.toJson(statistik));                                             // Mongo Bson Object
        MongoCollection<Document> statistikCollection = database.getCollection("Statistik");
        statistikCollection.insertOne(document);

        // Ausgabe Statistik auf Konsole
        System.out.println("=================================================");
        System.out.println("Deine Punkte: "  + statistik.getPunkte());
        System.out.println("Deine Zeit: "  + statistik.getMillis() + " ms");
    }
}

