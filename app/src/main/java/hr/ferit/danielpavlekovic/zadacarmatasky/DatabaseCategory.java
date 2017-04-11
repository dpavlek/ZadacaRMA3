package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by danielpavlekovic on 10.04.2017..
 */

class DatabaseCategory extends SQLiteOpenHelper{

    private static DatabaseCategory sInstanceCategory;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "categories.db";
    public static final String TABLE_NAME = "CATEGORY_TABLE";
    public static final String COL_1 = "CATEGORY";
    SQLiteDatabase db;

    public static synchronized DatabaseCategory getInstance(Context context) {
        if (sInstanceCategory == null) {
            sInstanceCategory = new DatabaseCategory(context.getApplicationContext());
        }
        return sInstanceCategory;
    }

    private DatabaseCategory(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + " (" + COL_1 + " TEXT PRIMARY KEY);";
        db.execSQL(SQL_String);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public boolean insertData(String category){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,category);
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

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"CATEGORY = ?",new String[] {id});
    }
}
