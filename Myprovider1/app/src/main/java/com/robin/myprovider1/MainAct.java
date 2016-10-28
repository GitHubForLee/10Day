package com.robin.myprovider1;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainAct extends AppCompatActivity {
    private static final String TAG = "robin";
    ContentResolver cr;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listview=(ListView)findViewById(R.id.listView1);
        cr=getContentResolver();
    }

    public void btnOperate(View view){
        switch (view.getId()){
            case R.id.btnAdd:
                addData();
                break;
            case R.id.btnDel:
                deleteAll();
                break;
            case R.id.btnDelById:
                int id=2;
                Uri uri= ContentUris.withAppendedId(Menus.CONTENT_URI,id);
                int reslut=cr.delete(uri,null,null);
                Log.e(TAG,"del data:"+uri.toString());
                break;
            case R.id.btnQuery:
                queryAll();

                break;
        }
    }

    private void deleteAll() {
        cr.delete(Menus.CONTENT_URI,null,null);
    }

    private void queryAll() {
        Cursor cursor=cr.query(Menus.CONTENT_URI,Menus.COLUNMS,null,null,null);
        ArrayList<String> list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    String s=cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2);
                    list.add(s);
                }while(cursor.moveToNext());
                listview.setAdapter(new ArrayAdapter<String>(MainAct.this,android.R.layout.simple_list_item_1,list));
            }
        }
    }

    private void addData() {
        Menus[] mms={
                new Menus("红烧鱼",30),
                new Menus("香菇滑稽",10),
                new Menus("红烧肉",20)
        };
        for(Menus mm:mms){
            ContentValues cv=new ContentValues();
            cv.put(Menus.KEY_NAME,mm.getName());
            cv.put(Menus.KEY_PRICE,mm.getPrice());
            Uri uri=cr.insert(Menus.CONTENT_URI,cv);
            Log.e(TAG,"add data:"+uri.toString());
        }
    }
}
