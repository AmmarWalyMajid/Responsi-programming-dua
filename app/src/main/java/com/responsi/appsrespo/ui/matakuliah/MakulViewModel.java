package com.responsi.appsrespo.ui.matakuliah;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MakulViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MakulViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is matakuliah fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}