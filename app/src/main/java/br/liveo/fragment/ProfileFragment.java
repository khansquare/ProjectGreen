package br.liveo.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
 * Date         :   September 23 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class ProfileFragment extends Fragment {

    private ListView listViewProfile;
    private TextView userName;
    ImageView profilePic;
    private GeneralUtils generalUtils;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        listViewProfile = (ListView) rootView.findViewById(R.id.listViewProfile);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Father's Name");
        titles.add("Phone Number");
        titles.add("Email Address");
        titles.add("Permanent Address");

        ArrayList<String> values = new ArrayList<>();
        User user = new SharedPreferencesUtil(getActivity()).getUser();
        values.add("Michael Clark");
        values.add("1234567890");
        values.add(user.getEmail());
        values.add("2/33, Main Street, Marray hills, California");
        TypedArray proficIcon = getResources().obtainTypedArray(R.array.profileIcon);
        profilePic = (ImageView) rootView.findViewById(R.id.imgPicture);
        generalUtils = new GeneralUtils(getActivity());
        generalUtils.loadProfilePic(this.profilePic, user.getPicUrl());
        userName = (TextView) rootView.findViewById(R.id.userName);
        userName.setText(user.getUsername().toUpperCase());
        listViewProfile.setAdapter(new CustomListAdapter(getActivity(), R.layout.layout_profile, titles, values, proficIcon));
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


}