package br.liveo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.liveo.model.User;
import br.liveo.navigationviewpagerliveo.R;
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

    private TextView userName;
    private TextView userEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        userName = (TextView)rootView.findViewById(R.id.userName);
        userEmail = (TextView)rootView.findViewById(R.id.userEmail);
        User user = new SharedPreferencesUtil(getActivity()).getUser();
        userName.setText(user.getUsername());
        userEmail.setText(user.getEmail());
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_basic, menu);


        menu.findItem(R.id.menu_notify).setVisible(true);
        menu.findItem(R.id.menu_sync).setVisible(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_notify:

                break;

            case R.id.menu_sync:


                break;
        }
        return true;
    }
}