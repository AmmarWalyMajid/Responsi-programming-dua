package com.responsi.appsrespo.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.responsi.appsrespo.R;
import com.responsi.appsrespo.sharedpreferences.Preferencs;
import com.responsi.appsrespo.ui.SignInActivity;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private Button logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profil, container, false);
        logout =root.findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferencs.clearLoggedInUser(getContext());
                startActivity(new Intent(getContext(), SignInActivity.class));
            }
        });
        final TextView textView = root.findViewById(R.id.text_2);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(Preferencs.getLoggedInUser(getContext()));
            }
        });
        return root;
    }
}