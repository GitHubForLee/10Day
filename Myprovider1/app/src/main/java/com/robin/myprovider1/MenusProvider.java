package com.robin.myprovider1;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by robin on 2016/10/27.
 */
public class MenusProvider extends ContentProvider {

    //1.创建urimatcher对象
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    //2.定义对应MIME类型的编码code常量(代表不同类型的操作)
    private static final int MULTIPLE_MENUS = 1;
    private static final int SINGLE_MENUS=2;
    private static final String TAG ="robin" ;

    //3.静态块,在类加载时执行一次 uri和code的绑定
    static {
        matcher.addURI(Menus.AUTHORITY, Menus.PATH_MULTIPLE, MULTIPLE_MENUS);
        matcher.addURI(Menus.AUTHORITY,Menus.PATH_SINGLE,SINGLE_MENUS);
    }

    static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //用db对象创建表
            db.execSQL(Menus.CREAT_TABLE);
        }

        @Override //new > old才会调用
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //版本更新
            db.execSQL("drop table if exists "+Menus.TABLENAME);
            db.execSQL(Menus.CREAT_TABLE);
        }
    }

    private SQLiteDatabase sqb;
    private DBOpenHelper helper;




    @Override //创建provider调用
    public boolean onCreate() {

        Context context=this.getContext();
        //底层数据库的初始化
        helper=new DBOpenHelper(context,Menus.DBNAME,null,Menus.DB_VER);
        sqb=helper.getWritableDatabase();
        if(sqb==null){
            return false;
        }else{
            return true;//创建数据库成功
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(Menus.TABLENAME);
        switch (matcher.match(uri)){
            case SINGLE_MENUS:
                qb.appendWhere(Menus.KEY_ID+"="+uri.getPathSegments().get(1));
                break;
            case MULTIPLE_MENUS:
                break;
        }
        Cursor cur=qb.query(sqb,projection,selection,selectionArgs,null,null,sortOrder);
        return cur;
    }

    @Nullable
    @Override
    //要实现该方法必须完成
    //1.定义MIME类型
    //2.定义uri字符串
    //3.用urimatcher建立code和uri映射
    //4.用code来映射不同的MIME类型


    public String getType(Uri uri) {
        //返回uri对象对应的MIME类型
        //根据uri找到类型决定是单条/多条记录操作(字符串运算)
       int matchCode=matcher.match(uri);

        switch (matchCode){
            case MULTIPLE_MENUS:
                return Menus.MIME_TYPE_MULTIPLE;
            case SINGLE_MENUS :
                return Menus.MIME_TYPE_SINGLE;
            default:
                throw new IllegalArgumentException("Unknow:uri"+uri);

        }

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.e(TAG," uri:"+uri.toString());

        long id=sqb.insert(Menus.TABLENAME,null,values);
        if(id>0){
            //根据输入的uri生成对应该id的新的uri
            //新的uri=uri.toString()+"/"+id
            Uri newUri= ContentUris.withAppendedId(uri,id);
            Log.e(TAG," newuri:"+newUri.toString());
            return newUri;
        }else{
            throw new SQLException("fail to insert row into "+uri);
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int result=-1;
        switch (matcher.match(uri)){
            //假设:uri=content://myfs.pub.menusprovider/menus/1
            //                          authorities/get(0)/get(1)/get(2)/..

            case SINGLE_MENUS:
                String id=uri.getPathSegments().get(1);
                result=sqb.delete(Menus.TABLENAME,Menus.KEY_ID+"="+id,selectionArgs);
                Log.e("re",String.valueOf(result));
                break;
            case MULTIPLE_MENUS:
                result=sqb.delete(Menus.TABLENAME,selection,selectionArgs);
                sqb.execSQL("truncate table "+Menus.TABLENAME);
                break;
            default:
                throw new IllegalArgumentException("Unknow uri:"+uri);
        }


        return result;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int result=-1;
        switch (matcher.match(uri)){
            //假设:uri=content://myfs.pub.menusprovider/menus/1
            //                          authorities/get(0)/get(1)/get(2)/..

            case SINGLE_MENUS:
                String id=uri.getPathSegments().get(1);
                result=sqb.update(Menus.TABLENAME,values,Menus.KEY_ID+"="+id,selectionArgs);
                break;
            case MULTIPLE_MENUS:
                result=sqb.update(Menus.TABLENAME,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknow uri:"+uri);
        }


        return result;
    }
}
