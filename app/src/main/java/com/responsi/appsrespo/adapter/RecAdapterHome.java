package com.responsi.appsrespo.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.responsi.appsrespo.R;
import com.responsi.appsrespo.common.DataListListener;
import com.responsi.appsrespo.database.Matakuliah;

import java.util.ArrayList;
import java.util.List;

public class RecAdapterHome extends RecyclerView.Adapter<RecAdapterHome.ViewHolder> {

    private List<Matakuliah>datalisthome = new ArrayList<>();
    private DataListListener listener;

    public void setData(List<Matakuliah>datalist){
        for (int i = 0;i<datalist.size();i++){
            Matakuliah data = datalist.get(i);
            int postion = findPosition(data);
            if (postion ==-1){
                this.datalisthome.add(data);
                notifyItemInserted(this.datalisthome.size()-1);
            }else {
                this.datalisthome.remove(postion);
                this.datalisthome.add(postion,data);
                notifyItemChanged(postion);
            }
        }
    }

    private int findPosition(Matakuliah data) {
        int position = -1;

        if (!this.datalisthome.isEmpty()){
            for (int i =0;i<datalisthome.size();i++){
                if (this.datalisthome.get(i).getId() == data.getId()){
                    position = i ;
                }
            }
        }

        return position;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(datalisthome.get(position),listener);

    }

    @Override
    public int getItemCount() {
        return datalisthome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSem,tvMak;
        private Matakuliah data;
        private DataListListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMak = itemView.findViewById(R.id.tv_Ma);
            tvSem = itemView.findViewById(R.id.tv_Se);
        }

        public void bind(Matakuliah data, DataListListener listener) {

            this.data = data;
            this.listener = listener;

            tvMak.setText(data.getMakul());
            tvSem.setText(data.getSemester());

        }
    }
}
