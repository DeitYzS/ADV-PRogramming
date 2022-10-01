package se233.chapter2.model;

import se233.chapter2.model.CurrencyEntity;

import java.util.ArrayList;

public class Currency {
    private String shortCode;
    private CurrencyEntity current;
    private ArrayList<CurrencyEntity> historical = new ArrayList<>();
    private Boolean isWatch;
    private Double watchRate;

    public Currency(String shortCode){
        this.shortCode = shortCode;
        this.isWatch = false;
        this.watchRate = 0.0;
    }

    public ArrayList<CurrencyEntity> getHistorucal() { return this.historical;}
    public void setHistorical(ArrayList<CurrencyEntity> hist) {this.historical = hist;}
    public String getShortCode(){ return shortCode;}
    public void setCurrent(CurrencyEntity cur) { current = cur;}
    public CurrencyEntity getCurrent() {return current;}
    public Boolean getWatch(){return isWatch;}
    public void setWatch(boolean watch) {isWatch = watch;}
    public Double getWatchRate(){return watchRate;}
    public void setWatchRate(Double watchR){ watchRate = watchR;}


}
