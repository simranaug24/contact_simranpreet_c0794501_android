package com.example.contact_simranpreet_c0794501_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.SyncStateContract;
import android.widget.Toast;

import com.example.contact_simranpreet_c0794501_android.Model.RowModel;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String tableName= "contact";
    public static final String fName= "first_name";
    public static final String lName= "last_name";
    public static final String Email= "email";
    public static final String Phone= "phone";
    public static final String Address= "address";
    public static final String Id= "id";

    private Context context ;


    // constructor
    SQLiteHelper(Context context)
    {
        super(context,"phoneBook_database",null,1);
        this.context = context;
    }

    public void queryData(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);

    }
    // insert data
    public long  insertData(String Fname, String Lname, String email, String phone , String address)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(fName,Fname);
        values.put(lName,Lname);
        values.put(Email,email);
        values.put(Phone,phone);
        values.put(Address,address);

        long result = database.insert("CONTACT",null,values);

        return result;

    }

    // update data
    public boolean updateData(int ID,String Fname, String Lname, String email, String phone , String address)
    {
        SQLiteDatabase database =getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(fName,Fname);
        values.put(lName,Lname);
        values.put(Email,email);
        values.put(Phone,phone);
        values.put(Address,address);

            long result = database.update(tableName, values, Id + " = ? ", new String[]{String.valueOf(ID)});

            if(result == -1)
            {
                return false;
            }
            else
            {
                return true;
            }


    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String query = "CREATE TABLE IF NOT EXISTS " +tableName+ " (" +fName+ " TEXT NOT NULL, "+lName+ " TEXT NOT NULL,"+Email+" TEXT NOT NULL,"+Phone+" TEXT NOT NULL,"+Address+" TEXT NOT NULL,"+Id+" INTEGER  NOT NULL CONSTRAINT contact_pk PRIMARY KEY AUTOINCREMENT)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop Table if exists CONTACT");
    }

    public  ArrayList<RowModel> getAllContacts() {

        SQLiteDatabase data = getReadableDatabase();
        ArrayList<RowModel> allContact = new ArrayList<>();

        Cursor cursor = null;

        cursor = data.rawQuery("SELECT * FROM " + tableName, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String fName = cursor.getString(0);
                String lName = cursor.getString(1);
                String Email = cursor.getString(2);
                String Phone = cursor.getString(3);
                String Address = cursor.getString(4);
                int Id = cursor.getInt(5);

                RowModel model = new RowModel(fName,lName,Email,Phone,Address,Id);
                allContact.add(model);

            }
        }
        cursor.close();

        return allContact;
    }

    public long DeleteContact(int id) {

        SQLiteDatabase database = getWritableDatabase();
       long result =  database.delete(tableName,Id+" = ? ",new String[]{String.valueOf(id)});
       return result;
    }




    public RowModel getOneContact (int id){
        SQLiteDatabase data = getReadableDatabase();
        ArrayList<RowModel> allContact = new ArrayList<>();

        RowModel model = new RowModel();
        Cursor cursor = null;

        cursor = data.rawQuery("SELECT * FROM " + tableName, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String fName = cursor.getString(0);
                String lName = cursor.getString(1);
                String Email = cursor.getString(2);
                String Phone = cursor.getString(3);
                String Address = cursor.getString(4);
                int Id = cursor.getInt(5);

                model = new RowModel(fName,lName,Email,Phone,Address,Id);


            }
        }
        cursor.close();

        return model;

    }


}
