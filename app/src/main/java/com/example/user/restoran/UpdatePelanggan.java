package com.example.user.restoran;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdatePelanggan extends AppCompatActivity {


    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    protected Cursor cursor;
    DataHelper dbHelper;
    Button update, kembali, btnDate;
    EditText no, nama, tgldate, alamat;
    RadioGroup rg;
    RadioButton rbLaki, rbperempuan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pelanggan);

        dbHelper = new DataHelper(this);
        no = (EditText) findViewById(R.id.etNo);
        nama = (EditText) findViewById(R.id.etNama);
        rg = (RadioGroup) findViewById(R.id.radio);
        tgldate = (EditText) findViewById(R.id.etTgl);
        alamat = (EditText) findViewById(R.id.etAlamat);
        update = (Button) findViewById(R.id.btnUpdate);
        kembali = (Button) findViewById(R.id.btnKembali);
        rbLaki = (RadioButton) findViewById(R.id.radioButton1);
        rbperempuan = (RadioButton) findViewById(R.id.radioButton2);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pelanggan WHERE nama = '" + getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            no.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            tgldate.setText(cursor.getString(2).toString());
            alamat.setText(cursor.getString(4).toString());

            if(cursor.getString(3).equals("Laki-Laki")) {
                rbLaki.setChecked(true);
            }else if(cursor.getString(3).equals("Perempuan")){
                rbperempuan.setChecked(true);
            }
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                String s_nomor, s_nama, s_tgl, GetJK, s_alamat;

                s_nomor = no.getText().toString();
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
                db.execSQL("update pelanggan set nama='"+s_nama +"', tgl='" +s_tgl +"', jk='" +GetJK +"', alamat='" +s_alamat +"' where no='"+s_nomor+"'");
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