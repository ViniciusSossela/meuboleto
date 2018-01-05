package com.vsossella.meuboleto.servico;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;

import com.vsossella.meuboleto.codigodebarras.CodigoDeBarra;
import com.vsossella.meuboleto.codigodebarras.InterpretadorCodigoBarras;
import com.vsossella.meuboleto.notificacao.Notificacao;

import org.reactivestreams.Subscription;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vsossella on 02/04/17.
 */

public class ServicoBoleto {
    final static String PREFS_NAME = "MyPrefsFile";


    public static void salvarPagamento(CodigoDeBarra codigoDeBarra, Context context) {

        scheduleNotification(codigoDeBarra, context);



        Single.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                AppDatabaseSingleton.instanceDb(context).ServicoBoletoDao().insert(codigoDeBarra);
                return null;
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();



//        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.putString("pagamentos", buscarPagamentos(context) + codigoDeBarra.toString());
//        editor.commit();
    }

    public static void scheduleNotification(CodigoDeBarra codigoDeBarra, Context context) {
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

//    public static void paidOut(Context context, String codigoDeBarrasPaid) {
//        String codigos = buscarPagamentos(context);
//
//        if (codigos != null && !codigos.isEmpty()) {
//            String[] codigosDeBarrasString = codigos.split(";");
//
//            for (String codigoDeBarra :
//                    codigosDeBarrasString) {
//
//
//                if(codigoDeBarra.contains(codigoDeBarrasPaid)) {
//                    String[] values = codigoDeBarra.split(",");
//                    new CodigoDeBarra(values[1], values[0], values[2], values[3], true);
//                }
//
//                //String[] values = codigoDeBarra.split(",");
//
//
//            }
//        }
//
//    }

//    public static String buscarPagamentos(Context context) {
//        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
//        return settings.getString("pagamentos", "");
//    }

    public static Flowable<List<CodigoDeBarra>> getBoletos(final Context context) {
        return AppDatabaseSingleton.instanceDb(context).ServicoBoletoDao().getAll();
    }

    public static void scheduleAgainBoletos(final Context context) {
//        List<CodigoDeBarra> boletos = getBoletos(context);

        getBoletos(context).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<List<CodigoDeBarra>>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(List<CodigoDeBarra> codigoDeBarras) {
                        for (CodigoDeBarra boleto:
                                codigoDeBarras) {
                            scheduleNotification(boleto, context);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }


//    public static void scheduleAgainPagamentos(Context context) {
//        String codigos = buscarPagamentos(context);
//
//        if (codigos != null && !codigos.isEmpty()) {
//            String[] codigosDeBarrasString = codigos.split(";");
//
//            for (String codigoDeBarra :
//                    codigosDeBarrasString) {
//                String[] values = codigoDeBarra.split(",");
//
//                scheduleNotification(new CodigoDeBarra(values[1], values[0], values[2], values[3], false), context);
//            }
//        }
//    }


}
