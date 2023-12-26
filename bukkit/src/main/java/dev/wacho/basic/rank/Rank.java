package dev.wacho.basic.rank;

import lombok.Getter;

@Getter
public class Rank {
    private String name;
    private int coinsCost;
    private int coinsNeeded;

    public Rank(String name, int coinsCost, int coinsNeeded) {
        this.name = name;
        this.coinsCost = coinsCost;
        this.coinsNeeded = coinsNeeded;
    }

    public int getCoinsCost() {
        return coinsCost;
    }

    public int getCoinsNeeded() {
        return coinsNeeded;
    }
}