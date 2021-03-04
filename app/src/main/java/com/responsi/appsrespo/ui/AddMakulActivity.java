package com.responsi.appsrespo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.responsi.appsrespo.R;
import com.responsi.appsrespo.apps.CrudRoomApp;
import com.responsi.appsrespo.database.Matakuliah;
import com.responsi.appsrespo.dao.MatakuliahDao;

public class AddMakulActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String TAG_DATA_INTENT = "data_matakuliah";
    private EditText etMakul,eSem;
    private Button btnSave;
    private MatakuliahDao dao;
    private Matakuliah matakuliah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_makul);

        dao = CrudRoomApp.getInstance().getDataBase().matakuliahDao();

        if (getIntent() != null) {
            int id = getIntent().getIntExtra(TAG_DATA_INTENT, 0);
            matakuliah = dao.findById(id);
        }
        if (matakuliah == null) {
            matakuliah = new Matakuliah();
        }
        etMakul = findViewById(R.id.et_matakuliah);
        eSem = findViewById(R.id.et_semester);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                addUpdate();
                if (matakuliah.getId()== 0){
                    dao.insertData(matakuliah);
                }else {
                    dao.update(matakuliah);
                }
                Toast.makeText(this,btnSave.getText().toString(),Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

    }

    private void addUpdate() {
        matakuliah.setMakul(etMakul.getText().toString());
        matakuliah.setSemester(eSem.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (matakuliah.getId()>0){
            etMakul.setText(matakuliah.getMakul());
            eSem.setText(matakuliah.getSemester());
            btnSave.setText("Ubah Data");

        }
    }
}