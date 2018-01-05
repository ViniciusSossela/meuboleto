package com.vsossella.meuboleto.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by vsossella on 26/05/17.
 */

public class DialogUtil {

    public static void openDialog(Context context,
                                  String message,
                                  String positive,
                                  String negative,
                                  DialogInterface.OnClickListener positiveListener,
                                  DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("O que você deseja fazer?");
        builder.setMessage(message);
        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, negativeListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
