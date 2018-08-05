package com.tianlin.currencyconverter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class RateCheck extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> currencies = null;
    private ArrayAdapter<String> fromCurrency = null;
    private Spinner initialCur = null;
    private ArrayAdapter<String> toCurrency = null;
    private Spinner afterCur = null;
    public static String fromcashType;
    public static String tocashType=null;
    private String toCurr;
    ArrayList<String> urls;
    ArrayList<String> current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_check);
        initialCur = (Spinner) findViewById(R.id.initialCur);
        currencies = new ArrayList<String>();
        currencies.add("人民币");
        currencies.add("美元");
        currencies.add("港币");
        currencies.add("英镑");
        currencies.add("澳大利亚元");
        currencies.add("澳门元");
        currencies.add("日元");
        currencies.add("加拿大元");
        currencies.add("欧元");
        currencies.add("韩元");
        initialCur = (Spinner) findViewById(R.id.initialCur);
        fromCurrency = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,currencies);
        fromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        initialCur.setAdapter(fromCurrency);
        initialCur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fromcashType = fromCurrency.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        afterCur = (Spinner) findViewById(R.id.transferedCur);
        toCurrency = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,currencies);
        toCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        afterCur.setAdapter(toCurrency);
        afterCur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tocashType = toCurrency.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button finish = (Button) findViewById(R.id.check);
        finish.setOnClickListener(this);
    }
    /** 判断网络是否连接 */

    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String intentName = info.getTypeName();
            Log.i("通了没！", "当前网络名称：" + intentName);
            return true;
        } else {
            Log.i("通了没！", "没有可用网络");
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check:
                ArrayList<String> f = new ArrayList<String>();
                f.add(fromcashType);f.add("1");
                if (isConnectIsNomarl()) {
//                                   System.out.println(ids.funds);
                    CurrencySeeker thelist = new CurrencySeeker(f, tocashType);
                    urls = thelist.getUrlList();
                    current = thelist.getCurrencies();
                    toCurr=thelist.getToCur();
                    Intent intent = new Intent();
                    intent.setClass(RateCheck.this, ShowRate.class);
                    intent.putExtra("websites", urls);
                    intent.putExtra("currencies", current);
                    intent.putExtra("toCurrencyForNow", tocashType);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(RateCheck.this);
                    dialog.setTitle("未能连接到服务器");
                    dialog.setMessage("请检查网络连接，确保连通后再次运行");
                    dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                }
        }
    }

    @Override
    protected void onDestroy() {
        ids.funds.removeAll(ids.funds);
        super.onDestroy();
    }
}
