package br.liveo.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.liveo.adapter.TabPagerItem;
import br.liveo.adapter.ViewPagerAdapter;
import br.liveo.model.Question;
import br.liveo.navigationviewpagerliveo.R;

public class RunningTestPagerFragment extends Fragment {
    public static ViewPager mViewPager;
    public static TabLayout mSlidingTabLayout;
    private List<TabPagerItem> mTabs = new ArrayList<>();
    private FloatingActionButton btnNext;
    private FloatingActionButton btnPrevious;
    private FloatingActionButton btnSummary;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTabPagerItem();
    }

    private void createTabPagerItem() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Which is a reserved word in the Java programming language?","method","native","subclasses","reference"));
        questions.add(new Question("Which one of these lists contains only Java programming language keywords?","class, if, void, long, Int, continue","goto, instanceof, native, finally, default, throws","try, virtual, throw, final, volatile, transient","byte, break, assert, switch, include"));
        questions.add(new Question("Which is a valid keyword in java?", "interface", "string", "unsigned", "Float"));
        questions.add(new Question("Which is the valid declarations within an interface definition?", "public double methoda();", "ublic final double methoda();", "static void methoda(double d1);", "protected void methoda(double d1);"));
        questions.add(new Question("Which is the valid declarations within an interface definition the valid declarations within an interface definition the valid declarations within an interface definition?", "public double methoda();public double methoda();public double methoda();", "public double methoda();public double methoda();public double methoda();;", "public double methoda();public double methoda();public double methoda();public double methoda();public double methoda();public double methoda();public double methoda();", "protected void methoda(double d1);"));
        for (int i = 0; i < questions.size(); i++) {
            Bundle bundle = new Bundle();
            RunningTestQuestionFragment runningTestQuestionFragment = new RunningTestQuestionFragment();
            bundle.putParcelable("QUESTION", questions.get(i));
            runningTestQuestionFragment.setArguments(bundle);
            mTabs.add(new TabPagerItem(String.valueOf(i), runningTestQuestionFragment));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager_running_test, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        btnNext = (FloatingActionButton)rootView.findViewById(R.id.btnNext);
        btnPrevious = (FloatingActionButton)rootView.findViewById(R.id.btnPrevious);
        btnSummary =  (FloatingActionButton)rootView.findViewById(R.id.btnSummary);
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new RunningTestReviewFragment()).commit();
            }
        });
        btnPrevious.setVisibility(View.INVISIBLE);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(mTabs.size());
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mTabs));
        mSlidingTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mSlidingTabLayout.setBackgroundColor(Color.parseColor("#EEEEEE"));
        mSlidingTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mSlidingTabLayout.setupWithViewPager(mViewPager);
        mSlidingTabLayout.setTop(20);
        mSlidingTabLayout.setSoundEffectsEnabled(true);
        mSlidingTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#EEEEEE"));

        mSlidingTabLayout.getTabAt(0).select();
        for (int i = 0; i < mSlidingTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mSlidingTabLayout.getTabAt(i);
            View convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_text, null, false);
            ((TextView) convertView.findViewById(R.id.txtQuestionNumber)).setText(String.valueOf(i+1));
            tab.setCustomView(convertView);
            if (i == 0) {
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
                    btnPrevious.setVisibility(View.VISIBLE);
                    deselect((TextView) mSlidingTabLayout.getTabAt(position - 1).getCustomView().findViewById(R.id.txtQuestionNumber));
                } else {
                    btnPrevious.setVisibility(View.INVISIBLE);
                }
                if (position < mSlidingTabLayout.getTabCount() - 1 ) {
                    deselect((TextView) mSlidingTabLayout.getTabAt(position + 1).getCustomView().findViewById(R.id.txtQuestionNumber));
                } else if (mViewPager.getCurrentItem() == mSlidingTabLayout.getTabCount()) {
                    //btnNext.setIcon(R.drawable.ic_done_all_white_24dp);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSlidingTabLayout.setElevation(10);
        }
        setBtnNextOnClickListener();
        setBtnPreviousOnClickListener();
    }

    private void select(TextView textView) {
        textView.setBackgroundResource(R.drawable.circle_green_filled_smaller);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void deselect(TextView textView) {
        textView.setBackgroundColor(Color.parseColor("#00000000"));
        textView.setTextColor(Color.parseColor("#4CAF50"));
    }
    private void setBtnNextOnClickListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() < mSlidingTabLayout.getTabCount() - 1) {
                    mSlidingTabLayout.getTabAt(mViewPager.getCurrentItem() + 1).select();
                } else if (mViewPager.getCurrentItem() == mSlidingTabLayout.getTabCount() - 1) {
                    //btnNext.setIcon(R.drawable.ic_done_all_white_24dp);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new RunningTestReviewFragment()).commit();
                }
            }
        });
    }

    private void setBtnPreviousOnClickListener() {

        btnPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() > 0) {
                    mSlidingTabLayout.getTabAt(mViewPager.getCurrentItem() - 1).select();
                }
            }
        });
    }
}