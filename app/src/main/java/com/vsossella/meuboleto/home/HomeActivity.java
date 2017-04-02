package com.vsossella.meuboleto.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.vsossella.meuboleto.R;
import com.vsossella.meuboleto.codigodebarras.CodigoDeBarra;
import com.vsossella.meuboleto.codigodebarras.CodigoDeBarraAdapter;
import com.vsossella.meuboleto.databinding.ActivityHomeBinding;
import com.vsossella.meuboleto.digitarcodigobarras.view.DigitarCodigoBarrasActivity;
import com.vsossella.meuboleto.lercodigobarras.view.LerCodigoBarrasActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);


        HomeViewModel viewModel = new HomeViewModel();
        viewModel.carregarPagamentosFromString(buscarPagamentosFromSharedPreferences());

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setHomeVModel(viewModel);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        requestCameraPermission();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, LerCodigoBarrasActivity.class));

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private String buscarPagamentosFromSharedPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getString("pagamentos", "");
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @BindingAdapter("bind:pagamentos")
    public static void bindListPagamentos(final RecyclerView view, ObservableArrayList<CodigoDeBarra> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        view.setAdapter(new CodigoDeBarraAdapter(list));
    }
//
//    public static class LinearLayoutManagerNoScroll extends LinearLayoutManager {
//        private boolean isScrollEnabled = false;
//
//        public LinearLayoutManagerNoScroll(Context context) {
//            super(context);
//        }
//
//        public void setScrollEnabled(boolean flag) {
//            this.isScrollEnabled = flag;
//        }
//
//        @Override
//        public boolean canScrollVertically() {
//            return isScrollEnabled && super.canScrollVertically();
//        }
//    }
}
