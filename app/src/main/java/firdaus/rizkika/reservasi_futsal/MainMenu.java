package firdaus.rizkika.reservasi_futsal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import firdaus.rizkika.reservasi_futsal.Adapter.LihatData;
import firdaus.rizkika.reservasi_futsal.database.SimpanData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity implements LihatData.FirebaseDataListener{
    private DatabaseReference futsalReferance;
    private RecyclerView RC;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager LYM;
    private ArrayList<SimpanData>datafutsal;
    private Button Logout,AddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //inisialisasi fields
        RC = findViewById(R.id.rcData);
        RC.setHasFixedSize(true);
        LYM = new LinearLayoutManager(this);
        RC.setLayoutManager(LYM);
        Logout = findViewById(R.id.bttnLogOut);
        AddData = findViewById(R.id.bttnTambahData);


        //inisialisasi untuk membaca database
        futsalReferance = FirebaseDatabase.getInstance().getReference();
        //mengambil data dan membaca data yang ada di dalam firebase
        futsalReferance.child("Futsal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //memasukkan data baru ke dallam array list
                datafutsal = new ArrayList<>();
                for (DataSnapshot data:snapshot.getChildren()){
                    SimpanData SD = data.getValue(SimpanData.class);
                    //di ambil idfutsal utk edit dan delete
                    SD.setIdFutsal(data.getKey());
                    //memasukkan obeject SD ke dalam arraylist datafutsal
                    datafutsal.add(SD);
                }

                adapter = new LihatData(datafutsal,MainMenu.this);
                //menampilkan data ke dalam Recycler View
                RC.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getDetails()+""+error.getMessage());
            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        AddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Tambah_Data.class));
            }
        });
    }

    //method ini digunakan di dalam Adapter
    @Override
    public void onDeleteData(SimpanData simpanData, final int position) {
        if (futsalReferance!=null){
            futsalReferance.child("Futsal").child(simpanData.getIdFutsal()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MainMenu.this,"Data dihapus", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainMenu.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}