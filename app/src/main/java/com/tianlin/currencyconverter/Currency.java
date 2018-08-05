package com.tianlin.currencyconverter;

import java.util.ArrayList;

public class Currency {
    private String name;
    private ArrayList<String> toCurrencies = new ArrayList<String>();
    private ArrayList<Integer> rates;

    public Currency(){
        toCurrencies.add("人民币");
        toCurrencies.add("美元");
        toCurrencies.add("港币");
        toCurrencies.add("英镑");
        toCurrencies.add("澳大利亚元");
        toCurrencies.add("澳门元");
        toCurrencies.add("日元");
        toCurrencies.add("加拿大元");
        toCurrencies.add("欧元");
        toCurrencies.add("韩元");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRates(ArrayList<Integer> rates) {
        this.rates = rates;
    }

    public ArrayList<Integer> getRates() {
        return rates;
    }

    public ArrayList<String> getToCurrencies() {
        return toCurrencies;
    }
}
