package com.example.mart.calcapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.BroadcastReceiver;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public String sign = "";
    public Double number1, number2;
    public boolean dot = false; // dot logic
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
           output.setText(output.getText());



        } else if  (isOperator(string1)){
          if(sign == "" && output.getText().toString() != "") {

                  number1 = Double.parseDouble(output.getText().toString());
                  output.setText("");
                  sign = string1;
                  dot = false; }
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


        } else if (string1.contains("=") && number1 != null && sign != null ){
            number2 = Double.parseDouble(output.getText().toString());
            calcBroad();


           // output.setText(engine.calcAns(number1, number2, sign));
           //   number1=null;
           // number2=null;

        }
    }

    public void calcBroad (){

        final TextView output = (TextView) findViewById(R.id.textView);
        Intent intent = new Intent();
        intent.setAction("com.example.mart.calcapp");
        intent.addFlags(intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("number1", number1);
        intent.putExtra("number2", number2);
        intent.putExtra("sign", sign);
        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s;
                s = getResultData();
                output.setText(s.toString());
            }
        }, null, Activity.RESULT_OK, null, null);
        number1 = null;
        number1 = null;

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
