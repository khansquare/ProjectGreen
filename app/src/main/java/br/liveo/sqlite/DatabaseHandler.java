package br.liveo.sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.liveo.model.Test;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author       :   Mohsin Khan & Rakesh Kumawat
 * Designation  :   Android Developer
 * Company      :   Parasme Softwares & Technology
 * Date         :   September 11 , 2015
 * Purpose      :   Database Handling
 * Description  :   Detailed Description...
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "edtechguy";

    private final static String TABLE_TESTS = "tests";
    private final static String TEST_ID	= "id";
    private final static String TEST_DATE = "date";
    private final static String TEST_TITLE = "title";
    private final static String TEST_DURATION = "duration";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createTestTable =
                "create table " + TABLE_TESTS + "("
                        + TEST_ID		+ " INTEGER PRIMARY KEY AUTOINCREMENT , "
                        + TEST_DATE		+ " TEXT , "
                        + TEST_TITLE	+ " TEXT , "
                        + TEST_DURATION + " TEXT ) ";

        db.execSQL(createTestTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist " + TABLE_TESTS);
        onCreate(db);
    }

    public void insertTest(Test test) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEST_DATE, test.getDate().toString());
        contentValues.put(TEST_TITLE, test.getTitle());
        contentValues.put(TEST_DURATION, test.getDuration());
        db.insert(TABLE_TESTS, null, contentValues);
        db.close();
    }


    public ArrayList<Test> getTests() throws ParseException {
        ArrayList<Test> tests = new ArrayList<Test>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_TESTS, null);
        if(cursor.moveToFirst()) {
            do {
                tests.add(new Test(
                        cursor.getInt(0),
                        new SimpleDateFormat("DD-MM-YYYY").parse(cursor.getString(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)));
            } while (cursor.moveToNext());
        }
        db.close();
        return tests;
    }
}