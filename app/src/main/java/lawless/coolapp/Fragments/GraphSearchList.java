package lawless.coolapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lawless.coolapp.R;

/**
 * Created by Chris on 13/08/2016.
 */
public class GraphSearchList  extends Fragment {



    TextView results;


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.graph_search_list_layout, container, false);

        results = (TextView) view.findViewById(R.id.results_text);

        getMoviePreferences();


        return view;
    }




    public void getMoviePreferences(){

        GraphRequest request =   new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/movies",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                      //  System.out.println("request onCompleted");
                       // try{
                            JSONObject main = response.getJSONObject();
                           // JSONArray jArray = main.getJSONArray("data");

                            results.setText(main.toString());
//
//                            for(int i = 0; i < jArray.length(); i++){
//
//                                System.out.println("Likes:" +jArray.get(i));
//
//                            }
                       // } catch (JSONException exception){
                      //      exception.printStackTrace();
                      //  }
                    }
                });

        request.executeAsync();
        System.out.println("executed");

    }







}
