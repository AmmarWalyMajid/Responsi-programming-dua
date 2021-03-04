package com.responsi.appsrespo.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.responsi.appsrespo.BuildConfig;
import com.responsi.appsrespo.R;
import com.responsi.appsrespo.apps.CrudRoomApp;
import com.responsi.appsrespo.common.DialogOptionsListener;
import com.responsi.appsrespo.database.Matakuliah;
import com.responsi.appsrespo.dao.MatakuliahDao;
import com.responsi.appsrespo.dialog.DialogImage;

import java.io.File;
import java.io.IOException;

public class EditActivty extends AppCompatActivity implements View.OnClickListener, DialogOptionsListener {

    public final static String TAG_DATA_INTENT ="data mahasiswa";
    public final static int REQUEST_CAMERA = 101;
    public final static int REQUEST_GALLERY = 202;
    public final static int PICK_CAMERA = 1001;
    public final static int PICK_GALLERY = 2002;
    private EditText etUser,etEmail,etPassword;
    private Button btnSave;
    private MatakuliahDao dao;
    private Matakuliah matakuliah;
    private ImageView imageView,addImage;
    private RequestOptions requestOptions;
    private File fileimage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activty);
        dao = CrudRoomApp.getInstance().getDataBase().matakuliahDao();

        if (getIntent()!= null){

            int id = getIntent().getIntExtra(TAG_DATA_INTENT,0);
            matakuliah = dao.findById(id);
        }
        if (matakuliah == null){
            matakuliah = new Matakuliah();
        }

        requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(false)
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.ic_round)
                .error(R.drawable.ic_round);

        etUser = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_emailet);
        etPassword = findViewById(R.id.et_passet);
        imageView = findViewById(R.id.img_imgcamera);
        addImage = findViewById(R.id.img_addImage);
        btnSave = findViewById(R.id.btn_saveedit);

        addImage.setOnClickListener(this);
        btnSave.setOnClickListener(this);



    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                addUpdate();
                if (matakuliah.getId()==0){
                    dao.insertData(matakuliah);
                }else {
                    dao.update(matakuliah);
                }
                Toast.makeText(this,btnSave.getText().toString(),Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.img_addImage:
            default:
                new DialogImage(EditActivty.this,EditActivty.this).show();
                break;

        }

    }

    private void addUpdate() {

        matakuliah.setNama(etUser.getText().toString());
        matakuliah.setEmail(etEmail.getText().toString());
    }

    @Override
    public void onCameraClick() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },REQUEST_CAMERA);
        }

    }

    @Override
    public void onGalleryClick() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_GALLERY);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA){
            openCamera();
        } else  if (requestCode == REQUEST_GALLERY){
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            String path;
            if (requestCode == PICK_GALLERY){
                path = getRealPathFromUri(data.getData());
            }else {
                path = fileimage.getAbsolutePath();
            }
            matakuliah.setImage(path);
            loadImage(new File(path));
        }
    }

    private String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = getContentResolver().query(contentUri,null,null,null,null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null
        ,MediaStore.Images.Media._ID + " = ? ",new String[]{document_id},null);
        cursor.moveToFirst();

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;


    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_GALLERY);

    }

    private void openCamera() {
        try {
            fileimage = File.createTempFile(String.valueOf(System.currentTimeMillis()),".jpg",
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            Uri imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider",fileimage);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            startActivityForResult(intent,PICK_CAMERA);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadImage(File image){
        if (image == null) return;

        Glide.with(this)
                .asBitmap()
                .apply(requestOptions)
                .load(image)
                .into(imageView);
    }



}