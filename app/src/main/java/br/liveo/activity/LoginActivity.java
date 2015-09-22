package br.liveo.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import br.liveo.model.UserType;
import br.liveo.model.User;
import br.liveo.navigationviewpagerliveo.R;
import br.liveo.util.GeneralUtils;
import br.liveo.util.SharedPreferencesUtil;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import static br.liveo.model.UserType.STUDENT;
import static br.liveo.model.UserType.GUARDIAN;

/**
 * Author       :   Mohsin Khan & Rakesh Kumawat
 * Company      :   Parasme Software & Technology
 * Date         :   Sept 5, 2015
 * Purpose      :   User Login
 * Description  :   Login using app default login, google plus, twitter and facebook.
 */

@SuppressWarnings({"deprecation", "TryWithIdenticalCatches"})
public class LoginActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private ViewFlipper viewFlipper;
    private EditText editEmail;
    private EditText editPassword;
    private TextView txtEmail;

    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;
    private static final int RC_SIGN_IN = 0;

    private CallbackManager callbackManager;

    private static final String TAG = "LoginActivity";
    private GeneralUtils generalUtils;

    static String CONSUMER_KEY = "n4DkGeJKFHwmI0e4G6MnGkPJy";
    static String CONSUMER_SECRET = "mQY34Rm2QXxB986wKgL7M9DI2347DmFjvhXhyhKBT7L0J8PfAc";
    private Twitter twitter;
    private RequestToken requestToken = null;
    private AccessToken accessToken;

    private Dialog dialogAuth;
    private WebView webview;
    private ProgressDialog progress;
    private String oauth_url,oauth_verifier;
    private String twitterName,twitterPicUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(new SharedPreferencesUtil(this).getUser() != null ) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
        initializeAllElements();
        setBtnNextListener();
        setBtnBackListener();
        setBtnBackToLoginListener();
        setBtnLoginListener();
        setBtnGoogleListener();
        setBtnFacebookListener();
        setBtnTwitterListener();
        setOnForgetPasswordListener();
    }

    private void initializeAllElements() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        generalUtils = new GeneralUtils(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    private void setBtnTwitterListener() {
        ImageButton btnTwitter = (ImageButton) findViewById(R.id.btnTwitter);
        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
                new TokenGet().execute();
            }
        });

    }

    private void setBtnGoogleListener() {
        ImageButton btnGoogle = (ImageButton) findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "GOOGLE BUTTON CLICK");
                if (generalUtils.isConnected()) {
                    if (!mGoogleApiClient.isConnecting()) {
                        mSignInClicked = true;
                        resolveSignInError();
                    }
                } else {
                    generalUtils.showAlertDialog("Error", "Please connect to the internet. then give it another shot.");
                }
            }
        });
    }

    private void setBtnLoginListener() {
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(editPassword.getText().toString().trim().equals(""))) {
                    startMainActivity(new User("Charles Gibson", "gibson_charles@yahoo.com", "gibson_charles@yahoo.com", STUDENT));
                } else {
                    editPassword.setError("Please enter password");
                }
            }
        });
    }

    private void setBtnNextListener() {
        Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (editEmail.getText().toString().matches ("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {

                } else {
                    editEmail.setError("Please enter a valid email address");
                }*/
                txtEmail.setText(editEmail.getText().toString());
                viewFlipper.showNext();
            }
        });
    }

    private void setOnForgetPasswordListener() {
        findViewById(R.id.txtForgetPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
                viewFlipper.showNext();
            }
        });
    }

    private void setBtnBackToLoginListener() {
        ImageButton btnBackToLogin = (ImageButton) findViewById(R.id.btnBackToLogin);
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
                viewFlipper.showPrevious();
            }
        });
    }

    private void setBtnBackListener() {
        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void resolveSignInError() {
        if (mConnectionResult != null) {
            if (mConnectionResult.hasResolution()) {
                try {
                    mIntentInProgress = true;
                    mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
                } catch (IntentSender.SendIntentException e) {
                    mIntentInProgress = false;
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
            return;
        }

        if (!mIntentInProgress) {
            mConnectionResult = result;
            if (mSignInClicked) {
                resolveSignInError();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        callbackManager.onActivityResult(requestCode, responseCode, intent);
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }
            mIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mSignInClicked = false;
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
                String personName = currentPerson.getDisplayName();
                String personPhotoUrl = currentPerson.getImage().getUrl();
                String personGooglePlusProfile = currentPerson.getUrl();
                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
                Log.e(TAG, "Name: " + personName + ", Profile: " + personGooglePlusProfile + ", email: " + email + ", Gender: " + currentPerson.getGender() + ", Location: " + currentPerson.getCurrentLocation() + ", Age Group: " + currentPerson.getAgeRange() + ", About Me: " + currentPerson.getAboutMe() + ", Birthday: " + currentPerson.getBirthday() + ", Image: " + personPhotoUrl);
                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                }
                startMainActivity(new User(personName, email, personPhotoUrl.substring(0, personPhotoUrl.length() - 2) + 200, STUDENT));
            } else {
                generalUtils.showAlertDialog("Error", "Did not find any useful information in profile.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    private void setBtnFacebookListener() {
        LoginButton loginButton = (LoginButton) findViewById(R.id.btnFacebook);
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        loginButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        loginButton.setBackgroundResource(R.drawable.btn_facebook);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("FACEBOOK", "SUCCESS");
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e("FB_JSON", object.toString());
                        try {
                            User user;
                            if (object.has("email")) {
                                user = new User(object.getString("name"), object.getString("email"), "http://graph.facebook.com/"+object.getString("id")+"/picture?type=large", STUDENT);
                            } else {
                                user = new User(object.getString("name"), "Facebook : Email is not available", "http://graph.facebook.com/"+object.getString("id")+"/picture?type=small", STUDENT);
                            }
                            startMainActivity(user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                generalUtils.showAlertDialog("Error", "Please connect to the internet. then give it another shot.");
            }

            @Override
            public void onError(FacebookException e) {
                generalUtils.showAlertDialog("Error", "Some errors has been occurred while logging into the facebook.");
            }
        });
        try {
            PackageInfo info = getPackageManager().getPackageInfo("br.liveo.navigationviewpagerliveo", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }
        this.finish();
    }

    private class TokenGet extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            try {
                requestToken = twitter.getOAuthRequestToken();
                oauth_url = requestToken.getAuthorizationURL();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return oauth_url;
        }
        @Override
        protected void onPostExecute(String oauth_url) {
            if(oauth_url != null){
                dialogAuth = new Dialog(LoginActivity.this);
                dialogAuth.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogAuth.setContentView(R.layout.layout_auth);
                webview = (WebView) dialogAuth.findViewById(R.id.webview);
                webview.getSettings().setJavaScriptEnabled(true);
                webview.loadUrl(oauth_url);
                webview.setWebViewClient(new WebViewClient() {
                    boolean authComplete = false;
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon){
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if (url.contains("oauth_verifier") && authComplete == false){
                            authComplete = true;
                            Uri uri = Uri.parse(url);
                            oauth_verifier = uri.getQueryParameter("oauth_verifier");
                            dialogAuth.dismiss();
                            new AccessTokenGet().execute();
                        }else if(url.contains("denied")){
                            dialogAuth.dismiss();
                            generalUtils.showAlertDialog("Error", "Sorry !, Permission Denied.");
                        }
                    }
                });
                dialogAuth.show();
                dialogAuth.setCancelable(true);
            }else{
                generalUtils.showAlertDialog("Error", "Please connect to the internet. then give it another shot.");
            }
        }
    }

    private class AccessTokenGet extends AsyncTask<String, String, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(LoginActivity.this);
            progress.setMessage("Fetching Data...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
        }

        @Override
        protected Boolean doInBackground(String... args) {

            try {
                accessToken = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
                accessToken.getToken();
                twitter4j.User user = twitter.showUser(accessToken.getUserId());
                twitterName=user.getName();
                twitterPicUrl= user.getOriginalProfileImageURL();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return true;
        }
        @Override
        protected void onPostExecute(Boolean response) {
            if(response){
                progress.hide();
                startMainActivity(new User(twitterName, "Twitter : Email is not available", twitterPicUrl, STUDENT));
            }
        }
    }

    public void startMainActivity(User user) {
        new SharedPreferencesUtil(LoginActivity.this).setUser(user);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        LoginActivity.this.finish();
    }
}