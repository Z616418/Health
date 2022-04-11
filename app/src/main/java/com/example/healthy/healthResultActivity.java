package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class healthResultActivity extends AppCompatActivity{
    TextView temperature_text;
    TextView heartrate_text;
    TextView bloodoxygen_text;
    TextView bloodpressure_text;
    TextView reminding_text;
    TextView suggestion_text;

    String temperature;
    String heartrate;
    String bloodoxygen;
    String bloodpressure;
    String reminding;
    String suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_result);
        sendRequestWithOkHttp();
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http://192.168.0.106/healthy.json").build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseJSONWithJSONObject(responseData);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithJSONObject(String jsonData){
        temperature_text=(TextView)findViewById(R.id.degree_data);
        heartrate_text=(TextView)findViewById(R.id.heartrate_data);
        bloodoxygen_text=(TextView)findViewById(R.id.xueyang_data);
        bloodpressure_text=(TextView)findViewById(R.id.xueya_data);
        reminding_text=(TextView)findViewById(R.id.health_notice);
        suggestion_text=(TextView)findViewById(R.id.comfort_text);
        try{
            JSONArray jsonArray=new JSONArray(jsonData);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                temperature=jsonObject.getString("temperature");
                heartrate=jsonObject.getString("heartrate");
                bloodoxygen=jsonObject.getString("bloodoxygen");
                bloodpressure=jsonObject.getString("bloodpressure");
                reminding=jsonObject.getString("reminding");
                suggestion=jsonObject.getString("suggestion");
                temperature_text.setText(temperature);
                heartrate_text.setText(heartrate);
                bloodoxygen_text.setText(bloodoxygen);
                bloodpressure_text.setText(bloodpressure);
                reminding_text.setText(reminding);
                suggestion_text.setText(suggestion);
            }
        }catch (Exception e){e.printStackTrace();}
    }
}