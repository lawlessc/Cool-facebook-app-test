package lawless.coolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

import lawless.coolapp.Fragments.LoginFragment;
import lawless.coolapp.Fragments.ProfileDetailsFragment;
import lawless.coolapp.Fragments.ProfilePhotoFragment;

/**
 * Created by Chris on 13/08/2016.
 */
public class ProfileDataActivity  extends AppCompatActivity {

    TokenTrackerExt tokenTrackerExt;

    Button filmPreferences;
    ProfilePhotoFragment  photoFragment;
    ProfileDetailsFragment profileDetailsFragment;
    FilmPreferencesButtonListener buttonListener;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        setContentView(R.layout.profile_view_activity);
        tokenTrackerExt = new TokenTrackerExt();
        photoFragment= (ProfilePhotoFragment) getSupportFragmentManager().findFragmentById(R.id.profile_photo_fragment);
        profileDetailsFragment= (ProfileDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.profile_det_fragment);



        filmPreferences =  (Button) findViewById(R.id.films_button);
        buttonListener= new FilmPreferencesButtonListener();
        filmPreferences.setOnClickListener(buttonListener);


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

    public void goToFilmPreferences()

    {
        Intent Intent = new Intent(this, FilmPreferencesActivity.class);
        Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(Intent);


    }

    public class FilmPreferencesButtonListener implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            goToFilmPreferences();
        }
    }


}


