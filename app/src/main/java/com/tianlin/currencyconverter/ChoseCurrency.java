package com.tianlin.currencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ChoseCurrency extends AppCompatActivity implements View.OnClickListener {
    public static String from;
    private Spinner fromCur = null;
    private ArrayList<String> currencies = null;
    private ArrayAdapter<String> fromCurrency = null;
    public static String cashType;
    private EditText input;
    private int button_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_currency);
        Intent intent = getIntent();
        String getCurrencyFornow = intent.getStringExtra("currencyfornow");
        String getMoneyAmount = intent.getStringExtra("amountfornow");
        button_id = intent.getIntExtra("button_id",-1);
        fromCur = (Spinner) findViewById(R.id.fromCur);
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
        ArrayList<String> curt = new ArrayList<String>();
        curt.add(getCurrencyFornow);
        for (String c:currencies){
            if (!curt.contains(c)) curt.add(c);
        }

        //选择转换方
        fromCurrency = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,curt);
        fromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCur.setAdapter(fromCurrency);
        input = (EditText) findViewById(R.id.cash_amount);
        Button finish = (Button) findViewById(R.id.okay);
        input.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        input.setKeyListener(DigitsKeyListener.getInstance(".0123456789"));
        finish.setOnClickListener(this);
        if (!getMoneyAmount.equals("0")){
            input.setText(getMoneyAmount);
        }
        fromCur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cashType = fromCurrency.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button clear = (Button) findViewById(R.id.remove);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.okay:
                String getMoney = input.getText().toString();
                if (getMoney.equals("0")||getMoney.equals("")) {
                    Toast.makeText(ChoseCurrency.this, "资金输入无效,未保存", Toast.LENGTH_SHORT);
                }
                Intent intent = new Intent();
                intent.putExtra("currency",cashType);
                intent.putExtra("amount",getMoney);
                intent.putExtra("button_id",button_id);
                setResult(RESULT_OK,intent);
                finish();
            case R.id.remove:
                Intent intentremove = new Intent();
                intentremove.putExtra("currency","人民币");
                intentremove.putExtra("amount","0");
                intentremove.putExtra("button_id",button_id);
                setResult(RESULT_CANCELED,intentremove);
                finish();

        }
    }
}
