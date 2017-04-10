package hr.ferit.danielpavlekovic.zadacarmatasky;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by danielpavlekovic on 10.04.2017..
 */

class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_NAME = "tasks_table";
    public static final String COL_1 = "DATECREATED";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DUEDATE";
    public static final String COL_4 = "CATEGORY";
    public static final String COL_5 = "PRIORITY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " DATE PRIMARY KEY," + COL_2 + " TEXT," + COL_3 + " DATE," + COL_4 +"TEXT,"+ COL_5 + "INTEGER" + ")";
        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
