package com.tarantsov.menuetto;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tarantsov.menuetto.model.CafeMenu;
import com.tarantsov.menuetto.model.CafeSection;
import com.tarantsov.menuetto.model.ICafeEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ListView listView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.listView);

        List<ICafeEntry> values = new ArrayList<>();
        CafeMenu menu = CafeMenu.instance(getContext());
        for (CafeSection section : menu.sections) {
            values.add(section);
            values.addAll(section.items);
        }

        ArrayAdapter<ICafeEntry> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
    }
}
