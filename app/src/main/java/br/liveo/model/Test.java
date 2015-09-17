package br.liveo.model;

import java.util.Date;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 11 , 2015
 * Purpose      :   Test model class
 * Description  :   Detailed Description...
 */

public class Test {
    private int id;
    private Date date;
    private String title;
    private String duration;
    private int category;

    public Test() {

    }

    public Test(Date date, String title, String duration, int category) {
        this.id = 0;
        this.date = date;
        this.title = title;
        this.duration = duration;
        this.category = category;
    }

    public Test(int id, Date date, String title, String duration, int category) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.duration = duration;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
