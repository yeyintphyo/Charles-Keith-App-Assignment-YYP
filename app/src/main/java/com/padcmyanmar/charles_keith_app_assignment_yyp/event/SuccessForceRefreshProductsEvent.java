package com.padcmyanmar.charles_keith_app_assignment_yyp.event;

import com.padcmyanmar.charles_keith_app_assignment_yyp.data.vos.NewProductVO;

import java.util.List;

public class SuccessForceRefreshProductsEvent extends SuccessGetNewProductsEvent {

    public SuccessForceRefreshProductsEvent(List<NewProductVO> productList) {
        super(productList);
    }
}
