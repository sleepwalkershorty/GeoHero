package de.nrg.geohero;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import database.Highscore;

import static de.nrg.geohero.MyApplication.playSound;

public class ActivityMain extends Activity
{
    private static Bitmap bmpMask, bmpOrigin, bmpOriginBack, bmpMaskBack;
    private static int[] pixelsMask, pixelsBack, pixelsMaskBack;
    private static TextView tvCommand, tvStatus, tvError, tvTime;
    private static int randomCountry;
    private static int errors = 0;
    private static ArrayList<Integer> found;
    private static boolean isOnClick = false;
    private static long lastClickTime;
    private static final long DOUBLE_PRESS_INTERVAL = 250; // in millis
    private static float downX, downY;
    private final static float SCROLL_THRESHOLD = 10;
    private static long startTime, endTime;
    private static Thread timeThread, actionThread;
    private static long currentActionTime;
    private static int currentProgress = 1000;

    private static TouchImageView image1;
    private static TouchImageView imgMask;
    private static TouchImageView imgSatellite;
    private static ImageView imgBack;
    private static ImageView imgMaskBack;
    private static TextView tvLoading;
    private static ProgressBar pbLoading, pbAction;
    private static FrameLayout overlay;

    private static ArrayList<Country> countries = new ArrayList<Country>(1);

    private static InterstitialAd mInterstitialAd;

    //Sounds
    private static MediaPlayer player;
    public static ArrayList<Integer> soundIDs = new ArrayList<Integer>(1);

    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        ctx = this;

        lastClickTime = System.currentTimeMillis();

        this.getActionBar().setDisplayHomeAsUpEnabled(true);

        countries.clear();

        for (int i = 0; i < MyApplication.countries.size(); i++)
        {
            if (MyApplication.countries.get(i).getContinent() == MyApplication.currentGame - 1)
                countries.add(MyApplication.countries.get(i));
        }

        if (MyApplication.isTablet(ctx))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pixelsMask = null;
        pixelsBack = null;
        bmpMask = null;
        bmpOrigin = null;

        ArrayList<Integer> backSounds = new ArrayList<Integer>(1);
        backSounds.add(R.raw.back6);

        player = MediaPlayer.create(this, backSounds.get(new Random().nextInt(backSounds.size())));
        player.setLooping(true); // Set looping
        player.setVolume(90, 90);

