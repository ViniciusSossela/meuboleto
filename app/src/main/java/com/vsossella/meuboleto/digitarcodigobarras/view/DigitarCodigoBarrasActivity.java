package com.vsossella.meuboleto.digitarcodigobarras.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vsossella.meuboleto.R;
import com.vsossella.meuboleto.databinding.DigitarCodigoBarrasActivityBinding;
import com.vsossella.meuboleto.digitarcodigobarras.viewmodel.DigitarCodigoBarrasViewModel;

/**
 * Created by vsossella on 01/04/17.
 */

public class DigitarCodigoBarrasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DigitarCodigoBarrasActivityBinding binding =
                DataBindingUtil.setContentView(this, R.layout.digitar_codigo_barras_activity);

        binding.setDigitarCodigoBarrasVM(new DigitarCodigoBarrasViewModel(this));
    }
}
