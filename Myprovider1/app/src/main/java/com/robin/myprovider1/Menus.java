package com.robin.myprovider1;

import android.net.Uri;

/**
 * Created by robin on 2016/10/27.
 */
public class Menus {
    //定义provider需要的相关数据 和uri有关

    //以下定义provider的MIME类型常量
    public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";//MIME类型 多条
    public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";//单条
    public static final String MIME_ITEM = "vnd.pub.menus";//自定义MIME类型字符串
    //将固定前缀+自定义字符串生成两个类型 (单条/多条)
    public static final String MIME_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MIME_ITEM;//单条
    public static final String MIME_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MIME_ITEM;//多条

    //用来定义uri常量      content://<authority>/<data path>/..../id
    public static final String AUTHORITY = "myfs.pub.menusprovider";//授权者 用哪个provider
    public static final String PATH_SINGLE = "menus/#"; //路径下单条记录  #代表数字id
    public static final String PATH_MULTIPLE = "menus";//路径下多条记录  数据库讲对应表名
    public static final String PATH_MULTIPLE_NAME="menus/*"; //*代表文本 例如按名称访问等

    //组合成所需要的uri字符传
    public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
    //将uri字符串转换为uri对象
    public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

    //以上就完成自定义provider所需的uri和MIME类型的定义


    //涉及sqlite底层实现的内容
    public static final String CREAT_TABLE = "create table menus(id integer primary key autoincrement ,name varchar(50),price integer)";
    public static final String DBNAME = "pub.db";
    public static final String TABLENAME="menus";
    public static final int DB_VER = 1;

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PRICE = "price";

    public static final String[] COLUNMS = {KEY_ID,KEY_NAME,KEY_PRICE};


    private int id;
    private String name;
    private int price;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Menus(String name, int price) {
        super();
        this.name = name;
        this.price = price;
    }
    public Menus(){}
    @Override
    public String toString() {
        return "id:"+id+",name:"+name+",price:"+price;
    }

}
