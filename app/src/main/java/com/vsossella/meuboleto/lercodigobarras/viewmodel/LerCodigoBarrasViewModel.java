package com.vsossella.meuboleto.lercodigobarras.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.vsossella.meuboleto.digitarcodigobarras.view.DigitarCodigoBarrasActivity;

/**
 * Created by vsossella on 01/04/17.
 */

public class LerCodigoBarrasViewModel {

    Context context;

    public LerCodigoBarrasViewModel(Context context) {
        this.context = context;
    }

    public void digitarCodigoDeBarras() {
        context.startActivity(new Intent(context, DigitarCodigoBarrasActivity.class));
    }

}
