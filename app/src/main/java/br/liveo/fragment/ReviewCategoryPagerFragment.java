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
    public static ViewPager mViewPager;
    public static TabLayout mSlidingTabLayout;
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
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(mTabs.size());
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mTabs));

        mSlidingTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mSlidingTabLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
        mSlidingTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#EEEEEE"));
        mSlidingTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mSlidingTabLayout.setSoundEffectsEnabled(true);

        mSlidingTabLayout.getTabAt(getArguments().getInt("POSITION")).select();
        for (int i = 0; i < mSlidingTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mSlidingTabLayout.getTabAt(i);
            View convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_text, null, false);
            ((TextView) convertView.findViewById(R.id.txtQuestionNumber)).setText(bullets.get(i).getText());
            tab.setCustomView(convertView);
            if (i == getArguments().getInt("POSITION")) {
                select((TextView) convertView.findViewById(R.id.txtQuestionNumber));
            }
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                select((TextView) mSlidingTabLayout.getTabAt(position).getCustomView().findViewById(R.id.txtQuestionNumber));
                if (position > 0) {
                    deselect((TextView) mSlidingTabLayout.getTabAt(position - 1).getCustomView().findViewById(R.id.txtQuestionNumber));
                }
                if (position < mSlidingTabLayout.getTabCount() - 1 ) {
                    deselect((TextView) mSlidingTabLayout.getTabAt(position + 1).getCustomView().findViewById(R.id.txtQuestionNumber));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSlidingTabLayout.setElevation(10);
        }
    }

    private void select(TextView textView) {
        textView.setBackgroundResource(R.drawable.circle_green_filled_smaller);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void deselect(TextView textView) {
        textView.setBackgroundColor(Color.parseColor("#00000000"));
        textView.setTextColor(Color.parseColor("#4CAF50"));
    }
}