package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper
{
    public static final String TABLE_HIGHSCORES = "highscores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_ERRORS = "errors";
    public static final String COLUMN_POINTS = "points";
    public static final String COLUMN_GAME_TYPE = "game_type";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    public static final String TABLE_COUNTRIES = "countries";
    public static final String COLUMN_NAME_EN = "name_en";
    public static final String COLUMN_CONTINENT = "continent";
    public static final String COLUMN_ABBR = "abbr";
    public static final String COLUMN_CAPITOL = "capitol";
    public static final String COLUMN_CAPITOL_EN = "capitol_en";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_POPULATION = "population";
    public static final String COLUMN_DENSITY = "density";
    public static final String COLUMN_COLOR = "color";

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_HIGHSCORES + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_TIME + " integer, "
            + COLUMN_ERRORS + " integer, "
            + COLUMN_POINTS + " integer, "
            + COLUMN_GAME_TYPE + " integer, "
            + COLUMN_TIMESTAMP + " integer "
            + "); " +
            "create table "
            + TABLE_COUNTRIES + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_NAME_EN + " text not null, "
            + COLUMN_CONTINENT + " integer, "
            + COLUMN_ABBR + " text not null, "
            + COLUMN_CAPITOL + " text not null, "
            + COLUMN_CAPITOL_EN + " text not null, "
            + COLUMN_AREA + " integer, "
            + COLUMN_POPULATION + " integer, "
            + COLUMN_DENSITY + " integer, "
            + COLUMN_COLOR + " integer "
            + ");";

    private static final String CREATE_TABLE_COUNTRIES = "create table "
            + TABLE_COUNTRIES + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_NAME_EN + " text not null, "
            + COLUMN_CONTINENT + " integer, "
            + COLUMN_ABBR + " text not null, "
            + COLUMN_CAPITOL + " text not null, "
            + COLUMN_CAPITOL_EN + " text not null, "
            + COLUMN_AREA + " integer, "
            + COLUMN_POPULATION + " integer, "
            + COLUMN_DENSITY + " integer, "
            + COLUMN_COLOR + " integer "
            + ");";

    private static final String CREATE_TABLE_HIGHSCORES = "create table "
            + TABLE_HIGHSCORES + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_TIME + " integer, "
            + COLUMN_ERRORS + " integer, "
            + COLUMN_POINTS + " integer, "
            + COLUMN_GAME_TYPE + " integer, "
            + COLUMN_TIMESTAMP + " integer "
            + ");";

    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " + newVersion);
        switch (oldVersion)
        {
            case 1:
            case 2:
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES + ";");
                db.execSQL(CREATE_TABLE_COUNTRIES);
            break;
        }
    }

}
