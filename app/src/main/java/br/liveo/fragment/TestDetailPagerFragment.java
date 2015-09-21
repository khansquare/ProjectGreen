package br.liveo.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.liveo.adapter.TabPagerItem;
import br.liveo.adapter.ViewPagerAdapter;
import br.liveo.navigationviewpagerliveo.R;
import br.liveo.util.GeneralUtils;

import static br.liveo.model.TestCategory.*;

public class TestDetailPagerFragment extends Fragment {
    private final int TOTAL_ITEMS = 3;
    private GeneralUtils generalUtils;
	private List<TabPagerItem> mTabs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generalUtils = new GeneralUtils(getActivity());
        createTabPagerItem();
        generalUtils.setActionBarTitle(((AppCompatActivity) getActivity()).getSupportActionBar(), getArguments().getString("TITLE"));
    }

    private void createTabPagerItem(){
        Bundle bundle = new Bundle();
        if (getArguments().getInt(ACCESS_KEY) == LIVE) {
            bundle.putInt(ACCESS_KEY,LIVE);
            TestDetailOverviewFragment testDetailOverviewFragment = new TestDetailOverviewFragment();
            testDetailOverviewFragment.setArguments(bundle);
            mTabs.add(new TabPagerItem("Overview", testDetailOverviewFragment));
            mTabs.add(new TabPagerItem("Syllabus", new TestDetailSyllabusFragment()));
        } else if (getArguments().getInt(ACCESS_KEY) == MISSED) {
            bundle.putInt(ACCESS_KEY,MISSED);
            TestDetailOverviewFragment testDetailOverviewFragment = new TestDetailOverviewFragment();
            testDetailOverviewFragment.setArguments(bundle);
            mTabs.add(new TabPagerItem("Overview", testDetailOverviewFragment));
            mTabs.add(new TabPagerItem("Syllabus", new TestDetailSyllabusFragment()));
        } else if (getArguments().getInt(ACCESS_KEY) == ATTEMPTED){
            bundle.putInt(ACCESS_KEY,ATTEMPTED);
            TestDetailOverviewFragment testDetailOverviewFragment = new TestDetailOverviewFragment();
            testDetailOverviewFragment.setArguments(bundle);
            mTabs.add(new TabPagerItem("Overview", testDetailOverviewFragment));
            mTabs.add(new TabPagerItem("Syllabus", new TestDetailSyllabusFragment()));
            mTabs.add(new TabPagerItem("Report", new TestDetailReportFragment()));
        } else if (getArguments().getInt(ACCESS_KEY) == UPCOMING){
            bundle.putInt(ACCESS_KEY,UPCOMING);
            TestDetailOverviewFragment testDetailOverviewFragment = new TestDetailOverviewFragment();
            testDetailOverviewFragment.setArguments(bundle);
            mTabs.add(new TabPagerItem("Overview", testDetailOverviewFragment));
            mTabs.add(new TabPagerItem("Syllabus", new TestDetailSyllabusFragment()));
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

    @Override
    public void onDetach() {
        super.onDetach();
        generalUtils.resetActionBarTitle(((AppCompatActivity) getActivity()).getSupportActionBar());
    }
}