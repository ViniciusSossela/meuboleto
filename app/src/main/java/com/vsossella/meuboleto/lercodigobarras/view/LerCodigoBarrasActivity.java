package com.vsossella.meuboleto.lercodigobarras.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.vsossella.meuboleto.R;
import com.vsossella.meuboleto.codigodebarras.InterpretadorCodigoBarras;
import com.vsossella.meuboleto.databinding.LerCodigoBarrasActivityBinding;
import com.vsossella.meuboleto.home.HomeActivity;
import com.vsossella.meuboleto.lercodigobarras.viewmodel.LerCodigoBarrasViewModel;
import com.vsossella.meuboleto.servico.ServicoBoleto;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by vsossella on 01/04/17.
 */

public class LerCodigoBarrasActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView mScannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LerCodigoBarrasActivityBinding binding =
                DataBindingUtil.setContentView(this, R.layout.ler_codigo_barras_activity);
        binding.setLerCodigoBarrasVM(new LerCodigoBarrasViewModel(this));

        mScannerView = new ZXingScannerView(this) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                return new BarCodeReaderView(context, getWindowManager().getDefaultDisplay());
            }

            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
            }
        };
        mScannerView.setFormats(getSupportedFormats());
        ((ViewGroup) findViewById(R.id.content_frame)).addView(mScannerView);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private List<BarcodeFormat> getSupportedFormats() {
        List<BarcodeFormat> formats = new ArrayList<>();
        formats.add(BarcodeFormat.ITF);
        formats.add(BarcodeFormat.CODABAR);
        formats.add(BarcodeFormat.CODE_128);
        return formats;
    }

    @Override
    public void handleResult(Result result) {
        if (!result.getText().isEmpty() && InterpretadorCodigoBarras.isValid(result.getText())) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            ServicoBoleto.salvarPagamento(InterpretadorCodigoBarras.decodeString44(result.getText()), this);
            startActivity(homeIntent);
        } else mScannerView.resumeCameraPreview(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

