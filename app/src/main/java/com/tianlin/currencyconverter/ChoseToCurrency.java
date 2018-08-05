package com.tianlin.currencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class ChoseToCurrency extends AppCompatActivity implements View.OnClickListener {
    private Spinner toCur = null;
    private ArrayList<String> currencies = null;
    private int button_id;
    private ArrayAdapter<String> toCurrency = null;
    public static String cashType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_to_currency);
        Intent intent = getIntent();
        ArrayList<String> curt = new ArrayList<String>();
        String getCurrencyFornow = intent.getStringExtra("toCurrencyForNow");
        curt.add(getCurrencyFornow);
        button_id = intent.getIntExtra("button_id",-1);
        toCur = (Spinner) findViewById(R.id.toCur);
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
        for (String c:currencies){
            if (!curt.contains(c)) curt.add(c);
        }
        toCurrency = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,curt);
        toCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toCur.setAdapter(toCurrency);
        toCur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cashType = toCurrency.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button finish = (Button) findViewById(R.id.convert);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.convert:
                Intent intent = new Intent();
                intent.putExtra("toCurrency",cashType);
                intent.putExtra("button_id",button_id);
                setResult(RESULT_OK,intent);
                finish();
        }
    }
}
