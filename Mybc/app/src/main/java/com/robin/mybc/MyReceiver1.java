package com.robin.mybc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by robin on 2016/10/26.
 */
public class MyReceiver1 extends BroadcastReceiver {

    private static final String TAG = "bc";
    String json;
    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.robin.mybc.rec1".equals(intent.getAction())){
            Log.e(TAG,"from intent:"+intent.getStringExtra("bc"));
            Toast.makeText(context,"from intent:"+intent.getStringExtra("bc"),Toast.LENGTH_SHORT).show();

        }

    }
}
