package lawless.coolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

import lawless.coolapp.Fragments.GraphSearchList;
import android.view.View.OnClickListener;
/**
 * Created by Chris on 13/08/2016.
 */
public class FilmPreferencesActivity extends AppCompatActivity {


    TokenTrackerExt tokenTrackerExt;
    GraphSearchList graphSearchList;
    Button backButton;
    BackButtonListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.film_preferences);

        tokenTrackerExt = new TokenTrackerExt();
        graphSearchList= (GraphSearchList) getSupportFragmentManager().findFragmentById(R.id.profile_det_fragment);


        backButton = (Button) findViewById(R.id.button);
        listener= new BackButtonListener();
        backButton.setOnClickListener(listener);


    }



    public class TokenTrackerExt extends AccessTokenTracker
    {

        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                   AccessToken currentAccessToken) {
            //On logout
            if (currentAccessToken == null || currentAccessToken.isExpired()) {
                return_to_login();
            }
        }
    }

    public void return_to_login()
    {
        Intent myIntent = new Intent(this, LoginActivity.class);
        this.startActivity(myIntent);
    }


    public void returnToProfile()

    {
        Intent myIntent = new Intent(this, ProfileDataActivity.class);
        this.startActivity(myIntent);
    }

    public class BackButtonListener implements OnClickListener
    {


        @Override
        public void onClick(View view) {
             returnToProfile();
        }
    }









}
