package com.company.shir.myastrologicalkit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;

import javax.net.ssl.HttpsURLConnection;


public class Signs extends Fragment {
    private static final String TAG = "Signs";
    public static final String BASIC_URL = "https://www.astrology-zodiac-signs.com/horoscope/";
    private TextView txtDisplaySign;
    private Button btnAquarius;
    private Button btnPisces;
    private Button btnAries;
    private Button btnTaurus;
    private Button btnGemini;
    private Button btnCancer;
    private Button btnLeo;
    private Button btnVirgo;
    private Button btnLibra;
    private Button btnScorpio;
    private Button btnSagittarius;
    private Button btnCapricorn;
    private ProgressBar prgBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.signs_fragment, container, false);

        txtDisplaySign = view.findViewById(R.id.txtDisplaySign);
        btnAquarius = view.findViewById(R.id.btnAquarius);
        btnPisces = view.findViewById(R.id.btnPisces);
        btnAries = view.findViewById(R.id.btnAries);
        btnTaurus = view.findViewById(R.id.btnTaurus);
        btnGemini = view.findViewById(R.id.btnGemini);
        btnCancer = view.findViewById(R.id.btnCancer);
        btnLeo = view.findViewById(R.id.btnLeo);
        btnVirgo = view.findViewById(R.id.btnVirgo);
        btnLibra = view.findViewById(R.id.btnLibra);
        btnScorpio = view.findViewById(R.id.btnScorpio);
        btnSagittarius = view.findViewById(R.id.btnSagittarius);
        btnCapricorn = view.findViewById(R.id.btnCapricorn);
        prgBar = view.findViewById(R.id.prgBar);


        final String urlDailyAquarius = "aquarius/daily/";
        final String urlDailyPisces = "pisces/daily/";
        final String urlDailyAries = "aries/daily/";
        final String urlDailyTaurus = "taurus/daily/";
        final String urlDailyGemini = "gemini/daily/";
        final String urlDailyCancer = "cancer/daily/";
        final String urlDailyLeo = "leo/daily/";
        final String urlDailyVirgo = "virgo/daily/";
        final String urlDailyLibra = "libra/daily/";
        final String urlDailyScorpio = "scorpio/daily/";
        final String urlDailySagittarius = "sagittarius/daily/";
        final String urlDailyCapricorn = "capricorn/daily/";

        btnAquarius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyAquarius);
                showPrgBar();
            }
        });

        btnPisces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyPisces);
                showPrgBar();
            }
        });


        btnAries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyAries);
                showPrgBar();
            }
        });

        btnTaurus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyTaurus);
                showPrgBar();
            }
        });

        btnGemini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyGemini);
                showPrgBar();
            }
        });

        btnCancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyCancer);
                showPrgBar();
            }
        });

        btnLeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyLeo);
                showPrgBar();
            }
        });

        btnVirgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyVirgo);
                showPrgBar();
            }
        });

        btnLibra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyLibra);
                showPrgBar();
            }
        });

        btnScorpio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyScorpio);
                showPrgBar();
            }
        });

        btnSagittarius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailySagittarius);
                showPrgBar();
            }
        });

        btnCapricorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUrl(BASIC_URL + urlDailyCapricorn);
                showPrgBar();
            }
        });

        return view;

    }

    public void showPrgBar(){
        txtDisplaySign.setVisibility(View.INVISIBLE);
        prgBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100){
                    progressStatus++;
                    android.os.SystemClock.sleep(10);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            prgBar.setProgress(progressStatus);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtDisplaySign.setVisibility(View.VISIBLE);
                        prgBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }

    //check if INTERNET is connected
    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

//  API request
    private void sendUrl(final String linkUrl) {
        //if INTERNET connected do:
        if (isNetworkConnected()) {
            new AsyncTask<Void, Void, String>() {
                String partsDir[];
                String partsDir2[];

                @Override
                protected String doInBackground(Void... voids) {
                    URL url;
                    HttpsURLConnection connection = null;
                    InputStream inputStream = null;
                    try {
                        url = new URL(linkUrl);
                        connection = (HttpsURLConnection) url.openConnection();
                        connection.setUseCaches(false);
                        connection.setDoOutput(false);
                        connection.setRequestMethod("GET");
                        connection.connect();
                        StringBuilder stringBuilder = new StringBuilder();
                        inputStream = connection.getInputStream();
                        byte[] bytes = new byte[1024];
                        int actuallyRead;
                        while ((actuallyRead = inputStream.read(bytes)) != -1) {
                            stringBuilder.append(new String(bytes, 0, actuallyRead));
                        }
                        String temp = stringBuilder.toString();
                        String[] parts = temp.split("Date\">");
                        partsDir = parts[1].split("p>");
                        String unnecessaryOff = "</";
                        String unnecessaryOff2 = "<p>";
                        partsDir[1] = partsDir[1].replace(unnecessaryOff, "");

                        partsDir2 = parts[1].split("</p>");
                        partsDir2[1] = partsDir2[1].replace(unnecessaryOff2, "");

                        Log.d("shir", partsDir[1]);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (connection != null)
                            connection.disconnect();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return partsDir[1];
                }

                @Override
                protected void onPostExecute(String s) {
                    s = partsDir[1] + partsDir2[1];
                    txtDisplaySign.setText(s);
                }
            }.execute();

            Log.d("shir", "Internet is working");

        //if INTERNET is not connected do:
        } else {
            Log.d("shir", "No internet connection");
            txtDisplaySign.setText(getString(R.string.internet_error));
        }
    }

}
