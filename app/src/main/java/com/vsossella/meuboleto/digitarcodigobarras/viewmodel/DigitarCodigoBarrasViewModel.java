package com.vsossella.meuboleto.digitarcodigobarras.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ObservableField;

import com.vsossella.meuboleto.codigodebarras.CodigoDeBarra;
import com.vsossella.meuboleto.codigodebarras.InterpretadorCodigoBarras;
import com.vsossella.meuboleto.home.HomeActivity;

/**
 * Created by vsossella on 01/04/17.
 */

public class DigitarCodigoBarrasViewModel {

    ObservableField<String> codigoDeBarras;
    Context context;
    final String PREFS_NAME = "MyPrefsFile";

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
        salvarPagamento(InterpretadorCodigoBarras.decodeStringFromInputLine(getCodigoDeBarras().get()));
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    private void salvarPagamento(CodigoDeBarra codigoDeBarra) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("pagamentos", buscarPagamentos() + codigoDeBarra.toString());

        // Commit the edits!
        editor.commit();
    }

    private String buscarPagamentos() {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString("pagamentos", "");
    }
}
