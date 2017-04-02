package com.vsossella.meuboleto.codigodebarras;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsossella.meuboleto.R;
import com.vsossella.meuboleto.databinding.PagamentoItemBinding;

/**
 * Created by vsossella on 01/04/17.
 */

public class CodigoDeBarraAdapter extends RecyclerView.Adapter<CodigoDeBarraAdapter.ViewHolder> {

    private ObservableArrayList<CodigoDeBarra> extractList;
    private LayoutInflater inflater;

    public CodigoDeBarraAdapter(ObservableArrayList<CodigoDeBarra> extractList) {
        super();
        this.extractList = extractList;
    }

    @Override
    public CodigoDeBarraAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        PagamentoItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.pagamento_item, parent, false);
        return new CodigoDeBarraAdapter.ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(CodigoDeBarraAdapter.ViewHolder holder, int position) {
        holder.bindExtractViewModel(extractList.get(position));
    }

    @Override
    public int getItemCount() {
        return extractList == null ? 0 : extractList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private PagamentoItemBinding extractListItemBinding;

        public ViewHolder(View itemView, PagamentoItemBinding extractListItemBinding) {
            super(itemView);
            this.extractListItemBinding = extractListItemBinding;
        }

        public void bindExtractViewModel(CodigoDeBarra extract) {
            extractListItemBinding.setBoletoVM(extract);
            extractListItemBinding.executePendingBindings();
        }
    }
}