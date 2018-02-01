package com.example.user.restoran;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatMinuman extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button kembali;
    TextView no, nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_minuman);
        dbHelper = new DataHelper(this);
        no = (TextView) findViewById(R.id.textView1);
        nama = (TextView) findViewById(R.id.textView2);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM minuman WHERE nama = '" + getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            no.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
        }
        kembali = (Button) findViewById(R.id.button1);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                finish();
            }
        });

    }
}
