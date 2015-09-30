package br.liveo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 30 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class SettingsActivity extends ActionBarActivity {
    private ImageView btnBack;
    private LinearLayout layoutChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ((ImageView)findViewById(R.id.imgNotification)).setColorFilter(getApplicationContext().getResources().getColor(R.color.nliveo_green_colorPrimary));
        ((ImageView)findViewById(R.id.imgPassword)).setColorFilter(getApplicationContext().getResources().getColor(R.color.nliveo_green_colorPrimary));
        ((ImageView)findViewById(R.id.imgOrganization)).setColorFilter(getApplicationContext().getResources().getColor(R.color.nliveo_green_colorPrimary));
        ((ImageView)findViewById(R.id.imgTerms)).setColorFilter(getApplicationContext().getResources().getColor(R.color.nliveo_green_colorPrimary));
        layoutChangePassword = (LinearLayout)findViewById(R.id.layoutChangePassword);
        layoutChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogLayout = getLayoutInflater().inflate(R.layout.layout_change_password, null);
                final PopupWindow popupWindow = new PopupWindow(dialogLayout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
                Button btnDialogClose = (Button) dialogLayout.findViewById(R.id.btnChangePassword);
                btnDialogClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(dialogLayout, Gravity.CENTER, 0, 0);
            }
        });
    }
}