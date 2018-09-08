package com.example.android.braindump;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME="BrainDump";

    //Table
    private static final String TABLE_NAME="Persons";
    // private static final String KEY_ID="Id";
    //private static final String KEY_NAME="Name";
    private static final String KEY_IDEAS="Ideas";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("
                /*    + KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"*/
                +KEY_IDEAS+" TEXT"+")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //CRUD Persons
    public void addIdeas(Ideas ideas)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //  values.put(KEY_NAME,person.getName());
        values.put(KEY_IDEAS, ideas.getIdeas());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updateIdeas(Ideas ideas)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put(KEY_NAME,person.getName());
        values.put(KEY_IDEAS, ideas.getIdeas());

        return db.update(TABLE_NAME,values,KEY_IDEAS+" =?",new String[]{String.valueOf(ideas.getIdeas())});
    }

    public void deleteIdeas(Ideas ideas){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_IDEAS+" =?",new String[]{String.valueOf(ideas.getIdeas())});
        db.close();
    }

    public Ideas getActivity(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_IDEAS},KEY_IDEAS+"=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Ideas(cursor.getString(0));

    }

    public List<Ideas> getAllPerson(){
        List<Ideas> lstPersons = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Ideas ideas = new Ideas();
                ideas.setIdeas(cursor.getString(0));
                /*person.setName(cursor.getString(1));
                person.setEmail(cursor.getString(2));*/

                lstPersons.add(ideas);

            }
            while(cursor.moveToNext());

        }
        return lstPersons;
    }


}
