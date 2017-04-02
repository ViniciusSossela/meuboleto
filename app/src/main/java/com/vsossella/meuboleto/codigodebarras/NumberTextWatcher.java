package com.vsossella.meuboleto.codigodebarras;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by vsossella on 02/04/17.
 */

public class NumberTextWatcher implements TextWatcher {

    private final WeakReference<EditText> editTextWeakReference;
    final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public NumberTextWatcher(EditText editText) {
        editTextWeakReference = new WeakReference<EditText>(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        EditText editText = editTextWeakReference.get();
        if (editText == null) return;
        String s = editable.toString();
        editText.removeTextChangedListener(this);
        String cleanString = s.toString().replaceAll("[$,.]", "");
        BigDecimal parsed = new BigDecimal(cleanString)
                .setScale(2, BigDecimal.ROUND_FLOOR)
                .divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);

        String decimalValue = decimalFormat.format(parsed);
        editText.setText(decimalValue);
        editText.setSelection(decimalValue.length());
        editText.addTextChangedListener(this);
    }
}