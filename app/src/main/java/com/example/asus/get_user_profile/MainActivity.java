package com.example.asus.get_user_profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvUser;
    ListAdapter adapter=null;
    ArrayList<User> arrUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvUser = (ListView) findViewById(R.id.listViewUser);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://api.github.com/search/users?q=t&per_page%3A100");
            }
        });

    }
    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = docNoiDung_Tu_URL(params[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            try {
                JSONObject root = new JSONObject(s);
                JSONArray arr = root.getJSONArray("items");
                ArrayList<User> arrUser = new ArrayList<User>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject son = arr.getJSONObject(i);
                    String name= son.getString("login");
                    String avatar= son.getString("avatar_url");
                    arrUser.add(new User(name,avatar));
                }
                //Toast.makeText(MainActivity.this, arrUser.toString(), Toast.LENGTH_LONG).show();

                adapter = new ListAdapter(MainActivity.this, R.layout.activity_list_item_user, arrUser);
                lvUser.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }








    //////////////////////////////



    //doc du lieu tu web
    private static String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }


}
