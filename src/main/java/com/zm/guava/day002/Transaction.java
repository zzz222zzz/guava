package com.zm.guava.day002;

public class Transaction {
    private Trader trader;
    private Integer year;
    private Integer value;

    public Transaction(Trader trader, Integer year, Integer value) {
        this.trader = trader;
        this.value = value;
        this.year = year;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