        if (savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
                handler.sendEmptyMessage(4);
            }
        }).start();
    }

    private static InterstitialAd newInterstitialAd()
    {
        InterstitialAd interstitialAd = new InterstitialAd(ctx);
        interstitialAd.setAdUnitId(ctx.getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdLoaded()
            {
                showInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode)
            {
                ActivityMain.handler.sendEmptyMessage(0);
            }

            @Override
            public void onAdClosed()
            {
                ActivityMain.handler.sendEmptyMessage(0);
            }
        });
        return interstitialAd;
    }

    private static void showInterstitial()
    {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        }
        else
        {
            //Toast.makeText(ctx, "Ad did not load", Toast.LENGTH_SHORT).show();
            //goToNextLevel();
        }
    }

    private static void loadInterstitial()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    private static void startGame()
    {

        pbLoading.setVisibility(View.GONE);
        tvLoading.setVisibility(View.GONE);
        overlay.setVisibility(View.GONE);

        player.start();
        image1.setVisibility(View.VISIBLE);
        startTime = System.currentTimeMillis();

        found = new ArrayList<Integer>(1);

        int x = new Random().nextInt(countries.size());
        while (found.contains(x))
            x = new Random().nextInt(countries.size());
        randomCountry = x;
        found.add(x);

        tvStatus.setText("0/" + countries.size());

        if (Locale.getDefault().getLanguage().equals("de"))
            tvCommand.setText(ctx.getString(R.string.command_find)
                    + " " + countries.get(randomCountry).name);
        else
            tvCommand.setText(ctx.getString(R.string.command_find)
                    + " " + countries.get(randomCountry).name_eng);

        tvCommand.setVisibility(View.VISIBLE);

        tvError.setText(ctx.getString(R.string.errors) + " 0");
        errors = 0;

        tvTime.setText("00:00");

        image1.setZoom(1f);
        imgMask.setZoom(image1);
        imgSatellite.setZoom(image1);

//        image1.setZoom(4);
//        imgMask.setZoom(image1);

        switch (MyApplication.currentGame)
        {
            case 1:
                image1.setImageResource(R.drawable.europe);
                imgBack.setImageResource(R.drawable.europe);
                imgMask.setImageResource(R.drawable.europe_mask);
                imgMaskBack.setImageResource(R.drawable.europe_mask);
                break;
            case 2:
                image1.setImageResource(R.drawable.africa_new);
                imgBack.setImageResource(R.drawable.africa_new);
                imgMask.setImageResource(R.drawable.africa_new_mask);
                imgMaskBack.setImageResource(R.drawable.africa_new_mask);
                imgSatellite.setImageResource(R.drawable.africa_sat_new);
                break;
        }

        timeThread = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                        break;
                    }

                    int seconds = (int) ((System.currentTimeMillis() - startTime) / 1000.);
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

                    Message m = new Message();
                    m.what = 1;
                    m.obj = sb.toString();
                    handler.sendMessage(m);
                }
            }
        };

        timeThread.start();

        currentActionTime = System.currentTimeMillis();

        actionThread = new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(10);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                        break;
                    }

                    currentProgress = (int) ((System.currentTimeMillis() - currentActionTime) / 10.);
                    currentProgress = 1000 - currentProgress;

                    if (currentProgress <= 0)
                    {
                        currentProgress = 1000;
                        currentActionTime = System.currentTimeMillis();
                    }

                    Message m = new Message();
                    m.what = 5;
                    handler.sendMessage(m);
                }
            }
        };

        actionThread.start();
    }

    public static class PlaceholderFragment extends Fragment
    {

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            final View rootView = inflater.inflate(R.layout.layout_frag_main,
                    container, false);

            tvCommand = (TextView) rootView.findViewById(R.id.tv_command);
            tvStatus = (TextView) rootView.findViewById(R.id.tv_status);
            tvError = (TextView) rootView.findViewById(R.id.tv_errors);
            tvTime = (TextView) rootView.findViewById(R.id.tv_time);

            image1 = (TouchImageView) rootView.findViewById(R.id.imageView1);
            imgMask = (TouchImageView) rootView.findViewById(R.id.imageView2);
            imgBack = (ImageView) rootView.findViewById(R.id.imageView3);
            imgMaskBack = (ImageView) rootView.findViewById(R.id.imageView4);
            imgSatellite = (TouchImageView) rootView.findViewById(R.id.imageView5);

            pbLoading = (ProgressBar) rootView.findViewById(R.id.pbLoading);
            pbAction = (ProgressBar) rootView.findViewById(R.id.pbAction);
            tvLoading = (TextView) rootView.findViewById(R.id.tvLoading);
            overlay = (FrameLayout) rootView.findViewById(R.id.overLay);

            image1.setMaxZoom(10);
            imgMask.setMaxZoom(10);
            imgSatellite.setMaxZoom(10);

            image1.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event)
                {
                    imgSatellite.setZoom(image1);
                    return false;
                }
            });

            image1.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener()
            {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e)
                {
//                    Toast.makeText(getActivity(),"SingleTap",Toast.LENGTH_SHORT).show();
                    //normal tap
                    //check the click
                    imgMask.setZoom(image1);
                    imgSatellite.setZoom(image1);
                    int x = (int) e.getX();
                    int y = (int) e.getY();

                    imgMask.setDrawingCacheEnabled(true);
                    bmpMask = Bitmap.createBitmap(imgMask.getDrawingCache());
                    imgMask.setDrawingCacheEnabled(false);

                    pixelsMask = new int[bmpMask.getWidth() * bmpMask.getHeight()];
                    bmpMask.getPixels(pixelsMask, 0, bmpMask.getWidth(), 0, 0, bmpMask.getWidth(), bmpMask.getHeight());

                    image1.setDrawingCacheEnabled(true);
                    bmpOrigin = Bitmap.createBitmap(image1.getDrawingCache());
                    image1.setDrawingCacheEnabled(false);

                    int[] pixels = new int[bmpOrigin.getWidth() * bmpOrigin.getHeight()];
                    bmpOrigin.getPixels(pixels, 0, bmpOrigin.getWidth(), 0, 0, bmpOrigin.getWidth(), bmpOrigin.getHeight());

                    int color = bmpMask.getPixel(x, y);

//                                  int color2 = bmpOrigin.getPixel(x, y);
//                                  Toast.makeText(getActivity(), "Color=" + color, Toast.LENGTH_SHORT).show();

                    if (randomCountry >= 0)
                    {
                        if (countries.get(randomCountry).color == color)
                        {
                            playSound(soundIDs.get(0), 0.67f);
                            //gefunden

                            //Take bmp from original Back
                            imgBack.setDrawingCacheEnabled(true);
                            bmpOriginBack = Bitmap.createBitmap(imgBack.getDrawingCache());
                            imgBack.setDrawingCacheEnabled(false);
                            pixelsBack = new int[bmpOriginBack.getWidth() * bmpOriginBack.getHeight()];
                            bmpOriginBack.getPixels(pixelsBack, 0, bmpOriginBack.getWidth(), 0, 0, bmpOriginBack.getWidth(), bmpOriginBack.getHeight());

                            //Take bmp from mask Back
                            imgMaskBack.setDrawingCacheEnabled(true);
                            bmpMaskBack = Bitmap.createBitmap(imgMaskBack.getDrawingCache());
                            imgMaskBack.setDrawingCacheEnabled(false);
                            pixelsMaskBack = new int[bmpMaskBack.getWidth() * bmpMaskBack.getHeight()];
                            bmpMaskBack.getPixels(pixelsMaskBack, 0, bmpMaskBack.getWidth(), 0, 0, bmpMaskBack.getWidth(), bmpMaskBack.getHeight());

                            //just do drawing on bmp original back
                            if (pixelsMaskBack.length != pixelsBack.length)
                            {
                                //fix bug
                            }

                            for (int i = 0; i < pixelsMaskBack.length; i++)
                            {
                                if (i < pixelsBack.length && i < pixelsMaskBack.length
                                        && pixelsMaskBack[i] == color && (pixelsBack[i] == -4144960)) // || pixelsBack[i] == -4605511))
                                    pixelsBack[i] = color;
//                                    pixelsBack[i] = Color.TRANSPARENT;
                            }

                            imgMask.setImageBitmap(bmpMaskBack);
                            bmpOriginBack.setPixels(pixelsBack, 0, bmpOriginBack.getWidth(), 0, 0, bmpOriginBack.getWidth(), bmpOriginBack.getWidth());
                            imgBack.setImageBitmap(bmpOriginBack);
                            image1.setImageBitmap(bmpOriginBack);

                            if (found.size() < countries.size())
                            {
                                tvStatus.setText(found.size() + "/" + countries.size());
                                int c = new Random().nextInt(countries.size());
                                while (found.contains(c))
                                    c = new Random().nextInt(countries.size());
                                randomCountry = c;
                                found.add(c);

                                image1.savePreviousImageValues();
                                if (Locale.getDefault().getLanguage().equals("de"))
                                    tvCommand.setText(ctx.getString(R.string.command_find)
                                            + " " + countries.get(randomCountry).name);
                                else
                                    tvCommand.setText(ctx.getString(R.string.command_find)
                                            + " " + countries.get(randomCountry).name_eng);
                            }
                            else
                            {
                                tvStatus.setText(found.size() + "/" + countries.size());
                                Toast.makeText(getActivity(), R.string.toast_superb, Toast.LENGTH_SHORT).show();
                                tvCommand.setVisibility(View.GONE);
                                randomCountry = -1;

                                if (timeThread != null)
                                {
                                    timeThread.interrupt();
                                    timeThread = null;
                                }

                                if (actionThread != null)
                                {
                                    actionThread.interrupt();
                                    actionThread = null;
                                }

                                endTime = System.currentTimeMillis();
                                int seconds = (int) ((endTime - startTime) / 1000.);

                                Object[] params = new Object[2];
                                params[0] = seconds;
                                params[1] = errors;

                                int points = MyApplication.calcPoints(seconds, errors);
                                ArrayList<Highscore> list = MyApplication.getDatasource()
                                        .getTop20(MyApplication.currentGame);
                                MyDialogFragment dialog2 = null;
                                if (list.size() < 20)
                                    dialog2 = MyDialogFragment.getInstance(getActivity(), 1, params);
                                else
                                {
                                    if (list.get(list.size() - 1).getPoints() < points)
                                        dialog2 = MyDialogFragment.getInstance(getActivity(), 1, params);
                                    else
                                        dialog2 = MyDialogFragment.getInstance(getActivity(), 2, params);
                                }
                                dialog2.setCancelable(false);
                                dialog2.show(getFragmentManager(), "myNameFragment");
                            }
                        }
                        else
                        {
                            MyApplication.playSound(soundIDs.get(1), 0.70f);

                            errors++;

                            image1.savePreviousImageValues();
                            tvError.setText(ctx.getString(R.string.errors) + " " + errors);
                        }
                    }
                    return false;
                }

                @Override
                public boolean onDoubleTap(MotionEvent e)
                {
//                    Toast.makeText(getActivity(),"DoubleTap",Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onDoubleTapEvent(MotionEvent e)
                {
//                    Toast.makeText(getActivity(),"DoubleTap Event",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            return rootView;
        }
    }

    public static Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    startGame();
                    break;
                case 1:
                    image1.savePreviousImageValues();
                    tvTime.setText(msg.obj.toString());
                    break;
                case 2:
                    loadInterstitial();
                    break;
                case 3:
                    image1.setVisibility(View.INVISIBLE);
                    pbLoading.setVisibility(View.VISIBLE);
                    tvLoading.setVisibility(View.VISIBLE);
                    overlay.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    mInterstitialAd = newInterstitialAd();
                    loadInterstitial();
                    break;
                case 5:
                    if (pbAction != null)
                    {
                        pbAction.setProgress(currentProgress);
                    }
                    break;
            }
        }
    };

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (timeThread != null)
        {
            timeThread.interrupt();
            timeThread = null;
        }

        if (actionThread != null)
        {
            actionThread.interrupt();
            actionThread = null;
        }

        try
        {
            player.stop();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

//        if (imgBack != null && imgMaskBack != null)
//        {
//            imgBack.setImageResource(R.drawable.europe);
//            for (int i = 0; i < found.size() - 1; i++)
//            {
//                //jedes Land wieder einzeichnen
//                for (int j = 0; j < countries.size(); j++)
//                {
//                    if (countries.get(j).id == found.get(i))
//                    {
//                        //Land gefunden
//                        //Take bmp from original Back
//                        imgBack.setDrawingCacheEnabled(true);
//                        bmpOriginBack = Bitmap.createBitmap(imgBack.getDrawingCache());
//                        imgBack.setDrawingCacheEnabled(false);
//                        pixelsBack = new int[bmpOriginBack.getWidth() * bmpOriginBack.getHeight()];
//                        bmpOriginBack.getPixels(pixelsBack, 0, bmpOriginBack.getWidth(), 0, 0, bmpOriginBack.getWidth(), bmpOriginBack.getHeight());
//
//                        //Take bmp from mask Back
//                        imgMaskBack.setDrawingCacheEnabled(true);
//                        bmpMaskBack = Bitmap.createBitmap(imgMaskBack.getDrawingCache());
//                        imgMaskBack.setDrawingCacheEnabled(false);
//                        pixelsMaskBack = new int[bmpMaskBack.getWidth() * bmpMaskBack.getHeight()];
//                        bmpMaskBack.getPixels(pixelsMaskBack, 0, bmpMaskBack.getWidth(), 0, 0, bmpMaskBack.getWidth(), bmpMaskBack.getHeight());
//
//                        //just do drawing on bmp original back
//                        for (int x = 0; x < pixelsMaskBack.length; x++)
//                        {
//                            if (pixelsMaskBack[x] == countries.get(j).color && pixelsBack[x] == -4144960)
//                                pixelsBack[x] = countries.get(j).color;
//                        }
////
////						imgMask.setImageBitmap(bmpMaskBack);
//                        bmpOriginBack.setPixels(pixelsBack, 0, bmpOriginBack.getWidth(), 0, 0, bmpOriginBack.getWidth(), bmpOriginBack.getWidth());
//                        imgBack.setImageBitmap(bmpOriginBack);
////						image1.setImageBitmap(bmpOriginBack);
//
//                        break;
//                    }
//                }
//            }
//        }
    }

}
