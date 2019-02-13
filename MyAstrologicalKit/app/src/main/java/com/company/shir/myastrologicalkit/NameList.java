package com.company.shir.myastrologicalkit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class NameList extends Fragment implements Notifyable {
    private static final String TAG = "NameList";
    private ListView listNames;
    private ActivityTransfarable activityTransfarable;
    private List<String> list;
    private ArrayAdapter<String> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.namelist_fragment, container, false);

        listNames = view.findViewById(R.id.listNames);
        listNames.setChoiceMode(ListView.CHOICE_MODE_NONE);

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        list = activityTransfarable.getNames();


        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_list_edit, list);
        listNames.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();


        return view;
    }

    public void setTransfarable(ActivityTransfarable activityTransfarable) {
        this.activityTransfarable = activityTransfarable;
    }

    @Override
    public void doNotified(String string) {
        list.add(string);
        arrayAdapter.notifyDataSetChanged();
    }

}