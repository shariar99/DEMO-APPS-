package com.shariar99.demoapps.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        ImageView pImageView;
        TextView mTitle , price, catagory;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            catagory = itemView.findViewById(R.id.product_category);
            pImageView = itemView.findViewById(R.id.product_image);


        }
    }


}
