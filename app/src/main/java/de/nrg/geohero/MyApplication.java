package de.nrg.geohero;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import database.DataSource;

/**
 * Created by Ich on 12.09.2016.
 */
public class MyApplication extends Application
{
    private static DataSource datasource;
    private static final int MAX_POINTS = 100000;
    public static int currentGame = 1;

    public static ArrayList<Country> countries = new ArrayList<Country>(1);

    @Override
    public void onCreate()
    {
        super.onCreate();
        createSQLiteSession();

        buildDatabase();
        buildDatabaseNew();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        pause_SQLiteSession();
    }

    private void createSQLiteSession()
    {
        datasource = new DataSource(this);
        datasource.open();
    }

    private static void pause_SQLiteSession()
    {
        datasource.close();
    }

    public static DataSource getDatasource()
    {
        return datasource;
    }

    public static int calcPoints(int seconds, int errors)
    {
        int decreaseValue = (seconds * 75 + errors * 10 * 50) + seconds + errors
                + new Random().nextInt(99);
        if (decreaseValue >= MAX_POINTS)
            decreaseValue = MAX_POINTS - 1;
        int points = MAX_POINTS - decreaseValue;
        return points;
    }

    public static boolean isTablet(Context context)
    {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private void buildDatabaseNew()
    {
        for (int i = 0; i < countries.size(); i++)
        {
            if (countries.get(i).getCapitol_en() != null)
                datasource.createCountry(countries.get(i).getName(), countries.get(i).getName_eng(),
                        countries.get(i).getContinent(), countries.get(i).getAbbr(),
                        countries.get(i).getCapitol(), countries.get(i).getCapitol_en(), countries.get(i).getArea(), countries.get(i).getPopulation(),
                        countries.get(i).getPop_density(), countries.get(i).getColor());
            else
                datasource.createCountry(countries.get(i).getName(), countries.get(i).getName_eng(),
                        countries.get(i).getContinent(), countries.get(i).getAbbr(),
                        countries.get(i).getCapitol(), countries.get(i).getCapitol(), countries.get(i).getArea(), countries.get(i).getPopulation(),
                        countries.get(i).getPop_density(), countries.get(i).getColor());
        }

//        datasource.createCountry("Ägypten","Egypt",1,"EGY","Kairo","Cairo",1001450,80722000,81,0);
        ArrayList<Country> list1 = datasource.getAllCountries(0);

    }

    private void buildDatabase()
    {
        countries.clear();

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Island";
        countries.get(countries.size() - 1).abbr = "ISL";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Reykjavík";
        countries.get(countries.size() - 1).name_eng = "Iceland";
        countries.get(countries.size() - 1).area = 103000;
        countries.get(countries.size() - 1).population = 330000;
        countries.get(countries.size() - 1).pop_density = 3;
        countries.get(countries.size() - 1).color = -65536;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Norwegen";
        countries.get(countries.size() - 1).abbr = "NOR";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Oslo";
        countries.get(countries.size() - 1).name_eng = "Norway";
        countries.get(countries.size() - 1).area = 323802;
        countries.get(countries.size() - 1).population = 5200000;
        countries.get(countries.size() - 1).pop_density = 15;
        countries.get(countries.size() - 1).color = -49152;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Schweden";
        countries.get(countries.size() - 1).abbr = "SWE";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Stockholm";
        countries.get(countries.size() - 1).name_eng = "Sweden";
        countries.get(countries.size() - 1).area = 450295;
        countries.get(countries.size() - 1).population = 9800000;
        countries.get(countries.size() - 1).pop_density = 21;
        countries.get(countries.size() - 1).color = -38400;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Finnland";
        countries.get(countries.size() - 1).abbr = "FIN";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Helsinki";
        countries.get(countries.size() - 1).name_eng = "Finland";
        countries.get(countries.size() - 1).area = 338145;
        countries.get(countries.size() - 1).population = 5500000;
        countries.get(countries.size() - 1).pop_density = 16;
        countries.get(countries.size() - 1).color = -27392;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Russland";
        countries.get(countries.size() - 1).abbr = "RUS";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Moskau";
        countries.get(countries.size() - 1).capitol_en = "Moscow";
        countries.get(countries.size() - 1).name_eng = "Russia";
        countries.get(countries.size() - 1).area = 17124322;
        countries.get(countries.size() - 1).population = 146189400;
        countries.get(countries.size() - 1).pop_density = 9;
        countries.get(countries.size() - 1).color = -16640;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Irland";
        countries.get(countries.size() - 1).abbr = "IRL";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Dublin";
        countries.get(countries.size() - 1).name_eng = "Ireland";
        countries.get(countries.size() - 1).area = 70273;
        countries.get(countries.size() - 1).population = 4600000;
        countries.get(countries.size() - 1).pop_density = 67;
        countries.get(countries.size() - 1).color = -256;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Vereinigtes Königreich";
        countries.get(countries.size() - 1).abbr = "GBR";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "London";
        countries.get(countries.size() - 1).name_eng = "United Kingdom";
        countries.get(countries.size() - 1).area = 243610;
        countries.get(countries.size() - 1).population = 65100000;
        countries.get(countries.size() - 1).pop_density = 259;
        countries.get(countries.size() - 1).color = -1376512;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Dänemark";
        countries.get(countries.size() - 1).abbr = "DNK";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Kopenhagen";
        countries.get(countries.size() - 1).capitol_en = "Copenhagen";
        countries.get(countries.size() - 1).name_eng = "Denmark";
        countries.get(countries.size() - 1).area = 44487;
        countries.get(countries.size() - 1).population = 5764653;
        countries.get(countries.size() - 1).pop_density = 128;
        countries.get(countries.size() - 1).color = -5632;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Estland";
        countries.get(countries.size() - 1).abbr = "EST";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Tallinn";
        countries.get(countries.size() - 1).name_eng = "Estonia";
        countries.get(countries.size() - 1).area = 45228;
        countries.get(countries.size() - 1).population = 1300000;
        countries.get(countries.size() - 1).pop_density = 29;
        countries.get(countries.size() - 1).color = -3604736;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Lettland";
        countries.get(countries.size() - 1).abbr = "LVA";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Riga";
        countries.get(countries.size() - 1).name_eng = "Latvia";
        countries.get(countries.size() - 1).area = 64589;
        countries.get(countries.size() - 1).population = 2000000;
        countries.get(countries.size() - 1).pop_density = 31;
        countries.get(countries.size() - 1).color = -9765120;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Litauen";
        countries.get(countries.size() - 1).abbr = "LTU";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Vilnius";
        countries.get(countries.size() - 1).name_eng = "Lithuania";
        countries.get(countries.size() - 1).area = 65300;
        countries.get(countries.size() - 1).population = 2900000;
        countries.get(countries.size() - 1).pop_density = 46;
        countries.get(countries.size() - 1).color = -16711702;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Polen";
        countries.get(countries.size() - 1).abbr = "POL";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Warschau";
        countries.get(countries.size() - 1).capitol_en = "Warsaw";
        countries.get(countries.size() - 1).name_eng = "Poland";
        countries.get(countries.size() - 1).area = 312685;
        countries.get(countries.size() - 1).population = 38500000;
        countries.get(countries.size() - 1).pop_density = 123;
        countries.get(countries.size() - 1).color = -13107456;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Deutschland";
        countries.get(countries.size() - 1).abbr = "DEU";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Berlin";
        countries.get(countries.size() - 1).name_eng = "Germany";
        countries.get(countries.size() - 1).area = 357121;
        countries.get(countries.size() - 1).population = 81100000;
        countries.get(countries.size() - 1).pop_density = 225;
        countries.get(countries.size() - 1).color = -16711745;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Niederlande";
        countries.get(countries.size() - 1).abbr = "NLD";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Amsterdam";
        countries.get(countries.size() - 1).name_eng = "Netherlands";
        countries.get(countries.size() - 1).area = 41543;
        countries.get(countries.size() - 1).population = 16900000;
        countries.get(countries.size() - 1).pop_density = 402;
        countries.get(countries.size() - 1).color = -16728065;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Belgien";
        countries.get(countries.size() - 1).abbr = "BEL";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Brüssel";
        countries.get(countries.size() - 1).capitol_en = "Brussels";
        countries.get(countries.size() - 1).name_eng = "Belgium";
        countries.get(countries.size() - 1).area = 30528;
        countries.get(countries.size() - 1).population = 11200000;
        countries.get(countries.size() - 1).pop_density = 364;
        countries.get(countries.size() - 1).color = -16738817;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Luxemburg";
        countries.get(countries.size() - 1).abbr = "LUX";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Luxemburg";
        countries.get(countries.size() - 1).capitol_en = "Luxembourg";
        countries.get(countries.size() - 1).name_eng = "Luxembourg";
        countries.get(countries.size() - 1).area = 2586;
        countries.get(countries.size() - 1).population = 600000;
        countries.get(countries.size() - 1).pop_density = 193;
        countries.get(countries.size() - 1).color = -16749825;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Frankreich";
        countries.get(countries.size() - 1).abbr = "FRA";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Paris";
        countries.get(countries.size() - 1).name_eng = "France";
        countries.get(countries.size() - 1).area = 551500;
        countries.get(countries.size() - 1).population = 64300000;
        countries.get(countries.size() - 1).pop_density = 115;
        countries.get(countries.size() - 1).color = -16711817;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Spanien";
        countries.get(countries.size() - 1).abbr = "ESP";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Madrid";
        countries.get(countries.size() - 1).name_eng = "Spain";
        countries.get(countries.size() - 1).area = 505370;
        countries.get(countries.size() - 1).population = 46400000;
        countries.get(countries.size() - 1).pop_density = 91;
        countries.get(countries.size() - 1).color = -7864065;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Andorra";
        countries.get(countries.size() - 1).abbr = "AND";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Andorra la Vella";
        countries.get(countries.size() - 1).name_eng = "Andorra";
        countries.get(countries.size() - 1).area = 468;
        countries.get(countries.size() - 1).population = 85000;
        countries.get(countries.size() - 1).pop_density = 182;
        countries.get(countries.size() - 1).color = -3106280;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Portugal";
        countries.get(countries.size() - 1).abbr = "PRT";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Lissabon";
        countries.get(countries.size() - 1).capitol_en = "Lisbon";
        countries.get(countries.size() - 1).name_eng = "Portugal";
        countries.get(countries.size() - 1).area = 92090;
        countries.get(countries.size() - 1).population = 10300000;
        countries.get(countries.size() - 1).pop_density = 115;
        countries.get(countries.size() - 1).color = -327425;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Malta";
        countries.get(countries.size() - 1).abbr = "MLT";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Valletta";
        countries.get(countries.size() - 1).name_eng = "Malta";
        countries.get(countries.size() - 1).area = 316;
        countries.get(countries.size() - 1).population = 413000;
        countries.get(countries.size() - 1).pop_density = 1288;
        countries.get(countries.size() - 1).color = -12336299;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Italien";
        countries.get(countries.size() - 1).abbr = "ITA";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Rom";
        countries.get(countries.size() - 1).capitol_en = "Rome";
        countries.get(countries.size() - 1).name_eng = "Italy";
        countries.get(countries.size() - 1).area = 301340;
        countries.get(countries.size() - 1).population = 62500000;
        countries.get(countries.size() - 1).pop_density = 202;
        countries.get(countries.size() - 1).color = -2031361;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Schweiz";
        countries.get(countries.size() - 1).abbr = "CHE";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Bern";
        countries.get(countries.size() - 1).name_eng = "Switzerland";
        countries.get(countries.size() - 1).area = 41277;
        countries.get(countries.size() - 1).population = 8300000;
        countries.get(countries.size() - 1).pop_density = 196;
        countries.get(countries.size() - 1).color = -14024449;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Liechtenstein";
        countries.get(countries.size() - 1).abbr = "LIE";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Vaduz";
        countries.get(countries.size() - 1).name_eng = "Liechtenstein";
        countries.get(countries.size() - 1).area = 160;
        countries.get(countries.size() - 1).population = 37100;
        countries.get(countries.size() - 1).pop_density = 228;
        countries.get(countries.size() - 1).color = -13354392;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Österreich";
        countries.get(countries.size() - 1).abbr = "AUT";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Wien";
        countries.get(countries.size() - 1).capitol_en = "Vienna";
        countries.get(countries.size() - 1).name_eng = "Austria";
        countries.get(countries.size() - 1).area = 83871;
        countries.get(countries.size() - 1).population = 8600000;
        countries.get(countries.size() - 1).pop_density = 101;
        countries.get(countries.size() - 1).color = -6749953;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Tschechien";
        countries.get(countries.size() - 1).abbr = "CZE";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Prag";
        countries.get(countries.size() - 1).capitol_en = "Prague";
        countries.get(countries.size() - 1).name_eng = "Czech Republik";
        countries.get(countries.size() - 1).area = 78867;
        countries.get(countries.size() - 1).population = 10600000;
        countries.get(countries.size() - 1).pop_density = 133;
        countries.get(countries.size() - 1).color = -16773633;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Slowakei";
        countries.get(countries.size() - 1).abbr = "SVK";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Bratislava";
        countries.get(countries.size() - 1).name_eng = "Slovakia";
        countries.get(countries.size() - 1).area = 49035;
        countries.get(countries.size() - 1).population = 5400000;
        countries.get(countries.size() - 1).pop_density = 110;
        countries.get(countries.size() - 1).color = -9502465;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Ungarn";
        countries.get(countries.size() - 1).abbr = "HUN";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Budapest";
        countries.get(countries.size() - 1).name_eng = "Hungary";
        countries.get(countries.size() - 1).area = 93028;
        countries.get(countries.size() - 1).population = 9800000;
        countries.get(countries.size() - 1).pop_density = 106;
        countries.get(countries.size() - 1).color = -65413;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Ukraine";
        countries.get(countries.size() - 1).abbr = "UKR";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Kiew";
        countries.get(countries.size() - 1).capitol_en = "Kiev";
        countries.get(countries.size() - 1).name_eng = "Ukraine";
        countries.get(countries.size() - 1).area = 577470;
        countries.get(countries.size() - 1).population = 40910600;
        countries.get(countries.size() - 1).pop_density = 71;
        countries.get(countries.size() - 1).color = -16760577;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Weißrussland";
        countries.get(countries.size() - 1).abbr = "BLR";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Minsk";
        countries.get(countries.size() - 1).name_eng = "Belarus";
        countries.get(countries.size() - 1).area = 207600;
        countries.get(countries.size() - 1).population = 9500000;
        countries.get(countries.size() - 1).pop_density = 46;
        countries.get(countries.size() - 1).color = -16718337;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Moldawien";
        countries.get(countries.size() - 1).abbr = "MDA";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Chișinău";
        countries.get(countries.size() - 1).name_eng = "Moldova";
        countries.get(countries.size() - 1).area = 33851;
        countries.get(countries.size() - 1).population = 4100000;
        countries.get(countries.size() - 1).pop_density = 92;
        countries.get(countries.size() - 1).color = -65306;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Rumänien";
        countries.get(countries.size() - 1).abbr = "ROU";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Bukarest";
        countries.get(countries.size() - 1).capitol_en = "Bucharest";
        countries.get(countries.size() - 1).name_eng = "Romania";
        countries.get(countries.size() - 1).area = 238391;
        countries.get(countries.size() - 1).population = 19800000;
        countries.get(countries.size() - 1).pop_density = 84;
        countries.get(countries.size() - 1).color = -3669761;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Slowenien";
        countries.get(countries.size() - 1).abbr = "SVN";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Ljubljana";
        countries.get(countries.size() - 1).name_eng = "Slovenia";
        countries.get(countries.size() - 1).area = 20273;
        countries.get(countries.size() - 1).population = 2100000;
        countries.get(countries.size() - 1).pop_density = 104;
        countries.get(countries.size() - 1).color = -65332;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Kroatien";
        countries.get(countries.size() - 1).abbr = "HRV";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Zagreb";
        countries.get(countries.size() - 1).name_eng = "Croatia";
        countries.get(countries.size() - 1).area = 56594;
        countries.get(countries.size() - 1).population = 4200000;
        countries.get(countries.size() - 1).pop_density = 76;
        countries.get(countries.size() - 1).color = -11796225;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Bosnien und Herzegowina";
        countries.get(countries.size() - 1).abbr = "BIH";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Sarajevo";
        countries.get(countries.size() - 1).name_eng = "Bosnia and Herzegovina";
        countries.get(countries.size() - 1).area = 51197;
        countries.get(countries.size() - 1).population = 3700000;
        countries.get(countries.size() - 1).pop_density = 74;
        countries.get(countries.size() - 1).color = -65383;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Serbien";
        countries.get(countries.size() - 1).abbr = "SRB";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Belgrad";
        countries.get(countries.size() - 1).capitol_en = "Belgrade";
        countries.get(countries.size() - 1).name_eng = "Serbia";
        countries.get(countries.size() - 1).area = 77474;
        countries.get(countries.size() - 1).population = 7100000;
        countries.get(countries.size() - 1).pop_density = 89;
        countries.get(countries.size() - 1).color = -7611223;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Mazedonien";
        countries.get(countries.size() - 1).abbr = "MKD";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Skopje";
        countries.get(countries.size() - 1).name_eng = "Macedonia";
        countries.get(countries.size() - 1).area = 25713;
        countries.get(countries.size() - 1).population = 2100000;
        countries.get(countries.size() - 1).pop_density = 78;
        countries.get(countries.size() - 1).color = -6779897;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Montenegro";
        countries.get(countries.size() - 1).abbr = "MNE";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Podgorica";
        countries.get(countries.size() - 1).name_eng = "Montenegro";
        countries.get(countries.size() - 1).area = 13812;
        countries.get(countries.size() - 1).population = 600000;
        countries.get(countries.size() - 1).pop_density = 48;
        countries.get(countries.size() - 1).color = -65472;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Kosovo";
        countries.get(countries.size() - 1).abbr = "RKS";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Priština";
        countries.get(countries.size() - 1).capitol_en = "Pristina";
        countries.get(countries.size() - 1).name_eng = "Kosovo";
        countries.get(countries.size() - 1).area = 10887;
        countries.get(countries.size() - 1).population = 1800000;
        countries.get(countries.size() - 1).pop_density = 165;
        countries.get(countries.size() - 1).color = -16659659;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Albanien";
        countries.get(countries.size() - 1).abbr = "ALB";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Tirana";
        countries.get(countries.size() - 1).name_eng = "Albania";
        countries.get(countries.size() - 1).area = 28748;
        countries.get(countries.size() - 1).population = 2900000;
        countries.get(countries.size() - 1).pop_density = 97;
        countries.get(countries.size() - 1).color = -5373697;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Griechenland";
        countries.get(countries.size() - 1).abbr = "GRC";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Athen";
        countries.get(countries.size() - 1).capitol_en = "Athens";
        countries.get(countries.size() - 1).name_eng = "Greece";
        countries.get(countries.size() - 1).area = 131957;
        countries.get(countries.size() - 1).population = 11500000;
        countries.get(countries.size() - 1).pop_density = 82;
        countries.get(countries.size() - 1).color = -65362;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Bulgarien";
        countries.get(countries.size() - 1).abbr = "BGR";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Sofia";
        countries.get(countries.size() - 1).name_eng = "Bulgaria";
        countries.get(countries.size() - 1).area = 110879;
        countries.get(countries.size() - 1).population = 7200000;
        countries.get(countries.size() - 1).pop_density = 65;
        countries.get(countries.size() - 1).color = -65442;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Türkei";
        countries.get(countries.size() - 1).abbr = "TUR";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Ankara";
        countries.get(countries.size() - 1).name_eng = "Turkey";
        countries.get(countries.size() - 1).area = 783562;
        countries.get(countries.size() - 1).population = 78200000;
        countries.get(countries.size() - 1).pop_density = 96;
        countries.get(countries.size() - 1).color = -9992772;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Zypern";
        countries.get(countries.size() - 1).abbr = "CYP";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Nikosia";
        countries.get(countries.size() - 1).capitol_en = "Nicosia";
        countries.get(countries.size() - 1).name_eng = "Cyprus";
        countries.get(countries.size() - 1).area = 9251;
        countries.get(countries.size() - 1).population = 1200000;
        countries.get(countries.size() - 1).pop_density = 130;
        countries.get(countries.size() - 1).color = -65506;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "San Marino";
        countries.get(countries.size() - 1).abbr = "SMR";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "San Marino";
        countries.get(countries.size() - 1).name_eng = "San Marino";
        countries.get(countries.size() - 1).area = 61;
        countries.get(countries.size() - 1).population = 32800;
        countries.get(countries.size() - 1).pop_density = 530;
        countries.get(countries.size() - 1).color = -13133744;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Monaco";
        countries.get(countries.size() - 1).abbr = "MCO";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Monaco";
        countries.get(countries.size() - 1).name_eng = "Monaco";
        countries.get(countries.size() - 1).area = 2;
        countries.get(countries.size() - 1).population = 37800;
        countries.get(countries.size() - 1).pop_density = 17950;
        countries.get(countries.size() - 1).color = -14930516;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Vatikanstadt";
        countries.get(countries.size() - 1).abbr = "VAT";
        countries.get(countries.size() - 1).continent = 0;
        countries.get(countries.size() - 1).capitol = "Vatikanstadt";
        countries.get(countries.size() - 1).capitol_en = "Vatican City";
        countries.get(countries.size() - 1).name_eng = "Vatican City";
        countries.get(countries.size() - 1).area = 1;
        countries.get(countries.size() - 1).population = 829;
        countries.get(countries.size() - 1).pop_density = 1884;
        countries.get(countries.size() - 1).color = -14462929;
    }
}
