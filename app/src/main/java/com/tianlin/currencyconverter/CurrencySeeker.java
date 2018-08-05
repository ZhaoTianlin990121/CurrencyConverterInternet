package com.tianlin.currencyconverter;

import java.util.ArrayList;

public class CurrencySeeker {
    private ArrayList<String> urlList = new ArrayList<String>();
    private ArrayList<String> currencies = new ArrayList<String>();
    private String toCur;
    private String CNY = "人民币";
    private String USD = "美元";
    private String GBP = "英镑";
    private String AUD = "澳大利亚元";
    private String HKD = "港币";
    private String MOP = "澳门元";
    private String JPY = "日元";
    private String CAD = "加拿大元";
    private String EUR = "欧元";
    private String KRW = "韩元";
    public CurrencySeeker(ArrayList<String> from,String to){
        toCur = to;
        for (int i = 0; i<=from.size()/2-1; i++){//System.out.print(from.get(2*i+1));System.out.println(!from.get(2*i+1).equals("0"));
            if (!from.get(2*i+1).equals("0")&&!currencies.contains(from.get(2*i))){
                currencies.add(from.get(2*i));
            }
        }
        for (String s:currencies){
            String a = "http://hl.anseo.cn/cal_";
            if (s.equals(CNY)){
                a = a+"CNY_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(USD)){
                a = a+"USD_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(GBP)){
                a = a+"GBP_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(AUD)){
                a = a+"AUD_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(HKD)){
                a = a+"HKD_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(MOP)){
                a = a+"MOP_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(JPY)){
                a = a+"JPY_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(CAD)){
                a = a+"CAD_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(EUR)){
                a = a+"EUR_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            if (s.equals(KRW)){
                a = a+"KRW_To_";
                if (to.equals(CNY)) a=a+"CNY";if (to.equals(USD)) a=a+"USD";if (to.equals(GBP)) a=a+"GBP";if (to.equals(AUD)) a=a+"AUD";if (to.equals(HKD))a=a+"HKD";if (to.equals(MOP))a=a+"MOP";if (to.equals(JPY))a=a+"JPY";if (to.equals(CAD)) a=a+"CAD";if (to.equals(EUR)) a=a+"EUR";if (to.equals(KRW))a=a+"KRW";
            }
            a = a+".aspx";
            urlList.add(a);
        }
    }

    public ArrayList<String> getUrlList(){return urlList;}

    public ArrayList<String> getCurrencies() {return currencies;}

    public String getToCur() {return toCur;}
}
