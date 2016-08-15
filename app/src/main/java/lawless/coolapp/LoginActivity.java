package lawless.coolapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;


public class LoginActivity extends AppCompatActivity {


    TokenTrackerExt tokenTrackerExt;
    ImageView background;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());



        setContentView(R.layout.activity_login);
        tokenTrackerExt = new TokenTrackerExt();
        background = (ImageView) findViewById(R.id.background);


        Drawable back = getResources().getDrawable(R.drawable.wall);
        background.setImageDrawable(back);

        int width =background.getMeasuredWidth();
        background.setMaxHeight(width);

        checkToken();

    }




    public  void checkToken()
    {System.out.println("checkToken()" );
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if(accessToken == null || accessToken.isExpired() ) {


        }
        else
        {
           login();
        }


    }



     public class TokenTrackerExt extends  AccessTokenTracker
     {

         @Override
         protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                    AccessToken currentAccessToken) {
             //On logout
             if(currentAccessToken == null || currentAccessToken.isExpired() ) {


             }
             else
             {
                 login();
             }
         }
     }

    public void login()
    {
        Intent Intent = new Intent(this , ProfileDataActivity.class);
        Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(Intent);
    }

}





