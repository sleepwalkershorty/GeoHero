package de.nrg.geohero;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import database.Highscore;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Ich on 13.09.2016.
 */
public class HighscoreAdapter extends BaseAdapter
{
    private ArrayList<Highscore> data;
    private Context ctx;
    private LayoutInflater infl;

    public HighscoreAdapter(ArrayList<Highscore> hList, Context ctx)
    {
        this.data = hList;
        this.ctx = ctx;
        infl = ((Activity) ctx).getLayoutInflater();
    }

    @Override
    public Object getItem(int i)
    {
        if (data != null)
            return data.get(i);
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (infl != null)
        {
            view = infl.inflate(R.layout.layout_highscore, null);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvPoint = (TextView) view.findViewById(R.id.tvPoints);
            TextView tvErrors = (TextView) view.findViewById(R.id.tvErrors);
            TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
            TextView tvPlace = (TextView) view.findViewById(R.id.tvPlace);

            tvPlace.setText(String.valueOf(i+1) + ". ");
            tvName.setText(data.get(i).getName());
            tvPoint.setText(ctx.getString(R.string.points) + " " + String.valueOf(data.get(i).getPoints()));
            tvErrors.setText(ctx.getString(R.string.errors) + " " + String.valueOf(data.get(i).getErrors()));

            int seconds = data.get(i).getTime();
            int minutes = seconds / 60;
            if (minutes > 0)
                seconds %= 60;

            StringBuilder sb = new StringBuilder();
            if (minutes < 10)
                sb.append("0");
            sb.append(minutes);
            sb.append(":");
            if (seconds < 10)
                sb.append("0");
            sb.append(seconds);

            tvTime.setText(ctx.getString(R.string.time) + " " + sb.toString());

            return view;
        }

        return null;
    }

    @Override
    public int getCount()
    {
        if (data != null)
            return data.size();
        return 0;
    }
}
