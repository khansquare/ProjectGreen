package br.liveo.model;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 21 , 2015
 * Purpose      :   Message Model
 * Description  :   Detailed Description...
 */
public class Message {
    private int id;
    private String date;
    private String sender;
    private String message;
    private byte[] picture;

    public Message() {
        this.id = 0;
        this.date = "";
        this.sender = "";
        this.message = "";
    }

    public Message(int id, String date, String sender, String message) {
        this.id = id;
        this.date = date;
        this.sender = sender;
        this.message = message;
    }

    public Message(int id, String date, String sender, String message, byte[] picture) {
        this.id = id;
        this.date = date;
        this.sender = sender;
        this.message = message;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}