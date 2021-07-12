package firdaus.rizkika.reservasi_futsal.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import firdaus.rizkika.reservasi_futsal.MainMenu;
import firdaus.rizkika.reservasi_futsal.R;
import firdaus.rizkika.reservasi_futsal.Tambah_Data;
import firdaus.rizkika.reservasi_futsal.database.SimpanData;

public class LihatData extends RecyclerView.Adapter<LihatData.ViewHolder> {

    FirebaseDataListener listener;
    private ArrayList<SimpanData>Datas;
    private Context context;

    public LihatData(ArrayList<SimpanData> Data, Context ctx) {
        //inisailisasi data dan variabel yang akan digunakan
        Datas = Data;
        context = ctx;
        listener = (MainMenu) ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lihatdata,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nama = Datas.get(position).getNamaPemesan();
        String tgl = Datas.get(position).getTanggalPemesanan();
        String waktu = Datas.get(position).getPaketWaktu();
        String harga = Datas.get(position).getHargaPaket();

        holder.Name.setText(nama);
        holder.Dante.setText(tgl);
        holder.Times.setText(waktu);
        holder.Prices.setText(harga);

        holder.CRD.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.option);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.pengeditan);
                Button deleteButton = dialog.findViewById(R.id.penghapusan);
                //ketika edit button di click
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        context.startActivity(Tambah_Data.getActIntent((Activity)context).putExtra("data", Datas.get(position)));
                    }
                });
                //ketika delete button di click
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        listener.onDeleteData(Datas.get(position),position);
                    }
                });
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return Datas.size();
    }

    //handle view
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name,Dante,Times,Prices;
        CardView CRD;
        public ViewHolder(View v) {
            super(v);
            CRD = v.findViewById(R.id.cardku);
            Name = v.findViewById(R.id.txtNama);
            Dante = v.findViewById(R.id.txtTanggal);
            Times = v.findViewById(R.id.txtWaktu);
            Prices = v.findViewById(R.id.txtHarga);
        }
    }

    //method menghapus data
    public interface FirebaseDataListener{
        void onDeleteData (SimpanData simpanData, int position);
    }


}
