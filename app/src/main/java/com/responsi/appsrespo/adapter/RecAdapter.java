package com.responsi.appsrespo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.responsi.appsrespo.R;
import com.responsi.appsrespo.apps.CrudRoomApp;
import com.responsi.appsrespo.common.DataListListener;
import com.responsi.appsrespo.database.Matakuliah;
import com.responsi.appsrespo.ui.AddMakulActivity;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private List<Matakuliah>datalist = new ArrayList<>();
    private DataListListener listener;


    public void setData(List<Matakuliah>datalist){
        for (int i = 0;i<datalist.size();i++){
            Matakuliah data = datalist.get(i);
            int postion = findPosition(data);
            if (postion ==-1){
                this.datalist.add(data);
                notifyItemInserted(this.datalist.size()-1);
            }else {
                this.datalist.remove(postion);
                this.datalist.add(postion,data);
                notifyItemChanged(postion);
            }
        }
    }

    private int findPosition(Matakuliah data) {
        int position = -1;

        if (!this.datalist.isEmpty()){
            for (int i =0;i<datalist.size();i++){
                if (this.datalist.get(i).getId() == data.getId()){
                    position = i ;
                }
            }
        }

        return position;
    }

    public void removeData(Matakuliah data){
        if (this.datalist.isEmpty()){
            return;
        }

        for (int i =0;i<datalist.size(); i++){
            if (this.datalist.get(i).getId()==data.getId()){
                this.datalist.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void setRemoveListener(DataListListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matakuliah,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(datalist.get(position),listener);

    }

    @Override
    public int getItemCount() {


        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RequestOptions requestOptions;
        private TextView tvMakul,tvSemester;
        private ImageView imgDelete;
        private Matakuliah data;
        private DataListListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMakul = itemView.findViewById(R.id.tvMakuliah);
            tvSemester = itemView.findViewById(R.id.tvSemester);
            imgDelete = itemView.findViewById(R.id.imgDelete);

            itemView.setOnClickListener(this);
            imgDelete.setOnClickListener(this);
        }

        void bind(Matakuliah data,DataListListener listener){
            this.data = data;
            this.listener=listener;

            tvMakul.setText(data.getMakul());
            tvSemester.setText(data.getSemester());
        }

        @Override
        public void onClick(View view) {

            if (view.getId()== R.id.imgDelete){

                CrudRoomApp.getInstance().getDataBase().matakuliahDao().delete(data);
                listener.onRemoveClick(data);
                Toast.makeText(itemView.getContext(),"delete",Toast.LENGTH_SHORT).show();

            }else if (view.getId()== R.id.item_list){

                Intent intent = new Intent(itemView.getContext(), AddMakulActivity.class);
                intent.putExtra(AddMakulActivity.TAG_DATA_INTENT,data.getId());
                itemView.getContext().startActivity(intent);
            }

        }
    }
}