package com.robin.mybc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by robin on 2016/10/27.
 */
public class MyReceiver4 extends BroadcastReceiver{

    private static final String TAG = "robin";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"我收到一个动态注册的广播");
    }
}