package com.responsi.appsrespo.ui.matakuliah;

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

import com.responsi.appsrespo.R;

public class MakulFragment extends Fragment {

    private MakulViewModel makulViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        makulViewModel =
                new ViewModelProvider(this).get(MakulViewModel.class);
        View root = inflater.inflate(R.layout.fragment_makul, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        makulViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}