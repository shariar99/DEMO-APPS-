package com.shariar99.demoapps.View.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shariar99.demoapps.R;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button button = findViewById(R.id.signup);
        TextView textView = findViewById(R.id.backlogin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_up.this, Login.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_up.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}