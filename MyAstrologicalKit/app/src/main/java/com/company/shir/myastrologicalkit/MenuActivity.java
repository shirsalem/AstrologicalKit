package com.company.shir.myastrologicalkit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity implements ActivityTransfarable, YesNoFragment.YesNoFragmentListener {

    private static final String TAG = "MenuActivity";
    private SectionsPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        Log.d(TAG, "OnCreate: Starting");
        mSectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        //set up the viewPager with the section adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        setUpViewPager(mViewPager);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        NameGenerator nameGenerator = new NameGenerator();
        NameList nameList = new NameList();
        nameList.setTransfarable(this);
        nameGenerator.setInterfaces(nameList, this);
        adapter.addFragment(nameGenerator, "My Names Calculator");
        adapter.addFragment(nameList, "My Favorite Names List");
        adapter.addFragment(new Signs(), "My Astrological Sign");
        viewPager.setAdapter(adapter);
    }

    @Override
    public List<String> getNames() {
        String allNames = prefs.getString("savedName", "");
        List<String> list = new ArrayList<>();
        String[] strings = allNames.split("&");
        for (int i = 1; i < strings.length; i++) {
            list.add(strings[i]);
        }
        return list;
    }

    @Override
    public void updatePrefs(String name) {
        String allNames = prefs.getString("savedName", "");
        prefs.edit().putString("savedName", allNames + name).apply();
    }

//  setting the back button (for dialog fragment)
    public void onBackPressed() {
        YesNoFragment yesNoFragment = new YesNoFragment();
        yesNoFragment.setQuestion("Do you want to exit?");
        yesNoFragment.setYesNoFragmentListener(this);
        yesNoFragment.show(getFragmentManager(), "");
    }


    @Override
    public void onChoose(boolean isYes) {
        if (isYes == true) {
            finish();
        } else {
            return;
        }
    }
}
