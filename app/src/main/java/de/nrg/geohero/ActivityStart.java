package de.nrg.geohero;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static de.nrg.geohero.ActivityMain.soundIDs;
import static de.nrg.geohero.MyApplication.playSound;

public class ActivityStart extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_start);

        if (MyApplication.isTablet(this))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button bEurope = (Button) this.findViewById(R.id.bEurope);
        bEurope.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                playSound(soundIDs.get(0), 0.67f);
                MyApplication.currentGame = 1;
                startActivity(new Intent(ActivityStart.this,ActivityMain.class));
            }
        });

        Button bAfrica = (Button) this.findViewById(R.id.bAfrica);
        bAfrica.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                playSound(soundIDs.get(0), 0.67f);
                MyApplication.currentGame = 2;
                startActivity(new Intent(ActivityStart.this,ActivityMain.class));
            }
        });
    }
}
