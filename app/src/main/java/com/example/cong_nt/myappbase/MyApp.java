package com.example.cong_nt.myappbase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;

public class MyApp extends MultiDexApplication {
    private static MyApp intance;

    @Override
    public void onCreate() {
        super.onCreate();
        intance = this;
    }

    public static synchronized MyApp getIntance() {
        return intance;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager mConnectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectManager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
