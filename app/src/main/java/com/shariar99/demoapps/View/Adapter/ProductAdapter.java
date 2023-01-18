package com.shariar99.demoapps.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shariar99.demoapps.R;
import com.shariar99.demoapps.Service.Model.ProductModel;
import com.shariar99.demoapps.View.Ui.MainActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.myViewHolder> {

   MainActivity mainActivity;
    private List<ProductModel> list;

    public ProductAdapter(MainActivity mainActivity, List<ProductModel> list) {
        this.mainActivity = mainActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.single_product,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.mTitle.setText(list.get(position).getTitle());
        holder.price.setText(list.get(position).getPrice().toString());
        holder.catagory.setText(list.get(position).getCategory().toString());
        Glide.with(mainActivity).load(list.get(position).getImage()).into(holder.pImageView);

        holder.product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.product_count_layout.getVisibility() == View.VISIBLE)
                {
                    holder.product_count_layout.setVisibility(View.GONE);
                }
                else
                {
                    holder.product_count_layout.setVisibility(View.VISIBLE);

                }
            }
        });

        holder.addProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(holder.productCount.getText().toString());

                if (x <10)
                {
                    x++;
                }
                holder.productCount.setText(String.valueOf(x));
                holder.productCount.setText(String.valueOf(x));
            }
        });

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(holder.productCount.getText().toString());
                if (x >=1)
                {
                    x--;
                }
                else
                {
                    holder.product_count_layout.setVisibility(View.GONE);
                }
                holder.productCount.setText(String.valueOf(x));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.list!= null){
            return list.size();

        }
        else
            return 0;


    }

    public class  myViewHolder extends RecyclerView.ViewHolder{
        ImageView pImageView , addProduct, deleteProduct;;
        TextView mTitle , price, catagory, productCount;
        RelativeLayout product, product_count_layout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            catagory = itemView.findViewById(R.id.product_category);
            pImageView = itemView.findViewById(R.id.product_image);
            addProduct = itemView.findViewById(R.id.add_product);
            deleteProduct = itemView.findViewById(R.id.delete_product);
            productCount = itemView.findViewById(R.id.product_count);
            product = itemView.findViewById(R.id.product);
            product_count_layout = itemView.findViewById(R.id.product_count_layout);


        }
    }


}
