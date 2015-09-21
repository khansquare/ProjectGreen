package br.liveo.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import br.liveo.navigationviewpagerliveo.R;


public class TestAttemptFragment extends Fragment {
    static TextView timerText;
    private boolean mSearchCheck;
    private FloatingActionButton buttonNext,buttonPrevious;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_attempt, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        buttonNext = (FloatingActionButton)rootView.findViewById(R.id.button_next);
        buttonPrevious = (FloatingActionButton)rootView.findViewById(R.id.button_previous);

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("NEXT", TestAttemptPagerFragment.mViewPager.getCurrentItem() + "");
                TestAttemptPagerFragment.mViewPager.setCurrentItem(TestAttemptPagerFragment.mViewPager.getCurrentItem() + 1);
                TestAttemptPagerFragment.mSlidingTabLayout.getTabAt(TestAttemptPagerFragment.mViewPager.getCurrentItem()+1).select();
            }
        });
        buttonPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("PREVIOUS", TestAttemptPagerFragment.mViewPager.getCurrentItem() + "");
                TestAttemptPagerFragment.mViewPager.setCurrentItem(TestAttemptPagerFragment.mViewPager.getCurrentItem() - 1);
                TestAttemptPagerFragment.mSlidingTabLayout.getTabAt(TestAttemptPagerFragment.mViewPager.getCurrentItem() - 1).select();
            }
        });
        TestAttemptPagerFragment.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position == TestAttemptPagerFragment.mViewPager.getCurrentItem()) {
                    if (position== 0) {
                        buttonPrevious.setVisibility(View.INVISIBLE);
                    }
                    else if (position != 0) {
                        buttonPrevious.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
       /* TestAttemptPagerFragment.mSlidingTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
             Log.e("onTabSlected Method", "Call");
               // buttonPrevious.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("onTabUnselected","Call");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("onTabReselected","Call");
            }
        });*/
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
        inflater.inflate(R.menu.menu_timer, menu);
        MenuItem timerItem = menu.findItem(R.id.action_timer);
        timerText  = (TextView) MenuItemCompat.getActionView(timerItem);
        timerText.setPadding(10, 0, 10, 0);

        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            boolean flag = true;
            @Override
            public void onFinish() {
            }

            @Override
            public void onTick(long millisecondsLeft) {
                if (flag) {
                    timerText.setText(String.valueOf(millisecondsLeft));
                    flag = false;
                }

                timerText.setText(String.valueOf((int) Math.round((Integer.parseInt(timerText.getText().toString()) / (double) 1000))));
            }
        };

        timer.start();
        mSearchCheck = false;
    }


}


