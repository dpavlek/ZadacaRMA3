package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by danielpavlekovic on 10.04.2017..
 */

class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstanceHelper;

    private static final int DATABASE_VERSION=5;
    public static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_NAME = "tasks_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DUEDATE";
    public static final String COL_4 = "CATEGORY";
    public static final String COL_5 = "PRIORITY";

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstanceHelper == null) {
            sInstanceHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstanceHelper;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT);";
        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String duedate, String category, String priority){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, duedate);
        contentValues.put(COL_4, category);
        contentValues.put(COL_5, priority);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public boolean deleteData(String position){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = " + String.valueOf(position), null)>0;
    }
}
