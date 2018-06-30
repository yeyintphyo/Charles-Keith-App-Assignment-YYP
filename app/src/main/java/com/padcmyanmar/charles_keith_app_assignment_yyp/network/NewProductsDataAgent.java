package com.padcmyanmar.charles_keith_app_assignment_yyp.network;

public interface NewProductsDataAgent {
    void loadNewProductList(int page, String accessToken, boolean isForceRefresh);
}
