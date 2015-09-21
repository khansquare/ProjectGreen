package br.liveo.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   Sept 7 , 2015
 * Purpose      :   General utilities for the app
 * Description  :   Some basic operations like checking internet connectivity, setting default fonts, making toolbar etc.
 */

@SuppressWarnings({"ConstantConditions", "deprecation"})
public class GeneralUtils {
    private Context context;

    public GeneralUtils(Context context) {
        this.context = context;
    }

    public Typeface getRegularTypeFace () {
        return Typeface.createFromAsset(context.getAssets(), "fonts/regular_font.otf");
    }

    public Boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if(info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public int getScreenWidth() {
        int width;
        Point point = new Point();
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT < 13) {
            width=display.getWidth();
        } else {
            display.getSize(point);
            width=point.x;
        }
        return width;
    }
    public int getScreenHeight() {
        Point point = new Point();
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT < 13) {
            return display.getHeight();
        } else {
            display.getSize(point);
            return point.y;
        }
    }
    public static Rect locateView(View v) {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException e) {
            return null;
        }
        Rect position = new Rect();
        position.left = loc_int[0];
        position.top = loc_int[1];
        position.right = position.left + v.getWidth();
        position.bottom = position.top + v.getHeight();
        return position;
    }

    public void showAlertDialog(String title, String message) {
        View dialogLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_dialog, null);
        final PopupWindow popupWindow = new PopupWindow(dialogLayout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        TextView txtDialogTitle = (TextView) dialogLayout.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        txtDialogTitle.setTypeface(getRegularTypeFace());

        TextView txtDialogMessage = (TextView) dialogLayout.findViewById(R.id.txtDialogMessage);
        txtDialogMessage.setText(message);
        txtDialogMessage.setTypeface(getRegularTypeFace());

        Button btnDialogOk = (Button) dialogLayout.findViewById(R.id.btnDialogOk);
        btnDialogOk.setTypeface(getRegularTypeFace());
        btnDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(dialogLayout, Gravity.CENTER, 0, 0);
    }

    public void loadProfilePic(final ImageView imageView, final String url) {
        new AsyncTask<Void, Void, Void>() {
            Bitmap bitmap;
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Log.d("GeneralUtils", url);
                    InputStream in = new URL(url).openStream();
                    bitmap = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                if (bitmap != null)
                    imageView.setImageBitmap(bitmap);
            }
        }.execute();
    }

    public void resetActionBarTitle(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setTitle(context.getResources().getString(R.string.app_name));
        }
    }

    public void setActionBarTitle(ActionBar actionBar, String title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}