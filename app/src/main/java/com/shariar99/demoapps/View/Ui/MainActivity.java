package com.shariar99.demoapps.View.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shariar99.demoapps.R;
import com.shariar99.demoapps.Service.Model.Product;
import com.shariar99.demoapps.Service.Model.ProductModel;
import com.shariar99.demoapps.Service.Network.AppDatabase;
import com.shariar99.demoapps.Service.Network.ProductDao;
import com.shariar99.demoapps.Service.Network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static String BASEURL =  "https://fakestoreapi.com/";
    List<ProductModel> productModelList;

    EditText searchBox;
    ImageView searchBtn, bagBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = findViewById(R.id.search_box);
        searchBtn = findViewById(R.id.search_btn);
        bagBtn = findViewById(R.id.bag_btn);

        RetrofitInstance.getInstance().apiServices.getAllProductList().enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductModel>> call, @NonNull Response<List<ProductModel>> response) {
                productModelList = response.body();
                recyclerView.setAdapter(new ProductAdapter(MainActivity.this,productModelList));

            }

            @Override
            public void onFailure(@NonNull Call<List<ProductModel>> call, @NonNull Throwable t) {

            }
        });

        recyclerView = findViewById(R.id.recycleView);
        GridLayoutManager LayoutManager  = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(LayoutManager);





        bagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardViewActivity.class);
                startActivity(intent);
            }
        });


    }

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.myViewHolder> {

        MainActivity mainActivity;
        private final List<ProductModel> list;

        public ProductAdapter(MainActivity mainActivity, List<ProductModel> list) {
            this.mainActivity = mainActivity;
            this.list = list;
        }

        @NonNull
        @Override
        public ProductAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.single_product, parent, false);
            return new myViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.pName.setText(list.get(position).getTitle());
            holder.pPrice.setText(String.valueOf(list.get(position).getPrice()));
            holder.catagory.setText(list.get(position).getCategory());
            holder.id.setText(String.valueOf(list.get(position).getId()));
            Glide.with(mainActivity).load(list.get(position).getImage()).into(holder.pImageView);


            holder.product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.product_count_layout.setVisibility(View.VISIBLE);

                }
            });

            holder.addCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                    AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"cart_db").allowMainThreadQueries().build();
                    ProductDao productDao=db.ProductDao();
                    Boolean check=productDao.is_exist(Integer.parseInt(holder.id.getText().toString()));


                    if (check==false)
                    {
                        int pid=Integer.parseInt(holder.id.getText().toString());
                        String pname=holder.pName.getText().toString();
                        int price = 100;
                        int qnt=Integer.parseInt(holder.productCount.getText().toString());
                        String image = list.get(position).getImage();
                        productDao.insertrecord(new Product(pid,pname,price,qnt, image));
                    }


                }
            });

            holder.addProduct.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    int x = Integer.parseInt(holder.productCount.getText().toString());

                    if (x < 10) {
                        x++;
                    }
                    if (x>9)
                    {
                        holder.stock.setVisibility(View.VISIBLE);
                    }
                    holder.productCount.setText(String.valueOf(x));
                }
            });

            holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    int x = Integer.parseInt(holder.productCount.getText().toString());
                    if (x >= 1) {
                        x--;
                    }
                    if (x<10)
                    {
                        holder.stock.setVisibility(View.GONE);
                    }
                    else {
                        holder.product_count_layout.setVisibility(View.GONE);
                    }
                    holder.productCount.setText(String.valueOf(x));
                }
            });
        }

        @Override
        public int getItemCount() {
            if (this.list != null) {
                return list.size();

            } else
                return 0;


        }

        public class myViewHolder extends RecyclerView.ViewHolder {
            ImageView pImageView, addProduct, deleteProduct;
            TextView pName;
            TextView pPrice;
            TextView catagory;
            TextView productCount;
            TextView addCard;
            TextView id;
            TextView stock;
            RelativeLayout product, product_count_layout;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                pName = itemView.findViewById(R.id.product_name);
                pPrice = itemView.findViewById(R.id.product_price);
                catagory = itemView.findViewById(R.id.product_category);
                pImageView = itemView.findViewById(R.id.product_image);
                addProduct = itemView.findViewById(R.id.add_product);
                deleteProduct = itemView.findViewById(R.id.delete_product);
                productCount = itemView.findViewById(R.id.product_count);
                product = itemView.findViewById(R.id.product);
                product_count_layout = itemView.findViewById(R.id.product_count_layout);
                addCard = itemView.findViewById(R.id.add_card);
                id = itemView.findViewById(R.id.id);
                stock = itemView.findViewById(R.id.stock);


            }
        }
    }
}