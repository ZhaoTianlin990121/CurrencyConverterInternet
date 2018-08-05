package com.tianlin.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fund extends AppCompatActivity{
    TextView responseText;
    static HashMap<String,Float> requiredRates=new HashMap<>();
    ArrayList<String> urls;
    ArrayList<String> currencySequence;
    String toCur;
    float totalAmount = 0;
    static ArrayList<String> funds;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund);
        progressBar = (ProgressBar) findViewById(R.id.progress_fund);
        Intent intent = getIntent();
        urls = intent.getStringArrayListExtra("websites");
        currencySequence = intent.getStringArrayListExtra("currencies");
        toCur = intent.getStringExtra("toCurrencyForNow");
        funds = intent.getStringArrayListExtra("funds");
        responseText = (TextView) findViewById(R.id.response_text);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println(1);
                    OkHttpClient client = new OkHttpClient();
                    for(int i = 0;i<=urls.size()-1;i++) {
                        System.out.println(1.1);
                        Request request = new Request.Builder().url(urls.get(i)).build();
                        System.out.println(1.2);
                        Response response = client.newCall(request).execute();
                        System.out.println(1.3);
                        String responseData = response.body().string();
                        System.out.println("Thread");
                        int dang = responseData.indexOf("当前汇率");
                        System.out.println(1.4);
                        int start = responseData.indexOf(">", dang);
                        System.out.println(1.5);
                        int end = responseData.indexOf("<", start);
                        System.out.println(1.6);
                        System.out.println(Float.parseFloat(responseData.substring(start + 1, end)));
                        float rate = Float.parseFloat(responseData.substring(start + 1, end));
                        System.out.println(requiredRates);
                        requiredRates.put(currencySequence.get(i),rate);
                    }
                    System.out.println(2);
                    for (int i=0;i<=ids.funds.size()/2-1;i++){
                        System.out.println(ids.funds);
                        String thisCurrency = ids.funds.get(2*i);
                        float thisAmount = Float.parseFloat(ids.funds.get(2*i+1));
                        System.out.println(requiredRates); System.out.println(thisCurrency);
                        if (thisAmount!=0) {
                            float newAmount = thisAmount * requiredRates.get(thisCurrency);
                            System.out.println(newAmount);
                            totalAmount = totalAmount + newAmount;
                            System.out.println(totalAmount);
                        }
                    }
                    System.out.println(3);
                    showResponse("资金总计\n"+totalAmount+"\n"+toCur);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕");
        progressBar.setVisibility(View.GONE);
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
