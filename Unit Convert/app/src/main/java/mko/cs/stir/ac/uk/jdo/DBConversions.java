package mko.cs.stir.ac.uk.jdo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by jdo on 06/03/2018.
 */

public class DBConversions {
    myDbHelper myhelper;
    public DBConversions(Context context)
    {
        myhelper = new myDbHelper(context);
        SQLiteDatabase db = myhelper.getWritableDatabase();
        //db.execSQL("DROP TABLE IF EXISTS "+ myDbHelper.TABLE_NAME);
        //myhelper.onCreate(db);

        //insertData("Miles", "Miles", "1");
        //insertData("Miles", "Kilometers", "1.609344");
        //insertData("Miles", "Meters", "1609.34");
        //insertData("Miles", "Yards", "1760");

        //insertData("Kilometers", "Miles", "0.621371");
        //insertData("Kilometers", "Kilometers", "1");
        //insertData("Kilometers", "Meters", "1000");
        //insertData("Kilometers", "Yards", "1093.61");

        //insertData("Meters", "Miles", "0.000621371");
        //insertData("Meters", "Kilometers", "0.001");
        //insertData("Meters", "Meters", "1");
        //insertData("Meters", "Yards", "1.09361");

        //insertData("Yards", "Miles", "0.000568182");
        //insertData("Yards", "Kilometers", "0.0009144");
        //insertData("Yards", "Meters", "0.9144");
        //insertData("Yards", "Yards", "1");

        //insertData("Kilograms", "Kilograms", "1");
        //insertData("Kilograms", "Grams", "1000");
        //insertData("Kilograms", "Pounds", "2.20462");
        //insertData("Kilograms", "Ounces", "35.274");

        //insertData("Grams", "Kilograms", "0.001");
        //insertData("Grams", "Grams", "1");
        //insertData("Grams", "Pounds", "0.00220462");
        //insertData("Grams", "Ounces", "0.035274");

        //insertData("Pounds", "Kilograms", "0.453592");
        //insertData("Pounds", "Grams", "453.592");
        //insertData("Pounds", "Pounds", "1");
        //insertData("Pounds", "Ounces", "16");

        //insertData("Ounces", "Kilograms", "0.0283495");
        //insertData("Ounces", "Grams", "28.3495");
        //insertData("Ounces", "Pounds", "0.0625");
        //insertData("Ounces", "Ounces", "1");

    }

    public void insertData(String convertFrom, String convertTo, String conversion)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.convertFrom, convertFrom);
        contentValues.put(myDbHelper.convertTo, convertTo);
        contentValues.put(myDbHelper.conversion, conversion);

        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
    }


    //Returns the conversion number to be used in the calculation
    public String getConversion(String convertFrom, String convertTo)
    {
        String conversion = null;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT conversion FROM " + myDbHelper.TABLE_NAME + " WHERE convertFrom = '" + convertFrom + "' AND convertTo = '" + convertTo + "'", null);


        while (cursor.moveToNext())
        {
            conversion = cursor.getString(cursor.getColumnIndex(myDbHelper.conversion));


        }
       //System.out.println(conversion);
        return conversion;
    }

    //creates database
    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "Conversions";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column 1 (Primary Key)
        private static final String convertFrom = "convertFrom";    //Column 2
        private static final String convertTo = "convertTo";    // Column 3
        private static final String conversion = "conversion";    // Column 4

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+convertFrom+" VARCHAR(255) ,"+ convertTo+" VARCHAR(225) ,"+ conversion+" VARCHAR(225));";
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