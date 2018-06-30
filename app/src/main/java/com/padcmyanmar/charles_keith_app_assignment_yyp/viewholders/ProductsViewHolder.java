package com.padcmyanmar.charles_keith_app_assignment_yyp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.padcmyanmar.charles_keith_app_assignment_yyp.R;
import com.padcmyanmar.charles_keith_app_assignment_yyp.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_yyp.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_yyp.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    private ProductsDelegate mProductsDelegate;
    private NewProductVO mNewProduct;

    @BindView(R.id.tv_items_count)
    TextView tvItemsCount;

    @BindView(R.id.iv_product_img)
    ImageView ivProductImg;

    @BindView(R.id.tv_product_name)
    TextView tvProductName;


    public ProductsViewHolder(View itemView, ProductsDelegate productsDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mProductsDelegate = productsDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductsDelegate.onTagProduct(mNewProduct);
            }
        });
    }

    public void setNewProductData(List<NewProductVO> newProductList, int position) {
        mNewProduct = newProductList.get(position);
        tvItemsCount.setText(newProductList.size() + " ITEMS");
        if (position == 0) {
            tvItemsCount.setVisibility(View.VISIBLE);
        } else {
            tvItemsCount.setVisibility(View.GONE);
        }
        tvProductName.setText(mNewProduct.getProductTitle());

        if (!mNewProduct.getProductImage().isEmpty()) {
            ivProductImg.setVisibility(View.VISIBLE);
            GlideApp.with(ivProductImg.getContext())
                    .load(mNewProduct.getProductImage())
                    .into(ivProductImg);
        } else {
            ivProductImg.setVisibility(View.GONE);
        }
    }
}
