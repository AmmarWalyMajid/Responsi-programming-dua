package com.responsi.appsrespo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.responsi.appsrespo.R;
import com.responsi.appsrespo.common.DialogOptionsListener;

public class DialogImage extends Dialog {

    private DialogOptionsListener listener;

    public DialogImage(@NonNull Context context,DialogOptionsListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_image);

        findViewById(R.id.dg_Opcame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCameraClick();
                dismiss();
            }
        });
        findViewById(R.id.dg_OpGalery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGalleryClick();
                dismiss();
            }
        });


    }
}
