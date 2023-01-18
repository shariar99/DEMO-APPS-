package com.shariar99.demoapps.View.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.shariar99.demoapps.R;
import com.shariar99.demoapps.Service.Model.ProductModel;
import com.shariar99.demoapps.Service.Network.RetrofitInstance;
import com.shariar99.demoapps.View.Adapter.ProductAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static String BASEURL =  "https://fakestoreapi.com/";
    List<ProductModel> productModelList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitInstance.getInstance().apiServices.getAllProductList().enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                productModelList = response.body();
                recyclerView.setAdapter(new ProductAdapter(MainActivity.this,productModelList));


            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {

            }
        });

        recyclerView = findViewById(R.id.recycleView);
        GridLayoutManager LayoutManager  = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(LayoutManager);


    }
}