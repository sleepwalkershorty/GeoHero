package de.nrg.geohero;

import android.app.Activity;
import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityHighscores extends ListActivity
{
    private HighscoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_highscores);

//        this.getActionBar().setDisplayHomeAsUpEnabled(true);

        if (MyApplication.isTablet(this))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        adapter = new HighscoreAdapter((ArrayList) MyApplication.getDatasource()
                .getTop20(MyApplication.currentGame), this);
        this.getListView().setAdapter(adapter);
    }
}
