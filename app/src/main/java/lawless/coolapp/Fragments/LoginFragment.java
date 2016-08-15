package lawless.coolapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import lawless.coolapp.ProfileDataActivity;
import lawless.coolapp.R;

/**
 * Created by Chris on 11/08/2016.
 */
public class LoginFragment extends Fragment {

    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment_layout, container, false);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("user_likes");
        loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions("user_birthday");
        // If using in a fragment
        loginButton.setFragment(this);


        // Other app specific specialization


        // Callback registration
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("onSuccess   "+loginResult);
            //    ((MainActivity) getActivity()).update(); // We don't need to do this as access token tracker's call back can do it.

                Intent myIntent = new Intent(getActivity(), ProfileDataActivity.class);
                getActivity().startActivity(myIntent);



            }

            @Override
            public void onCancel() {
                // App code
                System.out.println("onCancel()");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                System.out.println("onError :" +exception);
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



}


