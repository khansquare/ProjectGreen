package br.liveo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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
    private View rootView;
    private ListView listViewTests;
    private ArrayList<Test> tests;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_test_list, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initializeAllElements();
        setListViewOnItemLongClickListener();
        setListViewOnItemClickListener();
        return rootView;
    }

    private void initializeAllElements() {
        tests = new ArrayList<>();
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
    }

    private void setListViewOnItemClickListener() {
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
    }

    private void setListViewOnItemLongClickListener() {
        listViewTests.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(getArguments().getInt(ACCESS_KEY) == MISSED) {
                    ((Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(50);
                    View dialogLayout = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_options, null);
                    final PopupWindow popupWindow = new PopupWindow(dialogLayout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

                    Button btnDialogClose = (Button) dialogLayout.findViewById(R.id.btnClose);
                    btnDialogClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });

                    popupWindow.showAtLocation(dialogLayout, Gravity.CENTER, 0, 0);
                }
                return true;
            }
        });
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