package com.padcmyanmar.charles_keith_app_assignment_yyp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.charles_keith_app_assignment_yyp.R;
import com.padcmyanmar.charles_keith_app_assignment_yyp.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_yyp.viewholders.ProductsViewHolder;

public class ProductsVerticalAdapter extends RecyclerView.Adapter {
    private ProductsDelegate mProductsDelegate;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_vertical_product, parent, false);
        return new ProductsViewHolder(view, mProductsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
