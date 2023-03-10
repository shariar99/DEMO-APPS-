package com.shariar99.demoapps.View.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.shariar99.demoapps.R;
import com.shariar99.demoapps.Service.Model.Product;
import com.shariar99.demoapps.Service.Network.AppDatabase;
import com.shariar99.demoapps.Service.Network.ProductDao;
import com.shariar99.demoapps.View.Ui.CardViewActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BagAdapter  extends RecyclerView.Adapter<BagAdapter.myviewholder>
{
    CardViewActivity cardViewActivity;
    List<Product> products;
    TextView rateview;
    public BagAdapter(CardViewActivity cardViewActivity, List<Product> products) {
        this.cardViewActivity = cardViewActivity;
        this.products = products;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_single_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BagAdapter.myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(String.valueOf(products.get(position).getPid()));
        holder.name.setText(products.get(position).getPname());
        holder.price.setText(String.valueOf("TK "+products.get(position).getPrice()));
        holder.count.setText(String.valueOf(products.get(position).getQnt()));
        Glide.with(cardViewActivity).load(products.get(position).getpImage()).into(holder.pImageView);
        double quantityprice = products.get(position).getPrice()*products.get(position).getQnt();
        holder.priceAll.setText(String.valueOf("TK "+quantityprice));
        holder.addProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(holder.count.getText().toString());

                if (x < 10) {
                    x++;
                    holder.priceAll.setText(String.valueOf("TK "+products.get(position).getPrice()*x));
                }
                holder.count.setText(String.valueOf(x));
            }
        });

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(holder.count.getText().toString());
                if (x >= 1) {
                    x--;
                    holder.priceAll.setText(String.valueOf("TK "+products.get(position).getPrice()*x));
                }
                holder.count.setText(String.valueOf(x));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView pImageView , addProduct, deleteProduct;;
        TextView price,priceAll, id, name, count;
        RelativeLayout product, product_count_layout;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.card_product_name);
            id=itemView.findViewById(R.id.card_id);
            pImageView=itemView.findViewById(R.id.card_product_image);
            addProduct=itemView.findViewById(R.id.card_add_product);
            deleteProduct=itemView.findViewById(R.id.card_delete_product);
            price=itemView.findViewById(R.id.single_product_price);
            priceAll=itemView.findViewById(R.id.total_product_price);
            count = itemView.findViewById(R.id.card_product_count);
        }
    }


}