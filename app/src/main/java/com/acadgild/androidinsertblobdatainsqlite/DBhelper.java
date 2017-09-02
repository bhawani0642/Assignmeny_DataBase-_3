package com.acadgild.androidinsertblobdatainsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.acadgild.androidinsertblobdatainsqlite.Employee;
import com.acadgild.androidinsertblobdatainsqlite.Utility;
  //creating a class DBhelper
public class DBhelper {
      //CREATING QUREY
    public static final String EMP_ID = "id";
    public static final String EMP_NAME = "name";
    public static final String EMP_AGE = "age";
    public static final String EMP_PHOTO = "photo";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "EmployessDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String EMPLOYEES_TABLE = "Employees";

    private static final String CREATE_EMPLOYEES_TABLE = "create table "
            + EMPLOYEES_TABLE + " (" + EMP_ID
            + " integer primary key autoincrement, " + EMP_PHOTO
            + " blob not null, " + EMP_NAME + " text not null unique, "
            + EMP_AGE + " integer );";

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
            //onCreate method
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_EMPLOYEES_TABLE);
        }
//onUpgrade method
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEES_TABLE);
            onCreate(db);
        }
    }
      //reset method
    public void Reset() {
        mDbHelper.onUpgrade(this.mDb, 1, 1);
    }
      //constructor
    public DBhelper(Context ctx) {
        mCtx = ctx;
        mDbHelper = new DatabaseHelper(mCtx);
    }
    //opening database
    public DBhelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    //closing database
    public void close() {
        mDbHelper.close();
    }
      //Insert method to perform insert operation
    public void insertEmpDetails(Employee employee) {
        ContentValues cv = new ContentValues();
        cv.put(EMP_PHOTO, Utility.getBytes(employee.getBitmap()));
        cv.put(EMP_NAME, employee.getName());
        cv.put(EMP_AGE, employee.getAge());
        mDb.insert(EMPLOYEES_TABLE, null, cv);
    }
   //retrive method to get employee details
    public Employee retriveEmpDetails() throws SQLException {
        Cursor cur = mDb.query(true, EMPLOYEES_TABLE, new String[] { EMP_PHOTO,
                EMP_NAME, EMP_AGE }, null, null, null, null, null, null);
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(EMP_PHOTO));
            String name = cur.getString(cur.getColumnIndex(EMP_NAME));
            int age = cur.getInt(cur.getColumnIndex(EMP_AGE));
            cur.close();
            return new Employee(Utility.getPhoto(blob), name, age);
        }
        cur.close();
        return null;
    }
}
