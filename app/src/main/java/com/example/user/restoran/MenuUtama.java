package com.example.user.restoran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuUtama extends AppCompatActivity {
    Button button1, button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        button1=(Button)findViewById(R.id.bt1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i =new Intent(getApplicationContext(),Pelanggan.class);
                startActivity(i);
            }
        });
        button2=(Button)findViewById(R.id.bt2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        button3=(Button)findViewById(R.id.bt3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i =new Intent(getApplicationContext(),Minuman.class);
                startActivity(i);
            }
        });
    }
}
