package com.padcmyanmar.charles_keith_app_assignment_yyp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.padcmyanmar.charles_keith_app_assignment_yyp.R;
import com.padcmyanmar.charles_keith_app_assignment_yyp.adapters.ProductsHorizontalAdapter;
import com.padcmyanmar.charles_keith_app_assignment_yyp.adapters.ProductsVerticalAdapter;
import com.padcmyanmar.charles_keith_app_assignment_yyp.data.models.ProductsModel;
import com.padcmyanmar.charles_keith_app_assignment_yyp.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_yyp.utils.CommonInstances;

public class ProductDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        RecyclerView rvProductHorizontal = findViewById(R.id.rv_products_horizontal);
        ProductsHorizontalAdapter horizontalAdapter = new ProductsHorizontalAdapter();
        rvProductHorizontal.setAdapter(horizontalAdapter);
        rvProductHorizontal.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL , false));

        RecyclerView rvProductVertical = findViewById(R.id.rv_products_vertical);
        ProductsVerticalAdapter verticalAdapter = new ProductsVerticalAdapter();
        rvProductVertical.setAdapter(verticalAdapter);
        rvProductVertical.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        String productId = getIntent().getStringExtra(CommonInstances.PRODUCT_ID);
        Log.d("ProductDetailsActivity","productId" + productId);
        NewProductVO productVO = ProductsModel.getObjInstance().getNewProductById(productId);
    }
}
