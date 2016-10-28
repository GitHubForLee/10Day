package com.robin.mybc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by robin on 2016/10/26.
 */
public class MyReceiver2 extends BroadcastReceiver {
    private static final String TAG = "bc";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_DATE_CHANGED.equals(intent.getAction())){
            Log.e(TAG,"警告:你系统日期被人篡改!");
        }




    }
}
