package br.liveo.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 10 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public ImageDownloader(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}