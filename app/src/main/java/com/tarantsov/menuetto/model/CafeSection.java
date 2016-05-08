package com.tarantsov.menuetto.model;

import java.util.List;

public class CafeSection implements ICafeEntry {
    public final String title;
    public final List<CafeItem> items;

    public CafeSection(String title, List<CafeItem> items) {
        this.title = title;
        this.items = items;
    }

    @Override
    public String toString() {
        return title;
    }
}
