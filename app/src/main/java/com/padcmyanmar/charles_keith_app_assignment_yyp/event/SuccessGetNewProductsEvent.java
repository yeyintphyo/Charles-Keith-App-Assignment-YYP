package com.padcmyanmar.charles_keith_app_assignment_yyp.event;

import com.padcmyanmar.charles_keith_app_assignment_yyp.data.vos.NewProductVO;

import java.util.List;

public class SuccessGetNewProductsEvent {

    private List<NewProductVO> productList;

    public SuccessGetNewProductsEvent(List<NewProductVO> productList) {
        this.productList = productList;
    }

    public List<NewProductVO> getProductList() {
        return productList;
    }
}
