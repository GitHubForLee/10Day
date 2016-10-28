package com.robin.mybc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by robin on 2016/10/27.
 */
public class MyReceiver5 extends BroadcastReceiver {

    private static final String TAG = "robin";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "receiver5收到一个有序广播");
        //处理结果发送给下一个接收器
        Bundle bundle=new Bundle();
        bundle.putString("rev5","这是rev5的处理结果");
        setResultExtras(bundle);
        abortBroadcast();
    }
}
