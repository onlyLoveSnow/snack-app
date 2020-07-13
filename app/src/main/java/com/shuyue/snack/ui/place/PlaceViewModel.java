package com.shuyue.snack.ui.place;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlaceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("下单页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}