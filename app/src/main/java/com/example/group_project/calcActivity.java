package com.example.group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class calcActivity extends AppCompatActivity {

    EditText l_amount;
    EditText d_amount;
    Button calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcpage);
        l_amount = (EditText) findViewById(R.id.lira_amount);
        d_amount = (EditText) findViewById(R.id.dollar_amount);
        calc = (Button) findViewById(R.id.convert_btn);
    }

    public void conv(View view) {
        String ll = l_amount.getText().toString();
        String dd = d_amount.getText().toString();
        closeKeyboard();
        if(ll.isEmpty() && dd.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter a value in one of the boxes", Toast.LENGTH_LONG).show();
        }
    }

    private String formatNum(String n) {
        DecimalFormat form = new DecimalFormat("###,###,##0.0");
        return form.format(Double.parseDouble(n));
    }

    private void closeKeyboard() {
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}