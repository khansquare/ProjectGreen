package br.liveo.model;

import java.io.Serializable;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 08 , 2015
 * Purpose      :   User Model
 * Description  :   Detailed Description...
 */

public class User implements Serializable {
    private String username;
    private String email;
    private String password;
    private String picUrl;
    private int userType;

    public User() {
        this.username = "";
        this.email = "";
        this.password = "";
        this.picUrl = "";
        this.userType = UserType.STUDENT;
    }

    public User(String username, String email, String password, String picUrl, int userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.picUrl = picUrl;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}