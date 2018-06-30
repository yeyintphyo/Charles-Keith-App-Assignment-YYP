package com.padcmyanmar.charles_keith_app_assignment_yyp.network;

import com.padcmyanmar.charles_keith_app_assignment_yyp.event.ApiErrorEvent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.event.SuccessForceRefreshProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.event.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles_keith_app_assignment_yyp.network.response.GetNewProductsResponse;
import com.padcmyanmar.charles_keith_app_assignment_yyp.utils.CommonInstances;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNewProductsDataAgentImpl implements NewProductsDataAgent {
    private static RetrofitNewProductsDataAgentImpl sObjInstance;
    private NewProductsApi mNewProductApi;

    private RetrofitNewProductsDataAgentImpl() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonInstances.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mNewProductApi = retrofit.create(NewProductsApi.class);
    }

    public static RetrofitNewProductsDataAgentImpl getObjInstance(){
        if(sObjInstance == null){
            sObjInstance = new RetrofitNewProductsDataAgentImpl();
        }
            return  sObjInstance;
    }

    @Override
    public void loadNewProductList(final int page, final String accessToken, final boolean isForceRefresh) {
        Call<GetNewProductsResponse> loadNewProductsCall = mNewProductApi.loadNewProducts(page, accessToken);

        loadNewProductsCall.enqueue(new Callback<GetNewProductsResponse>() {
            @Override
            public void onResponse(Call<GetNewProductsResponse> call, Response<GetNewProductsResponse> response) {
                GetNewProductsResponse productsResponse = response.body();
                if(productsResponse != null && productsResponse.isResponseOK()){
                    if(isForceRefresh){
                        SuccessForceRefreshProductsEvent event = new SuccessForceRefreshProductsEvent(productsResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    } else {
                        SuccessGetNewProductsEvent event = new SuccessGetNewProductsEvent(productsResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    }
                } else {
                    if(productsResponse == null){
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call.");
                        EventBus.getDefault().post(event);
                    } else {
                        ApiErrorEvent event = new ApiErrorEvent(productsResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewProductsResponse> call, Throwable t) {
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
