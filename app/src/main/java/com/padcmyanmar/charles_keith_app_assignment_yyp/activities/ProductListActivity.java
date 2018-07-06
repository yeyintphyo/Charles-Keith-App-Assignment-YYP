package com.padcmyanmar.charles_keith_app_assignment_yyp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.padcmyanmar.charles_keith_app_assignment_yyp.R;
import com.padcmyanmar.charles_keith_app_assignment_yyp.adapters.ProductsAdapter;
import com.padcmyanmar.charles_keith_app_assignment_yyp.data.models.ProductsModel;
import com.padcmyanmar.charles_keith_app_assignment_yyp.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_yyp.delegates.ProductsDelegate;
import com.padcmyanmar.charles_keith_app_assignment_yyp.event.ApiErrorEvent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.event.SuccessForceRefreshProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.event.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.utils.CommonInstances;
import com.padcmyanmar.charles_keith_app_assignment_yyp.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends BaseActivity implements ProductsDelegate {

    private ProductsAdapter mProductsAdapter;

    private GridLayoutManager mGridLayoutManager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.vp_empty)
    EmptyViewPod evpEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this,this);

        setSupportActionBar(toolbar);

        mProductsAdapter = new ProductsAdapter(this);

        rvProducts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean isListEndReached = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("ProductListActivity", "OnScrollListener:onScrollStateChanged : "+ newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                        && !isListEndReached) {
                    isListEndReached = true;
                    ProductsModel.getObjInstance().loadNewProducts();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("ProductListActivity", "OnScrollListener:onScrolled -dx : "+ dx + ", dy :" +dy);

                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if ((visibleItemCount + pastVisibleItems) < totalItemCount) {
                    isListEndReached = false;
                }
            }
        });


        rvProducts.setAdapter(mProductsAdapter);
        //rvProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mGridLayoutManager = new GridLayoutManager( getApplicationContext(), 2 );
        rvProducts.setLayoutManager(mGridLayoutManager);

        ProductsModel.getObjInstance().loadNewProducts();

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProductsModel.getObjInstance().forceRefreshNewProducts();
            }
        });

        evpEmpty.setEmptyData(R.drawable.empty_data_placeholder, getString(R.string.empty_msg));

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }

    }

    @Override
    public void onTagProduct(NewProductVO productVO) {
        Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
        intent.putExtra(CommonInstances.PRODUCT_ID, productVO.getProductId());
        startActivity(intent);
    }

    @Override
    public void onChangeViewLayout() {

    }

    @OnClick({R.id.btn_single_column, R.id.btn_double_column})
    public void clickViewColumn(View view) {
        if(view.getId() == R.id.btn_single_column) {
            mGridLayoutManager.setSpanCount(1);
        } else if (view.getId() == R.id.btn_double_column) {
            mGridLayoutManager.setSpanCount(2);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event){
        Log.d("onSuccessGetNewProducts", "onSuccessGetNewProducts" + event.getProductList());
        mProductsAdapter.setmNewProductList(event.getProductList());
        swipeRefreshLayout.setRefreshing(false);
        evpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceRefreshProducts(SuccessForceRefreshProductsEvent event){
        Log.d("onSuccessForceRefresh", "onSuccessForceRefresh" + event.getProductList());
        mProductsAdapter.setmNewProductList(event.getProductList());
        swipeRefreshLayout.setRefreshing(false);
        evpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailGetNewProducts(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(swipeRefreshLayout, event.getErrorMessage(), Snackbar.LENGTH_INDEFINITE).show();
        evpEmpty.setVisibility(View.VISIBLE);
    }
}
