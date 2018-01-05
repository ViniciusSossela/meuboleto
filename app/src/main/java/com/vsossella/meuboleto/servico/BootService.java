package com.vsossella.meuboleto.servico;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by vsossella on 15/05/17.
 */

public class BootService extends IntentService {

    public BootService() {
        super("BootService");
    }

    private void setAlarmsFromDatabase() {
        ServicoBoleto.scheduleAgainBoletos(getApplicationContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        setAlarmsFromDatabase();
        Intent service = new Intent(this, BootService.class);
        stopService(service);
    }

}
