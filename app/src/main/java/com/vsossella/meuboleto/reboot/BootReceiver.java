package com.vsossella.meuboleto.reboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.vsossella.meuboleto.servico.BootService;

/**
 * Created by vsossella on 15/05/17.
 */

public class BootReceiver extends BroadcastReceiver {

    private static final String BOOT_COMPLETED =
            "android.intent.action.BOOT_COMPLETED";
    private static final String QUICKBOOT_POWERON =
            "android.intent.action.QUICKBOOT_POWERON";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(BOOT_COMPLETED) ||
                action.equals(QUICKBOOT_POWERON)) {
            Intent service = new Intent(context, BootService.class);
            context.startService(service);
        }
    }

}