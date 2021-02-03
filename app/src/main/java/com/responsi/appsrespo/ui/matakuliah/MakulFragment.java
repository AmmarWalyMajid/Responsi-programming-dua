package com.responsi.appsrespo.ui.matakuliah;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.responsi.appsrespo.R;
import com.responsi.appsrespo.adapter.RecAdapter;
import com.responsi.appsrespo.apps.CrudRoomApp;
import com.responsi.appsrespo.database.Matakuliah;
import com.responsi.appsrespo.ui.AddMakulActivity;

import java.util.List;

public class MakulFragment extends Fragment {

    private RecAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View makulView = inflater.inflate(R.layout.fragment_makul, container, false);

        adapter = new RecAdapter();
        RecyclerView rvMatakuliah = makulView.findViewById(R.id.rv_list_matakuliah);
        rvMatakuliah.setAdapter(adapter);

        adapter.setRemoveListener(matakuliah -> adapter.removeData(matakuliah));

        FloatingActionButton fab = makulView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahdata();
            }
            private void tambahdata() {
                startActivity(new Intent(getActivity(),AddMakulActivity.class));
            }
        });

        return makulView;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Matakuliah>datas = CrudRoomApp.getInstance().getDataBase().matakuliahDao().getAll();
        adapter.setData(datas);
    }
}