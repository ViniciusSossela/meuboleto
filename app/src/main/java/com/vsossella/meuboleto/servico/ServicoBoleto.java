package com.vsossella.meuboleto.servico;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;

import com.vsossella.meuboleto.codigodebarras.CodigoDeBarra;
import com.vsossella.meuboleto.codigodebarras.InterpretadorCodigoBarras;
import com.vsossella.meuboleto.notificacao.Notificacao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vsossella on 02/04/17.
 */

public class ServicoBoleto {
    final static String PREFS_NAME = "MyPrefsFile";


    public static void salvarPagamento(CodigoDeBarra codigoDeBarra, Context context) {

        scheduleNotification(codigoDeBarra, context);

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("pagamentos", buscarPagamentos(context) + codigoDeBarra.toString());
        editor.commit();
    }

    private static void scheduleNotification(CodigoDeBarra codigoDeBarra, Context context) {
        String dataDeVencimento = InterpretadorCodigoBarras.parseToDate(codigoDeBarra.getDataDeVencimento());
        Date now = new Date();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        try {
            Date dataDeVencimentoFormatada = formatter.parse(dataDeVencimento);

            if (dataDeVencimentoFormatada.after(now)) {
                long miliSeconds = (dataDeVencimentoFormatada.getTime() - now.getTime());
                Notificacao.scheduleNotification(Notificacao.getNotification("Hoje vence um boleto de: R$ " + codigoDeBarra.getValor()
                        , context), miliSeconds, context);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String buscarPagamentos(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString("pagamentos", "");
    }

    public static void scheduleAgainPagamentos(Context context) {
        String codigos = buscarPagamentos(context);

        if (codigos != null && !codigos.isEmpty()) {
            String[] codigosDeBarrasString = codigos.split(";");

            for (String codigoDeBarra :
                    codigosDeBarrasString) {
                String[] values = codigoDeBarra.split(",");

                scheduleNotification(new CodigoDeBarra(values[1], values[0], values[2], values[3]), context);
            }
        }
    }


}
