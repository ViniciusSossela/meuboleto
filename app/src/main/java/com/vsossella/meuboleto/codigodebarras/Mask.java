package com.vsossella.meuboleto.codigodebarras;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by vsossella on 02/04/17.
 */

public abstract class Mask {

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public static TextWatcher insert(final String mask, final EditText editText) {

        return new TextWatcher() {
            boolean isUpdating;
            String old = "";
            String maskSplit = "and";
            Integer cpfLength = 11;

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = unmask(s.toString());

                String currentMask;
                String[] masks = mask.split(maskSplit);
                if (masks != null && masks.length > 1) {
                    if (str.length() <= cpfLength) {
                        currentMask = masks[MaskType.CPF.ordinal()];
                    } else
                        currentMask = masks[MaskType.CNPJ.ordinal()];
                } else
                    currentMask = mask;

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : currentMask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }


    public enum MaskType {
        CPF,
        CNPJ
    }
}
