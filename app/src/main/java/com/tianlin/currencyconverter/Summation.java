package com.tianlin.currencyconverter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Summation extends AppCompatActivity {
    static ArrayList<String> funds = ids.funds;
    static String toCur = "人民币";
    ArrayList<String> urls;
    ArrayList<String> current;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summation);
        Button addFund = (Button) findViewById(R.id.add_fund);
        addFund.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int width = dm.widthPixels;
                int height = dm.heightPixels;
                RelativeLayout layout = new RelativeLayout(Summation.this);
                if (ids.funds.isEmpty()) {
                    for (int i = 0; i < 16;i++) {
                        ids.funds.add("人民币");
                        ids.funds.add("0");
                    }
                }
                Button Btn[] = new Button[18];
                int j = -1;
                for  (int i=0; i<=17; i++) {
                    Btn[i]=new Button(Summation.this);
                    if (i!=16&&i!=17) {
                        Btn[i].setId(2000+i);
                        Btn[i].setText("点击添加资金");
                        RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams ((width-50)/4,250);
                        if (i%4 == 0) {
                            j++;
                        }
                        btParams.leftMargin = 10+ ((width-50)/4+10)*(i%4);   //横坐标定位
                        btParams.topMargin = 20 + 250*j;   //纵坐标定位
                        layout.addView(Btn[i],btParams);
                    } else if (i==16){
                        Btn[i].setId(3000);
                        Btn[i].setText("求和");
                        RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams (width-25,250);
                        btParams.leftMargin = 10;   //横坐标定位
                        btParams.topMargin = 20 + 250*5;   //纵坐标定位
                        layout.addView(Btn[i],btParams);
                    } else {
                        Btn[i].setId(4000);
                        Btn[i].setText("转换为");
                        RelativeLayout.LayoutParams btParams = new RelativeLayout.LayoutParams (width-25,250);
                        btParams.leftMargin = 10;   //横坐标定位
                        btParams.topMargin = 20 + 250*4;   //纵坐标定位
                        layout.addView(Btn[i],btParams);
                    }

                }
                Summation.this.setContentView(layout);
                for (int k = 0; k <= Btn.length-1; k++) {
                    //这里不需要findId，因为创建的时候已经确定哪个按钮对应哪个Id
                    Btn[k].setTag(k);
                    if (k<Btn.length-2){
                        //为按钮设置一个标记，来确认是按下了哪一个按钮
                        Btn[k].setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int i = (Integer) v.getTag();
                                Intent intent = new Intent();
                                intent.setClass(Summation.this, ChoseCurrency.class);
                                intent.putExtra("currencyfornow", ids.funds.get(2*i));
                                intent.putExtra("amountfornow", ids.funds.get(2*i+1));
                                intent.putExtra("button_id", i);
                                startActivityForResult(intent, i);
                            }
                        });
                    }
                    if (k==Btn.length-2){
                        Btn[k].setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isConnectIsNomarl()) {
//                                   System.out.println(ids.funds);
                                    CurrencySeeker thelist = new CurrencySeeker(ids.funds, toCur);
                                    urls = thelist.getUrlList();
                                    current = thelist.getCurrencies();
//                                 System.out.println(urls);
                                    int i = (Integer) v.getTag();
                                    Intent intent = new Intent();
                                    intent.setClass(Summation.this, Fund.class);
                                    intent.putExtra("websites", urls);
                                    intent.putExtra("currencies", current);
                                    intent.putExtra("funds", ids.funds);
                                    intent.putExtra("toCurrencyForNow", toCur);
                                    startActivity(intent);
                                } else {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Summation.this);
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
                        });
                    }
                    if (k==Btn.length-1){
                        Btn[k].setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int i = (Integer) v.getTag();
                                Intent intent = new Intent();
                                intent.setClass(Summation.this, ChoseToCurrency.class);
                                intent.putExtra("toCurrencyForNow", toCur);
                                intent.putExtra("button_id", i);
                                startActivityForResult(intent, i);
                            }
                        });
                    }
                }
                }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

            int getButtonId = data.getIntExtra("button_id",-1);
            if (getButtonId<16) {
                Button changetext = (Button) findViewById(getButtonId + 2000);
                String getCurrencyName = data.getStringExtra("currency");
                String getMoneyAmount = data.getStringExtra("amount");
                ids.funds.remove(2 * getButtonId + 1);
                ids.funds.remove(2 * getButtonId);
                ids.funds.add(2 * getButtonId, getCurrencyName);
                ids.funds.add(2 * getButtonId + 1, getMoneyAmount);
                String toput = "点击添加资金";
                if (resultCode == RESULT_OK){
                    toput = getCurrencyName + " " + getMoneyAmount;
                }
                if (getMoneyAmount.equals("0")||getMoneyAmount.equals("")){
                    Toast.makeText(Summation.this,"资金输入无效,未保存",Toast.LENGTH_LONG);
                    toput = "点击添加资金";
                }
                changetext.setText(toput);
            } else if (getButtonId==17){
                Button changetext = (Button) findViewById(getButtonId-getButtonId+4000);
                toCur = data.getStringExtra("toCurrency");
                changetext.setText("转换为 "+toCur);
            }

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
    protected void onDestroy() {
        ids.funds.removeAll(ids.funds);
        super.onDestroy();
    }
}
