package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.nrg.geohero.Country;

/**
 * Created by Ich on 12.09.2016.
 */
public class DataSource
{
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumnsHighscores = {MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_TIME, MySQLiteHelper.COLUMN_POINTS,
            MySQLiteHelper.COLUMN_ERRORS, MySQLiteHelper.COLUMN_TIMESTAMP, MySQLiteHelper.COLUMN_GAME_TYPE};

    private String[] allColumnsCountries = {MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_NAME_EN, MySQLiteHelper.COLUMN_CONTINENT,
            MySQLiteHelper.COLUMN_ABBR, MySQLiteHelper.COLUMN_CAPITOL, MySQLiteHelper.COLUMN_CAPITOL_EN,
            MySQLiteHelper.COLUMN_AREA, MySQLiteHelper.COLUMN_POPULATION,
            MySQLiteHelper.COLUMN_DENSITY, MySQLiteHelper.COLUMN_COLOR};

    public DataSource(Context context)
    {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Highscore createHighscore(String name, int time, int errors,
                                     int points, long when, int gameType)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_TIME, time);
        values.put(MySQLiteHelper.COLUMN_ERRORS, errors);
        values.put(MySQLiteHelper.COLUMN_POINTS, points);
        values.put(MySQLiteHelper.COLUMN_TIMESTAMP, when);
        values.put(MySQLiteHelper.COLUMN_GAME_TYPE, gameType);

        long insertId = database.insert(MySQLiteHelper.TABLE_HIGHSCORES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_HIGHSCORES,
                allColumnsHighscores, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        Highscore highScore = cursorToHighscore(cursor);
        cursor.close();
        return highScore;
    }

    public void deleteHighscore(Highscore highscore)
    {
        long id = highscore.getId();
        System.out.println("Highscore deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_HIGHSCORES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Highscore> getAllHighscores()
    {
        List<Highscore> highscores = new ArrayList<Highscore>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_HIGHSCORES,
                allColumnsHighscores, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Highscore highscore = cursorToHighscore(cursor);
            highscores.add(highscore);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return highscores;
    }

    public ArrayList<Highscore> getTop20(int gameType)
    {
        ArrayList<Highscore> highscores = new ArrayList<Highscore>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_HIGHSCORES,
                allColumnsHighscores,MySQLiteHelper.COLUMN_GAME_TYPE + "=?",
                new String[]{String.valueOf(gameType)},null,null,MySQLiteHelper.COLUMN_POINTS + " desc","20");

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Highscore highscore = cursorToHighscore(cursor);
            highscores.add(highscore);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return highscores;
    }

    private Highscore cursorToHighscore(Cursor cursor)
    {
        Highscore highscore = new Highscore();
        highscore.setId(cursor.getInt(0));
        highscore.setName(cursor.getString(1));
        highscore.setTime(cursor.getInt(2));
        highscore.setErrors(cursor.getInt(4));
        highscore.setPoints(cursor.getInt(3));
        highscore.setWhen(cursor.getLong(5));
        highscore.setGameType(cursor.getInt(6));
        return highscore;
    }

    public Country createCountry(String name, String name_en, int cont, String abbr, String capitol,
                                   String capitol_en, int area, int population, int density, int color)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_NAME_EN, name_en);
        values.put(MySQLiteHelper.COLUMN_CONTINENT, cont);
        values.put(MySQLiteHelper.COLUMN_ABBR, abbr);
        values.put(MySQLiteHelper.COLUMN_CAPITOL, capitol);
        values.put(MySQLiteHelper.COLUMN_CAPITOL_EN, capitol_en);
        values.put(MySQLiteHelper.COLUMN_AREA, area);
        values.put(MySQLiteHelper.COLUMN_POPULATION, population);
        values.put(MySQLiteHelper.COLUMN_DENSITY, density);
        values.put(MySQLiteHelper.COLUMN_COLOR, color);

        long insertId = database.insert(MySQLiteHelper.TABLE_COUNTRIES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COUNTRIES,
                allColumnsCountries, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();

        Country country = cursorToCountry(cursor);
        cursor.close();
        return country;
    }

    private Country cursorToCountry(Cursor cursor)
    {
        Country country = new Country();
        country.setId(cursor.getInt(0));
        country.setName(cursor.getString(1));
        country.setName_eng(cursor.getString(2));
        country.setContinent(cursor.getInt(3));
        country.setAbbr(cursor.getString(4));
        country.setCapitol(cursor.getString(5));
        country.setCapitol_en(cursor.getString(6));
        country.setArea(cursor.getInt(7));
        country.setPopulation(cursor.getInt(8));
        country.setPop_density(cursor.getInt(9));
        country.setColor(cursor.getInt(10));
        return country;
    }

    public void deleteCountry(Country country)
    {
        long id = country.getId();
        System.out.println("Country deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COUNTRIES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public ArrayList<Country> getAllCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COUNTRIES,
                allColumnsCountries, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Country country = cursorToCountry(cursor);
            countries.add(country);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return countries;
    }

    public ArrayList<Country> getAllCountries(int continent)
    {
        ArrayList<Country> countries = new ArrayList<Country>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COUNTRIES,
                allColumnsCountries, MySQLiteHelper.COLUMN_CONTINENT + "=?",
                new String[]{String.valueOf(continent)}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Country country = cursorToCountry(cursor);
            countries.add(country);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return countries;
    }
}
