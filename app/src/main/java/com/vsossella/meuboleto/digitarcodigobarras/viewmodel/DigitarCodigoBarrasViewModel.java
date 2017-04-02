package com.vsossella.meuboleto.digitarcodigobarras.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;

import com.vsossella.meuboleto.codigodebarras.InterpretadorCodigoBarras;
import com.vsossella.meuboleto.home.HomeActivity;
import com.vsossella.meuboleto.servico.ServicoBoleto;

/**
 * Created by vsossella on 01/04/17.
 */

public class DigitarCodigoBarrasViewModel {

    ObservableField<String> codigoDeBarras = new ObservableField<>();
    Context context;

    public DigitarCodigoBarrasViewModel(Context context) {
        this.context = context;
    }

    public ObservableField<String> getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(ObservableField<String> codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public void adicionarPagamentoTouched() {
        ServicoBoleto.salvarPagamento(InterpretadorCodigoBarras.decodeStringFromInputLine(getCodigoDeBarras().get()), context);
        context.startActivity(new Intent(context, HomeActivity.class));
    }
}
