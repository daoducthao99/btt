package com.example.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SongDB extends SQLiteOpenHelper {
    public static  final  String tblName="Songs";
    public static final  String ID="ID";
    public static final  String NameSong="NameSong";
    public static  final String SingerName="SingerName";
    public static  final String Time="Time";

    public SongDB(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //AUTOINCREMENT
        String sqlCreateDB="Create table if not exists "+ tblName+"( " + ID +" Integer Primary Key , "+ NameSong+" Text, "+ SingerName+" Text, "+ Time +" Text)";
        db.execSQL(sqlCreateDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+tblName);
        onCreate(db);
    }

    public void AddSong(Song song)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ID,song.getId());
        values.put(NameSong,song.getSongName());
        values.put(SingerName,song.getSingerName());
        values.put(Time,song.getTime());
        db.insert(tblName,null,values);
        db.close();
    }

    public void UpdateSong(int id,Song song)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ID,song.getId());
        values.put(NameSong,song.getSongName());
        values.put(SingerName,song.getSingerName());
        values.put(Time,song.getTime());
        db.update(tblName,values,ID +"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteSong(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From " + tblName + " Where ID=" + id;
        db.execSQL(sql);
        db.close();
    }

    public Song getSong(int id)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.query(tblName,new String[]{ID,NameSong,SingerName,Time},ID +"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            return new Song(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        }
        return null;
    }

    public ArrayList<Song> getAllSong()
    {
        ArrayList<Song> list=null;
        String sql="Select * from "+tblName;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null)
        {
            list=new ArrayList<Song>();
            while (cursor.moveToNext())
            {
                Song song=new Song(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                list.add(song);
            }
        }
        return list;
    }
    public boolean checkId(int id) {
        int kt = -1;
        for (Song item : getAllSong()) {
            if (item.getId() == id) {
                kt++;
            }
        }
        if (kt >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
