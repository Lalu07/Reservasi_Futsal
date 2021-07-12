package firdaus.rizkika.reservasi_futsal;

import androidx.appcompat.app.AppCompatActivity;
import firdaus.rizkika.reservasi_futsal.database.SimpanData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tambah_Data extends AppCompatActivity {

    private EditText nm,tgl,uang,spin;
    private Button save;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__data);

        //inisialisasi fields text
        nm = findViewById(R.id.edNama);
        tgl = findViewById(R.id.edTanggal);
        uang = findViewById(R.id.edJumlah);
        save = findViewById(R.id.bttnEdit);
        spin = findViewById(R.id.spinn1);

        //ambil referensi database firebase
        database = FirebaseDatabase.getInstance().getReference().child("Futsal");

        final SimpanData simpanData = (SimpanData) getIntent().getSerializableExtra("data");

        if (simpanData!=null){
            nm.setText(simpanData.getNamaPemesan());
            tgl.setText(simpanData.getTanggalPemesanan());
            uang.setText(simpanData.getHargaPaket());
            spin.setText(simpanData.getPaketWaktu());
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    simpanData.setNamaPemesan(nm.getText().toString());
                    simpanData.setTanggalPemesanan(tgl.getText().toString());
                    simpanData.setHargaPaket(uang.getText().toString());
                    simpanData.setPaketWaktu(spin.getText().toString());

                    updateData(simpanData);
                    finish();
                }
            });

        } else {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //validasi edit text
                    if (nm.getText().toString().isEmpty() && tgl.getText().toString().isEmpty() && uang.getText().toString().isEmpty()){
                        nm.setError("Nama Harus di isi!");
                        tgl.setError("Tanggalan harus di isi!");
                        uang.setError("Jumlah Tagihan harus di isi!");
                        return;
                    }
                    if (nm.getText().toString().isEmpty()){
                        nm.setError("Nama Harus di isi");
                        return;
                    }
                    if (tgl.getText().toString().isEmpty()){
                        tgl.setError("Tanggalan harus di isi!");
                        return;
                    }
                    if (uang.getText().toString().isEmpty()){
                        uang.setError("Jumlah Tagihan harus di isi!");
                        return;
                    }
                    else {
                        insertData();
                        finish();
                    }

                }
            });
        }

    }
//    private boolean isEmpty(String s){
//        return TextUtils.isEmpty(s);
//    }

    private void insertData(){
        String nam = nm.getText().toString();
        String tang = tgl.getText().toString();
        String spindata = spin.getText().toString();
        String ung = uang.getText().toString();

        SimpanData simpanData = new SimpanData(nam,tang,spindata,ung);
        //masukkan data ke firebase
        database.push().setValue(simpanData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nm.setText("");
                tgl.setText("");
                uang.setText("");
                spin.setText("");
                Toast.makeText(Tambah_Data.this,"Data berhasil Di simpan!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateData(SimpanData simpanData){
        database.child(simpanData.getIdFutsal()).setValue(simpanData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Tambah_Data.this,"Data berhasil di update",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainMenu.class));
                finish();
            }
        });
    }

//    private void deleteData(String id){
//        database = FirebaseDatabase.getInstance().getReference("SimpanData").child(id);
//        Task<Void> mTask = database.removeValue();
//    }

    public static Intent getActIntent(Activity activity){
        //pemgambilan intent
        return new Intent(activity,Tambah_Data.class);
    }
}