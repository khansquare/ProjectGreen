package br.liveo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.liveo.activity.MainActivity;
import br.liveo.navigationviewpagerliveo.R;

import static br.liveo.model.TestCategory.ACCESS_KEY;
import static br.liveo.model.TestCategory.LIVE;

/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 12 , 2015
 * Purpose      :   To display overview of the test
 * Description  :   Detailed Description...
 */
public class TestDetailOverviewFragment extends Fragment {
    private Button btnTakeTestAtOverview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_overview, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        btnTakeTestAtOverview=(Button)rootView.findViewById(R.id.btnTakeTestAtOverview);
        if (getArguments().getInt(ACCESS_KEY) == LIVE) {
            rootView.findViewById(R.id.layoutFooterAtOverview).setVisibility(View.VISIBLE);
        } else {
            rootView.findViewById(R.id.layoutFooterAtOverview).setVisibility(View.GONE);
        }
        btnTakeTestAtOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,  new TestAttemptPagerFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
            }
        });
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

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_sync));

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
