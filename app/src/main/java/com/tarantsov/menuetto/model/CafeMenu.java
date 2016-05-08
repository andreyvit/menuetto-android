package com.tarantsov.menuetto.model;

import android.content.Context;
import android.util.JsonReader;

import com.tarantsov.menuetto.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CafeMenu {

    private static CafeMenu instance;

    public static CafeMenu instance(Context context) {
        if (instance == null) {
            InputStream stream = context.getResources().openRawResource(R.raw.example_menu);
            try {
                instance = readCafeMenuJsonStream(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public final List<CafeSection> sections;

    public CafeMenu(List<CafeSection> sections) {
        this.sections = sections;
    }

    public static CafeMenu readCafeMenuJsonStream(InputStream in) throws IOException {
        List<CafeSection> sections = new ArrayList<>();
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("sections")) {
                    sections = readSections(reader);
                    break;
                }
            }
            reader.endObject();
        } finally {
            reader.close();
        }
        return new CafeMenu(sections);
    }

    private static List<CafeSection> readSections(JsonReader reader) throws IOException {
        List<CafeSection> sections = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            sections.add(readSection(reader));
        }
        reader.endArray();
        return sections;
    }

    private static CafeSection readSection(JsonReader reader) throws IOException {
        String title = null;
        List<CafeItem> items = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "title":
                    title = reader.nextString();
                    break;
                case "items":
                    items = readItemsArray(reader);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new CafeSection(title, items);
    }

    private static List<CafeItem> readItemsArray(JsonReader reader) throws IOException {
        List<CafeItem> items = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            items.add(readItem(reader));
        }
        reader.endArray();
        return items;
    }

    private static CafeItem readItem(JsonReader reader) throws IOException {
        String title = null;
        String price = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "title":
                    title = reader.nextString();
                    break;
                case "price":
                    price = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new CafeItem(title, price);
    }
}
