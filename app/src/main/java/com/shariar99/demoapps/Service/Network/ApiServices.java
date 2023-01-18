package com.shariar99.demoapps.Service.Network;

import com.shariar99.demoapps.Service.Model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("products")
    Call<List<ProductModel>> getAllProductList();

}
