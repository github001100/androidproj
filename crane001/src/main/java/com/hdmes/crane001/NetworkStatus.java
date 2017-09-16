package com.hdmes.crane001;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/16.
 */

public class NetworkStatus {
    public boolean netStatus = false;

    /**
     * @param
     */
    /*public NetworkStatus(Context context) {
        try {
            ConnectivityManager connectManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectManager
                    .getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()
                        && activeNetworkInfo.isConnected()) {
                    netStatus = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public NetworkStatus(Context context) {
        ConnectivityManager con=(ConnectivityManager)context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if(internet){
            //执行相关操作
            netStatus=true;
            Toast.makeText(context,
                    "当前移动网络已连接！", Toast.LENGTH_LONG)
                    .show();
        }else if(wifi){
            netStatus=true;
            Toast.makeText(context,
                    "当前WIFI已连接", Toast.LENGTH_LONG)
                    .show();
        } else
        {
            Toast.makeText(context,
                    "亲，网络连了么？", Toast.LENGTH_LONG)
                    .show();
        }
    }
}

/*
 * 另外需要权限 <uses-permission
 * android:name="android.permission.ACCESS_NETWORK_STATE" />
 */
