package com.example.group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class calcActivity extends AppCompatActivity {
    // declaring global variables
    EditText l_amount;
    EditText d_amount;
    Button calc;
    int buy_cal;
    int sell_cal;

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

    // Convert function
    public void conv(View view) {
        String ll = l_amount.getText().toString();
        String dd = d_amount.getText().toString();
        //closing the keyboard when the user press convert button
        closeKeyboard();
        // showing an error if the user did not fill any value
        if (ll.isEmpty() && dd.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a value in one of the boxes", Toast.LENGTH_LONG).show();
        } //showing an error if the user entered both values
        else if (!ll.isEmpty() && !dd.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill only ONE Box", Toast.LENGTH_LONG).show();
        } //converting from lira to dollar
        else if (!ll.isEmpty() && dd.isEmpty()){
            int lira = Integer.parseInt(ll);
            int dollar = lira/buy_cal;
            String dollar_s = String.valueOf(dollar);
            d_amount.setText(formatNum(dollar_s));
        } // converting from dollar to lira
        else {
            int dollar = Integer.parseInt(dd);
            int lira = dollar*sell_cal;
            String lira_s = String.valueOf(lira);
            l_amount.setText(formatNum(lira_s));
        }

    }

    //intent to go back to the rate page (home page)
    public void goback(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    // this function gives a format to the results
    private String formatNum(String n) {
        DecimalFormat form = new DecimalFormat("###,###,##0.0");
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