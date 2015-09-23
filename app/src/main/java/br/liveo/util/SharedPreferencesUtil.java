package br.liveo.util;

import android.content.Context;
import android.content.SharedPreferences;

import br.liveo.model.User;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softares & Technology
 * Date         :   7/29/2015
 * Purpose      :   To store user details in shared preferences.
 * Description  :   At startup, we will not prompt to user to login if a user is already logged in
 *                  So this login details will be stored in android shared preferences
 */

public class SharedPreferencesUtil {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedpreferences;
    public static final String PREFERENCES = "MahindraPreferences" ;
    public static final String NAME = "nameKey";
    public static final String EMAIL = "emailKey";
    public static final String PASSWORD = "passwordKey";
    public static final String PIC_URL = "picUrlKey";
    public static final String TYPE = "typeKey";

    public SharedPreferencesUtil(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }
    public User getUser() {
        if(sharedpreferences.contains(NAME)){
            return new User(
                    sharedpreferences.getString(NAME,""),
                    sharedpreferences.getString(EMAIL,""),
                    sharedpreferences.getString(PASSWORD,""),
                    sharedpreferences.getString(PIC_URL,""),
                    sharedpreferences.getInt(TYPE,0)
                    );
        }
        return null;
    }
    public void setUser(User user) {
        editor = sharedpreferences.edit();
        editor.putString(NAME, user.getUsername());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(PASSWORD, user.getPassword());
        editor.putString(PIC_URL, user.getPicUrl());
        editor.putInt(TYPE, user.getUserType());
        editor.commit();
    }

    public void flushPreferences() {
        editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }
}
