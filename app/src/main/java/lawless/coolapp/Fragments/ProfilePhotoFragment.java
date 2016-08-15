package lawless.coolapp.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import lawless.coolapp.AsynchronousTasks.GetImageTask;

import lawless.coolapp.R;

/**
 * Created by Chris on 14/08/2016.
 */
public class ProfilePhotoFragment extends Fragment {


    ImageView profile_photo;



    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_photo_fragment, container, false);

        profile_photo = (ImageView) view.findViewById(R.id.imageView);

        Drawable empty_profile = getResources().getDrawable(R.drawable.facebook_no_profile);
        profile_photo.setImageDrawable(empty_profile);

        getProfileData();

        return view;
    }


    public void setImage(Bitmap bitmap)
    {
        profile_photo.setImageBitmap(bitmap);
    }



    public void getProfileData()
    {
        Bundle params = new Bundle();
        params.putString("fields", "id,email,gender,cover,picture.type(large)");
        System.out.println("HEY : getProfileData()");

        GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                new GraphRequest.Callback() {


                    @Override
                    public void onCompleted(GraphResponse response) {
                        if (response != null) {
                            try {
                                JSONObject data = response.getJSONObject();
                                if (data.has("picture")) {
                                    String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");

                                    System.out.println("HEY : onCompleted(): url is :"+profilePicUrl );
                                    //Once we have url we pass off the image geting task
                                    GetImage getImage = new GetImage();
                                    getImage.execute(profilePicUrl);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        request.executeAsync();
    }



    public class GetImage extends GetImageTask {

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if(bitmap!=null) {
                setImage(bitmap);
            }
        }
    }





}
