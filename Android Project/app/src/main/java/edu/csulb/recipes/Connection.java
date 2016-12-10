package edu.csulb.recipes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Connection extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {

        String stars = (String) objects[0];
        String feedback = (String) objects[1];

        try {

            String link = "http://aniketvpatil.com/db.php?stars=" + (stars) + "& feedback=" + feedback;
            String data = URLEncoder.encode("stars", "UTF-8") + "=" + URLEncoder.encode((stars), "UTF-8");
            data += "&" + URLEncoder.encode("feedback", "UTF-8") + "=" + URLEncoder.encode(feedback, "UTF-8");
                        URL url = new URL(link);
            URLConnection conn =  url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
