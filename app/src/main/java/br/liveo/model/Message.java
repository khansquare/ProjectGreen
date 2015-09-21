package br.liveo.model;

import java.util.Date;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 21 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class Message {
    private int id;
    private String date;
    private String title;
    private String message;

    public Message(int id, String date, String title, String message) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {

        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}