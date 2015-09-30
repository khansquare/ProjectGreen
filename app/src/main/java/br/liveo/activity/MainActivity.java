package br.liveo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;

import br.liveo.Model.HelpLiveo;
import br.liveo.fragment.CalenderFragment;
import br.liveo.fragment.HelpAndSupportFragment;
import br.liveo.fragment.HomeFragment;

import br.liveo.fragment.MessageFragment;
import br.liveo.fragment.ProfileFragment;
import br.liveo.fragment.ReportsFragment;
import br.liveo.fragment.SettingsFragment;
import br.liveo.fragment.TestListPagerFragment;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.model.User;
import br.liveo.navigationliveo.NavigationLiveo;
import br.liveo.navigationviewpagerliveo.R;
import br.liveo.util.GeneralUtils;
import br.liveo.util.SharedPreferencesUtil;
/**
 * Author       :   Mohsin Khan
 * Designation  :   Android Developer
 * E-mail       :   khan.square@gmail.com
 * Company      :   Parasme Software & Technology
 * Date         :   September 5 , 2015
 * Purpose      :   Root Activity of the app
 * Description  :   On this activity,  we had implemented a drawer. All the main fragments will be loaded from here...
 */
@SuppressWarnings("Convert2Lambda")
public class MainActivity extends NavigationLiveo implements OnItemClickListener {
    private HelpLiveo mHelpLiveo;
    private GeneralUtils generalUtils;
    public static final String MAIN_FRAGMENT_STACK = "MAIN";
    @Override
    public void onInt(Bundle bundle) {
        generalUtils = new GeneralUtils(this);
        User user = new SharedPreferencesUtil(this).getUser();
        this.userName.setText(user.getUsername());
        this.userEmail.setText(user.getEmail());
        this.userBackground.setImageResource(R.drawable.ic_profile_background);
        generalUtils.loadProfilePic(this.userPhoto, user.getPicUrl());

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.home), R.mipmap.ic_home_black_24dp);
        //mHelpLiveo.addSubHeader(getString(R.string.categories)); //Item subHeader
        mHelpLiveo.addSeparator(); // Item separator
        mHelpLiveo.add(getString(R.string.tests), R.mipmap.ic_tests_black_24dp, 7);
        mHelpLiveo.add(getString(R.string.reports), R.mipmap.ic_report_black_24dp);
        mHelpLiveo.add(getString(R.string.message), R.mipmap.ic_message_black_24dp, 200);
        mHelpLiveo.add(getString(R.string.calender), R.mipmap.ic_calendar_black_24dp);
        mHelpLiveo.addSeparator(); // Item separator
        mHelpLiveo.add(getString(R.string.privacy_policy), R.mipmap.ic_privacy_black_24dp);
        mHelpLiveo.add(getString(R.string.help_and_support), R.mipmap.ic_help_black_24dp);

        with(this).startingPosition(0) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())
                .colorNameSubHeader(R.color.nliveo_green_colorPrimary)
                .colorItemSelected(R.color.nliveo_green_colorPrimary)
                .footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            case 0:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new TestListPagerFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReportsFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new MessageFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new CalenderFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
                break;
            case 8:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new HelpAndSupportFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new HelpAndSupportFragment())
                        .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
                break;
        }

        setElevationToolBar(position != 2 ? 15 : 0);
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment())
                    .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment())
                    .addToBackStack(MainActivity.MAIN_FRAGMENT_STACK).commit();
            footerBackground(R.color.nliveo_gray);
            closeDrawer();
        }
    };
}