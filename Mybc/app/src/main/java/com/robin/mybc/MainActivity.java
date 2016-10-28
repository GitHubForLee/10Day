package com.robin.mybc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    String[] functions={"发广播1","动态广播注册","发有序广播"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=(ListView)findViewById(R.id.listView);
        listview.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, functions));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        doSendBC1();//程序发广播,接收广播
                        break;
                    case 1:
                        doSendBC2();
                        break;
                    case 2:
                        doSendBC3();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    private void doSendBC3() {
        Intent intent=new Intent();
        intent.setAction("com.robin.mybc.ordered");
        sendOrderedBroadcast(intent,null);
    }

    private void doSendBC2() {
        startActivity(new Intent(this,TestAct.class));
    }

    private void doSendBC1() {
        Intent intent=new Intent();
        intent.setAction("com.robin.mybc.rec1");
        intent.putExtra("bc", "this is my broadcast1....");
        sendBroadcast(intent);//发送一个广播 (产生事件)

    }
}
