package edu.escuelaing.arep.logservice.services;

public class MongoServiceException extends Exception {
    public static final String ERROR_CONNECTION = "An error occurred while connecting to mongodb";
    public static final String ERROR_DATABASE = "An error occurred while consulting in the database";
    public static final String ERROR_COLLECTION = "An error occurred while consulting the collection";

    public MongoServiceException(String message){
        super(message);
    }
}
