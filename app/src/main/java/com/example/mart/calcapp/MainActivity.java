package com.example.mart.calcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public String sign = "";
    public Double number1, number2;
    public boolean dot = false;
    public String string1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreate called");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}


    public void onClicked (View v){
        TextView output = (TextView) findViewById(R.id.textView);
        Button button = (Button) v;
        string1 = (String) button.getText().toString();

        if (isNumber(string1)){
            output.append(string1);

        } else if  (isOperator(string1)){
          if(sign == "") {
              number1 = Double.parseDouble(output.getText().toString());
              output.setText("");
              sign = string1;
              dot = false;}
        } else if (string1.contains(".")){
            if (dot == false){
                output.append(dotLogic(string1, output.getText().toString()));
                dot=true;}
        } else if (string1.contains("C")){
            output.setText("");
            sign = "";
            dot = false;
        } else if (string1.contains("Del")){
            if(output.getText().toString().length()>0){
                output.setText(output.getText().toString().substring(0, output.getText().length() - 1));}


        } else if (string1.contains("=")){
            number2 = Double.parseDouble(output.getText().toString());
            if (sign.contains("+"))
            {
                output.setText(Double.toString(number1 + number2));
            }
            else if (sign.contains("-"))
            {
                output.setText(Double.toString(number1 - number2));

            }
            else if (sign.contains("*"))
            {
                output.setText(Double.toString(number1 * number2));

            }
            else if (sign.contains("/"))
            {
                if (number2 == 0)
                {
                    // Cannot Divide By Zero
                    output.setText("Cannot Divide By Zero!");
                }
                else
                {
                    output.setText(Double.toString(number1 / number2));

                }

            }
            sign = "";
        }


    }
public String dotLogic (String a, String c){
    String b = "";
    if(c.length()== 0){
        return b = "0.";
    }
    return b = ".";
}

    public boolean isOperator(String a){
        return a != null && a.matches("[/, *, +, -]");
    }

    public boolean isNumber(String a){
        return a != null && a.matches("[0-9]+");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onStart called"); }
        // The activity is about to become visible.
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onResume called"); }
        // The activity has become visible (it is now "resumed").
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onPause called"); }
        // Another activity is taking focus (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onStop called"); }
        // The activity is no longer visible (it is now "stopped")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onDestroy called"); }
        // The activity is about to be destroyed.
    }
}