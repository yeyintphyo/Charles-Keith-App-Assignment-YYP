package com.padcmyanmar.charles_keith_app_assignment_yyp.data.models;

import com.padcmyanmar.charles_keith_app_assignment_yyp.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith_app_assignment_yyp.event.SuccessForceRefreshProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.event.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.network.NewProductsDataAgent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.network.RetrofitNewProductsDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";
    private static ProductsModel sObjInstance;

    private int mPage ;
    private NewProductsDataAgent mDataAgent;
    private Map<String, NewProductVO> mProductMap;

    public static ProductsModel getObjInstance() {
        if (sObjInstance == null){
            sObjInstance = new ProductsModel();
        }
        return sObjInstance;
    }

    private ProductsModel() {
        mDataAgent = RetrofitNewProductsDataAgentImpl.getObjInstance();
        mPage = 1;
        mProductMap = new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public void loadNewProducts(){
        mDataAgent.loadNewProductList(2, DUMMY_ACCESS_TOKEN, false);
    }

    public void forceRefreshNewProducts(){
        mPage = 1;
        mDataAgent.loadNewProductList(1, DUMMY_ACCESS_TOKEN, false);
    }

    public NewProductVO getNewProductById (String productId) {
        return mProductMap.get(productId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProducts (SuccessGetNewProductsEvent event) {
        setDataIntoRepository(event.getProductList());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForceRefreshProducts (SuccessForceRefreshProductsEvent event) {
        setDataIntoRepository(event.getProductList());

    }

    private void setDataIntoRepository(List<NewProductVO> productList) {
        for(NewProductVO productVO : productList){
            mProductMap.put(productVO.getProductId(), productVO);
        }
    }


}
