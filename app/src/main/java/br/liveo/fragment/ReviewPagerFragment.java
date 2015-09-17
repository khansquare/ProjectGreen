package br.liveo.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.liveo.adapter.TabPagerItem;
import br.liveo.adapter.ViewPagerAdapter;
import br.liveo.navigationviewpagerliveo.R;

import static br.liveo.model.TestCategory.ACCESS_KEY;
import static br.liveo.model.TestCategory.ATTEMPTED;
import static br.liveo.model.TestCategory.MISSED;
import static br.liveo.model.TestCategory.UPCOMING;

public class ReviewPagerFragment extends Fragment {
	private List<TabPagerItem> mTabs = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTabPagerItem();
    }

    private void createTabPagerItem(){
        mTabs.add(new TabPagerItem("Summary", new ReviewSummaryFragment()));

        Bundle bundleCorrect = new Bundle();
        bundleCorrect.putString("FROM", "CORRECT");
        ReviewCategoryFragment reviewCategoryFragmentCorrect = new ReviewCategoryFragment();
        reviewCategoryFragmentCorrect.setArguments(bundleCorrect);
        mTabs.add(new TabPagerItem("Correct", reviewCategoryFragmentCorrect));

        Bundle bundleIncorrect = new Bundle();
        bundleIncorrect.putString("FROM", "INCORRECT");
        ReviewCategoryFragment reviewCategoryFragmentIncorrect = new ReviewCategoryFragment();
        reviewCategoryFragmentIncorrect.setArguments(bundleIncorrect);
        mTabs.add(new TabPagerItem("Incorrect", reviewCategoryFragmentIncorrect));

        Bundle bundleUnanswered = new Bundle();
        bundleUnanswered.putString("FROM", "UNANSWERED");
        ReviewCategoryFragment reviewCategoryFragmentUnanswered = new ReviewCategoryFragment();
        reviewCategoryFragmentUnanswered.setArguments(bundleUnanswered);
        mTabs.add(new TabPagerItem("Unanswered", reviewCategoryFragmentUnanswered));
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
        mSlidingTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabLayout.setupWithViewPager(mViewPager);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSlidingTabLayout.setElevation(10);
        }
    }
}