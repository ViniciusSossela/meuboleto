package com.vsossella.meuboleto.home;

import android.databinding.ObservableArrayList;

import com.vsossella.meuboleto.codigodebarras.CodigoDeBarra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsossella on 01/04/17.
 */

public class HomeViewModel {

    public ObservableArrayList<CodigoDeBarra> pagamentos;

    public HomeViewModel() {
        pagamentos = new ObservableArrayList<>();
//
//        CodigoDeBarra c1 = new CodigoDeBarra();
//        c1.setValor("1");
//        pagamentos.add(c1);
//
//        CodigoDeBarra c2 = new CodigoDeBarra();
//        c2.setValor("2");
//        pagamentos.add(c2);
//
//        CodigoDeBarra c3 = new CodigoDeBarra();
//        c3.setValor("3");
//        pagamentos.add(c3);

    }


    public void carregarPagamentosFromString(String codigos) {
        if (codigos != null && !codigos.isEmpty()) {
            String[] codigosDeBarrasString = codigos.split(";");

            List<CodigoDeBarra> codigosDeBarra = new ArrayList<>();

            for (String codigoDeBarra :
                    codigosDeBarrasString) {
                String[] values = codigoDeBarra.split(",");

                codigosDeBarra.add(new CodigoDeBarra(values[1], values[0], values[2], values[3]));
            }
            pagamentos.addAll(codigosDeBarra);
        }
    }


    public ObservableArrayList<CodigoDeBarra> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(ObservableArrayList<CodigoDeBarra> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
