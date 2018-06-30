package com.padcmyanmar.charles_keith_app_assignment_yyp.data.vos;

import com.google.gson.annotations.SerializedName;

public class NewProductVO {

    @SerializedName("product-id")
    private String productId;

    @SerializedName("product-image")
    private String productImage;

    @SerializedName("product-title")
    private String productTitle;

    public String getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }
}
