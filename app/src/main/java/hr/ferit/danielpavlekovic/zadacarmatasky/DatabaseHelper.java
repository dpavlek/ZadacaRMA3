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

    private static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_NAME = "tasks_table";
    public static final String COL_1 = "DATECREATED";
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
        String SQL_String = "CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " DATE PRIMARY KEY," + COL_2 + " TEXT," + COL_3 + " DATE," + COL_4 + " TEXT," + COL_5 + " TEXT);";
        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Date datecreated, String name, Date duedate, String category, String priority){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, String.valueOf(datecreated));
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, String.valueOf(duedate));
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

    public Integer deleteData(Date id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"DUEDATE = ?",new String[] {String.valueOf(id)});
    }
}
