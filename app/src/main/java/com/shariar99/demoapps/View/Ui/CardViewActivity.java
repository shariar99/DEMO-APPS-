package com.shariar99.demoapps.View.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shariar99.demoapps.R;
import com.shariar99.demoapps.Service.Model.Product;
import com.shariar99.demoapps.Service.Network.AppDatabase;
import com.shariar99.demoapps.Service.Network.ProductDao;
import com.shariar99.demoapps.View.Adapter.BagAdapter;

import java.util.List;

public class CardViewActivity extends AppCompatActivity {

    ImageView backBtn;
    RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        getroomdata();

        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getroomdata()
    {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "cart_db").allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();

        recview=findViewById(R.id.card_recycleView);
        recview.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products=productDao.getallproduct();

        BagAdapter adapter=new BagAdapter(products);
        recview.setAdapter(adapter);



    }
}