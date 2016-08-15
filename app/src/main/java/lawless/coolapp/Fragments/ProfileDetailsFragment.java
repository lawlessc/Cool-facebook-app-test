package lawless.coolapp.Fragments;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import lawless.coolapp.R;

/**
 * Created by Chris on 14/08/2016.
 */
public class ProfileDetailsFragment extends Fragment {


    TextView name;
    TextView email;
    TextView birthday;




    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_data_fragment, container, false);
        name = (TextView) view.findViewById(R.id.user_name_text);
        birthday = (TextView) view.findViewById(R.id.date_of_birth);
        email = (TextView) view.findViewById(R.id.email_address);

        getProfileData();

        return view;
    }



    public void getProfileData()
    {
        GraphRequest request = GraphRequest.newMeRequest( AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object,GraphResponse response) {
                        try {
                            String  name_=object.getString("name");
                            String  birthday_=object.getString("birthday");
                            String  email_=object.getString("email");

                            name.setText(name_);
                            birthday.setText(birthday_);
                            email.setText(email_);


                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();

                            name.setText("FAIL");
                            birthday.setText("FAIL");
                            email.setText("FAIL");

                        }

                    }

                });
        Bundle parameters = new Bundle();
        parameters.putString("fields","id,name,link,email,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }






}
