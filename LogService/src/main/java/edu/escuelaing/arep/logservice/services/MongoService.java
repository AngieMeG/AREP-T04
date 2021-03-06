package edu.escuelaing.arep.logservice.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MongoService {
    
    private static final String CONNECTION = "mongodb://db";
    /*private static final String CONNECTION = "mongodb://localhost:27017";*/
    private static final String DATABASE = "AREP-T04";
    private String collectionName;

    public MongoService(String collectionName){
        this.collectionName = collectionName;
    }

    public void saveMessage(String content) throws ParseException{
        MongoClient mongoClient = MongoClients.create(CONNECTION);
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        Document message = new Document();
        message.append("content", content);
        message.append("creation_date", dateFormat.format(currentDate));
        collection.insertOne(message);
        mongoClient.close();
    }

    public List<Document> getLastMessages() {
    ArrayList<Document> messageList;
        MongoClient mongoClient = MongoClients.create(CONNECTION);
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        messageList = collection.find().sort(new BasicDBObject("creation_date",-1)).limit(10).into(new ArrayList<>());
        mongoClient.close();
        return messageList;
    }

    public List<Document> getMessages() {
        MongoClient mongoClient = MongoClients.create(CONNECTION);
        ArrayList<Document> messageList;
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        messageList = collection.find().into(new ArrayList<>());
        mongoClient.close();
        return messageList;
    }


    public void delete(){
        MongoClient mongoClient = MongoClients.create(CONNECTION);
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.drop();
        mongoClient.close();
    }

    public static void main(String[] args){
        try{
            MongoService mongoService = new MongoService("Messages");
            mongoService.delete();
            /*mongoService.saveMessage("Prueba6", "11/05/2006");
            mongoService.saveMessage("Prueba1", "11/05/2001");
            mongoService.saveMessage("Prueba11", "11/05/2011");
            mongoService.saveMessage("Prueba2", "11/05/2002");
            mongoService.saveMessage("Prueba3", "11/05/2003");
            mongoService.saveMessage("Prueba8", "11/05/2008");
            mongoService.saveMessage("Prueba4", "11/05/2004");
            mongoService.saveMessage("Prueba5", "11/05/2005");
            mongoService.saveMessage("Prueba7", "11/05/2007");
            mongoService.saveMessage("Prueba9", "11/05/2009");
            mongoService.saveMessage("Prueba10", "11/05/2010");*/
            System.out.println("TODO");
            List<Document> documents = mongoService.getMessages();
            for (Document document : documents){
                System.out.println(document.toJson());
            }
            /*System.out.println("LIMITADO");
            List<Document> documents2 = mongoService.getLastMessages();
            for (Document document : documents2){
                System.out.println(document.toJson());
            }*/
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
