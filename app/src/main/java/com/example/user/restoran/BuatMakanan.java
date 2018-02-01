package com.example.user.restoran;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BuatMakanan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan, btnKembali;
    EditText editTextNama;
    RadioGroup radioGroupJenisPilihan, radioGroupJumlahPesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_makanan);
        dbHelper = new DataHelper(this);
        editTextNama = (EditText)findViewById(R.id.etNama);
        btnKembali = (Button) findViewById(R.id.btnKembali);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);

        radioGroupJenisPilihan = (RadioGroup)findViewById(R.id.rgJenisPil);
        radioGroupJumlahPesan = (RadioGroup)findViewById(R.id.rgPesan);
        btnSimpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                // TODO Auto-generated method stub
                String a_nama, getPilihan, getJumlah;

                a_nama = editTextNama.getText().toString();

                int idpilihanRb = radioGroupJenisPilihan.getCheckedRadioButtonId();
                if (idpilihanRb == R.id.rbBungkus){
                    getPilihan = "Bungkus";
                }
                else if (idpilihanRb == R.id.rbTempat){
                    getPilihan = "Di Tempat";
                }
                else {
                    getPilihan = "-";
                }
                int idjumlahRb = radioGroupJumlahPesan.getCheckedRadioButtonId();
                if (idjumlahRb == R.id.rb1){
                    getJumlah = "1";
                }
                else if (idjumlahRb == R.id.rb2){
                    getJumlah = "2";
                }
                else if (idjumlahRb == R.id.rb3){
                    getJumlah = "3";
                }
                else if (idjumlahRb == R.id.rb4){
                    getJumlah = "4";
                }
                else {
                    getJumlah = "-";
                }
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into makanan(nama, pesan, opsi) values('" +a_nama + "','"+getPilihan+"','"+getJumlah+"')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        btnKembali.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                //TODO Auto-generated method stud
                finish();
            }
        });
    }
}
