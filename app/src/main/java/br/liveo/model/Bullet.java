package br.liveo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 17 , 2015
 * Purpose      :   For questions circles, we are creating this model,
 * Description  :   Detailed Description...
 */
public class Bullet implements Parcelable {
    private String text;
    private int color;

    public Bullet(String text, int color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public int getColor() {
        return color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
