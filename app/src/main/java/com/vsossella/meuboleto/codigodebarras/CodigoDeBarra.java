package com.vsossella.meuboleto.codigodebarras;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by vsossella on 01/04/17.
 */
@Entity
public class CodigoDeBarra implements Serializable {

    @PrimaryKey
    String codigoDeBarras;

    @Ignore
    String campo1;
    @Ignore
    String campo2;
    @Ignore
    String campo3;
    @Ignore
    String campo4;
    @Ignore
    String campo5;

    @ColumnInfo(name = "banco")
    String banco;
    @ColumnInfo(name = "moeda")
    String moeda;
    @ColumnInfo(name = "data_vencimento")
    String dataDeVencimento;
    @ColumnInfo(name = "valor")
    String valor;
    @ColumnInfo(name = "pago")
    Boolean pago;

    @Ignore
    public CodigoDeBarra(String codigoDeBarras, String banco, String dataDeVencimento, String valor, Boolean pago) {
        this.codigoDeBarras = codigoDeBarras;
        this.banco = banco;
        this.dataDeVencimento = dataDeVencimento;
        this.valor = valor;
        this.pago = pago;
    }

    public CodigoDeBarra() {
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public String getCampo3() {
        return campo3;
    }

    public void setCampo3(String campo3) {
        this.campo3 = campo3;
    }

    public String getCampo4() {
        return campo4;
    }

    public void setCampo4(String campo4) {
        this.campo4 = campo4;
    }

    public String getCampo5() {
        return campo5;
    }

    public void setCampo5(String campo5) {
        this.campo5 = campo5;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String value) {
        this.codigoDeBarras = value;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getDataDeVencimento() {
        return dataDeVencimento;
    }

    public String getDataDeVencimentoFormatada() {
        return InterpretadorCodigoBarras.parseToDate(dataDeVencimento);
    }

    public void setDataDeVencimento(String dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        valor = String.valueOf( Double.parseDouble(valor)/100 );
        this.valor = valor;
    }

    public String toString() {
        return getBanco() + "," + getCodigoDeBarras() + "," + getDataDeVencimento()
                + "," + getValor() + "," + getPago() + ";";
    }

}
