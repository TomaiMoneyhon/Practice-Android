package com.example.hackeru.customadapterexample;

/**
 * Created by hackeru on 30/07/2015.
 */
public class TaxPayer {

    private String name;
    private double cash;
    private double realEstate;
    private double portfolio;
    private boolean isTaxImmune;

    public TaxPayer(String name, double cash, double realEstate, double portfolio, boolean isTaxImmune) {
        this.name = name;
        this.cash = cash;
        this.realEstate = realEstate;
        this.portfolio = portfolio;
        this.isTaxImmune = isTaxImmune;
    }

    public String getName() {
        return name;
    }

    public double getCash() {
        return cash;
    }

    public double getRealEstate() {
        return realEstate;
    }

    public double getPortfolio() {
        return portfolio;
    }

    public boolean isTaxImmune() {
        return isTaxImmune;
    }

    public void deductTax(double taxRatio) {
        cash*=(1-taxRatio);
        realEstate*=(1-taxRatio);
        portfolio*=(1-taxRatio);
    }
}
