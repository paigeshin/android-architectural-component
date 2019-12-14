package com.example.myapplication.practice2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Practice2ViewModel extends ViewModel {

    private int count = 0;
    private MutableLiveData<Integer> mutableCount = new MutableLiveData<>();

    public MutableLiveData<Integer> initialize(){
        mutableCount.setValue(count);
        return mutableCount;
    }

    public void increment(){
        count++;
        mutableCount.setValue(count);
    }


}
