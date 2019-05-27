package com.pirtail.piratilgame.Class;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import com.pirtail.piratilgame.R;

/**
 * Created by piratil on 5/8/2019.
 */

public class customSnackbar {
    String text;
    View parent;
    public static final int danger = 1;
    public static final int info = 2;
    public static final int succ = 3;
    public static final int alert = 4;
    Snackbar show;

    Snackbar snackbar;
    Context Context;
    public customSnackbar(String text,View Parent,Context context,int model) {

        Snackbar.make(parent, "Message", Snackbar.LENGTH_LONG).show();

        this.text = text;
        this.parent = parent;
        this.Context = context;


        final View snackBarView = snackbar.getView();

        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);

        switch (model) {
            case danger:
                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.backDanger));
                snackbar.setActionTextColor(context.getResources().getColor(R.color.textDanger));
                textView.setTextColor(context.getResources().getColor(R.color.textDanger));

                break;
            case info:

                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.backInfo));
                snackbar.setActionTextColor(context.getResources().getColor(R.color.textInfo));
                textView.setTextColor(context.getResources().getColor(R.color.textInfo));

                break;
            case succ:

                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.backSucc));
                snackbar.setActionTextColor(context.getResources().getColor(R.color.textSucc));
                textView.setTextColor(context.getResources().getColor(R.color.textSucc));

                break;
            case alert:

                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.backAlert));
                snackbar.setActionTextColor(context.getResources().getColor(R.color.textAlert));
                textView.setTextColor(context.getResources().getColor(R.color.textAlert));

                break;

        }

    }
}
