package com.example.user.restoran;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Minuman extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Minuman mi;
    Button kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minuman);
        Button btn=(Button)findViewById(R.id.btnKembali);

        kembali = (Button) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                Intent inte = new Intent(Minuman.this, InputMinuman.class);
                startActivity(inte);
            }
        });
        mi = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void kembali (View arg0) {
        // TODO Auto-generated method stub
        Intent i =new Intent(getApplicationContext(),MenuUtama.class);
        startActivity(i);
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM minuman",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                    long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Minuman", "Update Minuman", "Hapus Minuman"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Minuman.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch(item){
                                    case 0 :
                                        Intent i = new
                                                Intent(getApplicationContext(), LihatMinuman.class);
                                        i.putExtra("nama", selection);
                                        startActivity(i);
                                        break;
                                    case 1 :
                                        Intent in = new
                                                Intent(getApplicationContext(), UpdateMinuman.class);
                                        in.putExtra("nama", selection);
                                        startActivity(in);
                                        break;
                                    case 2 :
                                        SQLiteDatabase db =
                                                dbcenter.getWritableDatabase();
                                        db.execSQL("delete from minuman where nama = '"+selection+"'");
                                        RefreshList();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

    }
}
