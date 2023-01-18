package com.shariar99.demoapps.Service.Network;

import static com.shariar99.demoapps.View.Ui.MainActivity.BASEURL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static RetrofitInstance instance;
    public ApiServices apiServices;

    RetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();

        apiServices = retrofit.create(ApiServices.class);
    }
    public static RetrofitInstance getInstance(){
        if (instance==null){
            instance = new RetrofitInstance();
        }
        return instance;
    }


}
