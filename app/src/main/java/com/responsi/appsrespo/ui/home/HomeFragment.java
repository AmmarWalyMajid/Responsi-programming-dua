package com.responsi.appsrespo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.responsi.appsrespo.R;
import com.responsi.appsrespo.adapter.RecAdapter;
import com.responsi.appsrespo.apps.CrudRoomApp;
import com.responsi.appsrespo.database.Matakuliah;
import com.responsi.appsrespo.sharedpreferences.Preferencs;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        adapter = new RecAdapter();
        RecyclerView rvMatakuliah = root.findViewById(R.id.rv_list_home);
        rvMatakuliah.setAdapter(adapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Matakuliah> dataq = CrudRoomApp.getInstance().getDataBase().matakuliahDao().getAll();
        adapter.setData(dataq);
    }
}