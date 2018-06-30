package com.padcmyanmar.charles_keith_app_assignment_yyp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.charles_keith_app_assignment_yyp.R;
import com.padcmyanmar.charles_keith_app_assignment_yyp.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_yyp.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_yyp.viewholders.ProductsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {
    private ProductsDelegate mProductsDelegate;
    private List<NewProductVO> mNewProductList;

    public ProductsAdapter(ProductsDelegate productsDelegate) {
        mProductsDelegate = productsDelegate;
        mNewProductList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_product, parent, false);
        return new ProductsViewHolder(view, mProductsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        holder.setNewProductData(mNewProductList, position);
    }


    @Override
    public int getItemCount() {
        return mNewProductList.size();
    }

    public void setmNewProductList(List<NewProductVO> newProductList) {
        mNewProductList = newProductList;
        notifyDataSetChanged();
    }

    public void appendProductList(List<NewProductVO> newProductList) {
        mNewProductList.addAll(newProductList);
        notifyDataSetChanged();
    }
}
