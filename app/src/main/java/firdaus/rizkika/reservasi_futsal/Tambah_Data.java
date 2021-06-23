package firdaus.rizkika.reservasi_futsal;

import androidx.appcompat.app.AppCompatActivity;
import firdaus.rizkika.reservasi_futsal.database.SimpanData;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tambah_Data extends AppCompatActivity {

    private EditText nm,tgl,uang;
    private Button save;
    private Spinner spin;
    private DatabaseReference futsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__data);

        nm = findViewById(R.id.edNama);
        tgl = findViewById(R.id.edTanggal);
        uang = findViewById(R.id.edJumlah);
        save = findViewById(R.id.bttnSubmit);
        spin = findViewById(R.id.spinn1);

        futsal = FirebaseDatabase.getInstance().getReference().child("Futsal");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }

    private void insertData(){
        String nam = nm.getText().toString();
        String tang = tgl.getText().toString();
        String spindata = spin.getSelectedItem().toString();
        String ung = uang.getText().toString();

        SimpanData simpanData = new SimpanData(nam,tang,spindata,ung);
        futsal.push().setValue(simpanData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nm.setText("");
                tgl.setText("");
                uang.setText("");
                Toast.makeText(Tambah_Data.this,"Data berhasil Di simpan!",Toast.LENGTH_LONG).show();
            }
        });
    }
}