package com.robin.mybc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by robin on 2016/10/27.
 */
public class MyReceiver6 extends BroadcastReceiver {

    private static final String TAG = "robin";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "receiver6收到一个有序广播");
        //取出上一个接收器的处理结果
        Log.e(TAG,this.getResultExtras(true).getString("rev5"));
    }
}