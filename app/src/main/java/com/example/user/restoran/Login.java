package com.example.user.restoran;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText editText1, editText2; //Deklarasi object dari class EditText
    String text1, text2; //Deklarasi object string
    protected Cursor cursor;
    DataHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DataHelper(this);
    }
    public void loginMasuk(View view){
        //method onclick pada button

        editText1 = (EditText)findViewById(R.id.edittext_username);
        editText2 = (EditText)findViewById(R.id.edittext_password);
        text1 = editText1.getText().toString();
        text2 = editText2.getText().toString();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //kondisi jika username dan password benar maka akan menampilkan pesan text toast
        //login sukses dan masuk ke acivity 2
        cursor = db.rawQuery("select * from login where username = '" + text1
                + "' and password = '" + text2 + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            Intent intent = new Intent(Login.this, MenuUtama.class);
            startActivity(intent);
        }
        else if ((text1.matches("")||text2.matches(""))){
            Toast.makeText(this, "Isikan Username dan Password", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Login Gagal/Username Password Salah", Toast.LENGTH_SHORT).show();
        }
    }
}

