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
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import br.liveo.activity.MainActivity;
import br.liveo.adapter.TestListAdapter;
import br.liveo.model.Test;
import br.liveo.navigationviewpagerliveo.R;

import static br.liveo.model.TestCategory.ACCESS_KEY;
import static br.liveo.model.TestCategory.ATTEMPTED;
import static br.liveo.model.TestCategory.MISSED;
import static br.liveo.model.TestCategory.UPCOMING;

@SuppressWarnings("deprecation")
public class TestListFragment extends Fragment {
    private ListView listViewTests;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_list, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        final ArrayList<Test> tests = new ArrayList<>();
        listViewTests = (ListView) rootView.findViewById(R.id.listViewTests);
        listViewTests.setEmptyView(rootView.findViewById(R.id.txtEmptyListLabel));
        if(getArguments().getInt(ACCESS_KEY) == MISSED) {
            tests.add(new Test(new Date(2014,1,26), "Part Test - 1", "3 Hours", MISSED));
            tests.add(new Test(new Date(2015,2,1), "Part Test - 2", "3 Hours", MISSED));
            tests.add(new Test(new Date(2015,3,6), "Part Test - 3", "1 Hours", MISSED));
            tests.add(new Test(new Date(2015,6,11), "Part Test - 4", "2 Hours", MISSED));
            tests.add(new Test(new Date(2015,8,24), "Part Test - 5", "3 Hours", MISSED));
        } else if(getArguments().getInt(ACCESS_KEY) == ATTEMPTED) {
            tests.add(new Test(new Date(2015,5,2), "Part Test - 21", "1 Hours", ATTEMPTED));
            tests.add(new Test(new Date(2015,7,6), "Part Test - 25", "2 Hours", ATTEMPTED));
            tests.add(new Test(new Date(2015,8,6), "Part Test - 36", "1 Hours", ATTEMPTED));
            tests.add(new Test(new Date(2015,9,12), "Part Test - 48", "2 Hours", ATTEMPTED));
            tests.add(new Test(new Date(2015,11,28), "Part Test - 50", "1.3 Hours", ATTEMPTED));
            tests.add(new Test(new Date(2015,7,6), "Part Test - 25", "2 Hours", ATTEMPTED));
            tests.add(new Test(new Date(2015,8,6), "Part Test - 36", "1 Hours", ATTEMPTED));
            tests.add(new Test(new Date(2015,9,12), "Part Test - 48", "2 Hours", ATTEMPTED));
        } else if(getArguments().getInt(ACCESS_KEY) == UPCOMING) {
            tests.add(new Test(new Date(2015,9,12), "Part Test - 48", "2 Hours", ATTEMPTED));
        }

        listViewTests.setAdapter(new TestListAdapter(getActivity(), R.layout.layout_test, tests));
        listViewTests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt(ACCESS_KEY, getArguments().getInt(ACCESS_KEY));
                bundle.putString("TITLE", tests.get(position).getTitle());
                Fragment fragment = new TestDetailPagerFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
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