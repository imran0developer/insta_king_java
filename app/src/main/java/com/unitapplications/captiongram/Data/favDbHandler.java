package com.unitapplications.captiongram.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.unitapplications.captiongram.Models.FavModel;
import com.unitapplications.captiongram.Params.fav_params;

import java.util.ArrayList;

public class favDbHandler extends SQLiteOpenHelper {
    // creating a constructor for our database handler.
    public favDbHandler(Context context) {
        super(context,fav_params.DB_NAME, null, fav_params.DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + fav_params.TABLE_NAME + " ("
                + fav_params.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + fav_params.KEY_FAV + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addFav(FavModel favModel) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(fav_params.KEY_FAV, favModel.getFav_caption());
        

        // after adding all values we are passing
        // content values to our table.
        db.insert(fav_params.TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public ArrayList<FavModel> getAllFAv(){
        ArrayList<FavModel> favList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + fav_params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                FavModel models = new FavModel();
                models.setId(Integer.parseInt(cursor.getString(0)));
                models.setFav_caption(cursor.getString(1));
                
                favList.add(models);
            }while(cursor.moveToNext());
        }
        return favList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + fav_params.TABLE_NAME);
        onCreate(db);
    }
}