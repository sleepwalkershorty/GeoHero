package de.nrg.geohero;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyDialogFragment extends DialogFragment
{
    private static Context context;
    private static int which = -1;
    private static String[] inputArray = null;
    private static Object[] params;

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putStringArray("inputArray", inputArray);
    }

    public static MyDialogFragment getInstance(Context ctx, int whichInt, Object[] paramsX)
    {
        context = ctx;
        which = whichInt;
        params = paramsX;
        MyDialogFragment frag = new MyDialogFragment();
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new Builder(context);

        switch (which)
        {
            case 0:
                //Take picture or galery?
                LayoutInflater infl = ((Activity) context).getLayoutInflater();
                View content = infl.inflate(R.layout.layout_finish_dialog, null);

                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle(R.string.game_finished);
                builder.setView(content);

                int seconds = (Integer) params[0];
                int errors = (Integer) params[1];
                TextView tvTime = (TextView) content.findViewById(R.id.tvTime);
                TextView tvErrors = (TextView) content.findViewById(R.id.tvErrors);

                tvErrors.setText(context.getString(R.string.errors) + " " + errors);

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
                tvTime.setText(context.getString(R.string.time) + " " + sb.toString());

                builder.setPositiveButton(R.string.new_game, new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ActivityMain.handler.sendEmptyMessage(3);
                        ActivityMain.handler.sendEmptyMessage(2);
                    }
                });
                builder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ((Activity) context).finish();
                    }
                });

                Button bHighscores = (Button) content.findViewById(R.id.bHighscores);
                bHighscores.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        getActivity().startActivity(new Intent(getActivity(), ActivityHighscores.class));
                    }
                });
                break;
            case 1:
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle(R.string.dialog_name_title);
                infl = ((Activity) context).getLayoutInflater();
                content = infl.inflate(R.layout.layout_name_dialog, null);
                builder.setView(content);
                builder.setCancelable(false);

                builder.setPositiveButton(R.string.dialog_name_ok, new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
                builder.setNegativeButton(R.string.dialog_name_cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        MyDialogFragment dialog = MyDialogFragment.getInstance(getActivity(), 0, params);
                        dialog.setCancelable(false);
                        dialog.show(getFragmentManager(), "myFinishFragment");
                    }
                });
                break;
            case 2:
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle(R.string.dialog_name_title2);
                builder.setMessage(R.string.dialog_name_msg2);
                builder.setCancelable(false);

                builder.setPositiveButton(R.string.dialog_name_ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        MyDialogFragment dialog = MyDialogFragment.getInstance(getActivity(), 0, params);
                        dialog.setCancelable(false);
                        dialog.show(getFragmentManager(), "myFinishFragment");
                    }
                });
                break;
        }

        final AlertDialog dialogX = builder.create();

        //For additional button changes
        if (which == 1)
        {
            dialogX.setOnShowListener(new DialogInterface.OnShowListener()
            {

                @Override
                public void onShow(DialogInterface dialog)
                {
                    dialogX.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                    {

                        @Override
                        public void onClick(View v)
                        {
                            EditText edInput = (EditText) dialogX.findViewById(R.id.edInput);
                            if (edInput != null)
                            {
                                if (edInput.getText().length() < 2)
                                    Toast.makeText(getActivity(), R.string.dialog_name_toast1, Toast.LENGTH_LONG).show();
                                else
                                {
                                    Toast.makeText(getActivity(), R.string.dialog_name_toast2, Toast.LENGTH_LONG).show();

                                    int points = MyApplication.calcPoints((Integer) params[0],(Integer) params[1]);

                                    MyApplication.getDatasource().createHighscore(
                                            edInput.getText().toString(),(Integer) params[0],(Integer) params[1],
                                            points,System.currentTimeMillis(), MyApplication.currentGame);

                                    MyDialogFragment dialog = MyDialogFragment.getInstance(getActivity(), 0, params);
                                    dialog.setCancelable(false);
                                    dialog.show(getFragmentManager(), "myFinishFragment");
                                    dialogX.dismiss();

                                    getActivity().startActivity(new Intent(getActivity(), ActivityHighscores.class));
                                }
                            }
                        }
                    });
                }
            });
        }
        return dialogX;
    }
}
