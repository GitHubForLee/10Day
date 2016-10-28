package com.robin.mybc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by robin on 2016/10/26.
 */
public class MyReceiver3 extends BroadcastReceiver {
    private static final String TAG = "bc";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())){
            Log.e(TAG, "警告:有apk安装到你的手机!");
            Log.e(TAG,intent.getData().toString());
        }
        if(Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())){
            Log.e(TAG, "警告:有apk卸载你的手机!");
            Log.e(TAG,intent.getData().toString());
            Log.e(TAG,intent.getData().getSchemeSpecificPart());
        }
        if(Intent.ACTION_PACKAGE_CHANGED.equals(intent.getAction())){
            Log.e(TAG, "警告:有apk改变到你的手机!");
            Log.e(TAG,intent.getData().toString());
            Log.e(TAG,intent.getData().getSchemeSpecificPart());

        }
    }
}
