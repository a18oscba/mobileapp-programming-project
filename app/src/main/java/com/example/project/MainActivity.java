package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button button;
    ArrayList<TravelNations> travelNations;
    ArrayAdapter<TravelNations> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        travelNations = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.list_item_textview, travelNations);

        ListView listView = findViewById(R.id.travelNations);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String message = "The town" +
                        " " + travelNations.get(i).getName() + " is located in " + travelNations.get(i).getLocation() +
                        " and the terrain consists mostly of " + travelNations.get(i).getCategory();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        new JsonTask().execute("https://mobprog.webug.se/json-api?login=a18oscba");


        button = (Button) findViewById(R.id.buttonAbout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToAbout();
            }
        });
    }


    public void GoToAbout(){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                travelNations.clear();
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String ID = jsonObject.getString("ID");
                    String name = jsonObject.getString("name");
                    String location = jsonObject.getString("location");
                    String category = jsonObject.getString("category");
                    TravelNations travelNations = new TravelNations(ID, name, location, category);
                    MainActivity.this.travelNations.add(travelNations);
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                Log.e("tag", "E:" + e.getMessage());
            }
        }
    }
}
