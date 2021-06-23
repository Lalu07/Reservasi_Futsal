package firdaus.rizkika.reservasi_futsal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import firdaus.rizkika.reservasi_futsal.R;
import firdaus.rizkika.reservasi_futsal.database.SimpanData;

public class LihatData extends RecyclerView.Adapter<LihatData.ViewHolder> {

    private ArrayList<SimpanData>Data;
    private Context context;

    public LihatData(ArrayList<SimpanData> Data, Context context) {
        this.Data = Data;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lihatdata,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nama = Data.get(position).getNamaPemesan();
        holder.Name.setText(nama);
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        public ViewHolder(View v) {
            super(v);
            Name = v.findViewById(R.id.txtNama);
        }
    }
}
