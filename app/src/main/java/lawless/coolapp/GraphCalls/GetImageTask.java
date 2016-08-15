package lawless.coolapp.GraphCalls;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Chris on 14/08/2016.
 */

public class GetImageTask extends AsyncTask<String, Integer, Bitmap> {



    @Override
    protected void onPreExecute() {
        // Runs on UI thread- Any code you wants
        // to execute before web service call. Put it here.
        // Eg show progress dialog
    }


    @Override
    protected Bitmap doInBackground(String... params) {
        // Runs in background thread
        Bitmap result;
        URL url = null;
        try {
            url = new URL(params[0]);

        }catch (MalformedURLException error)
        {
            System.out.println(error);
        }


        try {
            result = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        }catch (Exception e)
        {
            return null;
        }

        return result;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }





}
