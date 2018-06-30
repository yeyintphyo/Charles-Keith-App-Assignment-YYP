package com.padcmyanmar.charles_keith_app_assignment_yyp.network;


import com.padcmyanmar.charles_keith_app_assignment_yyp.network.response.GetNewProductsResponse;
import com.padcmyanmar.charles_keith_app_assignment_yyp.utils.CommonInstances;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewProductsApi {

    @FormUrlEncoded
    @POST(CommonInstances.API_GET_NEW_PRODUCTS)
    Call<GetNewProductsResponse> loadNewProducts(
            @Field(CommonInstances.PARAM_PAGE) int page,
            @Field(CommonInstances.PARAM_ACCESS_TOKEN) String accessToken);
}
