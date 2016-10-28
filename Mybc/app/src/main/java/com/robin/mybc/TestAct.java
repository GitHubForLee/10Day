package com.robin.mybc;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by robin on 2016/10/27.
 */
public class TestAct extends AppCompatActivity {
    Button btn;
    MyReceiver4 receiver4;
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver4 = new MyReceiver4();
        filter = new IntentFilter();
        filter.addAction("com.robin.mybc.dynamic");

        setContentView(R.layout.test_act);
        btn = (Button) findViewById(R.id.btnSendBC);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(TestAct.this,MyReceiver4.class);
                Intent intent = new Intent("com.robin.mybc.dynamic");
                sendBroadcast(intent);
            }
        });

    }

    @Override //只有按钮可点击时注册广播接收器
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver4,filter);
    }

    @Override//当按钮不可点击时注销广播接收器
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver4);
    }
}
