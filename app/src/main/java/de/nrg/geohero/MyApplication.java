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
//        for (int i = 0; i < countries.size(); i++)
//        {
//            if (countries.get(i).getCapitol_en() != null)
//                datasource.createCountry(countries.get(i).getName(), countries.get(i).getName_eng(),
//                        countries.get(i).getContinent(), countries.get(i).getAbbr(),
//                        countries.get(i).getCapitol(), countries.get(i).getCapitol_en(), countries.get(i).getArea(), countries.get(i).getPopulation(),
//                        countries.get(i).getPop_density(), countries.get(i).getColor());
//            else
//                datasource.createCountry(countries.get(i).getName(), countries.get(i).getName_eng(),
//                        countries.get(i).getContinent(), countries.get(i).getAbbr(),
//                        countries.get(i).getCapitol(), countries.get(i).getCapitol(), countries.get(i).getArea(), countries.get(i).getPopulation(),
//                        countries.get(i).getPop_density(), countries.get(i).getColor());
//        }

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

        //Africa
        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Ägypten";
        countries.get(countries.size() - 1).name_eng = "Egypt";
        countries.get(countries.size() - 1).abbr = "EGY";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Kairo";
        countries.get(countries.size() - 1).capitol_en = "Cairo";
        countries.get(countries.size() - 1).area = 1001450;
        countries.get(countries.size() - 1).population = 80722000;
        countries.get(countries.size() - 1).pop_density = 81;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Algerien";
        countries.get(countries.size() - 1).name_eng = "Algeria";
        countries.get(countries.size() - 1).abbr = "DZA";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Algier";
        countries.get(countries.size() - 1).capitol_en = "Algiers";
        countries.get(countries.size() - 1).area = 2381741;
        countries.get(countries.size() - 1).population = 38482000;
        countries.get(countries.size() - 1).pop_density = 16;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Angola";
        countries.get(countries.size() - 1).name_eng = "Angola";
        countries.get(countries.size() - 1).abbr = "AGO";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Luanda";
        countries.get(countries.size() - 1).area = 1246700;
        countries.get(countries.size() - 1).population = 20821000;
        countries.get(countries.size() - 1).pop_density = 17;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Äquatorialguinea";
        countries.get(countries.size() - 1).name_eng = "Equatorial Guinea";
        countries.get(countries.size() - 1).abbr = "GNQ";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Malabo";
        countries.get(countries.size() - 1).area = 28051;
        countries.get(countries.size() - 1).population = 736000;
        countries.get(countries.size() - 1).pop_density = 26;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Äthiopien";
        countries.get(countries.size() - 1).name_eng = "Ethopia";
        countries.get(countries.size() - 1).abbr = "ETH";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Addis Abeba";
        countries.get(countries.size() - 1).area = 1133380;
        countries.get(countries.size() - 1).population = 91729000;
        countries.get(countries.size() - 1).pop_density = 81;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Benin";
        countries.get(countries.size() - 1).name_eng = "Benin";
        countries.get(countries.size() - 1).abbr = "BEN";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Porto Novo";
        countries.get(countries.size() - 1).capitol_en = "Porto-Novo";
        countries.get(countries.size() - 1).area = 112622;
        countries.get(countries.size() - 1).population = 10051000;
        countries.get(countries.size() - 1).pop_density = 89;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Botswana";
        countries.get(countries.size() - 1).name_eng = "Botswana";
        countries.get(countries.size() - 1).abbr = "BWA";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Gaborone";
        countries.get(countries.size() - 1).area = 581730;
        countries.get(countries.size() - 1).population = 2004000;
        countries.get(countries.size() - 1).pop_density = 3;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Burundi";
        countries.get(countries.size() - 1).name_eng = "Burundi";
        countries.get(countries.size() - 1).abbr = "BDI";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Bujumbura";
        countries.get(countries.size() - 1).area = 27834;
        countries.get(countries.size() - 1).population = 9850000;
        countries.get(countries.size() - 1).pop_density = 354;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Dschibuti";
        countries.get(countries.size() - 1).name_eng = "Djibouti";
        countries.get(countries.size() - 1).abbr = "DJI";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Dschibuti-Stadt";
        countries.get(countries.size() - 1).capitol_en = "Djibouti City";
        countries.get(countries.size() - 1).area = 23200;
        countries.get(countries.size() - 1).population = 860000;
        countries.get(countries.size() - 1).pop_density = 37;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Elfenbeinküste";
        countries.get(countries.size() - 1).name_eng = "Ivory Coast";
        countries.get(countries.size() - 1).abbr = "CIV";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Yamoussoukro";
        countries.get(countries.size() - 1).area = 322463;
        countries.get(countries.size() - 1).population = 19840000;
        countries.get(countries.size() - 1).pop_density = 62;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Eritrea";
        countries.get(countries.size() - 1).name_eng = "Eritrea";
        countries.get(countries.size() - 1).abbr = "ERI";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Asmara";
        countries.get(countries.size() - 1).area = 121144;
        countries.get(countries.size() - 1).population = 6131000;
        countries.get(countries.size() - 1).pop_density = 51;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Gabun";
        countries.get(countries.size() - 1).name_eng = "Gabon";
        countries.get(countries.size() - 1).abbr = "GAB";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Libreville";
        countries.get(countries.size() - 1).area = 267667;
        countries.get(countries.size() - 1).population = 1633000;
        countries.get(countries.size() - 1).pop_density = 6;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Gambia";
        countries.get(countries.size() - 1).name_eng = "Gambia";
        countries.get(countries.size() - 1).abbr = "GMB";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Banjul";
        countries.get(countries.size() - 1).area = 11295;
        countries.get(countries.size() - 1).population = 1791000;
        countries.get(countries.size() - 1).pop_density = 159;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Ghana";
        countries.get(countries.size() - 1).name_eng = "Ghana";
        countries.get(countries.size() - 1).abbr = "GHA";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Accra";
        countries.get(countries.size() - 1).area = 238537;
        countries.get(countries.size() - 1).population = 25366000;
        countries.get(countries.size() - 1).pop_density = 106;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Guinea";
        countries.get(countries.size() - 1).name_eng = "Guinea";
        countries.get(countries.size() - 1).abbr = "GIN";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Conakry";
        countries.get(countries.size() - 1).area = 245857;
        countries.get(countries.size() - 1).population = 11451000;
        countries.get(countries.size() - 1).pop_density = 47;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Guinea-Bissau";
        countries.get(countries.size() - 1).name_eng = "Guinea-Bissau";
        countries.get(countries.size() - 1).abbr = "GNB";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Bissau";
        countries.get(countries.size() - 1).area = 36125;
        countries.get(countries.size() - 1).population = 1664000;
        countries.get(countries.size() - 1).pop_density = 46;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Kamerun";
        countries.get(countries.size() - 1).name_eng = "Cameroon";
        countries.get(countries.size() - 1).abbr = "CMR";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Yaoundé";
        countries.get(countries.size() - 1).area = 475442;
        countries.get(countries.size() - 1).population = 21700000;
        countries.get(countries.size() - 1).pop_density = 46;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Kap Verde";
        countries.get(countries.size() - 1).name_eng = "Cape Verde";
        countries.get(countries.size() - 1).abbr = "CPV";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Praia";
        countries.get(countries.size() - 1).area = 4036;
        countries.get(countries.size() - 1).population = 494000;
        countries.get(countries.size() - 1).pop_density = 122;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Kenia";
        countries.get(countries.size() - 1).name_eng = "Kenya";
        countries.get(countries.size() - 1).abbr = "KEN";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Nairobi";
        countries.get(countries.size() - 1).area = 582646;
        countries.get(countries.size() - 1).population = 43178000;
        countries.get(countries.size() - 1).pop_density = 74;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Komoren";
        countries.get(countries.size() - 1).name_eng = "Comoros";
        countries.get(countries.size() - 1).abbr = "COM";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Moroni";
        countries.get(countries.size() - 1).area = 1862;
        countries.get(countries.size() - 1).population = 718000;
        countries.get(countries.size() - 1).pop_density = 386;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Dem. Republik Kongo";
        countries.get(countries.size() - 1).name_eng = "Congo (Dem. Republic)";
        countries.get(countries.size() - 1).abbr = "COD";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Kinshasa";
        countries.get(countries.size() - 1).area = 2344858;
        countries.get(countries.size() - 1).population = 65705000;
        countries.get(countries.size() - 1).pop_density = 28;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Republik Kongo";
        countries.get(countries.size() - 1).name_eng = "Congo (Republic)";
        countries.get(countries.size() - 1).abbr = "COG";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Brazzaville";
        countries.get(countries.size() - 1).area = 342000;
        countries.get(countries.size() - 1).population = 4337000;
        countries.get(countries.size() - 1).pop_density = 13;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Lesotho";
        countries.get(countries.size() - 1).name_eng = "Lesotho";
        countries.get(countries.size() - 1).abbr = "LSO";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Maseru";
        countries.get(countries.size() - 1).area = 30355;
        countries.get(countries.size() - 1).population = 2052000;
        countries.get(countries.size() - 1).pop_density = 68;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Liberia";
        countries.get(countries.size() - 1).name_eng = "Liberia";
        countries.get(countries.size() - 1).abbr = "LBR";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Monrovia";
        countries.get(countries.size() - 1).area = 97754;
        countries.get(countries.size() - 1).population = 4190000;
        countries.get(countries.size() - 1).pop_density = 43;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Libyen";
        countries.get(countries.size() - 1).name_eng = "Libya";
        countries.get(countries.size() - 1).abbr = "LBY";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Tripolis";
        countries.get(countries.size() - 1).capitol_en = "Tripoli";
        countries.get(countries.size() - 1).area = 1775500;
        countries.get(countries.size() - 1).population = 6155000;
        countries.get(countries.size() - 1).pop_density = 4;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Madagaskar";
        countries.get(countries.size() - 1).name_eng = "Madagascar";
        countries.get(countries.size() - 1).abbr = "MDG";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Antananarivo";
        countries.get(countries.size() - 1).area = 587041;
        countries.get(countries.size() - 1).population = 22294000;
        countries.get(countries.size() - 1).pop_density = 38;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Malawi";
        countries.get(countries.size() - 1).name_eng = "Malawi";
        countries.get(countries.size() - 1).abbr = "MWI";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Lilongwe";
        countries.get(countries.size() - 1).area = 118484;
        countries.get(countries.size() - 1).population = 15906000;
        countries.get(countries.size() - 1).pop_density = 134;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Mali";
        countries.get(countries.size() - 1).name_eng = "Mali";
        countries.get(countries.size() - 1).abbr = "MLI";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Bamako";
        countries.get(countries.size() - 1).area = 1240192;
        countries.get(countries.size() - 1).population = 14854000;
        countries.get(countries.size() - 1).pop_density = 12;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Marokko";
        countries.get(countries.size() - 1).name_eng = "Marocco";
        countries.get(countries.size() - 1).abbr = "MAR";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Rabat";
        countries.get(countries.size() - 1).area = 458730;
        countries.get(countries.size() - 1).population = 32521000;
        countries.get(countries.size() - 1).pop_density = 71;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Mauretanien";
        countries.get(countries.size() - 1).name_eng = "Mauritania";
        countries.get(countries.size() - 1).abbr = "MRT";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Nouakchott";
        countries.get(countries.size() - 1).area = 1030700;
        countries.get(countries.size() - 1).population = 3796000;
        countries.get(countries.size() - 1).pop_density = 4;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Mauritius";
        countries.get(countries.size() - 1).name_eng = "Mauritius";
        countries.get(countries.size() - 1).abbr = "MUS";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Port Louis";
        countries.get(countries.size() - 1).area = 2040;
        countries.get(countries.size() - 1).population = 1291000;
        countries.get(countries.size() - 1).pop_density = 633;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Mosambik";
        countries.get(countries.size() - 1).name_eng = "Mozambique";
        countries.get(countries.size() - 1).abbr = "MOZ";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Maputo";
        countries.get(countries.size() - 1).area = 799380;
        countries.get(countries.size() - 1).population = 25203000;
        countries.get(countries.size() - 1).pop_density = 32;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Namibia";
        countries.get(countries.size() - 1).name_eng = "Namibia";
        countries.get(countries.size() - 1).abbr = "NAM";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Windhoek";
        countries.get(countries.size() - 1).area = 824292;
        countries.get(countries.size() - 1).population = 2259000;
        countries.get(countries.size() - 1).pop_density = 3;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Niger";
        countries.get(countries.size() - 1).name_eng = "Niger";
        countries.get(countries.size() - 1).abbr = "NER";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Niamey";
        countries.get(countries.size() - 1).area = 1267000;
        countries.get(countries.size() - 1).population = 17157000;
        countries.get(countries.size() - 1).pop_density = 14;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Nigeria";
        countries.get(countries.size() - 1).name_eng = "Nigeria";
        countries.get(countries.size() - 1).abbr = "NGA";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Abuja";
        countries.get(countries.size() - 1).area = 923768;
        countries.get(countries.size() - 1).population = 168834000;
        countries.get(countries.size() - 1).pop_density = 183;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Ruanda";
        countries.get(countries.size() - 1).name_eng = "Rwanda";
        countries.get(countries.size() - 1).abbr = "RWA";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Kigali";
        countries.get(countries.size() - 1).area = 26338;
        countries.get(countries.size() - 1).population = 11458000;
        countries.get(countries.size() - 1).pop_density = 435;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Sambia";
        countries.get(countries.size() - 1).name_eng = "Zambia";
        countries.get(countries.size() - 1).abbr = "ZMB";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Lusaka";
        countries.get(countries.size() - 1).area = 752614;
        countries.get(countries.size() - 1).population = 14075000;
        countries.get(countries.size() - 1).pop_density = 19;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "São Tomé und Príncipe";
        countries.get(countries.size() - 1).name_eng = "São Tomé and Príncipe";
        countries.get(countries.size() - 1).abbr = "STP";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "São Tomé";
        countries.get(countries.size() - 1).area = 1001;
        countries.get(countries.size() - 1).population = 188000;
        countries.get(countries.size() - 1).pop_density = 188;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Senegal";
        countries.get(countries.size() - 1).name_eng = "Senegal";
        countries.get(countries.size() - 1).abbr = "SEN";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Dakar";
        countries.get(countries.size() - 1).area = 196722;
        countries.get(countries.size() - 1).population = 13726000;
        countries.get(countries.size() - 1).pop_density = 70;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Seychellen";
        countries.get(countries.size() - 1).name_eng = "Seychelles";
        countries.get(countries.size() - 1).abbr = "SYC";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Victoria";
        countries.get(countries.size() - 1).area = 454;
        countries.get(countries.size() - 1).population = 88000;
        countries.get(countries.size() - 1).pop_density = 194;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Sierra Leone";
        countries.get(countries.size() - 1).name_eng = "Sierra Leone";
        countries.get(countries.size() - 1).abbr = "SLE";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Freetown";
        countries.get(countries.size() - 1).area = 71740;
        countries.get(countries.size() - 1).population = 5979000;
        countries.get(countries.size() - 1).pop_density = 83;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Simbabwe";
        countries.get(countries.size() - 1).name_eng = "Zimbabwe";
        countries.get(countries.size() - 1).abbr = "ZWE";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Harare";
        countries.get(countries.size() - 1).area = 390757;
        countries.get(countries.size() - 1).population = 13724000;
        countries.get(countries.size() - 1).pop_density = 35;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Somalia";
        countries.get(countries.size() - 1).name_eng = "Somalia";
        countries.get(countries.size() - 1).abbr = "SOM";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Mogadischu";
        countries.get(countries.size() - 1).capitol_en = "Mogadishu";
        countries.get(countries.size() - 1).area = 637657;
        countries.get(countries.size() - 1).population = 10195000;
        countries.get(countries.size() - 1).pop_density = 16;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Südafrika";
        countries.get(countries.size() - 1).name_eng = "South Africa";
        countries.get(countries.size() - 1).abbr = "ZAF";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Pretoria";
        countries.get(countries.size() - 1).area = 1220813;
        countries.get(countries.size() - 1).population = 51189000;
        countries.get(countries.size() - 1).pop_density = 42;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Sudan";
        countries.get(countries.size() - 1).name_eng = "Sudan";
        countries.get(countries.size() - 1).abbr = "SDN";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Khartum";
        countries.get(countries.size() - 1).capitol_en = "Khartoum";
        countries.get(countries.size() - 1).area = 1840687;
        countries.get(countries.size() - 1).population = 37195000;
        countries.get(countries.size() - 1).pop_density = 20;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Südsudan";
        countries.get(countries.size() - 1).name_eng = "South Sudan";
        countries.get(countries.size() - 1).abbr = "SSD";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Juba";
        countries.get(countries.size() - 1).area = 619745;
        countries.get(countries.size() - 1).population = 10838000;
        countries.get(countries.size() - 1).pop_density = 18;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Swasiland";
        countries.get(countries.size() - 1).name_eng = "Swaziland";
        countries.get(countries.size() - 1).abbr = "SWZ";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Mbabane";
        countries.get(countries.size() - 1).area = 17364;
        countries.get(countries.size() - 1).population = 1231000;
        countries.get(countries.size() - 1).pop_density = 71;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Tansania";
        countries.get(countries.size() - 1).name_eng = "Tanzania";
        countries.get(countries.size() - 1).abbr = "TZA";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Dodoma";
        countries.get(countries.size() - 1).area = 945087;
        countries.get(countries.size() - 1).population = 47783000;
        countries.get(countries.size() - 1).pop_density = 51;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Togo";
        countries.get(countries.size() - 1).name_eng = "Togo";
        countries.get(countries.size() - 1).abbr = "TGO";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Lomé";
        countries.get(countries.size() - 1).area = 56785;
        countries.get(countries.size() - 1).population = 6643000;
        countries.get(countries.size() - 1).pop_density = 117;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Tschad";
        countries.get(countries.size() - 1).name_eng = "Chad";
        countries.get(countries.size() - 1).abbr = "TCD";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "N'Djamena";
        countries.get(countries.size() - 1).area = 1284000;
        countries.get(countries.size() - 1).population = 12448000;
        countries.get(countries.size() - 1).pop_density = 10;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Tunesien";
        countries.get(countries.size() - 1).name_eng = "Tunisia";
        countries.get(countries.size() - 1).abbr = "TUN";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Tunis";
        countries.get(countries.size() - 1).area = 163610;
        countries.get(countries.size() - 1).population = 10778000;
        countries.get(countries.size() - 1).pop_density = 66;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Uganda";
        countries.get(countries.size() - 1).name_eng = "Uganda";
        countries.get(countries.size() - 1).abbr = "UGA";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Kampala";
        countries.get(countries.size() - 1).area = 241548;
        countries.get(countries.size() - 1).population = 36346000;
        countries.get(countries.size() - 1).pop_density = 151;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Westsahara";
        countries.get(countries.size() - 1).name_eng = "Sahrawi Arab DR";
        countries.get(countries.size() - 1).abbr = "ESH";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "El Aaiún";
        countries.get(countries.size() - 1).area = 252120;
        countries.get(countries.size() - 1).population = 567000;
        countries.get(countries.size() - 1).pop_density = 2;
        countries.get(countries.size() - 1).color = -0;

        countries.add(new Country());
        countries.get(countries.size() - 1).id = countries.size() - 1;
        countries.get(countries.size() - 1).name = "Zentralafrikanische Republik";
        countries.get(countries.size() - 1).name_eng = "Central African Republic";
        countries.get(countries.size() - 1).abbr = "CAF";
        countries.get(countries.size() - 1).continent = 1;
        countries.get(countries.size() - 1).capitol = "Bangui";
        countries.get(countries.size() - 1).area = 622984;
        countries.get(countries.size() - 1).population = 4525000;
        countries.get(countries.size() - 1).pop_density = 7;
        countries.get(countries.size() - 1).color = -0;
    }
}
