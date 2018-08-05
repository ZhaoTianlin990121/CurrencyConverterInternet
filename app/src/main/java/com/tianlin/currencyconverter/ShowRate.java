package com.tianlin.currencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowRate extends AppCompatActivity {
    ArrayList<String> urls;
    ArrayList<String> currencySequence;
    String toCur;
    TextView responseText;
    static HashMap<String,Float> requiredRates=new HashMap<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rate);
        System.out.println(1);
        Intent intent = null;
        System.out.println(2);
        try{intent = getIntent();}catch (Exception e){e.printStackTrace();}
        System.out.println(3);
        progressBar = (ProgressBar) findViewById(R.id.progress_rate);
        urls = intent.getStringArrayListExtra("websites");
        System.out.println(urls);
        currencySequence = intent.getStringArrayListExtra("currencies");
        toCur = intent.getStringExtra("toCurrencyForNow");
        responseText = (TextView) findViewById(R.id.rate_info);
        System.out.println(currencySequence);
        sendRequestWithOkHttp();
//        System.out.println("执行完毕");
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
//                    System.out.println(1);
                    OkHttpClient client = new OkHttpClient();
                    for(int i = 0;i<=urls.size()-1;i++) {
//                        System.out.println(1.1);
                        Request request = new Request.Builder().url(urls.get(i)).build();
//                        System.out.println(1.2);
                        Response response = client.newCall(request).execute();
//                        System.out.println(1.3);
                        String responseData = response.body().string();
//                        System.out.println("Thread");
                        int dang = responseData.indexOf("当前汇率");
//                        System.out.println(1.4);
                        int start = responseData.indexOf(">", dang);
//                        System.out.println(1.5);
                        int end = responseData.indexOf("<", start);
//                        System.out.println(1.6);
//                        System.out.println(Float.parseFloat(responseData.substring(start + 1, end)));
                        float rate = Float.parseFloat(responseData.substring(start + 1, end));
//                        System.out.println(requiredRates);
                        requiredRates.put(currencySequence.get(i),rate);
                    }
//                    System.out.println(2);
                    showResponse("1 "+ currencySequence.get(0)+"\n兑换为\n"+requiredRates.get(currencySequence.get(0))+" "+toCur);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);responseText.setTextSize(45);
            }
        });

    }
}
