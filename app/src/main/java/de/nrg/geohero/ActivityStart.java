package de.nrg.geohero;

import android.*;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static de.nrg.geohero.ActivityMain.soundIDs;
import static de.nrg.geohero.MyApplication.playSound;

public class ActivityStart extends Activity
{

    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1002;
    private static Context ctx;

    public static class HighscoreTask extends AsyncTask<Void, Void, Void>
    {
        private ProgressDialog asyncDialog = new ProgressDialog(ctx);
        private String name, device, imei;
        private int country, gender;
        private JSONObject json_highscores;

        public HighscoreTask(String name, String device, String imei, int country, int gender)
        {
            this.name = name;
            this.device = device;
            this.imei = imei;
            this.country = country;
            this.gender = gender;
        }

        @Override
        protected void onPreExecute()
        {
            asyncDialog.setCancelable(false);
            asyncDialog.setMessage("Verbindung zum Server aufbauen ...");
            asyncDialog.setTitle("Upload");
            asyncDialog.setIcon(android.R.drawable.ic_dialog_info);
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... paramsX)
        {
            json_highscores = null;
            URL url;
            try {
                url = new URL("http://192.168.2.213:12345/db_insert.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setReadTimeout(10000);
                con.setConnectTimeout(15000);
                con.setRequestMethod("POST");
                con.setDoInput(true);
                con.setDoOutput(true);

                ArrayList<Pair<String, String>> params = new ArrayList<Pair<String,String>>(1);
                params.add(new Pair<String, String>("name",name));
                params.add(new Pair<String, String>("device",device));
                params.add(new Pair<String, String>("imei",imei));
                params.add(new Pair<String, String>("country",String.valueOf(country)));
                params.add(new Pair<String, String>("gender",String.valueOf(gender)));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));

                writer.write(getQuery(params));
                writer.flush();
                writer.close();

                int responseCode = con.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK)
                {
                    String response = "";
                    String line;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    while ((line = reader.readLine()) != null)
                        response += line;

                    try {
                        json_highscores = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            if (asyncDialog != null && asyncDialog.isShowing())
                asyncDialog.dismiss();
            super.onPostExecute(result);

            try {
                if (json_highscores != null)
                {
                    String username = "";
                    JSONArray array = json_highscores.getJSONArray("highscores");
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject highscore = array.getJSONObject(i);
                        JSONObject hObject = highscore.getJSONObject("highscore");
                        username = hObject.getString("name");
                    }
                    Toast.makeText(ctx, username, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getQuery(ArrayList<Pair<String,String>> params)
    {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Pair<String,String> pair : params)
        {
            if (first)
                first = false;
            else
                sb.append("&");

            try {
                sb.append(URLEncoder.encode(pair.first, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(pair.second, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_start);
        ctx = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED)
            {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        android.Manifest.permission.READ_PHONE_STATE))
                {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(new String[]{android.Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant
            }
        }

        if (MyApplication.isTablet(this))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String name = "Rjafo";
        String device = Build.MANUFACTURER + ", " + Build.MODEL;
        TelephonyManager man = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = man.getDeviceId();
        int country = 25;
        int gender = 1;
        new HighscoreTask(name, device, imei, country, gender).execute((Void) null);

        Button bEurope = (Button) this.findViewById(R.id.bEurope);
        bEurope.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                playSound(soundIDs.get(0), 0.67f);
                MyApplication.currentGame = 1;
                startActivity(new Intent(ActivityStart.this, ActivityMain.class));
            }
        });

        Button bAfrica = (Button) this.findViewById(R.id.bAfrica);
        bAfrica.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                playSound(soundIDs.get(0), 0.67f);
                MyApplication.currentGame = 2;
                startActivity(new Intent(ActivityStart.this, ActivityMain.class));
            }
        });
    }
}
