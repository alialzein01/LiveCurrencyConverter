package com.example.group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class calcActivity extends AppCompatActivity {
    // declaring global variables
    EditText l_amount;
    EditText d_amount;
    Button calc;
    int buy_cal;
    int sell_cal;
    String api_rate = null;
    String to_display = null;
    EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcpage);
        // linking the variables to the xml by id
        l_amount = (EditText) findViewById(R.id.lira_amount);
        d_amount = (EditText) findViewById(R.id.dollar_amount);
        calc = (Button) findViewById(R.id.convert_btn);
        // catch values from previous page
        Intent x = getIntent();
        buy_cal = Integer.parseInt(x.getStringExtra("buy_rate"));
        sell_cal = Integer.parseInt(x.getStringExtra("sell_rate"));
    }

    public class StoreRate extends AsyncTask<String, Void, String> {

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

            //setting the rates from API2
            try{
                String[] rates = s.split(" ");
                d_amount.setText(rates[0]);
                l_amount.setText(rates[1]);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    // Convert function
    public void conv(View view) {

        String ll = l_amount.getText().toString();
        String dd = d_amount.getText().toString();
        double buy_rate = buy_cal;
        double sell_rate = sell_cal;
        double first_rate = 0;
        double second_rate = 0;
        double amount = 0;

        double x = Double.parseDouble(editText.getText().toString());
        double rate = Double.parseDouble(api_rate);
        String url = null;

        // showing an error if the user did not fill any value
        if (ll.isEmpty() && dd.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a value in one of the boxes", Toast.LENGTH_LONG).show();
        } //showing an error if the user entered both values
        else if (!ll.isEmpty() && !dd.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill only ONE Box", Toast.LENGTH_LONG).show();
        } else{
            if(ll.isEmpty()){
                 url = "http://10.0.2.2/api2/?buy_rate="+buy_rate+"&sell_rate="+sell_rate+"&first_rate="+first_rate+"&second_rate="+second_rate+"&amount="+amount;
            }
            else if (dd.isEmpty()){
                url = "http://10.0.2.2/api2/?buy_rate="+buy_rate+"&sell_rate="+sell_rate+"&first_rate="+first_rate+"&second_rate="+second_rate+"&amount="+amount;;
            }
        }
        StoreRate storeRate = new StoreRate();
        storeRate.execute(url);
    }

    //intent to go back to the rate page (home page)
    public void goback(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    // this function gives a format to the results
    private String formatNum(String n) {
        DecimalFormat form = new DecimalFormat("###,###,##0.##");
        return form.format(Double.parseDouble(n));
    }

    // the function used to close the keyboard
    private void closeKeyboard() {
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
