package br.liveo.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.liveo.adapter.TabPagerItem;
import br.liveo.adapter.ViewPagerAdapter;
import br.liveo.model.Bullet;
import br.liveo.navigationviewpagerliveo.R;

public class ReviewCategoryPagerFragment extends Fragment {
    private ArrayList<Bullet> bullets;
	private List<TabPagerItem> mTabs = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTabPagerItem();
    }

    private void createTabPagerItem(){
        bullets = getArguments().getParcelableArrayList("DATA");
        Bundle bundle[] = new Bundle[getArguments().getInt("SIZE")];
        for (int i = 0; i < bundle.length; i++) {
            bundle[i] = new Bundle();
            //bundle[i].putString("FROM", "UNANSWERED");
            ReviewQuestionFragment reviewQuestionFragment = new ReviewQuestionFragment();
            reviewQuestionFragment.setArguments(bundle[i]);
            mTabs.add(new TabPagerItem(String.valueOf(i), reviewQuestionFragment));
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
        mSlidingTabLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
        mSlidingTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mSlidingTabLayout.getTabAt(getArguments().getInt("POSITION")).select();
        mSlidingTabLayout.setTop(20);
        mSlidingTabLayout.setSoundEffectsEnabled(true);

        for (int i = 0; i < mSlidingTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mSlidingTabLayout.getTabAt(i);
            View convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_text, null, false);
            ((TextView) convertView.findViewById(R.id.txtQuestionNumber)).setText(bullets.get(i).getText());
            tab.setCustomView(convertView);
        }

        mSlidingTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /*convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_bullet, null, false);
                tab.setCustomView(convertView);*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                /*convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_text, null, false);
                tab.setCustomView(convertView);*/
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                /*v = getActivity().getLayoutInflater().inflate(R.layout.layout_bullet, null, false);
                tab.setCustomView(v);*/
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSlidingTabLayout.setElevation(10);
        }
    }
}