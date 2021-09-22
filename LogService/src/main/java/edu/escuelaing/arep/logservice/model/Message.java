package edu.escuelaing.arep.logservice.model;

public class Message {
    private String date;
    private String content;

    public Message(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +" Content: " + content +", Date: " + date + "}";
    }
    
}
