package br.liveo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import br.liveo.navigationviewpagerliveo.R;

/**
 * Author       :   Rakesh Kumawat
 * Designation  :   Android Developer
 * E-mail       :   er.r.kumawat@gmail.com
 * Company      :   Parasme Softwares & Technology
 * Date         :   October 01 , 2015
 * Purpose      :   Home screen of the application
 * Description  :   Detailed Description...
 */
public class SplashActivity extends ActionBarActivity{
    private static String TAG = SplashActivity.class.getName();
    private static long SLEEP_TIME = 10;
    private ImageView one,two,three,four,five,six,seven,eight,nine,ten;
    private int i = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_splash);
        initialize();

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                if(i==1){}
                else if(i==2)
                    ten.setVisibility(View.VISIBLE);
                else if(i==3)
                    nine.setVisibility(View.VISIBLE);
                else if(i==4)
                    eight.setVisibility(View.VISIBLE);
                else if(i==5)
                    seven.setVisibility(View.VISIBLE);
                else if(i==6)
                    six.setVisibility(View.VISIBLE);
                else if(i==7)
                    five.setVisibility(View.VISIBLE);
                else if(i==8)
                    four.setVisibility(View.VISIBLE);
                else if(i==9)
                    three.setVisibility(View.VISIBLE);
                else if(i==10)
                    two.setVisibility(View.VISIBLE);
                i++;
            }

            @Override
            public void onFinish() {

            }
        }.start();

        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }

    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(SLEEP_TIME*1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            // Start main activity
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
    private void initialize(){
        one = (ImageView) findViewById(R.id.imageView1);
        one.setVisibility(View.VISIBLE);
        two = (ImageView) findViewById(R.id.imageView2);
        three = (ImageView) findViewById(R.id.imageView3);
        four= (ImageView) findViewById(R.id.imageView4);
        five = (ImageView) findViewById(R.id.imageView5);
        six = (ImageView) findViewById(R.id.imageView6);
        seven = (ImageView) findViewById(R.id.imageView7);
        eight = (ImageView) findViewById(R.id.imageView8);
        nine = (ImageView) findViewById(R.id.imageView9);
        ten = (ImageView) findViewById(R.id.imageView10);

    }
}