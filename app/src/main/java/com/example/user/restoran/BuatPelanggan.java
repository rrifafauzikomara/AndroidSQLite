package com.example.user.restoran;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.user.restoran.Pelanggan.pa;


public class BuatPelanggan extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    DataHelper dbHelper;
    Button simpan, kembali, btnDate;
    EditText nama, alamat, tgldate;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pelanggan);

        dbHelper = new DataHelper(this);
        nama = (EditText) findViewById(R.id.etNama);
        rg = (RadioGroup) findViewById(R.id.radio);
        alamat = (EditText) findViewById(R.id.etAlamat);
        simpan = (Button) findViewById(R.id.btnSimpan);
        kembali = (Button) findViewById(R.id.btnKembali);
        tgldate = (EditText) findViewById(R.id.etTgl);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                String s_nama, s_tgl, GetJK, s_alamat;

                s_nama = nama.getText().toString();
                s_tgl = tgldate.getText().toString();
                s_alamat = alamat.getText().toString();

                int idpilihanRb = rg.getCheckedRadioButtonId();
                if (idpilihanRb==R.id.radioButton1){
                    GetJK = "Laki-Laki";
                }
                else if (idpilihanRb==R.id.radioButton2){
                    GetJK = "Perempuan";
                }
                else {
                    GetJK = "-";
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into pelanggan (nama, tgl, jk, alamat) values('" + s_nama + "','" + s_tgl + "','" + GetJK + "','" + s_alamat + "')");
                Toast.makeText(getApplicationContext(), "Berhasil",
                        Toast.LENGTH_LONG).show();
                Pelanggan.pa.RefreshList();
                finish();
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                finish();
            }
        });

    }

    public void getTgl (View v) {
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tgldate = (EditText) findViewById(R.id.etTgl);
        btnDate = (Button) findViewById(R.id.btDate);
        showDateDialog();
    }

    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tgldate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

}
