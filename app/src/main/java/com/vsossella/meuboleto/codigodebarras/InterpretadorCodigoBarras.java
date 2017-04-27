package com.vsossella.meuboleto.codigodebarras;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vsossella on 01/04/17.
 */

public class InterpretadorCodigoBarras {


    public static final int _47 = 47;
    public static final int _44 = 44;

    public static CodigoDeBarra decodeString44(String value) {
        CodigoDeBarra ret = new CodigoDeBarra();
        if (value != null) {
            String codigo = value.replace("^[0-9].", "");

            if (codigo.length() == _44) {
                String campo1 = codigo.substring(0, 4) + codigo.substring(19, 20) + codigo.substring(20, 24);
                String campo2 = codigo.substring(24, 29) + codigo.substring(29, 34);
                String campo3 = codigo.substring(34, 39) + codigo.substring(39, 44);
                String campo4 = codigo.substring(4, 5); // Digito verificador
                String campo5 = codigo.substring(5, 19); // Vencimento + Valor

                String codigoOrdenado = campo1 + modulo10(campo1) +
                        campo2 + modulo10(campo2) +
                        campo3 + modulo10(campo3) +
                        campo4 +
                        campo5;

                ret.setCodigoDeBarras(codigoOrdenado);
                ret.setBanco((codigo.substring(0, 3)));
                ret.setMoeda((codigo.substring(3, 4)));
                ret.setDataDeVencimento((codigo.substring(5, 9)));
                ret.setValor((codigo.substring(9, 19)));
            }
        }
        return ret;
    }

    public static CodigoDeBarra decodeStringFromInputLine(String value) {
        CodigoDeBarra ret = new CodigoDeBarra();
        if (value != null) {
            String codigo = value.replace("^[0-9].", "").replace(" ", "").replace(".", "");
            if (codigo.length() >= _47) {
                ret.setCodigoDeBarras(codigo);
                ret.setCampo1(codigo.substring(0, 10));
                ret.setCampo2(codigo.substring(10, 21));
                ret.setCampo3(codigo.substring(21, 32));
                ret.setCampo4(codigo.substring(32, 33));
                ret.setCampo5(codigo.substring(33, codigo.length()));

                ret.setBanco((codigo.substring(0, 3)));
                ret.setMoeda((codigo.substring(3, 4)));
                ret.setDataDeVencimento((codigo.substring(33, 37)));
                ret.setValor((codigo.substring(37, codigo.length())));
            }
        }
        return ret;
    }

    public static String unmask(String s){
        return s.replaceAll("[^0-9]*", "");
    }

    public static String modulo10(String numero) {
        numero = unmask(numero);
        int soma = 0;
        int peso = 2;
        int contador = numero.length() - 1;
        while (contador >= 0) {
            int multiplicacao = Integer.parseInt(numero.substring(contador, contador + 1)) * peso;
            if (multiplicacao >= 10) multiplicacao = 1 + (multiplicacao - 10);

            soma = soma + multiplicacao;
            if (peso == 2) peso = 1;
            else peso = 2;

            contador = contador - 1;
        }
        int digito = 10 - (soma % 10);
        if (digito == 10) digito = 0;
        return String.valueOf(digito);
    }

    public static boolean isValid( String barcode ) {
        if(barcode != null)
            return barcode.length() == _44 || barcode.length() == _47;
        return false;
    }

    public static double parseToDouble(String value) {
        try { return Double.parseDouble(value); }
        catch (Exception e) {}
        return 0.00;
    }

    public static String parseToDate(String value) {
        Calendar c = Calendar.getInstance();
        c.set(1997,9,7,8,0,0);
        try {
            int days = Integer.parseInt(value);
            c.add(Calendar.DATE, days);
        }
        catch (Exception e ) { }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String output = sdf.format(c.getTime());
        return output;
    }

    public static String parseToCash( String value) {
        double temp = parseToDouble(value);
        String pattern = "###,###.00";
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator(',');
        ds.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat(pattern,ds);
        df.setGroupingUsed(true);
        return df.format(temp);
    }

}
