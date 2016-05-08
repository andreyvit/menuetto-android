package com.tarantsov.menuetto.model;

public class CafeItem implements ICafeEntry {
    private final String title;
    private final String price;

    public CafeItem(String title, String price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return title + "    " + price;
    }
}
