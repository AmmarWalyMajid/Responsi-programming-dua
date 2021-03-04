package com.responsi.appsrespo.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.responsi.appsrespo.R;
import com.responsi.appsrespo.sharedpreferences.Preferencs;
import com.responsi.appsrespo.ui.EditActivty;
import com.responsi.appsrespo.ui.SignInActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profil, container, false);

        CircleImageView imgCamera = root.findViewById(R.id.circleimg);
        TextView tvUser = root.findViewById(R.id.profil_name);
        TextView tvEmail = root.findViewById(R.id.profil_email);

        Button logout = root.findViewById(R.id.prof_logout);
        Button btnEdit = root.findViewById(R.id.prof_Edit);

        btnEdit.setOnClickListener( this);
        logout.setOnClickListener( this);



//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Preferencs.clearLoggedInUser(getContext());
//                startActivity(new Intent(getContext(), SignInActivity.class));
//            }
//        });
//
//        final TextView textView = root.findViewById(R.id.profil_name);
//        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(Preferencs.getLoggedInUser(getContext()));
//            }
//        });
        return root;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prof_logout:
                Preferencs.clearLoggedInUser(getContext());
                startActivity(new Intent(getContext(), SignInActivity.class));
                break;

            case R.id.prof_Edit:
                startActivity(new Intent(getContext(), EditActivty.class));
                break;


        }

    }
}