package br.liveo.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.liveo.adapter.CustomListAdapter;
import br.liveo.model.User;
import br.liveo.navigationviewpagerliveo.R;
import br.liveo.util.GeneralUtils;
import br.liveo.util.SharedPreferencesUtil;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 30 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class ProfileActivity extends ActionBarActivity {
    private  ImageView btnBack;
    private ListView listViewProfile;
    private TextView userName;
    ImageView profilePic;
    private GeneralUtils generalUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnBack = (ImageView)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listViewProfile = (ListView)findViewById(R.id.listViewProfile);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Father's Name");
        titles.add("Phone Number");
        titles.add("Email Address");
        titles.add("Permanent Address");

        ArrayList<String> values = new ArrayList<>();
        User user = new SharedPreferencesUtil(getApplicationContext()).getUser();
        values.add("Michael Clark");
        values.add("1234567890");
        values.add(user.getEmail());
        values.add("2/33, Main Street, Marray hills, California");
        TypedArray proficIcon = getResources().obtainTypedArray(R.array.profileIcon);
        profilePic = (ImageView)findViewById(R.id.imgPicture);
        generalUtils = new GeneralUtils(getApplicationContext());
        generalUtils.loadProfilePic(this.profilePic, user.getPicUrl());
        userName = (TextView)findViewById(R.id.userName);
        userName.setText(user.getUsername().toUpperCase());
        listViewProfile.setAdapter(new CustomListAdapter(ProfileActivity.this, R.layout.layout_profile, titles, values, proficIcon));
        listViewProfile.addHeaderView(getLayoutInflater().inflate(R.layout.layout_message, null));
    }
}