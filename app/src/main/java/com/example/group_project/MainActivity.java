package com.example.group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView refresh_button;
    public TextView buy_r;
    public TextView sell_r;
    public String buyRate;
    public String sellRate;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        //method to read from the specified URL
        protected String doInBackground(String... urls){
            String result = "";
            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while( data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }


        protected void onPostExecute(String s){
            super.onPostExecute(s);
            // display rates on homepage
            try{
                String[] rates = s.split(" ");
                buy_r.setText(rates[0]);
                buyRate = rates[0];
                sell_r.setText(rates[1]);
                sellRate = rates[1];
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        //Getting the values from API1 On App creation
        buy_r = (TextView) findViewById(R.id.buy_rate);
        sell_r = (TextView) findViewById(R.id.sell_rate);
        String url = "http://10.0.2.2/apiReq.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);

    }

    public void nextP(View view) {
        // Use the intent to move from one page to another, here from home page to calculator page
        Intent in = new Intent(MainActivity.this, calcActivity.class);
        String buy_calc = buyRate;
        String sell_calc = sellRate;
        in.putExtra("buy_rate",buy_calc);
        in.putExtra("sell_rate",sell_calc);
        startActivity(in);
    }
}