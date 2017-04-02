package com.vsossella.meuboleto.home;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.vsossella.meuboleto.codigodebarras.CodigoDeBarra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsossella on 01/04/17.
 */

public class HomeViewModel {

    public ObservableArrayList<CodigoDeBarra> pagamentos;
    public ObservableField<Boolean> existePagamentos = new ObservableField<>();

    public HomeViewModel() {
        pagamentos = new ObservableArrayList<>();
        existePagamentos.set(false);
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
            existePagamentos.set(true);
        } else existePagamentos.set(false);
    }


    public ObservableArrayList<CodigoDeBarra> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(ObservableArrayList<CodigoDeBarra> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
