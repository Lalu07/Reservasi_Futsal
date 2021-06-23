package firdaus.rizkika.reservasi_futsal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Tambah_Data extends AppCompatActivity {

    private EditText nm,tgl,waktu,uang;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__data);

        nm = findViewById(R.id.edNama);
        tgl = findViewById(R.id.edTanggal);
        waktu = findViewById(R.id.edWaktu);
        uang = findViewById(R.id.edJumlah);
        save = findViewById(R.id.bttnSubmit);
    }
}