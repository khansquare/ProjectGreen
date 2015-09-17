package br.liveo.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.liveo.adapter.TabPagerItem;
import br.liveo.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.liveo.navigationviewpagerliveo.R;

import static br.liveo.model.TestCategory.*;

public class TestListPagerFragment extends Fragment {
    private final int TOTAL_ITEMS = 3;
	private List<TabPagerItem> mTabs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTabPagerItem();
    }

    private void createTabPagerItem(){
        ArrayList<String> arrayListTabCaptions = new ArrayList<>();
        arrayListTabCaptions.add(getString(R.string.missed));
        arrayListTabCaptions.add(getString(R.string.attempted));
        arrayListTabCaptions.add(getString(R.string.upcoming));

        Bundle bundles [] = new Bundle[TOTAL_ITEMS];
        TestListFragment fragments[] = new TestListFragment[TOTAL_ITEMS];

        for (int i = 0; i < TOTAL_ITEMS; i++) {
            bundles[i] = new Bundle();
            fragments[i] = new TestListFragment();
            fragments[i].setArguments(bundles[i]);
            if (i == 0) bundles[i].putInt(ACCESS_KEY, MISSED);
            else if (i == 1) bundles[i].putInt(ACCESS_KEY, ATTEMPTED);
            else if (i == 2) bundles[i].putInt(ACCESS_KEY, UPCOMING);
            mTabs.add(new TabPagerItem(arrayListTabCaptions.get(i), fragments[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
    	
    	mViewPager.setOffscreenPageLimit(mTabs.size());
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mTabs));
        TabLayout mSlidingTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSlidingTabLayout.setElevation(10);
        }
        mSlidingTabLayout.setupWithViewPager(mViewPager);
    }
}