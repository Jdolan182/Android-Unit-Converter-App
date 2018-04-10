package mko.cs.stir.ac.uk.jdo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by jdo on 06/03/2018.
 */
//Allows access to the database holding the units available to convert
public class DBUnits {
    myDbHelper myhelper;
    public DBUnits(Context context)
    {
        myhelper = new myDbHelper(context);
        //SQLiteDatabase db = myhelper.getWritableDatabase();
        //db.execSQL("DROP TABLE IF EXISTS "+ myDbHelper.TABLE_NAME);
       // myhelper.onCreate(db);

        //insertData("Distance", "Miles", "Kilometers", "Meters", "Yards");
        //insertData("Weight","Kilograms", "Grams", "Pounds", "Ounces");
       // getData("Distance");
       // getUnitIDs();


    }


    public void insertData(String UnitID, String Units1, String Units2, String Units3 ,String Units4)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.UnitID, UnitID);
        contentValues.put(myDbHelper.Units1, Units1);
        contentValues.put(myDbHelper.Units2, Units2);
        contentValues.put(myDbHelper.Units3, Units3);
        contentValues.put(myDbHelper.Units4, Units4);

        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
    }

    //Returns the categories for each unit
    public ArrayList<String> getUnitIDs(){
        ArrayList<String> units = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT UnitID FROM " + myDbHelper.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String UnitID = cursor.getString(cursor.getColumnIndex(myDbHelper.UnitID));

                units.add(UnitID);
                cursor.moveToNext();
            }
        }
        for (String UnitID : units){
            Log.i("UnitID: ", UnitID);
        }

        return units;
    }

    //returns each individual unit from a specified category(unitID)
    public String[] getData(String UnitID)
    {
        String[] units = new String[4];
        SQLiteDatabase db = myhelper.getWritableDatabase();
        //String[] columns = {myDbHelper.UID,myDbHelper.UnitID,myDbHelper.Units1,myDbHelper.Units2,myDbHelper.Units3,myDbHelper.Units4};
        Cursor cursor =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME + " WHERE UnitID = '" + UnitID + "'", null);

        //StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            units[0] = cursor.getString(cursor.getColumnIndex(myDbHelper.Units1));
            units[1] = cursor.getString(cursor.getColumnIndex(myDbHelper.Units2));
            units[2] = cursor.getString(cursor.getColumnIndex(myDbHelper.Units3));
            units[3] = cursor.getString(cursor.getColumnIndex(myDbHelper.Units4));
        }
       // System.out.println("arr: " + Arrays.toString(units));
        return units;
    }



    //Creates the database
    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "Units";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column 1 (Primary Key)
        private static final String UnitID = "UnitID";    //Column 2
        private static final String Units1 = "Unit1";    // Column 3
        private static final String Units2 = "Unit2";    // Column 4
        private static final String Units3 = "Unit3";    // Column 5
        private static final String Units4 = "Unit4";    // Column 6
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+UnitID+" VARCHAR(255) ,"+ Units1+" VARCHAR(225) ,"+ Units2+" VARCHAR(225) ,"+ Units3 +
                " VARCHAR(225) ,"+ Units4+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                //Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
              //  Message.message(context,"OnUpgrade");
                Log.w("delet",
                        "Upgrading database from version " + oldVersion + " to "
                                + newVersion + ", which will destroy all old data");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
               // Message.message(context,""+e);
            }
        }
    }
}